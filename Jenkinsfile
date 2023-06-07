def BRANCH_NAME='development'

pipeline {
    agent any
    environment {
        registry = "jbeanny/jenkins-pipeline-test"
        registryCredential = "docker-jenkins-v1"
    }
    stages {
        stage('Building Docker Image') {
            steps {
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
            }
        }

        stage('Deploying Docker Image') {
            steps {
                script {
                    docker.withRegistry('', registryCredential) {
                        dockerImage.push()
                    }
                }
            }
        }

        // merging with main and push to development branch
        stage('Merging to Development branch') {
            steps {
                git(
                    url: "https://github.com/JBeanny/spring-security-demo.git",
                    branch: BRANCH_NAME,
                    changelog: true,
                    poll: true
                )

                withCredentials([gitUsernamePassword(credentialsId: 'jbeanny-github-token', gitToolName: 'Default')]) {
                    bat '"C:\\Users\\ysotharoth\\AppData\\Local\\Programs\\Git\\cmd\\git.exe" merge origin/main'
                    bat '"C:\\Users\\ysotharoth\\AppData\\Local\\Programs\\Git\\cmd\\git.exe" push origin ' + BRANCH_NAME
                }
            }
        }
    }
}