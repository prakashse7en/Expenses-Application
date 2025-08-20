# Expenses Application

## Overview

The Expenses application is designed for recording daily expenses. Each registered and authorized user can create and view their expenses, receive notifications for added expenses, and access all details associated with their entries.

### Future Enhancements
- Enable Machine Learning Capability (MCP) for the expenses app to provide additional insights into spending habits and suggest ways to reduce unnecessary expenses.


<img width="1121" height="391" alt="image" src="https://github.com/user-attachments/assets/829c9c11-42bc-40d9-a67f-228b206acd8c" />

userprofile ms swagger  

<img width="1562" height="831" alt="Screenshot 2025-08-20 at 6 31 33 PM" src="https://github.com/user-attachments/assets/35dc2638-0f7b-4149-b437-c9e6ff29fbc3" />


expenses ms swagger  
<img width="1577" height="738" alt="Screenshot 2025-08-20 at 6 31 24 PM" src="https://github.com/user-attachments/assets/d8e24f25-c30b-4197-ad9b-8858d7c2e3d0" />



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
  vision is to add MCP so enduser has a capablity to interact with their budeget reports using Gemini /Claude etc models to derive knowledge this can be a lead for pulling the customers into the application
#How is the app being monetized
- for each report of their budget ,app chat asks what is the inward amount you recive every month and against how much you spend within the time frame suggest equity savings  products   to the user depending on RISK level
  Commission based on each time you purchase equity investment /insurance
  Main thing ask each user consent if they use their pattern saving not the entire savings data to derive knowledge on their invward and budget amount and what they do for savings can be used as suggestion to other users
  say person earning 30k saves 15k  investments made via app and 15k uses as expenses school fees rent grocerries etc
  other person who has similar income and similar expenses can use this above pattern savings as reference if above person is considered success in make huge profits <subjected to risk agreed by consumer>


Workspace setup
Docker images of keycloak,redis and kafka used 


