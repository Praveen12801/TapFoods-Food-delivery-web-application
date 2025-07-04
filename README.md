# 🍽️ TapFoods - Food Delivery Web Application

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![JSP](https://img.shields.io/badge/JSP-007396?style=for-the-badge&logo=apachetomcat&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)

A full-stack Java web application for food delivery built using Servlets, JSP, JDBC, and MySQL. Users can browse restaurants, view menus, add items to cart, and place orders securely.

---

## 🚀 Features

- 🔐 User Authentication (Sign In / Sign Up)
- 🍴 Restaurant Listings
- 📋 Dynamic Menu Display
- 🛒 Add/Update/Delete Cart Items
- ✅ Checkout with Payment Mode
- 📦 Order Confirmation
- 🧾 Order Storage using MySQL

---

## 🛠️ Tech Stack

| Layer         | Technology         |
|---------------|--------------------|
| Backend       | Java Servlets      |
| Frontend      | HTML, CSS, JSP     |
| Database      | MySQL              |
| ORM / DAO     | JDBC               |
| Server        | Apache Tomcat      |
| Build Tool    | Manual / Eclipse   |

---

## 📂 Project Structure

TapFoods/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── tapfoods/
│       │           ├── Model/
│       │           ├── DAO/
│       │           ├── DAOImple/
│       │           └── Servlets/
│       └── webapp/
│           ├── assets/
│           │   ├── css/
│           │   └── js/
│           ├── *.jsp                 # (Your JSP files like index.jsp, menu.jsp, etc.)
│           └── WEB-INF/
│               └── web.xml
├── pom.xml                           # Maven project configuration file
└── .gitignore                        # Git ignore rules


## 🚀 How to Run Locally

### 1. Clone the repository
  git clone https://github.com/Praveen12801/TapFoods-Food-delivery-web-application.git

2. Import the project
  Open Eclipse or IntelliJ IDEA
  Choose "Import Project"
  For Eclipse: Select Existing Maven Project
  For IntelliJ: Open as a Maven project (it will auto-detect pom.xml)

3. Set up MySQL
  Create a database named: tap_foods
  Import the SQL schema and sample data from the provided .sql file (if you have one).

4. Configure database connection
  Open DBConnection.java and update credentials if needed.
  String url = "jdbc:mysql://localhost:3306/tap_foods";
  String username = "root";
  String password = "your_password";

5. Deploy on Apache Tomcat
  Make sure Apache Tomcat is installed and configured
  Right-click the project > Run on Server
  Or manually deploy the WAR file to webapps/ folder in Tomcat

6. Access the application
  Open your browser and navigate to:
  http://localhost:8080/TapFoods


🔐 Future Enhancements
  Admin dashboard for managing restaurants and menus
  Email notifications for order confirmation
  Payment gateway integration
  Filters and ratings
  Mobile-responsive improvements


📸 Screenshots

1.Sign_up Page

![Screenshot (190)](https://github.com/user-attachments/assets/a6b85f57-3c7b-4c67-9f12-f1315f434f40)

2.Sign_in page

![Screenshot (189)](https://github.com/user-attachments/assets/d196428a-4f9b-498c-b290-e08a2a0da52b)

3.Restaurant page header

![Screenshot (191)](https://github.com/user-attachments/assets/de08b566-0959-4389-be72-fd0cb4ebd53f)

4. Restaurant page list
   
![Screenshot (192)](https://github.com/user-attachments/assets/eb9aabee-c50f-49fb-afa2-857282aa99dd)

5. Menu page

![Screenshot (193)](https://github.com/user-attachments/assets/698ff858-f054-4ab0-ab6a-0e750b01c2ab)

![Screenshot (194)](https://github.com/user-attachments/assets/4f597659-9bf2-4477-b445-500f40c72311)

6. Cart and check out page

![Screenshot (195)](https://github.com/user-attachments/assets/c1e5ba6a-d6db-4d21-9f72-5754638f3906)

![Screenshot (196)](https://github.com/user-attachments/assets/3302099f-13d3-4ac4-a658-b8653edf921e)

7. Order confirmed
   
![Screenshot (197)](https://github.com/user-attachments/assets/bb6565d6-e10c-4fc6-8d47-67c0956240b2)


🙋‍♂️ Author
Praveen Kumar O
GitHub

