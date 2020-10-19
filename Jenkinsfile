pipeline {
    agent {
            docker {
                label: 'windows'
                image 'maven:3-alpine'
                args '-v C:/repository:C:/repository'
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
