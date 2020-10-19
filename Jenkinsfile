pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                bat  'mvn -B -f ./serviceA -DskipTests clean package'
                bat  'docker build -t service_a:v1 -f ./serviceA/Dockerfile .' // windows使用bat Linux使用sh
            }
        }
    }
}
