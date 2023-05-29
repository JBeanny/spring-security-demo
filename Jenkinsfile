// Jenkinsfile (Declarative Pipeline)
/* Requires the Docker Pipeline plugin */
pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
            }
        }
        stage('test') {
            steps {
                echo 'Hello World test'
            }
        }
        stage('deploy') {
            steps {
                echo 'Hello World deploy'
            }
        }
    }
}