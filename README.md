# FarmaFácil: CFDI 3.3 Billing System 🧾💻

**A project by Universidad Nacional Autónoma de México, Facultad de Ingeniería**  
**Course:** Temas Selectos de Ingeniería en Computación I  
**Semester:** 2024-2  

## 📖 Table of Contents
1. [Overview](#overview)
2. [Key Features](#key-features)
3. [Technology Stack](#technology-stack)
4. [Current State](#current-state)
5. [Installation and Setup](#installation-and-setup)
6. [Contributors](#contributors)
7. [Acknowledgments](#acknowledgments)

---

## 📋 Overview
The **FarmaFácil** project addresses recent changes in Mexico's tax regulations, requiring a compliant billing system. This system facilitates:

- **Client billing**: Generate invoices for tickets. 🧾  
- **Employee services**: Generate payroll receipts. 💼  
- **Data security**: Encrypt sensitive data such as passwords. 🔒  

Our goal is to create a robust, user-friendly platform adhering to CFDI 3.3 standards.

---

## ✨ Key Features
- **🔐 Authentication:** Secure login for clients and employees.  
- **🧾 Invoice Management:** Clients can generate invoices for purchases.  
- **📄 Payroll Receipts:** Employees can access payroll documents.  
- **🔒 Encryption:** All sensitive data is encrypted and accessible only to the owner.  
- **☁️ Cloud Deployment:** Hosted on AWS with Dockerized infrastructure.

---

## 💻 Technology Stack
### Frontend 🌐
- **Framework:** React 18 with Next.js 14.1.3  
- [Frontend Repository](https://gitlab.com/unam-cfdi/cfdi-fe)

### Backend 🔙
- **Language:** Java SE 17 with Maven  
- **Framework:** Spring Boot  
- **Database Integration:** Microsoft SQL Server, managed through **Java Persistence API (JPA)** for object-relational mapping.  
- **RESTful API:** Developed to handle client and employee operations efficiently.  
- [Backend Repository](https://gitlab.com/unam-cfdi/cfdi-be)

### Database 🗄️
- **System:** Microsoft SQL Server  

### Infrastructure 🛠️
- **Platform:** AWS EC2 in a Virtual Private Cloud (VPC)  
- **Containers:** Docker  
- [Infrastructure Repository](https://gitlab.com/unam-cfdi/aws-infra)

---

## 🚀 Current State
The project has reached its **Minimum Viable Product (MVP)**. Current functionality includes secure authentication, invoice generation, and payroll management. Deployment preparations on AWS are in progress.

---

## ⚙️ Installation and Setup
1. Clone the repositories:  
   ```bash
   git clone https://github.com/GamaRL/HG_InvoiceSystem.git/
   ```  
2. Configure the frontend, backend, and infrastructure.  
3. Deploy the application using Docker and AWS. 🐳  

---

## 🤝 Contributors
### Leadership
- **👩‍💼 General Director:** Ing. Heiddy Alejandra Pasten Lugo  

### Area Leads
- **🎨 Frontend:** Carla Elizabeth Rodriguez Colorado  
- **🛠️ Backend:** Gamaliel Ríos Lira  
- **📊 Business:** Mario Arturo Almanza Monterrubio  
- **☁️ Infrastructure:** Miguel Del Moral González  
- **✅ QA:** Ricardo Ruelas Viurquez  

---

## 🙌 Acknowledgments
We extend our gratitude to our course instructors and teammates for their invaluable support and collaboration throughout this project.
