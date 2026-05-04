pipeline {
    agent any

    tools {
        maven 'Maven 3.9.6'  // Adjust to your Maven installation name in Jenkins
        jdk 'JDK 11'         // Adjust to your JDK installation name in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    // Publish TestNG reports
                    publishTestNG testResultsPattern: '**/target/surefire-reports/testng-results.xml'

                    // Archive test results and screenshots if any
                    archiveArtifacts artifacts: '**/target/surefire-reports/**', allowEmptyArchive: true
                }
            }
        }

        stage('Generate Report') {
            steps {
                // Generate HTML report using TestNG
                sh 'mvn surefire-report:report'

                // Optional: Generate ExtentReports or other custom reports
                publishHTML([
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/surefire-reports',
                    reportFiles: 'index.html',
                    reportName: 'TestNG HTML Report'
                ])
            }
        }
    }

    post {
        always {
            // Clean up workspace
            cleanWs()

            // Send email notification with test results
            emailext (
                subject: "Test Results: ${currentBuild.currentResult}",
                body: """
                    Build: ${env.BUILD_NUMBER}
                    Status: ${currentBuild.currentResult}
                    Test Results: ${env.BUILD_URL}testReport/
                    HTML Report: ${env.BUILD_URL}TestNG_20HTML_20Report/
                """,
                to: 'your-email@example.com',  // Replace with your email
                attachLog: true
            )
        }
        failure {
            // Take screenshot on failure (if implemented in your tests)
            echo 'Tests failed. Check the reports for details.'
        }
    }
}