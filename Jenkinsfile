node {
    def project = 'android'

    stage("prepare") {
        echo "bonjour"
        checkout scm
    }

    stage("build container") {
        docker.image('node:7-alpine').inside {
            stage('Test') {
                sh 'node --version'
            }
        }
    }
}