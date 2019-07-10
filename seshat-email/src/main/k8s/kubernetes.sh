#! /bin/bash

KUBECTL="/opt/kubernetes/server/bin/kubectl"
KUBERNETES_MASTER="--server=${KUBERNETES_MASTER_HOST}:${KUBERNETES_MASTER_PORT}" 
K8S_TARGET_DIR="target/k8s"

# Generate yaml files from template
erb ${K8S_TARGET_DIR}/replication-controller.yaml.erb > ${K8S_TARGET_DIR}/replication-controller.yaml
erb ${K8S_TARGET_DIR}/service.yaml.erb > ${K8S_TARGET_DIR}/service.yaml

if [ ${CREATE_KUBERNETES_RC} = true ]
then
	# Delete replication controller and pods
	${KUBECTL} ${KUBERNETES_MASTER} delete -f ${K8S_TARGET_DIR}/replication-controller.yaml
	${KUBECTL} ${KUBERNETES_MASTER} delete pods -l name=${docker.name}

	# Create replication controller and pods
	${KUBECTL} ${KUBERNETES_MASTER} create -f ${K8S_TARGET_DIR}/replication-controller.yaml
fi

# Create service 
if [ ${CREATE_KUBERNETES_SERVICE} = true ]
then
   ${KUBECTL} ${KUBERNETES_MASTER} create -f ${K8S_TARGET_DIR}/service.yaml
fi
