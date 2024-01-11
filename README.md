# Course Info API

## Overview

Course Info API is a project aimed at creating a comprehensive course information API for developers within your company. The API allows users to store, retrieve, delete, and share details about interesting courses, including the ability to add personalized notes. It helps the group to share the courses and select the best. Additionally, the project incorporates automation to fill the database with Pluralsight course information.


## Technologies Used

- Java
- MySQL (Database)
- Maven (Build tool)
- JUnit 5 (Testing)
- Mockito (Mocking framework)
- Jackson (JSON processing)
- JAX-RS (Jersey - RESTful Web Services)

## Project Structure

The project is organized into three main modules, each serving a specific purpose. The `CourseResource` class within the `Course-Info-API-server` module represents the resource for the REST API, providing endpoints for interacting with course data.

## How to Use

1. **Course-Info-API-cli:**
   - Use this module to retrieve course information and store it in the database.

2. **Course-Info-API-repository:**
   - Handles database operations.
   - Saves, retrieves, and deletes courses.
   - Adds notes to specific courses.

3. **Course-Info-API-server:**
   - Exposes a RESTful API with the following endpoints:
     - `GET /courses`: Retrieves a list of all courses.
     - `GET /courses/{id}/delete`: Deletes a course by ID.
     - `POST /courses/{id}/notes`: Adds a note to a course by ID.
     - `GET /courses/add-course`: Allows the addition of a new course via the command line.

## Build and Run

Ensure you have Java, Maven, and MySQL installed.

1. Clone the repository.
2. Configure the MySQL database settings in the `application.properties` file.
3. Build the project using Maven: `mvn clean install`.
4. Run each module separately, starting with `Course-Info-API-server` to activate the REST API.

## Contributions

Contributions to the project are welcome! Feel free to fork the repository, make improvements, and submit pull requests.

## License

This project is licensed under the [MIT License](LICENSE).

---

Feel free to customize the README according to your specific needs, providing more details or specific instructions as necessary.
