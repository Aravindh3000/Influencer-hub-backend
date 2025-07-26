# Influencer Marketing Backend

A modern Spring Boot backend application for influencer marketing platforms, featuring Docker containerization, automated database migrations, and Nginx reverse proxy.

## 🏗️ Architecture

| Component | Technology | Version |
|-----------|------------|---------|
| **Backend Framework** | Spring Boot | 3.5.4 |
| **Database** | PostgreSQL | 15 |
| **Migration Tool** | Liquibase | Latest |
| **Containerization** | Docker & Docker Compose | Latest |
| **Reverse Proxy** | Nginx | Alpine |
| **Java** | OpenJDK | 21 |
| **Architecture Pattern** | Domain-Driven Design | - |

## 📋 Prerequisites

- **Docker & Docker Compose** (required)
- **Java 21** (for local development)
- **Maven** (for local development)

## 🚀 Quick Start

### Option 1: Docker (Recommended)

```bash
# First time setup
sudo docker-compose up --build

# 1. Start all services
docker-compose up -d

# 2. Apply database migrations
docker-compose exec backend ./mvnw liquibase:update

# 3. Verify services are running
docker-compose ps

# 4. Check application logs
docker-compose logs -f backend
```

### Option 2: Local Development

```bash

# 1. Start only PostgreSQL
docker-compose up db -d

# 2. Apply database migrations
./mvnw liquibase:update

# 3. Run the application
./mvnw spring-boot:run

# Start all services in background
sudo docker-compose up -d

# Stop and remove containers, networks
sudo docker-compose down

# Stop and remove everything (including volumes)
sudo docker-compose down -v
```

## 🌐 Services & Ports

| Service | Port | Description | Health Check |
|---------|------|-------------|--------------|
| **Nginx** | 80 | Reverse proxy & load balancer | `curl http://localhost/health` |
| **Spring Boot** | 8080 | Main application | `curl http://localhost:8080/actuator/health` |
| **PostgreSQL** | 5432 | Database | `docker-compose exec db pg_isready` |

## 📡 API Endpoints

### Brands Management
```http
GET    /api/brands          # List all brands
GET    /api/brands/{id}     # Get brand by ID
POST   /api/brands          # Create new brand
PUT    /api/brands/{id}     # Update brand
DELETE /api/brands/{id}     # Delete brand
```

### System Health
```http
GET /health                 # Application health status
GET /actuator/health        # Detailed health information
```

## 🗄️ Database Management

### Manual Migrations

**⚠️ Important:** Database migrations are disabled by default and must be run manually.

#### Local Development
```bash
./mvnw liquibase:status      # Check migration status
./mvnw liquibase:update      # Apply pending migrations
./mvnw liquibase:rollback -Dliquibase.rollbackCount=1  # Rollback last migration
./mvnw liquibase:history     # View migration history
./mvnw liquibase:validate    # Validate changelog
./mvnw liquibase:updateSQL   # Generate SQL without executing
./mvnw liquibase:releaseLocks # Release locks if stuck
```

#### Docker Environment
```bash
docker-compose exec backend ./mvnw liquibase:status
docker-compose exec backend ./mvnw liquibase:update
docker-compose exec backend ./mvnw liquibase:rollback -Dliquibase.rollbackCount=1
docker-compose exec backend ./mvnw liquibase:history
docker-compose exec backend ./mvnw liquibase:validate
docker-compose exec backend ./mvnw liquibase:updateSQL
docker-compose exec backend ./mvnw liquibase:releaseLocks
```

### Database Schema

**Current Tables:**
- `brand` - Enhanced brand information with UUID primary key

**Migration Files Location:**
```
src/main/resources/db/changelog/
├── db.change-log.yaml
└── sql/
    └── 001-create-brand-table.sql
```

## ⚙️ Configuration

### Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://localhost:5432/influencer_db` | Database connection URL |
| `SPRING_DATASOURCE_USERNAME` | `user` | Database username |
| `SPRING_DATASOURCE_PASSWORD` | `password` | Database password |
| `SPRING_PROFILES_ACTIVE` | `default` | Active Spring profile |
| `SHOW_SQL` | `false` | Enable SQL query logging |

## 🛠️ Development

### Project Structure
```
src/main/java/com/influencermarket/backend/
├── Brand/           # Brand domain
│   ├── Brand.java              # Entity
│   ├── BrandRepository.java    # Data access layer
│   ├── BrandService.java       # Business logic
│   └── BrandController.java    # REST controller
├── config/          # Configuration classes
└── BackendApplication.java     # Main application class
```

### Adding New Features

1. **Create Domain Folder** → `src/main/java/.../DomainName/`
2. **Create Entity** → `src/main/java/.../DomainName/DomainName.java`
3. **Create Repository** → `src/main/java/.../DomainName/DomainNameRepository.java`
4. **Create Service** → `src/main/java/.../DomainName/DomainNameService.java`
5. **Create Controller** → `src/main/java/.../DomainName/DomainNameController.java`
6. **Add Migration** → `src/main/resources/db/changelog/sql/`

**Example for a new "Product" domain:**
```
src/main/java/com/influencermarket/backend/
├── Brand/           # Existing domain
├── Product/         # New domain
│   ├── Product.java
│   ├── ProductRepository.java
│   ├── ProductService.java
│   └── ProductController.java
└── config/
```

### Database Migrations

1. Create SQL file in `src/main/resources/db/changelog/sql/`
2. Add rollback statement: `--rollback drop table table_name cascade;`
3. Add changeset to `db.change-log.yaml`
4. Test with `./mvnw liquibase:update`

## 🔧 Troubleshooting

### Common Issues

| Issue | Solution |
|-------|----------|
| **Port conflicts** | `docker-compose down && docker-compose up -d` |
| **Database connection** | `docker-compose logs db` |
| **Application startup** | `docker-compose logs backend` |
| **Migration locks** | `./mvnw liquibase:releaseLocks` |

### Health Checks

```bash
# Database health
docker-compose exec db pg_isready -U user -d influencer_db

# Application health
curl http://localhost/health

# Nginx configuration
docker-compose exec nginx nginx -t
```

## 🚀 Production Deployment

### Security Checklist
- [ ] Change default database passwords
- [ ] Enable HTTPS with SSL certificates
- [ ] Configure firewall rules
- [ ] Set up proper user permissions

### Performance Optimization
- [ ] Adjust JVM heap settings
- [ ] Configure database connection pooling
- [ ] Enable Nginx caching
- [ ] Set up database indexes

### Monitoring Setup
- [ ] Application performance monitoring
- [ ] Log aggregation and analysis
- [ ] Database monitoring
- [ ] Health check alerts

## 📄 License

This project is licensed under the **MIT License**.

---

**Need Help?** Check the troubleshooting section or create an issue in the repository. 