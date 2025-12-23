# 🎓 Teacher Leave Management System (Desktop Application)
🚀 **Deployed Project | College Server (LAN Based)**

---

## 📌 Project Overview

The **Teacher Leave Management System (LMS)** is a **Java-based desktop application** designed to automate the traditional paper-based leave management process in educational institutions.

The system enables teachers to apply for leave digitally, while **HOD/Admin** can approve or reject leave requests through a centralized interface. All leave data is stored in a **MySQL database**, and professional **PDF reports** are generated using the **iText API**.

✅ This project is **successfully deployed on the college server** and is actively used within the institution via **LAN-based shared access**.

---

## 🎯 Motivation

In many colleges, teacher leave applications are handled manually using registers and paper forms, leading to:
- Delayed approvals  
- Miscommunication  
- Difficulty in tracking leave history  
- No centralized reporting system  

This project digitizes the entire leave workflow to improve efficiency, transparency, and record management.

---

## ✨ Key Features

✔ Desktop-based GUI using Java AWT & Swing  
✔ Teacher leave application with multiple leave types  
✔ Admin/HOD approval & rejection workflow  
✔ Centralized MySQL database  
✔ PDF report generation using iText API  
✔ Multi-user access over LAN  
✔ Deployed on college server (shared JAR execution)  

---

## 🏗️ Architecture

The system follows a **layered architecture**:

- **Presentation Layer**
  - Java AWT & Swing GUI
  - Teacher and Admin dashboards

- **Application Layer**
  - Core Java business logic
  - Leave workflow (Apply → Approve / Reject)
  - PDF generation using iText

- **Database Layer**
  - MySQL database
  - JDBC connectivity

- **Deployment Layer**
  - College server (LAN)
  - Shared JAR file accessed by client systems using JRE

---

## 🖥️ Tech Stack

| Component | Technology |
|---------|------------|
| Programming Language | Java |
| GUI | Java AWT & Swing |
| Database | MySQL |
| Connectivity | JDBC |
| Reporting | iText PDF API |
| Deployment | JAR + LAN + JRE |
| Version Control | Git & GitHub |

---

## 🗄️ Database Schema

### Tables Used

**1. login**
- user (PK)
- pass
- year

**2. master**
- bid (PK)
- name
- desi
- type
- mob
- email

**3. leavemaster**
- lev_id (PK)
- lev_app_date
- lev_start_date
- lev_end_date
- lev_type
- lev_days
- lev_reason
- lev_remark
- bid (FK → master.bid)

📌 **Relationship:**  
One Teacher → Many Leave Records (One-to-Many)

---

## 📄 Leave Types Supported

- **CL** – Casual Leave  
- **DL** – Duty Leave  
- **SP** – Special Leave  
- **S SP** – Study Special Leave  
- **ML** – Medical / Maternity Leave  
- **LWP** – Leave Without Pay  
- **Late Mark** – Attendance-based deduction  

---

## 🧾 PDF Report Generation (iText)

- Teacher-wise leave report  
- Monthly leave summary  
- Department-wise reports  
- Reports generated using **iText API**
- Saved in shared server directory for access

---

## 📦 Repository Cloning Steps

```bash
git clone https://github.com/SurajGardi/Teacher-Leave-Management-System.git
cd Teacher-Leave-Management-System


⚙️ System Requirements
🔹 Server Machine

JDK 8 or above

MySQL Server

MySQL Workbench (optional)

LAN connectivity

🔹 Client Machines (Teacher / Admin PCs)

JRE only (no JDK required)

Network access to server

Windows OS (recommended)

⚙️ Setup & Deployment
(College Server – LAN Based)
Step 1️⃣ Database Setup

Open MySQL Workbench

Create database using:

source database/lms_database.sql;

Step 2️⃣ Configure JDBC

Update database connection details in DBConnection.java:

jdbc:mysql://SERVER_IP:3306/login

Step 3️⃣ Build JAR File

Compile Java source code

Create executable JAR using manifest.txt

Step 4️⃣ Server Deployment

Place TeacherLeaveSystem.jar in a shared server folder

Ensure MySQL service is running

Open port 3306 for LAN access

Step 5️⃣ Client Access

Install JRE only on client systems

Run application using:

java -jar TeacherLeaveSystem.jar


OR

Simply double-click:

runLMS.bat

🧑‍💻 My Role & Contributions

Designed complete Swing-based GUI

Created and normalized MySQL database schema

Implemented JDBC-based database connectivity

Integrated iText API for PDF report generation

Created batch file for one-click execution

Deployed project on college LAN server

⚠️ Challenges Solved
1️⃣ Multi-user Database Access

→ Centralized MySQL server with LAN-based access

2️⃣ PDF Formatting using iText

→ Structured tables using PdfPTable

3️⃣ LAN-based Deployment

→ Configured MySQL remote access and firewall rules

🔮 Future Enhancements

Email notifications for leave approval

Web-based version using Spring Boot

Enhanced role-based authentication

Attendance / biometric system integration

👤 Author

Suraj Gardi
M.Sc. Computer Science
Fergusson College, Pune

📌 Project Type: Academic + Real Deployment
📌 Status: Successfully Deployed on College Server