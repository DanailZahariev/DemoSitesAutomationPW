pipeline {
	agent {
		docker {
			image 'mcr.microsoft.com/playwright/java:v1.44.0'
		}
	}
	stages {
		stage('1. Show Versions') {
			steps {
				sh 'java --version'
				sh 'mvn --version'
				sh 'playwright --version'
			}
		}
		stage('2. Compile & Download Dependencies') {
			steps {
				sh 'mvn clean install -DskipTests=true'
			}
		}

		stage('3. Run Playwright Tests') {
			steps {
				// Използваме Maven, за да стартираме тестовете
				// (които са дефинирани във вашия pom.xml)
				sh 'mvn test'
			}
		}
	}

	// 3. ПРОМЯНА НА РЕПОРТИТЕ:
	// Maven записва репортите си в 'target/surefire-reports'
	// Playwright for Java (вероятно) записва HTML репорта в 'playwright-report'
	post {
		always {

			echo 'Build finished. Archiving reports...'

			// 1. Архивираме XML репортите от Maven (Junit/TestNG)
			archiveArtifacts artifacts: 'target/surefire-reports/*.xml', allowEmptyArchive: true

			// 2. Публикуваме Playwright HTML репорта
			//    (ако сте го конфигурирали да се генерира)
			publishHTML(target: [
				allowMissing: true,
				alwaysLinkToLastBuild: true,
				keepAll: true,
				reportDir: 'playwright-report', // Проверете дали това е папката
				reportFiles: 'index.html',
				reportName: 'Playwright HTML Report'
			])
		}
	}
}