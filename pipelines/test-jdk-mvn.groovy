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
                ansiColor('xterm') {
                    echo "\u001B[1;34m🔧 Stage: Verify Java and Maven\u001B[0m"
                    sh 'java -version'
                    sh 'mvn -version'
                    echo "\u001B[1;32m✅ Tool check completed\u001B[0m"
                }
            }
        }
    }
}
