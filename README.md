# Syndesis Quickstarts

<img align="left" width="150" height="150" src="img//syndesis.png">Syndesis makes Camel super easy to use. It packages Camel components as `Connectors` with a described dataInput and dataOutput shape. This is makes building an integration as easy as clicking Duplos together. Each of our QuickStarts have a little video to see help you build your own integration, plus there is an export file you can use to import to load up the entire scenario. Note that you might have to update things like credentials. 

Have fun playing!

## 1. Introduction to Syndesis

Syndesis is a cloud native application targeted at systems integration. It leverages Apache Camel, and adds a UI layer that helps developers, and more importantly the more technically business analysist, to create integrations without writing any code. Integrations are deployed as Spring Boot applications inside a container onto OpenShift. 

The easiest way to try Syndesis is to use a trial of the product called Fuse Online. You can sign up at [fuse-online](https://www.redhat.com/en/technologies/jboss-middleware/fuse-online), and skip right to the [QuickStart](#lets-run-some-quickstarts) section.
However if you’re like me and you want to be able try things out by running it locally, you probably want to run the Syndesis project bits. This is where this blog is about. I will take you through to the few straightforward steps to
  * [Install a mini cloud ‘MiniShift’ on your laptop](README.md#2-install-a-local-mini-cloud-called-minishift)
  * [Install Syndesis on Minishift](README.md#3-install-syndesis)
  * [Run some QuickStarts](README.md#4-lets-run-some-quickstarts)

## 2. Install a local mini cloud called MiniShift
In order to have a small cloud running on your laptop you need to install MiniShift. MiniShift is a one node version installation of [OpenShift OKD](https://docs.okd.io/latest/welcome/index.html) running in a virtual machine. MiniShift supports a few virtualization technogies such as KVM, xHyve, Hyper-V and VirtualBox and can be installed on different operating systems:

  * [Linux](https://docs.okd.io/latest/minishift/getting-started/setting-up-virtualization-environment.html#for-linux)
  * [Windows](https://docs.okd.io/latest/minishift/getting-started/setting-up-virtualization-environment.html#for-windows)
  * [MacOS](https://docs.okd.io/latest/minishift/getting-started/setting-up-virtualization-environment.html#for-macos)

If you are on MacOS and you want to use Hyperkit, and are ok with [homebrew](https://brew.sh) then you can follow the following instructions
```
brew install hyperkit
brew install docker-machine-driver-hyperkit
sudo chown root:wheel /usr/local/bin/docker-machine-driver-hyperkit
sudo chmod u+s /usr/local/bin/docker-machine-driver-hyperkit
```
To install [minishift](https://www.okd.io/minishift/) itself use
```
brew cask install minishift
minishift config set vm-driver hyperkit
```
or simply download the minishift binary from [github](https://github.com/minishift/minishift/releases). Hop onto [Gitter](https://gitter.im/syndesisio/community) to chat with us if you need help with any of this!

## 3. Install Syndesis
We will now download the docker images from our official dockerhub repository and start them on your minishift installation using
```
bash <(curl -sL https://syndes.is/start) --vm-driver hyperkit
```
Note that on Windows you will need something like [Cygwin](https://www.cygwin.com) or [KornShell](http://www.kornshell.com) for this to work. The installation process itself can take a few minutes depending on your download speed, but this is all there is to it. At the end of the installation it will open your default browser to the syndesis address. By default you can log in using any username/pw combination. For more details see [syndesis.io](https://syndesis.io/quickstart/). 

## 4. Let's run some QuickStarts

Syndesis lets you build application without writing any code. Perhaps the easiest way to learn about Syndesis is to simply run to quickstarts. The quickstarts are zero code, so instead we offer Syndesis integration `exports` from scenarios we built for you. You can try them out by `importing` an export as an integration into Syndesis. After the import you sometimes may need to re-enter some connection information like passwords. The accompanying README should detail this. We have ordered the Quickstarts loosely ordered by simplicity, so it is recommended to start from the top of this list. 

### 4.1 Prerequisite
Some of the Quickstarts below use a sample database. If you don't see this DB or the sampleDB connection, then edit the syndesis application:
```
oc edit syndesis
```
and add `demoData: true`. Then restart (delete) the db and server pods.

### 4.2 Integrations
  * [Hello World](hello-world) - log 'Hello World!'
  * [DB 2 DB](db-2-db) - read from a database table, insert in another database table
  * [FHIR 2 FHIR](fhir) - breath 'fire' from a FHIR REST service and update the same record
  * [DB 2 API Connector](db-2-api-connector) - create a Custom RESTful Connector to the Todo REST API
  * [DynamoDB](aws-dynamodb) - connect to an AWS DynamoDB database
  * [Twitter 2 DB](twitter-2-db) - search twitter and push matches to a database
  * [Twitter 2 GMail](twitter-2-gmail) - search twitter and send out matches in the body of an email
  * [Kafka 2 DB](kafka-2-db) - setup a Kafka Broker, then listen to a Kafka Topic and write to a database
  * [Jira 2 Twitter](jira-2-twitter) - retrieve Jira comments and send them as Twitter direct messages
  * [DB 2 GoogleSheets](db-2-googlesheets) - read from a database, insert into googlesheet.
  * [MongoDB](mongodb) - read and write to a MongoDB NoSQL database. You may want to do [API Provider](api-provider) before doing this one.
  
### 4.3 Flow Manipulation
  * [Conditional Flows](conditional-flows) - learn about splitting messages, primary flows and subflows

### 4.3 API
  * [WebHook](webhook-2-db) - create and expose a webhook to invoke an integration with some data
  * [API Provider](api-provider) - create and expose a REST interface to (remotely) interact with integration flows
  * [API Provider FTP](api-provider-with-ftp) - create and expose a REST interface to interact with FTP server
  * [Syndesis API](public-api) - Syndesis has a public API you can use to integrate Syndesis with your systems
  
  
  ![Integration Quickstarts](img/quickstarts.png)
  
### 4.4 Acquire OAuth Credentials for the Syndesis Setting Section
  * [Twitter](twitter-2-db/TwitterCredentials.md)
  * [GMail](twitter-2-gmail/GmailCredentials.md)
  * [GoogleSheets API](db-2-googlesheets/GoogleSheetsCredentials.md)
  * [Jira](jira-2-twitter/JiraConnection.md)
  
### 4.5 Advanced Configuration
  * [Kafka Autodiscovery](kafka-autodiscovery) - How to configure your environment to auto discover kafka brokers
  
  
## 5. Interesting 3rd Party Projects/Use Cases using Syndesis

Here is a list of projects using Syndesis/FuseOnline:

  * [Chuck Norris Streams](https://github.com/lbroudoux/chuck-norris-streams) Kafka, Debezium and Fuse demonstration based on Chuck Norris movies!
  * Red Hat Fuse Demos [Database to Salesforce](https://www.redhat.com/en/about/videos/fuse-online-demo), [Salesforce Ordering](https://www.redhat.com/en/about/videos/salesforce-ordering), [Data Backup to AWS S3](https://youtu.be/YXaV5pDWhy8), [AMQP and AMQ Integration Demo](https://youtu.be/5Aw92Mprumg)
  
  
(if you want a link to your project added, just open a PR)


## 6. Become part of the Syndesis community!
<img align="left" width="200" height="200" src="img//hey-girl-read-the-quick-start-guide-and-call-me-back-k.jpg">

We are an Open Source, Apache 2 Licensed project. You are free to use our project but to ensure the health of our project we need your feedback. Become a part of [our community](https://syndesis.io/community/). Did I mention we love hearing your experiences? Please log any issues you find or even better contribute a patch back for it. Good luck, and please give us feedback! We love you!

[@Syndesisio](https://twitter.com/syndesisio)
