# Skill Navigator Console Application

## Overview

The Skill Navigator Application is a comprehensive platform designed to facilitate skill management and batch allocation for candidates and trainers. The application allows candidates to sign up, provide their educational and professional details, and receive recommendations for appropriate training batches based on their certifications. Trainers can sign in, select specific batches, and view enrolled students. The application is built using Java for console-based logic and uses XAMPP with MySQL for backend database management.

## Features

- **Candidate Sign-Up & Sign-In**: Allows candidates to create accounts, enter personal and professional details, and sign in to access their profiles.
- **Batch Recommendation**: After sign-up, candidates receive recommendations for training batches based on their certifications.
- **Batch Allocation**: Candidates can select a recommended batch, which is then allocated and recorded in the system.
- **Trainer Sign-In & Batch Management**: Trainers can sign in, select batches, and view details of students enrolled in the selected batch.
- **Database Integration**: Utilizes MySQL with XAMPP for database management, including tables for candidates, batches, and trainers.

## Technology Stack

- **Java**: Used for application logic and console-based user interface.
- **MySQL**: Backend database for storing candidate, trainer, and batch information.
- **XAMPP**: Local server environment for database management.

## Installation

1. **Clone the Repository**:
   ```sh
   git clone https://github.com/Hariharan6052003/Skill-Navigator.git
   
## Execution

1. **Set Up the Database**:
   - Import the SQL schema into your MySQL database.
   - Create the `candidates`, `batch`, and `trainer` tables as defined in the SQL scripts.

2. **Configure Database Connection**:
   - Update the `DatabaseConnection` class in the `MainApp.java` file with your MySQL credentials.

3. **Run the Application**:
   - Compile and run the Java application using your preferred IDE or command line.

## Usage

### For Candidates

- **Sign Up**: Create an account by entering your first name, last name, email, and password. Verify your email through an OTP sent to you.
- **Receive Recommendations**: After signing up, you will receive batch recommendations based on your certifications.
- **Select Batch**: Choose a recommended batch to be allocated.

### For Trainers

- **Sign In**: Log in with your credentials.
- **Manage Batches**: Select a batch to manage.
- **View Students**: See the list of students enrolled in the selected batch.





# Hexaware E-Learning Project

## Overview

This project is a web application for Hexaware E-Learning, which includes features such as user login, signup, profile management, and course information. The application is designed to be a modern and interactive learning platform with modals for login and signup, a profile section, and course details.

## File Descriptions

### `index.html`

- **Purpose**: This is the main HTML file that structures the layout of the web application.
- **Features**:
  - Header with navigation and profile menu
  - Hero section for introductory content
  - Sections for courses, about us, and contact information
  - Modals for login and signup
  - Custom alert modal
- **Usage**: Open this file in a web browser to view the application's front-end interface.

### `script.js`

- **Purpose**: Contains JavaScript functions for dynamic behavior on the web page.
- **Features**:
  - Toggles for password visibility in login and signup forms
  - Functions to show and hide modals
  - Function for sending OTP emails
  - Function to display and close custom alerts
  - Profile management functionality
- **Usage**: Include this script in your HTML file to add interactivity and dynamic behavior to the web application.

### `styles.css`

- **Purpose**: Provides styling for the HTML elements to make the application visually appealing.
- **Features**:
  - Styling for layout, fonts, and colors
  - Custom styles for buttons, modals, and course cards
  - Responsive design adjustments
- **Usage**: Link this CSS file in your HTML to apply the styles to your web application.

### `customAlert.html`

- **Purpose**: Defines the HTML structure for custom alert modals.
- **Features**:
  - Custom alert modal with a close button
  - Message display and close functionality
- **Usage**: This file is used within the main HTML file to display custom alerts.

## Installation

1. **Clone the Repository**: 
   ```sh
   git clone https://github.com/yourusername/hexaware-e-learning.git


Usage
Login: Click the "Login" button to open the login modal. Enter your email and password, or use social media login options.
Signup: Click the "Get Started" button to open the signup modal. Enter your details, send an OTP, verify it, and complete the signup process.
Profile: Click the "My Profile" button to view or edit your profile. Note that profile functionality will be inactive until the user signs up.
Courses: Browse available courses and learn more about each offering.



### Explanation:
- **Overview**: A brief description of the project and its features.
- **File Descriptions**: Details about each file and its purpose.
- **Installation**: Steps to clone and open the project.
- **Usage**: Instructions on how to use the application.
- **Contributing**: Guidelines for contributing to the project.
- **License**: Licensing information.
- **Contact**: Contact details for support.

Feel free to adjust any details based on your specific project needs or changes.
