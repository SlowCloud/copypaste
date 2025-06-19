#!bin/bash

docker build -t copypaste-back:latest .

# should fix this
GCP_REGISTRY_TAG=asia-northeast3-docker.pkg.dev/copypaste/copypaste-registry/copypaste-back
docker tag copyppaste-back $GCP_REGISTRY_TAG

docker push $GCP_REGISTRY_TAG