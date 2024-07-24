# Journal Application

This is a Spring Boot journal application that provides functionalities for user authentication, journal entry management. The application uses MongoDB for backend operations and Spring Security for authentication and authorization.

## Project Structure

``` .
├── mvnw
├── mvnw.cmd
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── harsh
    │   │           └── journalApp
    │   │               ├── config
    │   │               │   ├── AuthenticationManager.java
    │   │               │   ├── SecurityConfig.java
    │   │               │   └── UserDetailsServiceImpl.java
    │   │               ├── controller
    │   │               │   ├── AdminController.java
    │   │               │   ├── JournalEntryController.java
    │   │               │   ├── PublicController.java
    │   │               │   └── UserController.java
    │   │               ├── entity
    │   │               │   ├── JournalEntry.java
    │   │               │   ├── User.java
    │   │               │   
    │   │               ├── JournalApplication.java
    │   │               ├── repository
    │   │               │   ├── JournalEntryRepository.java
    │   │               │   └── UserEntryRepository.java
    │   │               └── service
    │   │                   ├── JournalServiceEntry.java
    │   │                   ├── UserService.java
    │   │                   
    │   └── resources
    │       ├── application.properties
    │       ├── static
    │       └── templates
    └── test
        └── java
            └── com
                └── harsh
                    └── journalApp
                        └── JournalAppApplicationTests.java
```

## Installation

1. **Clone the repository:**

    ```sh
    git clone https://github.com/Harshyadav02/Journal-Application-Spring-Boot.git
    cd journalApp
    ```

2. **Build the project:**

    ```sh
    ./mvnw clean install
    ```

3. **Run the application:**

    ```sh
    ./mvnw spring-boot:run
    ```

## Configuration

- **`application.properties`**: Configure your MongoDB connection and other application settings in `src/main/resources/application.properties`.

## Project Components

### Config

- **`AuthenticationManager.java`**: Manages authentication processes.
- **`SecurityConfig.java`**: Configures Spring Security for the application.
- **`UserDetailsServiceImpl.java`**: Implements the `UserDetailsService` interface to load user-specific data.

### Controller

- **`AdminController.java`**: Handles admin-specific requests.
- **`JournalEntryController.java`**: Manages journal entry operations.
- **`PublicController.java`**: Handles public requests.
- **`UserController.java`**: Manages user-related operations.

### Entity

- **`JournalEntry.java`**: Represents a journal entry.
- **`User.java`**: Represents a user.

### Repository

- **`JournalEntryRepository.java`**: Repository for `JournalEntry` entities.
- **`UserEntryRepository.java`**: Repository for `User` entities.

### Service

- **`JournalServiceEntry.java`**: Provides services for managing journal entries.
- **`UserService.java`**: Provides services for user management.

### Notes
    1. This application includes user authentication and authorization using Spring Security.
    2. Ensure MongoDB is running and accessible for the application to function correctly.
