pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                bat 'mvn -B -f ./serviceA -DskipTests clean package'
            }
        }
    }
}
