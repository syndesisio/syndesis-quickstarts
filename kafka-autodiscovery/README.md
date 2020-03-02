# Kafka Broker AutoDiscovery

## Introduction

In this scenario we will autodiscover kafka brokers to create connections. 

You can find a screencast of this quickstart on youtube:

[![Kafka Broker AutoDiscovery](https://i.ytimg.com/vi/kxqjeXcfMY0/maxresdefault.jpg)](https://www.youtube.com/watch?v=kxqjeXcfMY0)

*`<<Click to Play>>`*

## Steps

From version 1.9.0 on, the Kafka connector allow the autodiscovery of brokers. This is achieved by giving privileges to your oc user to see resources on your cluster. Different configurations of privileges of your oc user will let you discover different subsets of brokers.

### Available Kafka Brokers

First, make sure you have installed some Kafka broker on your cluster. 

For example, if we want to install Strimzi on our minishift, we can do so by using the following commands, based on [the Strimzi tutorial](https://strimzi.io/quickstarts/okd/)

* Login to your minishift/openshift instance

```
oc login -u system:admin
```

NOTE: Make sure that you run this command with proper user credentials

* Apply Strimzi installation file

```
oc apply -f https://github.com/strimzi/strimzi-kafka-operator/releases/download/0.15.0/strimzi-cluster-operator-0.15.0.yaml -n myproject
```

* Provision the Apache Kafka cluster

```
oc apply -f https://raw.githubusercontent.com/strimzi/strimzi-kafka-operator/0.15.0/examples/kafka/kafka-persistent-single.yaml -n myproject 
oc wait kafka/my-cluster --for=condition=Ready --timeout=300s -n myproject
```

* Now you can create some producers

```
oc -n myproject run kafka-producer -ti --image=strimzi/kafka:0.15.0-kafka-2.3.1 --rm=true --restart=Never -- bin/kafka-console-producer.sh --broker-list my-cluster-kafka-bootstrap:9092 --topic my-topic
```

### Give privileges to your oc user

Depending on your environment, this configuration may be different. On our previous example, with a strimzi cluster on minishift, you can do the following.

* Create a cluster role to see kafka/strimzi resources

```
oc create clusterrole kafkas.kafka.strimzi.io-view --verb=get,list --resource=kafkas --resource=crd
```

* Add this cluster role to your oc user

```
oc adm policy add-cluster-role-to-user kafkas.kafka.strimzi.io-view -z syndesis-server
```

The username may be different on your usecase. For example, on minishift the user is called syndesis-default. Make sure your configuration fits your installation.

Your Syndesis connector should be able to auto-discover kafka brokers now.

## What did we learn?
* We learned how to configure our installation to autodiscover kafka brokers
