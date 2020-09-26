node('jnlp') {

    // Project name will be passed in as a parameter
    def project = "${GCP_PROJECT_NAME}"
    def mvnHome
    def appName = 'monitrng'

    // BUILD_DATE_TIME defined as a build parameter in Jenkins
    def imageTag = "eu.gcr.io/${project}/${appName}:${BUILD_DATE_TIME}"

    // Mark the code checkout 'stage'....
    stage 'Checkout'

    // Checkout the code
    checkout scm

    // Mark the code build 'stage'....
    stage 'Compile'

    //testing mvn tool install 

     mvnHome = tool 'mvn'
   
    // Run the maven build
    //sh "mvn clean compile"
      sh "'${mvnHome}/bin/mvn' clean compile"
       
       
    // Test and Package
    stage 'Test and Package'
    //sh "mvn package"
    
    sh "'${mvnHome}/bin/mvn' package" 
  
    stage 'Bake Docker Image'
    sh("docker build -t ${imageTag}  monitoring-backend/application")

    stage 'Push images to GCR'
    sh("gcloud config set project ${project}")
    //sh("gcloud docker push ${imageTag}")
    sh("gcloud docker -- push ${imageTag}")

    stage 'Deploy latest version'
    sh("sed -i.bak 's#eu.gcr.io/GCP_PROJECT/APP_NAME:1.0.0#${imageTag}#' ./k8s/deployments/app-deployment.yaml")
    sh("kubectl apply -f k8s/deployments/app-deployment.yaml")

}
