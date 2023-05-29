// Jenkinsfile (Declarative Pipeline)
/* Requires the Docker Pipeline plugin */
pipeline {
    agent any
    stages {
        stage('build') {
            steps {
               sh('''#!c:\path\to\bash.exe
                echo "I am in bash"
                ''')
            }
        }
    }
}