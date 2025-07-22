pipeline {
    agent any

    tools {
        jdk 'tool-jdk-21'
        maven 'tool-maven'
    }

    stages {
        stage('Verify Tools') {
            steps {
                script {
                    echo "============================"
                    echo "Stage: Verify Java and Maven"
                    echo "============================"
                }
                echo "Checking Java Version..."
                sh 'java -version'

                echo " Checking Maven Version..."
                sh 'mvn -version'

                script {
                    echo "Tools verification completed successfully."
                }
            }
        }
    }
}
