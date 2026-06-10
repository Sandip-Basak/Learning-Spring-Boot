#!/bin/bash
cd /home/ec2-user/inventory-app

echo "Building fresh application image from compiled build artifact and launching ecosystem..."
# Forces a container build step using the artifact contents and launches in detached background mode
docker compose up -d --build