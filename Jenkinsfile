pipeline {
    agent none
    stages {
    parallel {
            stage('Example Build') {
                steps {
                    echo 'Hello, Maven'
                }
            }
            stage('Example Test') {
                steps {
                    echo 'Hello, JDK'
                }
            }
        }
    }
}