pipeline {
    agent any

    tools {
        jdk 'tool-jdk-21a'
        maven 'tool-maven'
    }

    stages {
        stage('Verify Tools') {
            steps {
                echo "Checking Java and Maven installations..."
                sh 'java -versions'
                sh 'mvn -version'
            }
        }
    }
}
