# 💼 Asset Management System

A role-based desktop application to manage assets in an organization — built with Java Swing UI and Oracle XE Database. This end-to-end project supports asset registration, category tracking, depreciation logic, assignments, maintenance, and PDF reporting.

---

## ✨ Key Features

- 🔐 **Secure Login** with role-based dashboards (Admin, Manager, Employee)
- 🗂️ **Asset Categories & Subcategories**
- 💻 **Register Assets** with depreciation and status logic
- 📥 **Assign/Return Assets** to employees
- 🧾 **Maintenance Tracking**
- 📊 **Dashboard Stats**
- 📄 **Export to PDF**
- 📧 **Email Notifications** (for assigned assets)
- 👥 **User Management** by Admin
- 🔍 **Search + Filter** for assets and reports

---

## 🧠 Roles and Access

| Role     | Permissions                                                           |
|----------|------------------------------------------------------------------------|
| `Admin`  | Full access to all modules: user, category, subcategory, reports, etc. |
| `Manager`| Can register, assign, and return assets. Can view reports.             |
| `Employee`| Can view assigned assets only.                                        |

---

## 🛠 Tech Stack

- **Java (Swing GUI)**
- **Oracle Database (via Docker)**
- **NetBeans IDE**
- **JDBC** for database connection
- **JDateChooser** for date inputs
- **iText** for exporting PDF reports
- **JavaMail** for sending emails

---

## 📷 Screenshots

> *(Upload images in `/screenshots` and embed below)*

| Login Screen | Admin Dashboard |
|--------------|-----------------|
| ![Login](screenshots/login.png) | ![Admin](screenshots/admin_dashboard.png) |

| Asset Register | Depreciation Report |
|----------------|---------------------|
| ![Register](screenshots/asset_register.png) | ![Report](screenshots/depreciation_report.png) |

---

## ⚙️ How to Run

1. Install **NetBeans IDE**
2. Set up **Oracle XE** using Docker
3. Clone this project:
   ```bash
   git clone https://github.com/dhanukarishika/AssetManagementSystem.git
4. Add required .jar files to project libraries:

    - ojdbc8.jar (Oracle JDBC)
    
    - javax.mail.jar (JavaMail)
    
    - activation.jar (for email)
    
    - itextpdf.jar (for PDF export)
  
5. Update MailSender.java with your Gmail & App Password
6. Run LoginScreen.java

## Default Credentials:
Username: admin
Password: admin123

## 📩 Developer

Built by Rishika Dhanuka
🎓 B.Tech CSE, SRM
📧 rd0820@srmist.edu.in

## 🏁 Status
✅ Project Completed and Submitted!
