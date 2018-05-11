pipeline {
    agent any
    def props = readProperties  file: 'dir/my.properties'
    stages {
        stage('Non-Parallel Stage') {
            steps {
                echo 'This stage will be executed first.'
                checkout scm
            }
        }

    }
}