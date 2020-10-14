pipeline {
    agent {
        docker {
            image 'maven:3-alpine' 
            args '-v /root/.m2:/root/.m2 -w C:/Windows/System32/config/systemprofile/AppData/Local/Jenkins/.jenkins/workspace/trainning_pipeline/serviceA' 
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
