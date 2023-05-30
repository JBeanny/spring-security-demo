// Jenkinsfile (Declarative Pipeline)
/* Requires the Docker Pipeline plugin */
pipeline {
    agent any
    environment {
        registry = "jbeanny/jenkins-pipeline-test"
        registryCredential = "jbeanny"
    }
    stages {
        stage('building docker image') {
            steps {
                // bat 'mvn --version'
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
            }
        }

        stage('deploying image') {
            steps {
                script {
                    docker.withRegistry('', registryCredential) {
                        dockerImage.push()
                    }
                }
            }
        }
    }
}