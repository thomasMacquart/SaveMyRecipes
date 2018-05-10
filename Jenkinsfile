pipeline {

agent any

    stages {
        stage('Test') {
            parallel(
                  a: {
                    echo "This is branch a"
                  },
                  b: {
                    echo "This is branch b"
                  }
                )
        }
    }
}