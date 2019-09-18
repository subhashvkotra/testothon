String cron_string = BRANCH_NAME == "erp-transformation-test"
pipeline {
	agent  any

	stages {     
        stage ('Test'){
            steps {
                script {
                                     
                       sh 'mvn test -Ddevice=ALL  -Dsuite=TestNG'
                    
                }
            }
        }
    }
    post {
        always {
         	junit 'target/surefire-reports/xml/*.xml'
            echo 'One way or another, the job is finished!'
            deleteDir() /* clean up our workspace */
        }
        success {
            echo 'Hurray....Job succeeeded!!!'
        }
        unstable {
            echo 'Job is unstable :/ !!!'
        }
        failure {
            echo 'Job failed :( !!!'
        }
        changed {
            echo 'Things were different before...'
        }
    } 
}