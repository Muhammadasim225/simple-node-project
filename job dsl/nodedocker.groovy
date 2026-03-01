node("node js project v2"){
description("This project will clone from github and build and push to the dockerhub")
  scm {
        git('https://github.com/Muhammadasim225/simple-node-project.git','main') { node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Muhammadasim225')
            node / gitConfigEmail('muhammadasim4927@gmail.com')
        }
    }
    wrappers {
        nodejs('Node-JS')
        credentialsBinding {
            usernamePassword('USERNAME_DOC', 'PASSWORD_DOC','dockerhubcred')
        }
    }
     steps {
        shell('npm install')
        shell('docker login -u ${USERNAME_DOC} -p ${PASSWORD_DOC}')
         dockerBuildAndPublish {
            repositoryName('muhammadasim0333/nodejs_dummy')
            tag('${BUILD_NUMBER}')
            registryCredentials('dockerhubcred')
            forcePull(false)
            createFingerprints(false)
            skipDecorate()
        }
        shell('docker logout')
    }
}