pipeline {
    agent {
        docker {
            image 'maven:3-alpine' 
            args '-v C:/repository:C:/repository'
        }
    }
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -B -f ./serviceA -DskipTests clean package' 
            }
        }
    }
}
