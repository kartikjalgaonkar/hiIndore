node{

  //Define all variables
  def app;
  def project = 'my-project'
  def appName = 'my-first-microservice'
  def serviceName = "${appName}-backend"  
  def imageVersion = 'development'
  def namespace = 'development'
  def imageTag = "hub.docker.com/kartikjalgaonkar/${project}/${appName}:${imageVersion}.${env.BUILD_NUMBER}"
  
  //Checkout Code from Git
  checkout scm
  
  //Stage 1 : Build the docker image.
  stage('Build image') {
    sh 'mvn clean install'
   //   sh("docker build -t ${imageTag} .")
    app=docker.build("kartikjalgaonkar/hi-indore")
  }
  
  //Stage 2 : Push the image to docker registry
  stage('Push image to registry') {
      docker.withRegistry('https://registry.hub.docker.com', 'docker_credentials') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
}
  }
  
  //Stage 3 : Deploy Application
  stage('Deploy Application') {
       switch (namespace) {
              //Roll out to Dev Environment
              case "development":
         sh "minikube start"
        // sh "kubectl run my-app --image=kartikjalgaonkar/hi-indore --port=8081"
                   // Create namespace if it doesn't exist
                  // sh "kubectl create secret docker-registry my-secret --docker-username=kartikjalgaonkar --docker-password=docker@11 --docker-email=kartik.jalgaonkar@yash.com"

               //    sh "kubectl create secret docker-registry secret --docker-username=kartikjalgaonkar --docker-password=docker@11 --docker-email=kartik.jalgaonkar@yash.com"
                 // sh ("minikube start")
               //    sh("kubectl get ns ${namespace} || kubectl create namespace ${namespace}")
           //Update the imagetag to the latest version
        //           sh("sed -i.bak 's#hub.docker.com/${project}/${appName}:${imageVersion}#${imageTag}#' ./k8s/development/*.yaml")
                   //Create or update resources
        //   sh("kubectl --namespace=${namespace} apply -f k8s/development/deployment.yaml")
         //          sh("kubectl --namespace=${namespace} apply -f k8s/development/service.yaml")
           //Grab the external Ip address of the service
            //       sh("echo http://`kubectl --namespace=${namespace} get service/${feSvcName} --output=json | jq -r '.status.loadBalancer.ingress[0].ip'` > ${feSvcName}")
                   break
           
        //Roll out to Dev Environment
              case "production":
                   // Create namespace if it doesn't exist
                   sh("kubectl get ns ${namespace} || kubectl create ns ${namespace}")
           //Update the imagetag to the latest version
                   sh("sed -i.bak 's#hub.docker.com/${project}/${appName}:${imageVersion}#${imageTag}#' ./k8s/production/*.yaml")
           //Create or update resources
                   sh("kubectl --namespace=${namespace} apply -f k8s/production/deployment.yaml")
                   sh("kubectl --namespace=${namespace} apply -f k8s/production/service.yaml")
           //Grab the external Ip address of the service
                   sh("echo http://`kubectl --namespace=${namespace} get service/${feSvcName} --output=json | jq -r '.status.loadBalancer.ingress[0].ip'` > ${feSvcName}")
                   break
       
              default:
                   sh("kubectl get ns ${namespace} || kubectl create ns ${namespace}")
                   sh("sed -i.bak 's#hub.docker.com/${project}/${appName}:${imageVersion}#${imageTag}#' ./k8s/development/*.yaml")
                   sh("kubectl --namespace=${namespace} apply -f k8s/development/deployment.yaml")
                   sh("kubectl --namespace=${namespace} apply -f k8s/development/service.yaml")
                   sh("echo http://`kubectl --namespace=${namespace} get service/${feSvcName} --output=json | jq -r '.status.loadBalancer.ingress[0].ip'` > ${feSvcName}")
                   break
  }

}
}
