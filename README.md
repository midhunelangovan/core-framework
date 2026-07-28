# Core Framework

A robust, reusable Spring Boot based framework designed to accelerate the development of RESTful web services. This framework provides boilerplate implementations for common tasks such as CRUD operations, pagination, dynamic filtering, exception handling, and audit logging.

## Key Features

- **Base CRUD Abstractions**: Standardized interfaces (`AbstractCrudBaseService`) and implementations (`AbstractCrudService`) for Create, Read, Update, and Delete operations.
- **REST API Base Controller**: A generic base controller (`AbstractRestApiController`) providing ready-to-use endpoints with built-in validation and uniform response structures.
- **Lifecycle Hooks**: Extensible hooks (`AbstractLifeCycleHooks`) to execute custom logic before and after standard CRUD operations (e.g., `beforeCreate`, `afterUpdate`).
- **Dynamic Specifications**: Integrated `CommonSpecification` for dynamic data filtering using query strings.
- **Global Exception Handling**: Centralized `GlobalExceptionHandler` mapping various exceptions (e.g., `ResourceNotFoundException`, `ValidationFailedException`) to consistent JSON error responses.
- **Auditing**: Standardized auditing fields (`AuditEntity`) to track entity creation and modification metadata.
- **Pagination & Utilities**: Built-in mapping of Spring Data `Page` to custom paginated responses, plus string and header utilities.

## Architecture

The framework is structured into logical layers:
- **`controller`**: Base REST controllers mapping HTTP requests to service methods.
- **`service`**: Base service implementations encapsulating business logic and entity lifecycle management.
- **`repository`**: Spring Data JPA repository interfaces for database access.
- **`model` / `entity`**: DTOs, custom response models, and JPA entity base classes.
- **`exception`**: Custom exceptions and the global exception handler.
- **`specification`**: Criteria API based specification builder for dynamic database queries.

## Getting Started

To use this framework in your project:
1. Include it as a dependency in your `build.gradle` or `pom.xml`.
2. Extend your entities from `AuditEntity` (optional).
3. Extend your repositories from `AbstractBaseRepository`.
4. Extend your services from `AbstractCrudService`.
5. Extend your controllers from `AbstractRestApiController`.

This establishes a full functional REST API with minimal boilerplate code.
