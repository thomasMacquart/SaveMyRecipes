pipeline {
agent none
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