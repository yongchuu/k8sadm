def label = "k8sadm-${UUID.randomUUID().toString().replaceAll('-', '')}".toLowerCase()

podTemplate(
	label: label,
	containers: [
		containerTemplate(name: "docker", image: "docker:latest", ttyEnabled: true, command: "cat"),
		containerTemplate(name: "kubectl", image: "bitnami/kubectl", command: "cat", ttyEnabled: true)
	],
	//volume mount
	volumes: [
		hostPathVolume(hostPath: "/var/run/docker.sock", mountPath: "/var/run/docker.sock")
	]
)
{
	node(label) {
		stage("Get Source") {
		    git branch: 'main', url: 'https://github.com/yongchuu/k8sadm.git'
        }

		def tag = "0.1.1"
		def dockerRegistry = ""
		def credential_registry = "docker-hub"
		def image = "dongjoonju/k8sadm"
		def deployment = "deployment/deploy.yaml"
		def service = "deployment/svc.yaml"
		//def ingress = props["ingress"]
		def selector_key = "app"
		def selector_val = "k8sadm"
		def namespace = "k8sadm"

		try {
			stage("Build Microservice image") {
				container("docker") {
// 					docker.withRegistry("${dockerRegistry}", "${credential_registry}") {
// 						sh "docker build -f ./deployment/Dockerfile -t ${image}:${tag} ."
// 						sh "docker push ${image}:${tag}"
// 						sh "docker tag ${image}:${tag} ${image}:latest"
// 						sh "docker push ${image}:latest"
// 					}
				}
			}

			stage( 'List pods' ) {
                container("kubectl") {
                    withKubeConfig([credentialsId: '59349bfb-b7e7-4a0b-9461-7d48a799fc29']) {
                        sh 'curl -LO "https://storage.googleapis.com/kubernetes-release/release/v1.20.5/bin/linux/amd64/kubectl"'
                        sh 'chmod u+x ./kubectl'
                        sh './kubectl get pods'
                    }
                }
            }

// 			stage( "Clean Up Existing Deployments" ) {
// 			    withKubeConfig([credentialsId: '59349bfb-b7e7-4a0b-9461-7d48a799fc29']) {
// 				    sh "kubectl delete deployments -n ${namespace} --selector=${selector_key}=${selector_val}"
// 				}
// 			}
//
// 			stage( "Deploy to Cluster" ) {
// 			    withKubeConfig([credentialsId: '59349bfb-b7e7-4a0b-9461-7d48a799fc29']) {
//                     sh "kubectl apply -n ${namespace} -f ${deployment}"
//                     sh "sleep 5"
//                     sh "kubectl apply -n ${namespace} -f ${service}"
//                 }
//                 //sh "kubectl apply -n ${namespace} -f ${ingress}"
// 			}

		} catch(e) {
			currentBuild.result = "FAILED"
			echo "$e"
		}
	}
}