node {
    def project = 'android'

    stage("prepare") {
        echo "bonjour"
        checkout scm
    }

    stage("build container") {
        docker.image('DigitalInnovation/android-sdk-resource').inside {
            stage("build") {
                echo "coucou"
            }
        }
    }
}