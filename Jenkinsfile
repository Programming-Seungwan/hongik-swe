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

                // 테스트 결과를 텍스트 파일로 변환
                script {
                    // XML 결과 파일을 읽고 텍스트 파일로 변환
                    def testResultsDir = 'build/test-results/test'
                    def textOutputFile = "${testResultsDir}/test-results.txt"

                    // Groovy script to convert XML to Text
                    sh """
                    mkdir -p ${testResultsDir}
                    echo "Test Results:" > ${textOutputFile}
                    for file in ${testResultsDir}/*.xml; do
                        echo "Parsing: \$file" >> ${textOutputFile}
                        xmllint --format \$file | grep -E '<testcase|<failure|<skipped' >> ${textOutputFile}
                    done
                    """
                }

                // 텍스트 파일을 워크스페이스의 루트로 복사
                script {
                    sh 'cp build/test-results/test/test-results.txt ${WORKSPACE}/test-results.txt'
                }
            }
        }

        stage('Archive Results') {
            steps {
                // Gradle 테스트 결과를 아카이브
                archiveArtifacts artifacts: '**/build/test-results/test/*.xml', allowEmptyArchive: true
                // 변환된 테스트 결과를 아카이브
                archiveArtifacts artifacts: '**/build/test-results/test/test-results.txt', allowEmptyArchive: true
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
