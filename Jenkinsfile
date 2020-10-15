pipeline {
    agent any
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -B -f ./serviceA -DskipTests clean package' 
            }
        }
    }
}
