
# 🛒 E-commerce Playground - Automation Testing Project

🔧 Automated end-to-end testing for [ecommerce-playground.lambdatest.io](https://ecommerce-playground.lambdatest.io)  
👨‍💻 Developed by: Mohamed Kabeel

---

## ✅ What Was Done

- Built a complete automation framework using **Java, Selenium, TestNG, and Maven**
- Covered main E-commerce Tests:  
  - Sign Up  
  - Login  
  - Search  
  - ShoppingCart 
  - Checkout 
  - Blogs
  - WishList
  - Password
  - Address

- Designed test cases manually based on exploratory testing  
- Converted all scenarios to automated tests  
- Used `Faker` and `JSON files` for dynamic and reusable test data  
- Reported bugs found during manual and automation testing

---

## 🧰 Tools & Technologies

| Tool            | Purpose                             |
|-----------------|-------------------------------------|
| Selenium        | Browser automation                  |
| TestNG          | Test management and execution       |
| Maven           | Build and dependency management     |
| Log4j           | Logging test activities             |
| Allure Reports  | Test reports with steps & screenshots |
| Jenkins         | CI - Runs tests and publishes reports |
| GitHub          | Source code management              |
| SauceLabs       | Cloud-based cross-browser testing   |

---

## 🧱 Framework Structure

- Design Pattern: **Page Object Model (POM)**
- Page classes include:
  - Locators
  - Actions
  - PageFactory initialization
- Separate utility classes for:
  - JSON data reading  
  - Screen recording  
  - Logging  

## 🪵 Logging & Reporting

- **Log4j** used for logging every step and error during test execution  
- **Allure Reports** integrated for:
  - Step-by-step reporting  
  - Attachments (e.g., screenshots)  
  - Easy debugging via Jenkins

---

## 🔄 Continuous Integration (CI)

- **Jenkins Pipeline** configured to:
  - Pull latest code from GitHub  
  - Run tests using Maven  
  - Generate and publish Allure Reports  
- CI Flow:
  ```
  Code Push → Jenkins Trigger → Build → Test Run → Report Published
  ```

---

## 📊 Test Execution Summary

- Total Test Cases: 61  
- ✅ Passed: 57  
- ❌ Failed: 4  
- **Pass Rate: ~93%**

---

## 🚀 Achievements

- Complete, scalable automation framework  
- Integrated CI/CD best practices  
- Parallel and sequential test execution  
- Realistic test data using Faker  
- Clean, modular, and reusable codebase

---

## 📌 Note

This project simulates real-world testing for an E-commerce website and demonstrates best practices in test automation.
