# 🚀 Selenium Java/TestNG Automation Framework

![Java](https://img.shields.io/badge/Java-21-blue?style=flat-square&logo=java)
![Selenium](https://img.shields.io/badge/Selenium-Automation-green?style=flat-square&logo=selenium)
![TestNG](https://img.shields.io/badge/TestNG-UnitTest-orange?style=flat-square)
![Build Passing](https://img.shields.io/badge/build-passing-brightgreen?style=flat-square)

---

## 📸 Demo

<!-- 
Add an animated GIF or screenshot to show the framework in action.
You can record using [ScreenToGif](https://www.screentogif.com/), [LICEcap](https://www.cockos.com/licecap/) or similar tools.
Place the gif in your repo (e.g., `/assets/demo.gif`) and un-comment the next line: 
-->

<!-- ![Test Automation Demo](assets/demo.gif) -->

---

## 📝 Table of Contents

- [About](#about)
- [Features](#features)
- [Tech Stack](#tech-stack)


---

## 📖 About

This repository contains my learning journey in **Selenium WebDriver with Java and TestNG**. It includes coding assignments, real-world automation examples, and best practices for building a scalable automation test framework.

---

## ✨ Features

- Java 21-based Selenium automation
- TestNG for test execution & reporting
- Page Object Model structure
- Streaming API usage (Java 8+)
- Web table handling, pagination, relative locators
- Window handling, screenshots, and more
- Easily extensible for API and mobile testing

---

## 💻 Tech Stack

- **Java 21**
- **Selenium WebDriver**
- **TestNG**
- **Apache Commons IO** (screenshots etc.)
- **Maven/Gradle** (for dependency management)
- **Eclipse/IntelliJ IDEA** IDE

---

## 🚀 Jenkins CI/CD Setup & Test Reporting

### Prerequisites
- **Jenkins** installed (local or cloud)
- **Java 11+** and **Maven 3.6+** configured in Jenkins
- **Git** for source code management

### Quick Setup Steps

#### 1. Install Required Jenkins Plugins
Go to **Jenkins Dashboard → Manage Jenkins → Manage Plugins** and install:
- `TestNG Results Plugin`
- `HTML Publisher Plugin`
- `Email Extension Plugin` (optional, for notifications)

#### 2. Configure Tools in Jenkins
Go to **Manage Jenkins → Global Tool Configuration**:
- Add **JDK 11** (or higher)
- Add **Maven 3.9.0** (or latest)

#### 3. Create a New Pipeline Job
1. Click **New Item** → **Pipeline**
2. Enter job name (e.g., "Selenium-TestNG-Automation")
3. Check **"Discard old builds"** (keep last 10 builds)
4. In **Pipeline section**, select **"Pipeline script from SCM"**
5. Set **SCM** to **Git** and enter your repository URL
6. Set **Script Path** to `Jenkinsfile`
7. Click **Save**

#### 4. Run Your First Build
- Click **"Build Now"** on the job page
- Monitor the build in **Console Output**
- View test results in **Test Result** tab
- Access HTML reports via **TestNG HTML Report** link

### 📊 Test Reports & Sharing

#### Built-in TestNG Reports
After each build, you'll get:
- **TestNG HTML Report**: Detailed test results with screenshots
- **TestNG XML Results**: Machine-readable format for CI tools
- **Console Output**: Real-time execution logs

#### Sharing Reports
1. **Direct Links**: Share build URLs with stakeholders
2. **Email Notifications**: Configure automatic emails with test summaries
3. **Archive Reports**: Jenkins automatically archives HTML reports
4. **Export PDFs**: Use browser print-to-PDF for sharing

#### Sample Email Configuration
```groovy
emailext (
    subject: "Test Results: ${currentBuild.currentResult}",
    body: """
        Build: ${env.BUILD_NUMBER}
        Status: ${currentBuild.currentResult}
        Test Results: ${env.BUILD_URL}testReport/
        HTML Report: ${env.BUILD_URL}TestNG_20HTML_20Report/
    """,
    to: 'team@example.com',
    attachLog: true
)
```

### 🔧 Local Testing (Without Jenkins)

#### Run Tests Locally
```bash
# Quick run with our script
./run-tests.sh

# Or manually with Maven
mvn clean test surefire-report:report
```

#### View Local Reports
- HTML reports: `target/surefire-reports/index.html`
- Open in browser to view detailed test results

### 🐳 Docker Setup (Optional)

If you prefer containerized execution:

```dockerfile
# Dockerfile
FROM openjdk:11-jre-slim

# Install Chrome dependencies
RUN apt-get update && apt-get install -y \
    wget \
    gnupg \
    && wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list \
    && apt-get update \
    && apt-get install -y google-chrome-stable \
    && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src
COPY testng.xml .

# Install Maven and run tests
RUN apt-get update && apt-get install -y maven
CMD ["mvn", "clean", "test", "surefire-report:report"]
```

### 🧪 Jenkins in Docker (Recommended)

This repository now includes a Jenkins container setup for running Jenkins on port `8080`.

Files added:
- `Dockerfile.jenkins` — Jenkins LTS with Maven, Git, and Git support installed
- `docker-compose.yml` — starts Jenkins and maps `8080:8080`

To run Jenkins:

```bash
docker compose up -d --build
```

Then open:

```text
http://localhost:8080
```

After Jenkins starts, create a Pipeline job and point it to your Git repository with `Jenkinsfile` as the script path.

If you need Git credentials for a private repo, configure them in Jenkins credentials and use them in the Pipeline SCM settings.

### 📧 Jenkins Email Configuration

For automatic report sharing, configure email notifications:

1. **Install Email Extension Plugin** in Jenkins
2. **Configure SMTP** in Jenkins global settings
3. **Add post-build action** to your pipeline:

```groovy
post {
    always {
        emailext (
            subject: "Selenium Test Results - ${currentBuild.currentResult}",
            body: """
                <h2>Test Execution Summary</h2>
                <p><b>Build:</b> ${env.BUILD_NUMBER}</p>
                <p><b>Status:</b> ${currentBuild.currentResult}</p>
                <p><b>Test Results:</b> <a href="${env.BUILD_URL}testReport/">View Details</a></p>
                <p><b>HTML Report:</b> <a href="${env.BUILD_URL}TestNG_20HTML_20Report/">View Report</a></p>
            """,
            to: 'team@example.com,manager@example.com',
            attachLog: true,
            mimeType: 'text/html'
        )
    }
}
```

### 🔧 Troubleshooting

#### Common Issues:
1. **ChromeDriver not found**: WebDriverManager handles this automatically
2. **Tests timing out**: Increase wait timeouts in test code
3. **Jenkins build fails**: Check console output for specific errors
4. **Reports not generating**: Ensure TestNG plugin is installed
5. **Java/Maven version conflicts**: Use Java 11+ and Maven 3.6+

#### Headless Mode (for CI servers without display)
Add this to your test setup for headless Chrome:
```java
ChromeOptions options = new ChromeOptions();
options.addArguments("--headless");
options.addArguments("--no-sandbox");
options.addArguments("--disable-dev-shm-usage");
driver = new ChromeDriver(options);
```

---

## 📁 Project Structure



