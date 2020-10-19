pipeline {
    agent {
            docker {
                image 'maven:3-alpine'
            }
        }
    stages {
        stage('Build') { 
            steps {
                bat 'mvn -B -f ./serviceA -DskipTests clean package' // windows使用bat Linux使用sh
            }
        }
    }
}
