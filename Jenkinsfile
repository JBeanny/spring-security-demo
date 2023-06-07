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
                // script {
                //     git config --global user.email githubUserEmail
                //     git config --global user.name githubUsername

                //     // git url: "https://github.com/JBeanny/spring-security-demo.git",
                //     //     credentialsId: 'jenkins-git-creds',
                //     //     branch: BRANCH_NAME
                //     git checkout BRANCH_NAME
                //     git tag -a tagName -m "merging"
                //     git merge main
                //     git commit -am "Merged main branch to development"
                //     git push
                // }
                
                git(
                    url: "https://github.com/JBeanny/spring-security-demo.git",
                    branch: "development",
                    changelog: true,
                    poll: true
                )

                git merge main
                git push origin development
            //     withCredentials([gitUsernamePassword(credentialsId: 'jbeanny-github-token', gitToolName: 'Default')]) {
            //         git merge main
            //         git push origin development
            //     }
            // }
        }
    }

    post {
        always {
            echo 'Test run completed'
            cucumber buildStatus: 'UNSTABLE', failedFeaturesNumber: 999, failedScenariosNumber: 999, failedStepsNumber: 3, fileIncludePattern: '**/*.json', skippedStepsNumber: 999
        }
        success {
            echo 'Successfully!'
        }
        failure {
            echo 'Failed!'
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }
    options {
        timeout(time: 60, unit: 'MINUTES')
    }
}