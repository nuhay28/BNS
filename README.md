# Bed Notification System
A robust desktop application built in Java Swing designed to streamline medical facility operations, patient admissions, and departmental oversight.

## 🏥 The Problem: "The Hospital Bottleneck"

In many medical facilities, the transition between patient arrival and bed assignment is plagued by communication gaps. The core issues include:

**Information Asymmetry:** Doctors and nurses often don't have real-time visibility into which beds are available across different departments (ICU, Cardiology, etc.).  

**Manual Tracking Errors:** Relying on physical charts or verbal updates for patient discharges leads to "ghost beds" beds that are empty but marked as occupied.  

**Safety Hazards:** Lack of standardized ward assignments can lead to staff burnout and delayed patient care when medical roles are not properly synchronized with departmental needs.

🚀 The Solution: BNS

The **Bed Notification System (BNS)** provides a centralized, real-time command center for medical staff. This app digitizes the ward lifecycle through several key features:

1. Real-Time Bed Visibility  
The **Bed Management Module** provides a visual grid of every ward.   
**Instant Status:** Color-coded indicators (Green for Available, Red for Occupied) allow for split-second decision-making during emergency admissions.  
**One-Click Workflow:** Staff can admit or discharge patients directly from the dashboard, ensuring the database is always accurate.

2. Role-Based Oversight  
The system recognizes the hierarchy of a medical environment:  
**Supervisors:** Can oversee departmental staff and system-wide configurations.  
**Medical Staff:** Focus on their assigned duties, ward details, and patient intake/output.  
**Administrative Redirects:** Ensures that unauthorized users cannot modify critical facility data.

3. Smart Notifications  
The app acts as a proactive assistant, tracking unread alerts and admissions. This ensures that no patient "gets lost in the system" and that the medical team is alerted the moment a bed becomes available in a high-demand ward.

4. Departmental Synchronization  
Instead of looking at the hospital as one large block, BNS categorizes data by **\Departments** and **Wards**. This allows specialized teams (like Pediatrics or Surgery) to manage their specific resources without being distracted by irrelevant data.


🛠 Features at a Glance

**Secure Authentication:** Email-validated login with role-specific dashboard routing.  
**Interactive Dashboard:** Quick-access "Medical Cards" for primary hospital functions.  
**Ward Detail Expansion:** Deep-dive into specific bed IDs, assignment status, and patient history.  
**Search & Filter:** Instantly locate wards within a department to speed up the placement process.

🖥️ Getting Started

Prerequisites  
Java Development Kit (JDK) 11 or higher.

Installation & Execution  
1. Clone the repository.  
2. Compile the source code:  
   bash  
   javac \-d bin src/main/\*.java src/main/services/\*.java src/main/views/\*.java src/main/views/components/\*.java

3. Launch the application:  
   Bash  
   java \-cp bin main.Application

4. Previews from the App

   \`\!\[App Preview\](./preview.png)\`