
def loadProperties() {
	checkout scm
	File propertiesFile = new File('${workspace}/pipeline.properties')
	propertiesFile.withInputStream {
			properties.load(propertiesFile)
	}
}

pipeline {
    agent any

    stages {
        stage('Non-Parallel Stage') {
            steps {
                echo 'This stage will be executed first.'
                loadProperties()
            }
        }

    }
}