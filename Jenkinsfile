pipeline {
     agent any
     stages {
          stage("Compile") {
               steps {
                    sh "./gradlew compileJava"
               }
          }
          stage("Unit test") {
               steps {
                    sh "./gradlew test"
               }
          }

          stage("Code coverage") {
               steps {
                    sh "./gradlew jacocoTestReport"
                    publishHTML (target: [
                         reportDir: 'build/reports/jacoco/test/html',
                         reportFiles: 'index.html',
                         reportName: "JaCoCo Report"
                    ])
                    sh "./gradlew jacocoTestCoverageVerification"
               }
          }

          stage("Static code analysis") {
               steps {
                    sh "./gradlew checkstyleMain"
                    publishHTML (target: [
                         reportDir: 'build/reports/checkstyle/',
                         reportFiles: 'main.html',
                         reportName: "Checkstyle Report"
                    ])
               }
          }

          stage("Package") {
               steps {
                    sh "./gradlew build"
               }
          }

          stage("Docker build") {
               steps {
                    sh "docker build -t localhost:5000/calculator ."
               }
          }

          stage("Docker push") {
               steps {
                    sh "docker login localhost:5000 -u='sean' -p='stockmann2'"
                    sh "docker push localhost:5000/calculator"
               }
          }
          stage("Deploy to staging") {
               steps {
                    sh "docker run -d --rm -p 8765:7070 --name calculator localhost:5000/calculator"
               }
          }

          stage("Acceptance test") {
               steps {
                    sleep 60
                    sh 'chmod +x ./acceptance_test.sh'
                    sh "./acceptance_test.sh"
               }
          }

     }
}

post {
     always {
         sh "docker stop calculator"
             }
      }
