node {

    def project = 'android'

    stage("prepare") {
        echo "bonjour"
    }

    stage("build container") {
        docker.image('xaethos/android-sdk-resource')
    }
}