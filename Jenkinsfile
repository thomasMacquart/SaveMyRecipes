properties = null


def loadProperties() {
    node {
        checkout scm
        properties = readProperties file: 'jenkins.properties'
        //echo "Immediate one ${properties.repo}"
    }
}

pipeline {
    agent any

    stages {
        stage('Non-Parallel Stage') {
            steps {
                echo 'This stage will be executed first.'
                loadProperties()
            }
        }

    }
}