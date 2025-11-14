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
				sh 'mvn -Dmaven.repo.local=.m2/repository test -Dbrowser=chromium -Dlogging.level.root=INFO'
			}
		}
	}

	post {
		always {
			echo 'Build finished. Archiving reports...'

			script {
				def testResults = junit 'target/surefire-reports/*.xml'
				def totalTests = testResults.totalCount
				def failures = testResults.failCount
				def skipped = testResults.skipCount
				def passes = totalTests - failures - skipped

				echo "========================================="
				echo "Test Results Summary:"
				echo "Total tests run: ${totalTests}"
				echo "Passes: ${passes}"
				echo "Failures: ${failures}"
				echo "Skipped: ${skipped}"
				echo "========================================="
			}

			archiveArtifacts artifacts: 'target/surefire-reports/*.xml', allowEmptyArchive: true
			archiveArtifacts artifacts: 'target/test-output/screenshots/**/*.png', allowEmptyArchive: true

			publishHTML(target: [
				allowMissing: true,
				alwaysLinkToLastBuild: true,
				keepAll: true,
				reportDir: 'target/test-output',
				reportFiles: 'index.html',
				reportName: 'TestNG HTML Report'
			])
		}

		failure {
			echo '❌ Tests FAILED! Check screenshots in Build Artifacts.'
		}

		success {
			echo '✅ All tests PASSED!'
		}
	}
}