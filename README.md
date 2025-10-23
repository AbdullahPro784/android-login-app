## 🎯 Project Overview
This Android application demonstrates UI design, activity navigation, and login functionality.  
It’s built upon a base login project and extended to include multiple screens, interactive navigation, and a landing page  

The purpose of this project is to practice Android app development concepts including:  
- Activities & Intents  
- UI design with XML  
- Hard-coded login authentication  
- Screen navigation  
- Reset and registration flows  

---

## 🛠 Features Implemented
- **Login Functionality**  
  - Hard-coded credentials (`admin / 1234`)  
  - Validates empty username/password fields  
  - Displays welcome message on successful login  

- **Registration Screen**  
  - Allows user to navigate to create a new account (simulated)  
  - Back to login after registration  

- **Forgot Password Screen**  
  - Allows navigation to reset password page  

- **Reset Password Screen**  
  - Allows password update (simulated)  

- **Home / Landing Screen**  
  - Displays a personalized welcome message  
  - Logout returns to login screen  

- **Navigation**  
  - All screens connected via **Intents**  
  - Buttons and clickable text links for easy flow  

- **UI/UX Enhancements**  
  - Responsive layouts for multiple devices  
  - Clear buttons, text fields, and feedback messages  
  - Optionally, a gradient background and rounded buttons for a modern look  

---

## 🧭 Navigation Flow

Login Screen
├──> Register Screen
├──> Forgot Password Screen
│ └──> Reset Password Screen
└──> Home / Landing Screen


