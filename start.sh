#!/usr/bin/env bash

echo "🚀 Starting Influencer Marketing Backend..."

# Start Spring Boot in background
./mvnw spring-boot:run --color always &

# Check if live reload is enabled
if [ "$ENABLE_LIVE_RELOAD" != 'true' ]; then
    echo "📝 Live reload disabled, running in production mode..."
    tail -f /dev/null
fi

# Live reloading with inotify
echo "🔄 Live reload enabled - watching for file changes..."
while true; do
    inotifywait -e modify,create,delete,move -r ./src/ &&
        echo "📝 File changed, recompiling..." &&
        ./mvnw compile --color always
done 