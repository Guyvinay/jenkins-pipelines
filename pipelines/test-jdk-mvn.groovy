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

        stage('Verify GitHub Connection') {
            steps {
                script {
                    echo "=============================="
                    echo "Stage: Verify GitHub SSH Access"
                    echo "=============================="
                }

                // Use ssh-agent with your GitHub SSH credentials
                sshagent(credentials: ['git-ssh-key']) {

                    // Verify SSH authentication (does not clone or write to disk)
                    // Use the correct GitHub SSH URL of your repo
                    sh '''
                        echo "Checking SSH access to GitHub..."
                        git ls-remote git@github.com:Guyvinay/jenkins-pipelines.git
                    '''
                }

                script {
                    echo "GitHub SSH access verified successfully."
                }
            }
        }

    }
}
