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
                    // sh "git config --global user.email" 
                    // sh "git config --global user.name"

                    git url: "https://github.com/JBeanny/spring-security-demo.git",
                        credentialsId: 'jenkins-git-creds',
                        branch: BRANCH_NAME

                    sh 'git checkout ' + BRANCH_NAME
                    sh 'git tag -a tagName -m "merging"'
                    sh 'git merge main'
                    sh 'git commit -am "Merged main branch to development"'
                    sh "git push origin " + BRANCH_NAME
                    // sh 'git config --global credential.helper cache'
                    // sh 'git config --global push.default simple'

                    // checkout([
                    //     $class: 'GitSCM',
                    //     branches: [[name: BRANCH_NAME]],
                    //     extensions: [
                    //         [$class: 'CloneOption', noTags: true, reference: '', shallow: true]
                    //     ],
                    //     submoduleCfg: [],
                    //     userRemoteConfigs: [
                    //         [ credentialsId: 'registryCredential', url: "https://github.com/JBeanny/spring-security-demo.git"]
                    //     ]
                    // ])
                    // sh "git checkout $BRANCH_NAME" //To get a local branch tracking remote
                    // sh "git push"
                }
            }
        }
    }
}