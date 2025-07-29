FROM eclipse-temurin:21-jdk

# Install inotify-tools for file system monitoring
RUN apt-get update -y \
    && apt-get install -y --no-install-recommends inotify-tools curl \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy startup script
COPY start.sh .
RUN chmod +x start.sh

# Expose ports
EXPOSE 8080 35729

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run the application
CMD ["sh", "start.sh"] 