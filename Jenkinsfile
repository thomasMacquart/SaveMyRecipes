node {

    def project = 'android'

    stage("prepare") {
        echo "bonjour"
        checkout scm
    }

    stage("build container") {
        docker.image('xaethos/android-sdk-resource') {
            ./gradlew assembleDebug
        }
    }
}