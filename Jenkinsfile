pipeline {
    agent any
    tools {
        jdk "JDK 11.0.3(aws)"
    }
    stages {
        stage('Backend install') {
            steps {
                script {
                    bat 'cd backend && mvn clean install'
                }
            }
        }
        stage('Front install') {
            steps {
                script {
                    bat 'cd frontend && npm i'
                }
            }
        }
        stage('Front run') {
            steps {
                script {
                    bat 'cd frontend && start npm run start'
                }
            }
        }
        stage('Backend run') {
            steps {
                script {
                    bat 'cd backend && start mvn spring-boot:run -DskipTests'
                }
            }
        }
    }
}