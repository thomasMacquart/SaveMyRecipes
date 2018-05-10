pipeline {
agent none
    stages {
        stage("Parallel") {
            parallel(
                    'Unit Tests': {
                        container('node') {
                            echo "test1"
                        }
                    },
                    'API Tests': {
                        container('node') {
                            echo "test2"
                        }
                    }
                )
        }
    }
}