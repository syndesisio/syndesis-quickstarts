# Syndesis Public API

## Introduction

Syndesis supports a public API for interacting with underlying resources likes Integrations and Connections from external tools such as Jenkins and Ansible. This allows building CI/CD pipelines to automatically copy integration updates from one environment to another. 
To give users direct control over when and where an Integration is exported, Syndesis uses a CI/CD mechanism to tag integrations with labels. It also internally manages tag timestamps and export timestamps to only export updated integrations when using the export API.

This quickstart includes a couple of simple integrations and a Jenkins pipeline to demonstrate how to use the public API. 

## Screencast of this Quickstart

Link to a screencast of this quickstart on Vimeo:

[![Public API Quickstart](https://i.vimeocdn.com/video/824440438_640.jpg)](https://vimeo.com/367913566)

*`<<Click to Play>>`*


## Getting Started

Create a couple of simple integrations, or import the file [sample-export.zip](sample-export.zip?raw=true). 

Follow the instructions at [Public API Endpoint](https://github.com/syndesisio/syndesis/tree/1.8.x/install#create-template-for-public-api-endpoint) and install the public OAuth proxy to enable the public API. You will also need to create a service account to access the API using it's secret token. The following commands can be used to create an account called syndesis-cd-client:

```
oc create serviceaccount syndesis-cd-client
oc policy add-role-to-user edit system:serviceaccount:syndesis:syndesis-cd-client
oc describe serviceaccount syndesis-cd-client
oc describe secret syndesis-cd-client-token-<id>
```
Where id is the token id obtained from the `describe serviceaccount` command above. 

## Create Jenkins Pipeline Job

If you don't already have a Jenkins instance in your OpenShift namespace, install Jenkins by following the instructions at [Creating the Jenkins Master](https://docs.openshift.com/container-platform/3.11/dev_guide/dev_tutorials/openshift_pipeline.html#creating-the-jenkins-master).

Create a new Jenkins pipeline job, with the following String build parameters:

* EXPORT_HOSTNAME       - Hostname of source install, should be the hostname used to install the public API proxy.
* IMPORT_HOSTNAME       - Hostname of target install, which for this demo can be the same as source hostname.
* EXPORT_TAG_NAME       - Tag name used to mark and export integrations, set to `test` as shown in the video. 
* IMPORT_TAG_NAME       - Same as the tag name above, used to import integrations in target environment. 
* EXPORT_SECRET_TOKEN   - Secret token from the service account created to access the public API. 
* IMPORT_SECRET_TOKEN   - Same secret token as above if using a single Syndesis install, or secret token from service account from target Syndesis install. 

Copy the contents of the Jenkins pipeline script from [syndesis-pipeline.groovy](syndesis-pipeline.groovy?raw=true) and paste it in the scripts contents of the pipeline job.

Follow the instructions in the demonstration video on how to mark integrations for export and using the build pipeline to export and import integrations. 

## What did we learn?

* How to install the public API OAuth proxy. 
* How to mark integrations for export using environment names/tags. 
* How to create a Jenkins pipeline that exports, imports, and deploys integrations using the public API. 
