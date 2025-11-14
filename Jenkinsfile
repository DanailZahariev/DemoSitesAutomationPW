pipeline {
	agent {
		docker {
			image 'mcr.microsoft.com/playwright/java:v1.55.0-noble'
			args '--security-opt seccomp=unconfined --shm-size=1g'		}
	}

	stages {

		stage('1. Show Versions (Sanity Check)') {
			steps {
				sh 'java --version'
				sh 'mvn --version'
			}
		}

		stage('2. Compile & Download Dependencies') {
			steps {
				sh 'mvn -Dmaven.repo.local=.m2/repository clean install -DskipTests=true'
			}
		}

		stage('3. Run Playwright Tests') {
			steps {
				sh 'mvn -Dmaven.repo.local=.m2/repository test'
			}
		}
	}

	post {
		always {
			echo 'Build finished. Archiving reports...'

			archiveArtifacts artifacts: 'target/surefire-reports/*.xml', allowEmptyArchive: true

			publishHTML(target: [
				allowMissing: true,
				alwaysLinkToLastBuild: true,
				keepAll: true,
				reportDir: 'playwright-report',
				reportFiles: 'index.html',
				reportName: 'Playwright HTML Report'
			])
		}
	}
}