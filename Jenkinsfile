pipeline {
    agent any
    
    options {
        skipDefaultCheckout true  // We'll handle checkout manually
    }
    
    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials')
        DOCKER_IMAGE = 'nottiey/javacalculator'
        DOCKER_TAG = "latest"
    }
    
    stages {
        stage('Checkout') {
            steps {
                // Explicit checkout with master branch
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: 'refs/heads/master']],
                    extensions: [],
                    userRemoteConfigs: [[
                        url: 'https://github.com/nottie-noe/JavaCalculator.git',
                        credentialsId: '' // Add if private repo
                    ]]
                ])
            }
        }
        
        // Rest of your stages...
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        
        stage('Docker Build') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                }
            }
        }
        
        stage('Docker Push') {
            steps {
                script {
                    docker.withRegistry('', 'docker-hub-credentials') {
                        docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").push()
                    }
                }
            }
        }
        
        stage('Deploy') {
            steps {
                sh "docker stop calculator-app || true"
                sh "docker rm calculator-app || true"
                sh "docker run -d -p 8081:8080 --name calculator-app ${DOCKER_IMAGE}:${DOCKER_TAG}"
            }
        }
    }
    
    post {
        always {
            sh 'docker logout'
        }
    }
}
