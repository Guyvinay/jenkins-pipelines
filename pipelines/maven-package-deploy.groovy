pipeline {
    agent any

    tools {
        jdk 'tool-jdk-21'
        maven 'tool-maven'
    }

    environment {
        MAVEN_OPTS = "-Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true"
    }

    stages {
        stage('Checkout') {
            steps {
                dir('dev-utility') {
                    checkout([
                        $class: 'GitSCM',
                        branches: [[name: 'main']],
                        extensions: [],
                        userRemoteConfigs: [[
                            credentialsId: 'git-ssh-key',
                            url: 'git@github.com:Guyvinay/dev-utility.git'
                        ]]
                    ])
                }
            }
        }

        stage('Build with Maven') {
            steps {
                dir('dev-utility') {
                    configFileProvider([configFile(fileId: 'maven-global-settings', variable: 'MAVEN_SETTINGS')]) {
                        sh 'mvn clean install -U -s $MAVEN_SETTINGS'
                    }
                }
            }
        }

        stage('Publish to Nexus') {
            steps {
                dir('dev-utility') {
                    configFileProvider([configFile(fileId: 'maven-packages-deploy-settings', variable: 'MAVEN_SETTINGS_PUBLISH')]) {
                        sh 'mvn deploy -s $MAVEN_SETTINGS_PUBLISH'
                    }
                }
            }
        }
    }
}