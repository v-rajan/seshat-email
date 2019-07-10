#!/bin/bash

. ./setenv_test.sh

echo "Docker env"
#sudo service docker stop
#sudo dockerd -H tcp://127.0.0.1:2375 -H unix:///var/run/docker.sock &

echo "Docker pull"
gcloud docker -- pull asia.gcr.io/cnm000-142208/centos-openjdk11:1.0.0

echo "Build ${PROJECT_NAME}"
mvn clean install -DskipTests docker:build

echo "Stopping and removing previous ${PROJECT_NAME} containers"
OLD=$(sudo docker ps -a | grep "${PROJECT_NAME}" | awk '{print $1}' | paste -sd ' ' -)
if [ ! -z $OLD ]; then
  sudo docker stop $OLD
  sudo docker rm $OLD
fi

echo "Start ${PROJECT_NAME}"
sudo docker run  -t \
	--name ${PROJECT_NAME} \
	-p 9191:9191 \
	-e SERVICE_URL=${SERVICE_URL} \
   	-e SENDGRID_URL=${SENDGRID_URL} \
	-e SENDGRID_API_KEY=${SENDGRID_API_KEY} \
   	-e MAILGUN_URL=${SENDGRID_URL} \
	-e MAILGUN_API_KEY=${SENDGRID_API_KEY} \
	-e JAVA_APP_DIR=${JAVA_APP_DIR} \
	-e JAVA_OPTIONS=${JAVA_OPTIONS} \
	${PROJECT_NAME}:${PROJECT_VERSION}
