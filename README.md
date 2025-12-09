🏥 VetOffice Management System 🐾
This repository contains the source code for the VetOffice Management System, a desktop application designed to streamline the operations of a veterinary clinic. Built using JavaFX for the frontend and Spring Boot with JPA/Hibernate for the robust backend, this system facilitates the management of owners, animals, and medical events.


✨ Features

  -Owner Management: Add, view, update, and delete owner records.

  -Animal Records: Maintain detailed medical histories and demographic data for each animal.

  -Medical Event Tracking: Log medical procedures, vaccinations, and diagnoses tied to specific animals.

  -Relational Database: Data persistence is handled via a powerful relational database (MariaDB/MySQL).

  -MVC Architecture: Clean separation between the JavaFX frontend controllers and the Spring Boot backend services.

🛠️ Prerequisites

  To run this application, you will need the following installed:

  -Java Development Kit (JDK) 21 or later

  -Maven 3.8+

  -MariaDB/MySQL Database instance running locally or remotely.

🏃Running the Application

  -This is a Spring Boot application running a JavaFX client, typically launched via the javafx-maven-plugin.

  -This application uses environment variables for secure database connectivity. You must create a file named .env in the root directory of the project.

  -The file must contain the following database credentials:

    # Database connection settings for Spring Boot
    DB_HOST=
    DB_PORT=
    DB_NAME=
    DB_USER=
    DB_PASSWORD=

  -Execute the following command in the root directory of the project:
  
      mvn javafx:run

📐Recommendations

  -We recommend MariaDB 10.11.14 to run

🤝 Contribution

Contributions are welcome! Feel free to fork the repository, open issues, or submit pull requests.
