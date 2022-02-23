## backbase-jenkins

This repository was created in order to have a fully operational CI/CD running pipeline on a Linux-based machine.

It's mandatory to have installed the following tools:
- Docker
- Kubectl
- Kind
- Helm


Once everything is installed and the repo is cloned, the steps are:

```sh
kind create cluster --config kind-cluster-config.yaml
```

```sh
helm install -f helm-values.yaml jenkins/jenkins --generate-name
```

Then, the helm chart installation is going to show several commands with which it'll be possible to connect to Jenkins server. The most important ones are:

```sh
kubectl exec --namespace default -it svc/[svc_name] -c jenkins -- /bin/cat /run/secrets/chart-admin-password && echo
```

```sh
kubectl --namespace default port-forward svc/[svc_name] 8080:8080
```

Thus, it's going to be possible to access Jenkins server through out local web browser from http://localhost:8080 with user "**admin**" and the password obtained in the first **kubectl** command.


After got into Jenkins server UI, it can be seen the created pipeline from the groovy script that runs in the Dockerfile-controller.

Unfortunately, it wasn't able to make docker command works into the Jenkins agent.
