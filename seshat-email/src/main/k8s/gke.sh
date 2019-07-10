#! /bin/bash

# Google Container Registry
export PROJECT_ID=$(curl -s 'http://metadata/computeMetadata/v1/project/project-id' -H 'Metadata-Flavor: Google')
export DOCKER_REGISTRY=asia.gcr.io/${PROJECT_ID}

K8S_TARGET_DIR="target/k8s"

# Generate yaml files from template
erb ${K8S_TARGET_DIR}/replication-controller.yaml.erb > ${K8S_TARGET_DIR}/replication-controller.yaml
erb ${K8S_TARGET_DIR}/service.yaml.erb > ${K8S_TARGET_DIR}/service.yaml

if [ ${CREATE_KUBERNETES_RC} = true ]
then
	# Delete replication controller and pods
	kubectl --namespace=${PROJECT_NAMESPACE} delete -f ${K8S_TARGET_DIR}/replication-controller.yaml
	kubectl --namespace=${PROJECT_NAMESPACE} delete pods -l name=${PROJECT_NAME}

	# Create replication controller and pods
	kubectl --namespace=${PROJECT_NAMESPACE} create -f ${K8S_TARGET_DIR}/replication-controller.yaml
fi

# Create service 
if [ ${CREATE_KUBERNETES_SERVICE} = true ]
then
   kubectl --namespace=${PROJECT_NAMESPACE} create -f ${K8S_TARGET_DIR}/service.yaml
fi
