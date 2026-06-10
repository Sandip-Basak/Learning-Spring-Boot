#!/bin/bash
cd /home/ec2-user/inventory-app

# If a docker-compose setup is already up, drop it cleanly and purge unused network configurations
if [ -f docker-compose.yml ]; then
    echo "Existing deployment discovered. Tearing down active containers..."
    docker compose down || true
else
    echo "No prior active system deployments detected."
fi