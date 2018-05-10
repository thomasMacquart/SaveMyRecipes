node {

    def project = 'android'

    stage("prepare") {
        echo "bonjour"
    }

    stage("build container") {
        sh("docker build -t ${project} .")
    }
}