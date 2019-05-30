def exportHost = params.EXPORT_HOSTNAME
def importHost = params.IMPORT_HOSTNAME
def exportTagName = params.EXPORT_TAG_NAME
def importTagName = params.IMPORT_TAG_NAME
def exportToken = params.EXPORT_SECRET_TOKEN
def importToken = params.IMPORT_SECRET_TOKEN
def doImport = false
def exportFile = ''
def importResponseFile = ''

pipeline {

    agent any

    stages {

        stage('Prepare') {
            steps {
                script {
                    // create working directory
                    workDir = "work-${env.BUILD_NUMBER}"                
                    touch "${workDir}/.keep"
                }
                sh "mkdir -p ${workDir}"
                echo "Created working directory ${workDir}"
                script {
                    exportFile = "${workDir}/export.zip"
                    importResponseFile = "${workDir}/import_response.json"
                }
            }
        }

        stage('Export') {
            steps {
                // Export integrations from source host
                // call GET from https://${importHost}/api/v1/public/integrations/${tagName}/export.zip
                sh "curl -v -k -L -H 'Content-Type: application/json' -H 'SYNDESIS-XSRF-TOKEN: awesome' -H 'Authorization: Bearer ${exportToken}' https://${exportHost}/api/v1/public/integrations/${exportTagName}/export.zip -o ${exportFile}"
                script {
                    def exportZips = findFiles(glob: exportFile)
                    if (exportZips != null && exportZips.length == 1) {
                        echo "Exported integrations from ${exportHost} with tag ${exportTagName}"
                    } else {
                        echo "No integrations to export from ${exportHost} with tag ${exportTagName}"
                    }
                }
            }
        }
        
        stage('Confirm') {
            steps {
                // Confirm file exported and prompt to import
                script {
                    if (fileExists(exportFile)) {
                        input message: "Press Continue to import integrations in to ${importHost}", ok: 'Continue'
                        doImport = true
                    }
                }
            }
        }
        
        stage('Import') {
            steps {
                script {
                    if (doImport) {
                        // Import integration into target host
                        // call POST to https://${exportHost}/api/v1/public/integrations/
                        sh "curl -v -k -L -H 'Content-Type: multipart/form-data' -H 'SYNDESIS-XSRF-TOKEN: awesome' -H 'Authorization: Bearer ${importToken}' https://${importHost}/api/v1/public/integrations -F data=@${exportFile} -F environment=${importTagName} -o ${importResponseFile}"
                        echo "Imported integrations into ${importHost} with tag ${importTagName}"
                    } else {
                        echo 'No integrations to import!'
                    }
                }
            }
        }
        
        stage('Deploy') {
            steps {
                script {
                    if (fileExists(importResponseFile)) {
                        // read the response json
                        def response = readJSON file: importResponseFile
                        response.results.findAll { it.containsKey("flows") } .each { integration ->
                            // deploy the integration
                            def intId = integration.id
                            echo "Deploying integration ${integration.name} id:${intId} ..."
                            sh "curl -v -k -L -H 'Content-Type: application/json' -H 'SYNDESIS-XSRF-TOKEN: awesome' -H 'Authorization: Bearer ${exportToken}' https://${exportHost}/api/v1/public/integrations/${intId}/deployments -X POST -o ${workDir}/deploy_response_${intId}.json"
                            echo "Deployed ${intId}"
                        }
                    }
                }
            }
        }

    }

    post {
        always {
            // archive all files from the build
            archiveArtifacts "${workDir}/**"
        }
        
        cleanup {
            // delete work directory
            dir(workDir) {
                deleteDir()
            }
        }
    }
}