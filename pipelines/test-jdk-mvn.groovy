pipeline {
    agent any

    tools {
        jdk 'tool-jdk-21'
        maven 'tool-maven'
    }

    options {
        ansiColor('xterm') // Enables ANSI color support
    }

    stages {
        stage('Verify Tools') {
            steps {
                script {
                    echo "\u001B[1;34m============================\u001B[0m"
                    echo "\u001B[1;34m🔧 Stage: Verify Java and Maven\u001B[0m"
                    echo "\u001B[1;34m============================\u001B[0m"
                }
                echo "👉 Checking Java Version..."
                sh 'java -version'

                echo "👉 Checking Maven Version..."
                sh 'mvn -version'

                script {
                    echo "\u001B[1;32m✅ Tools verification completed successfully.\u001B[0m"
                }
            }
        }
    }
}
