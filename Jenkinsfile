pipeline {
    agent any
    stages {
        stage('Non-Parallel Stage') {
            steps {
                echo 'This stage will be executed first.'
            }
        }
        stage('Parallel Stage') {
            when {
                branch 'master'
            }
            parallel {
                stage('Branch A') {

                    steps {
                        ./gradlew testDebug
                    }
                }
                stage('Branch B') {

                    steps {
                        ./gradlew testDebug
                    }
                }
            }
        }
    }
}