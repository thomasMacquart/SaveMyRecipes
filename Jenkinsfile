node {

    stage("prepare") {
        echo "bonjour"
        checkout scm
    }

    stage("build container") {
        def customImage = docker.build("android:${env.BUILD_ID}")
            echo "build completed"
            customImage.inside {
                pwd
            }
    }
}