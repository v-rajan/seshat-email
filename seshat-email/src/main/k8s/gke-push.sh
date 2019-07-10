#! /bin/bash

# Google Container Registry
PROJECT_ID=$(curl -s 'http://metadata/computeMetadata/v1/project/project-id' -H 'Metadata-Flavor: Google')
docker tag ${docker.image} asia.gcr.io/${PROJECT_ID}/${docker.image}
gcloud docker -- push asia.gcr.io/${PROJECT_ID}/${docker.image}