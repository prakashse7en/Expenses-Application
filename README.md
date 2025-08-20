# Expenses Application

## Overview

The Expenses application is designed for recording daily expenses. Each registered and authorized user can create and view their expenses, receive notifications for added expenses, and access all details associated with their entries.

### Future Enhancements
- Enable Machine Learning Capability (MCP) for the expenses app to provide additional insights into spending habits and suggest ways to reduce unnecessary expenses.


<img width="1121" height="391" alt="image" src="https://github.com/user-attachments/assets/829c9c11-42bc-40d9-a67f-228b206acd8c" />

userprofile ms swagger  http://localhost:8081/swagger-ui/index.html
<img width="1565" height="774" alt="Screenshot 2025-08-19 at 2 17 45 PM" src="https://github.com/user-attachments/assets/996ecbcb-59a9-49cb-b8e5-c45ef158d4a6" />

expenses ms swagger  http://localhost:8084/swagger-ui/index.html
<img width="1600" height="881" alt="Screenshot 2025-08-19 at 2 17 33 PM" src="https://github.com/user-attachments/assets/55ed483d-bbee-444e-a3d0-8dccb6dae173" />


## Design Patterns Used
- Domain Driven Design
- Database per Service Pattern
- Circuit Breaker Pattern
- Event Driven Service Pattern

## Security
- **Authentication and Authorization**: Keycloak is used for User Authentication (AuthN) and Role-Based Access Control (AuthZ). Users with valid JWT tokens can access the expenses endpoints.
- **Future Enhancements**: Implement Redis to save nonces for continuous session handling with refresh tokens. When a nonce is not present in Redis, the user token will be invalidated, logging the user out.

## Scalability
- MySQL Database for persistence.
- Read replicas will be employed for read-intensive calls, while write operations will hit the live database.
- RDS Proxy is used to handle connection pooling.
- Elastic Redis Cache for caching GET call responses, evicting them based on TTL or updates via Kafka events.
- CDN will be utilized for images, HTML files, etc.

## Code Maintainability
- Sonar scans and SAST CONT scans are conducted to avoid vulnerabilities in production.
- Code coverage is ensured using integration test containers and JUnit.
- Liquibase is used for database versioning.
- Version tagging for production releases on Git, with auto-increment of the version in `pom.xml` for subsequent releases.
- Configuration values will be moved to a secret manager or key vault and managed with Terraform.
- Postman collections and README will be updated for proper documentation. Changes found here check the postmancollection [here](https://web.postman.co/workspace/My-Workspace~fc311867-897c-4ee6-8091-c2e6bdab755f/collection/45494508-48d52504-9333-486b-b4cd-d609b3b2d8e5?action=share&source=copy-link&creator=45494508)

## Checkpointing
- Backups (e.g., database to S3) will be taken at appropriate intervals to prevent data loss.

## High Availability
- Three Availability Zones are created for AWS components, allowing fallback without disrupting traffic.

## Resilience
- Circuit breakers are implemented to avoid failure of main tasks. The system can handle high traffic and is protected against a large number of invalid requests through DDoS protection, WAF, rate limiting, and throttling.

## Observability
- Alerts and monitoring dashboards will be created for critical components.
- DataDog will be used to monitor pod usage and CPU for compute resources.
- Health checks for each external component will be added to ensure the application can still poll critical services like Redis and the database. Actuator is used to display exact status information.
- In case of failures, pods will be restarted, and corresponding alerts will be triggered, with scheduled polling of critical components by the application.

## Future Enhancements
- Adding MCP capablities for the application

