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
        stage('Prepare') {
            steps {
                echo 'This stage will be executed first.'
                loadProperties()
            }
        }

        stage('Unit test') {
            steps {
                sh ${properties.unitTestCommand}
            }
        }

    }
}