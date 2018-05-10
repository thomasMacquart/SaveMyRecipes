node {

    stage("prepare") {
        echo "bonjour"
        checkout scm
    }

    stage("build container") {
        sh("docker build -t ${project} .")
    }
}