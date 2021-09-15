#!/bin/bash

VERSION=${1}
DOCKERHUB_USERNAME=${2}
DOCKERHUB_PASSWORD=${3}


ADMIN=${DOCKERHUB_USERNAME}/nistagram-admin-service:${VERSION}

DOCKER_BUILDKIT=1 docker build -t ${ADMIN} --no-cache .

docker login --username ${DOCKERHUB_USERNAME} --password ${DOCKERHUB_PASSWORD}

docker push ${ADMIN}