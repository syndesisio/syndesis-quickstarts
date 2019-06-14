# Kafka Broker Deployment

To run a Kafka Broker in your `my-project` you can use [strimzi](https://strimzi.io/quickstarts/okd/).
First login your minishift installation as the `cluster-admin` user, and deploy the strimzi install files:

```
oc login -u system:admin
oc apply -f https://github.com/strimzi/strimzi-kafka-operator/releases/download/0.11.4/strimzi-cluster-operator-0.11.4.yaml -n myproject
# Apply the `Kafka` Cluster CR file
oc apply -f https://raw.githubusercontent.com/strimzi/strimzi-kafka-operator/0.11.4/examples/kafka/kafka-persistent-single.yaml -n myproject
# Watch the pods come up
oc get pods -n myproject -w
```

The installation is complete, once the `my-cluster-entity-operator` is running, like:

```
my-cluster-entity-operator-6bc7f6985c-q29p5   3/3     Running   0          44s
my-cluster-kafka-0                            2/2     Running   1          91s
my-cluster-zookeeper-0                        2/2     Running   0          2m30s
strimzi-cluster-operator-78f8bf857-kpmhb      1/1     Running   0          3m10s
```


## References
[1] Provision the Apache Kafka cluster https://strimzi.io/quickstarts/okd/
