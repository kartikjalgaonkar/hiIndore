node{

  //Define all variables
  def app;
  def namespace = 'development'
 // def imageTag = "hub.docker.com/kartikjalgaonkar/${project}/${appName}:${imageVersion}.${env.BUILD_NUMBER}"
 // def feSvcName = "hi-indore"
  //Checkout Code from Git
  checkout scm
  
  //Stage 1 : Build the docker image.
  stage('Build image') {
  //  sh 'mvn clean install'
   //  app = docker.build("kartikjalgaonkar/hi-indore")
  }
  
  //Stage 2 : Push the image to docker registry
  stage('Push image to registry') {
   //   docker.withRegistry('https://registry.hub.docker.com', 'docker_credentials') {
     //       app.push("${env.BUILD_NUMBER}")
      //      app.push("latest")
//}
  }
  
  //Stage 3 : Deploy Application
  stage('Deploy Application') {
       switch (namespace) {
              //Roll out to Dev Environment
              case "development":
                  sh("kubectl get ns ${namespace} --kubeconfig=/home/yash/.kube/config || kubectl create namespace ${namespace} --kubeconfig=/home/yash/.kube/config")
                  sh("kubectl --namespace=${namespace} apply -f deployment.yml --kubeconfig=/home/yash/.kube/config")
                  sh("kubectl --namespace=${namespace} apply -f service.yml --kubeconfig=/home/yash/.kube/config")
                  sh ("kubectl --namespace=${namespace} apply -f ingress.yml --kubeconfig=/home/yash/.kube/config")
                  sh ("kubectl apply -f hi-indore-hpa.yaml --kubeconfig=/home/yash/.kube/config")
                  break
           

              
  }

}
}
