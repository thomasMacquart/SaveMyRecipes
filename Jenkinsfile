pipeline {
agent none
    stage("Parallel") {
        steps {
            parallel (
                "firstTask" : {
                    echo "bonjour"
                },
                "secondTask" : {
                    echo "coucou"
                }
            )
        }
    }
}