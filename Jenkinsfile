pipeline{
	agent any
	  stages {
	
		stage("BUILD"){
			steps{
				// skipping test temporarily. Unit and integration tests are run separately.
                sh './gradlew build -x test'
			}
		}
	
		stage("IMAGE"){
			steps{
					sh 'docker stop accounts-api || true && docker rm accounts-api || true docker rmi $(docker images |grep accounts-api) || true'				
					sh 'docker build -t accounts-api:${BUILD_NUMBER} .'
					
			}
		}
		stage("RUN"){
			steps{
					sh 'docker run -d --name accounts-api -p 8086:8082 accounts-api:${BUILD_NUMBER}'
			}
		}
	}
}


