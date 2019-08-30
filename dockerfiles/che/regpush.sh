#!/bin/bash

REG_TO=192.168.6.160:5000
TAG=$1
IMAGE=eclipse/che-server
docker tag $IMAGE:$TAG $REG_TO/$IMAGE:$TAG
docker push $REG_TO/$IMAGE:$TAG
