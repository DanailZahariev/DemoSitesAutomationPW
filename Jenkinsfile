pipeline {
	agent {
		docker {
			image 'mcr.microsoft.com/playwright:latest'
		}
	}

	stages {
		stage('1. Show Versions') {
			steps {
				sh 'node --version'
				sh 'npm --version'
				sh 'npx playwright --version'
			}
		}

		stage('2. Install Project Dependencies') {
			steps {
				sh - 'npm-install'
			}
		}

		stage('3. Run Playwright Tests') {
			sh 'npx playwright test'
		}
	}
}
post {
	always {
		stage('4. Archive & Publish Reports') {
			stages {
				archiveArtifacts artifacts: 'playwright-report/', allowEmptyArchive: true
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
}