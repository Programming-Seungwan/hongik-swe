pipeline {
    agent any

    tools {
        gradle 'Default Gradle'  // Jenkins에서 설정한 Gradle 설치 이름
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                // Gradle을 사용하여 소스 파일을 컴파일
                sh 'gradle clean build'
            }
        }

        stage('Test') {
            steps {
                // Gradle을 사용하여 테스트를 실행
                sh 'gradle test'
            }
        }

        stage('Archive Results') {
            steps {
                // Gradle 테스트 결과를 아카이브
                archiveArtifacts artifacts: '**/build/test-results/test/*.xml', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            // Gradle 테스트 결과를 수집하고 보고서를 생성합니다.
            junit '**/build/test-results/test/*.xml'
            // 빌드된 아티팩트를 아카이브합니다.
            archiveArtifacts artifacts: '**/build/libs/*.jar', allowEmptyArchive: true
        }
        success {
            echo 'Build and Test Succeeded!'
        }
        failure {
            echo 'Build and Test Failed!'
        }
    }
}
