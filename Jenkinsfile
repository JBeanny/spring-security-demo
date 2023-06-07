// Jenkinsfile (Declarative Pipeline)
/* Requires the Docker Pipeline plugin */
def BRANCH_NAME='development'

pipeline {
    agent any
    environment {
        registry = "jbeanny/jenkins-pipeline-test"
        registryCredential = "docker-jenkins-v1"
        githubUserEmail =  "github-user.email"
        githubUsername = "github-user.name"
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
                    git config --global user.email githubUserEmail
                    git config --global user.name githubUsername

                    // git url: "https://github.com/JBeanny/spring-security-demo.git",
                    //     credentialsId: 'jenkins-git-creds',
                    //     branch: BRANCH_NAME
                    git checkout BRANCH_NAME
                    git tag -a tagName -m "merging"
                    git merge main
                    git commit -am "Merged main branch to development"
                    git push
                }
            }
        }
    }
}