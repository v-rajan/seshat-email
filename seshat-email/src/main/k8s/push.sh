#! /bin/bash

# Private repository
sudo docker -H ${DOCKER_HOST} login  -u ${DOCKER_REGISTRY_USER} -p ${DOCKER_REGISTRY_PASS} -e ${DOCKER_REGISTRY_EMAIL} ${DOCKER_REGISTRY_HOST}:${DOCKER_REGISTRY_PORT}
sudo docker -H ${DOCKER_HOST} tag -f ${docker.image} ${DOCKER_REGISTRY_HOST}:${DOCKER_REGISTRY_PORT}/${docker.image}
sudo docker -H ${DOCKER_HOST} push ${DOCKER_REGISTRY_HOST}:${DOCKER_REGISTRY_PORT}/${docker.name}

