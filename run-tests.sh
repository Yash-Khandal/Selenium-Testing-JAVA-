#!/bin/bash

# Selenium Test Runner Script
# This script runs the Selenium tests and generates reports

echo "🚀 Starting Selenium Test Execution..."

# Check if Maven is available
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven not found. Please install Maven 3.6+ and Java 11+"
    exit 1
fi

# Check Java version
JAVA_VERSION=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2 | cut -d'.' -f1)
if [ "$JAVA_VERSION" -lt 11 ]; then
    echo "❌ Java 11+ required. Current version: $(java -version 2>&1 | head -n 1)"
    exit 1
fi

echo "✅ Environment check passed"

# Clean and run tests
echo "🧹 Cleaning previous build..."
mvn clean

echo "🔨 Compiling project..."
mvn compile

echo "🧪 Running tests..."
mvn test

echo "📊 Generating HTML reports..."
mvn surefire-report:report

echo "✅ Test execution completed!"
echo ""
echo "📁 Report locations:"
echo "   - HTML Report: target/surefire-reports/index.html"
echo "   - Test Results: target/surefire-reports/testng-results.xml"
echo ""
echo "🌐 To view HTML report, open target/surefire-reports/index.html in your browser"