#!/bin/bash

# Start the application
echo "Starting Influencer Marketing Backend..."

# Check if user is in docker group
if groups $USER | grep -q docker; then
    echo "User is in docker group, running without sudo..."
    docker-compose up -d
else
    echo "User is not in docker group, running with sudo..."
    sudo docker-compose up -d
fi

echo "Waiting for services to start..."
sleep 10

echo "Checking service status..."
if groups $USER | grep -q docker; then
    docker-compose ps
else
    sudo docker-compose ps
fi

echo ""
echo "Application is running!"
echo "API Base URL: http://localhost/api"
echo "Health Check: http://localhost/health"
echo ""
echo "Test the API:"
echo "  curl http://localhost/api/brands"
echo "  curl -X POST http://localhost/api/brands -H 'Content-Type: application/json' -d '{\"name\":\"Test Brand\"}'" 