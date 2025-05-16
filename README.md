# Spring Boot Paging and Sorting Example

This project is a Spring Boot application that demonstrates how to implement pagination and sorting for a list of entities retrieved from a database.

## Description

The application manages a list of customers and provides an API endpoint to fetch these customers with support for:
- **Pagination**: Retrieving a specific "page" of results (e.g., 10 customers per page).
- **Sorting**: Ordering the results by a specified field (e.g., `firstName`, `email`) in ascending or descending order.

This is a common requirement for applications that deal with large datasets, improving performance and user experience by not loading all data at once.

## Technologies Used

- **Java 21**
- **Spring Boot 3.4.5**:
    - Spring Web: For building RESTful APIs.
    - Spring Data JPA: For data persistence and interaction with the database.
- **MySQL**: As the relational database.
- **Lombok**: To reduce boilerplate code (e.g., getters, setters, constructors).
- **Maven**: For project build and dependency management.

## Prerequisites

Before you begin, ensure you have met the following requirements:
- **JDK 21** or later installed.
- **Maven** installed.
- **MySQL Server** installed and running.
- An IDE such as IntelliJ IDEA, Eclipse, or VS Code (optional, but recommended).

## Setup and Installation

1.  **Clone the repository (if applicable):**
    ```bash
    git clone <your-repository-url>
    cd spring-paging-sorting
    ```

2.  **Configure the database:**
    - Open the `src/main/resources/application.yaml` file.
    - Update the `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` properties to match your MySQL database configuration.
      ```yaml
      spring:
        application:
          name: spring-paging-sorting
        datasource:
          url: jdbc:mysql://localhost:3306/nguyethaDB # Ensure this database exists
          username: your_mysql_username # Replace with your MySQL username
          password: your_mysql_password # Replace with your MySQL password
          driver-class-name: com.mysql.cj.jdbc.Driver
        jpa:
          database: mysql
          show-sql: true
          hibernate:
            ddl-auto: update # Creates/updates the schema on startup. Use 'validate' or 'none' in production.
          properties:
            hibernate:
              format_sql: true
              use_sql_comments: true
      server:
        port: 8080
      ```
    - Create the database `nguyethaDB` (or the name you specified) in your MySQL server if it doesn't already exist.
      ```sql
      CREATE DATABASE nguyethaDB;
      ```

3.  **Build the project:**
    Open a terminal or command prompt in the project's root directory and run:
    ```bash
    mvn clean install
    ```
    This command will compile the code, run tests, and package the application.

## Running the Application

1.  **Using Maven:**
    In the project's root directory, run:
    ```bash
    mvn spring-boot:run
    ```

2.  **Using the JAR file:**
    After building the project (with `mvn clean install`), navigate to the `target` directory and run the JAR file:
    ```bash
    java -jar target/spring-paging-sorting-0.0.1-SNAPSHOT.jar
    ```

The application will start, and by default, it will be accessible at `http://localhost:8080`.

## Database Setup and Initial Data

- The application uses Spring Data JPA with `hibernate.ddl-auto: update`, which means it will attempt to create or update the database schema based on the `CustomerEntity` definition when the application starts.
- The `customer_001` table will be created in the `nguyethaDB` database.
- Sample data is provided in the `data.sql` file located in the root of the project. If your Spring Boot configuration is set up to run SQL scripts on startup (which is common but not explicitly configured in the provided `application.yaml` other than `ddl-auto`), this data might be inserted.
    - To ensure data is loaded, you can configure `spring.sql.init.mode=always` in `application.yaml` if `data.sql` is placed in `src/main/resources`.
    - Alternatively, you can manually execute the `data.sql` script against your `nguyethaDB` database using a MySQL client.

    **Example `data.sql` content:**
    ```sql
    -- Ensure this is placed in src/main/resources/data.sql for automatic execution
    -- or run manually against your 'nguyethaDB' database.

    -- Assuming customer_id is an auto-incrementing column.
    -- The schema 'nguyethaDB' should not be prefixed here if the connection URL already specifies the database.
    -- Example: INSERT INTO customer_001 (...)
    INSERT INTO customer_001 (email, first_name, last_name, password, phone_number, register_date)
    VALUES
        ('tran.van.thanh@gmail.com.vn', 'Thanh', 'Tran Van', 'thanh@Vn2025', '0905123456', '2025-05-16 09:50:00'),
        ('le.thi.hoa@yahoo.com', 'Hoa', 'Le Thi', 'hoaLeThi@123', '0918765432', '2025-05-16 09:52:00'),
        -- ... (other records from your data.sql)
        ('pham.ngoc.tram@yahoo.com.vn', 'Tram', 'Pham Ngoc', 'TramPhamNgoc@Beauty', '0978123450', '2025-05-16 10:48:00'),
        ('nguyen.van.hung@gmail.com', 'Hung', 'Nguyen Van', 'HungNguyenVan!Strong', '0932109876', '2025-05-16 10:51:00');
    ```

## API Endpoints

The application exposes the following REST API endpoint:

### Get All Customers (with Paging and Sorting)

-   **URL:** `/customer/findAll`
-   **Method:** `GET`
-   **Description:** Retrieves a paginated and sorted list of customers.
-   **Query Parameters:**
    -   `page` (optional, integer): The page number to retrieve (0-indexed). Default: `0`.
    -   `size` (optional, integer): The number of customers per page. Default: `10`.
    -   `sortBy` (optional, string): The field to sort by (e.g., `customerId`, `firstName`, `lastName`, `email`, `registerDate`). Default: `customerId`.
    -   `sortDirection` (optional, string): The sort direction. Accepts `asc` (ascending) or `desc` (descending). Default: `asc`.

-   **Example Request:**
    ```
    http://localhost:8080/customer/findAll?page=0&size=5&sortBy=firstName&sortDirection=desc
    ```

-   **Example Response (JSON):**
    ```json
    {
        "content": [
            {
                "customerId": 1,
                "firstName": "Thanh",
                "lastName": "Tran Van",
                "email": "tran.van.thanh@gmail.com.vn",
                "password": "thanh@Vn2025",
                "registerDate": "2025-05-16T02:50:00.000+00:00", // Note: Timestamp might be in UTC
                "phoneNumber": "0905123456"
            },
            // ... other customer records
        ],
        "pageable": {
            "sort": {
                "empty": false,
                "sorted": true,
                "unsorted": false
            },
            "offset": 0,
            "pageNumber": 0,
            "pageSize": 5,
            "paged": true,
            "unpaged": false
        },
        "last": false,
        "totalPages": 4, // Example, depends on total records and page size
        "totalElements": 20, // Example
        "size": 5,
        "number": 0,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "first": true,
        "numberOfElements": 5,
        "empty": false
    }
    ```

## Project Structure

```
spring-paging-sorting/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/haons/java/springpagingsorting/
│   │   │       ├── SpringPagingSortingApplication.java (Main application class)
│   │   │       ├── controller/
│   │   │       │   └── CustomerController.java       (API endpoints)
│   │   │       ├── entity/
│   │   │       │   └── CustomerEntity.java           (JPA entity for Customer)
│   │   │       ├── repository/
│   │   │       │   └── CustomerRepository.java       (Spring Data JPA repository)
│   │   │       └── service/
│   │   │           ├── CustomerService.java          (Service interface)
│   │   │           └── impl/
│   │   │               └── CustomerSericeImpl.java   (Service implementation)
│   │   └── resources/
│   │       ├── application.yaml                    (Spring Boot configuration)
│   │       └── data.sql                            (Optional: Sample data - place here for auto-load)
│   └── test/
│       └── java/
│           └── com/haons/java/springpagingsorting/
│               └── SpringPagingSortingApplicationTests.java (Test class)
├── data.sql                                        (Sample data as provided)
├── pom.xml                                         (Maven project configuration)
└── README.md                                       (This file)

```
## Contributing

Contributions are welcome! If you have suggestions or improvements, please feel free to:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature/YourFeature`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add some feature'`).
5. Push to the branch (`git push origin feature/YourFeature`).
6. Open a Pull Request.

## License

This project is open source. Feel free to use and modify it as you see fit. If a specific license is intended, please add it here (e.g., MIT, Apache 2.0).
