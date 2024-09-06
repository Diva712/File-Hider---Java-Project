# File Hider Project

This project is a **File Hider** application built using **Core Java**, **File Handling**, **JDBC**, **MySQL**, and **Object-Oriented Programming (OOP)** principles. The application allows users to hide and unhide files on their system by interacting with the file system and database.

## Features

- **File Hiding**: Users can give file path from their system and hide them, preventing them from being easily discovered or accessed.
- **File Unhiding**: Hidden files can be restored to their original visibility using the unhide feature.
- **User Management**: The application includes user registration and login functionality, ensuring that only authorized users can hide or unhide files.
- **OTP Authentication**: Users are required to authenticate using an OTP (One Time Password) sent to their email during registration.
- **Database Integration**: All user data (such as login credentials) is stored in a **MySQL** database using **JDBC** for data persistence.
- **File History Tracking**: The application keeps track of which files have been hidden or unhidden by each user.

## Technologies Used

- **Core Java**: The application is built entirely in Java, making use of Java's core libraries for file handling, user input, and system interaction.
- **File Handling**: Java's `File` class is used for hiding and unhiding files by manipulating file attributes.
- **JDBC (Java Database Connectivity)**: JDBC is used to connect the Java application to a **MySQL** database for storing and retrieving user data.
- **MySQL**: MySQL is used as the database to store user information and file metadata.
- **Object-Oriented Programming (OOP)**: The project follows OOP principles, organizing the application into classes like `User`, `FileManager`, and `UserDao` to handle various functionalities.

## How It Works

1. **User Registration**: A new user can register by providing their name and email. An OTP will be sent to the provided email address for verification.
2. **Login**: Existing users can log in using their registered email and password.
3. **Hide File**: After logging in, users can choose a file to hide. The application alters the file’s attributes so that it becomes hidden on the system.
4. **Unhide File**: Users can unhide previously hidden files through the application.

## Database Schema

The project uses a simple relational database with a table for storing user information:

### `users`
| Column   | Type         | Description                           |
|----------|--------------|---------------------------------------|
| `id`     | INT          | AUTO_INCREMENT, PRIMARY KEY           |
| `name`   | VARCHAR      | Name of the user                      |
| `email`  | VARCHAR      | Email address of the user             |
| `password` | VARCHAR    | Password for user authentication      |

## How to Run

1. **Clone the repository**:

    ```bash
    git clone https://github.com/your-username/file-hider-project.git
    ```

2. **Set up the MySQL database**:
    - Create a new database and configure the connection in the application.
    - Use the provided SQL file to create the `users` table.

3. **Compile and run** the Java application using your preferred IDE (e.g., Eclipse, IntelliJ).


