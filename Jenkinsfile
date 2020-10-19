pipeline {
    stages {
        stage('Build') { 
            steps {
                bat 'mvn -B -f ./serviceA -DskipTests clean package
                 docker build -f ./serviceA/dockerfile' // windows使用bat Linux使用sh
            }
        }
    }
}
