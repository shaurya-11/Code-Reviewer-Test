# AI Review Context

This file should evolve with the project. Keep architecture decisions, coding standards, known constraints, and repository-specific rules here so every PR review has current project context.

## Architecture

- Backend: Spring Boot
- Database: PostgreSQL
- Messaging: Kafka
- Frontend: Vite + React

## Coding Standards

- Controllers should call service classes for business workflows.
- Service layer should not access repositories directly.
- DTOs must be validated using Bean Validation.
- Exceptions should be handled through `GlobalExceptionHandler`.

## Known Constraints

- User IDs are UUIDs.
- All timestamps are stored in UTC.
- Authentication uses JWT.

## Repository Rules

[HIGH] Rule #1:
Controllers must not access repositories directly.

Violation example:
`UserController -> UserRepository`

Recommendation:
Use `UserService` instead.

[MEDIUM] Rule #2:
DTO request fields must use Bean Validation annotations.

Violation example:
`CreateUserRequest.email` has no `@Email` or `@NotBlank`.

Recommendation:
Annotate DTO fields and use `@Valid` in controllers.

[MEDIUM] Rule #3:
Frontend API calls must handle non-2xx responses and user-visible error states.

Violation example:
Calling `fetch()` and assuming `response.json()` always succeeds.

Recommendation:
Check `response.ok`, show an error state, and avoid swallowing failures.

