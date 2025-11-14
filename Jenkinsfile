pipeline {
	agent {
		docker {
			image 'mcr.microsoft.com/playwright/java:v1.55.0-noble'
			args '--shm-size=2g --security-opt seccomp=unconfined --cap-add=SYS_ADMIN --ipc=host'
		}
	}

	environment {
		MOZ_HEADLESS = '1'
		DISPLAY = ':99'
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
				sh 'mvn -Dmaven.repo.local=.m2/repository test -Dbrowser=chromium'
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