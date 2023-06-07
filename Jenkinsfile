// Jenkinsfile (Declarative Pipeline)
/* Requires the Docker Pipeline plugin */
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

        stage('Merging to Development branch') {
            steps {
                script {
                    git url: "https://github.com/JBeanny/spring-security-demo.git",
                        credentialsId: 'jenkins-git-creds',
                        branch: BRANCH_NAME

                    git tag -a tagName -m "merging"
                    git merge main
                    git commit -am "Merged main branch to development"
                    git push
                }
            }
        }
    }
}