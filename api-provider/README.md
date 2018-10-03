# API Provider

Introduction
In this scenario we start with a OpenAPI/Swagger file `task-api.json` containing an API definition to the TODO Task Management example. We will demonstrate how to create a REST API that can invoke your integration flows. Note that at the moment the example integrations use the SQL connector only. We will add other connectors to these flows soon. Adding a REST interface to your integrations allows you to integrate your integrations, so that they can be invokated on demand.


## Screencast of this Quickstart

Link to a screencast of this quickstart on our youtube channel: https://youtu.be/RAa1qy3WnWQ


## Getting Started

You can start by using the API Provider connector and selecting the task-api.json to implement your own flows, or you can start using the export. Here we describe using the export so you can get a feel for how things work quickly. In the Syndesis UI navigate to `Integrations` and click on the `Import` button in the right top corner. Now you can select the `TaskAPI-export.zip` file and start the import. On a succesful deployment, go to edit this integration. You will see that this integration contains three flows:

  1. Create Task          POST /todo
  2. Get Task by ID.      GET /todo/{id}
  3. Delete Task for ID   DELETE /todo/{id}
  
  
Navigate back to the Integration Detail screen and click to `Start` (or `Deploy`) this integration. The deploy process will take a few minutes, but at the tail end of it it will show the URL at which it is live, the `external URL` which should be something like 

https://i-task-management-integration-myproject.192.168.42.72.nip.io

That's it, your integration is now live! Let's create an environmental parameter with the external URL using

export externalURL="https://i-task-management-integration-myproject.192.168.42.72.nip.io"

Make sure to use the externalURL for your integration. Now we are ready to play with the Task API:

### 1. Create Task

```
curl -k --header "Content-Type: application/json" --request POST --data '{ "task":"my new task!"}' $externalURL/api/todo

{"id":1,"task":"my new task!","completed":false}
```


### 2. Get Task by ID

```
curl -k $externalURL/api/todo/1 

{"id":1,"task":"my new task!","completed":false}
```
  
### 3. Delete Task for ID

```
curl -k -X DELETE $externalURL/api/todo/1
```

## Extra Credit

You can check what's going on using the Todo app using with your update IP address

https://todo-syndesis.192.168.42.72.nip.io/

and you can login to the DB pod using

```
oc get pods
oc rsh syndesis-db-1-c84cz 
sh-4.2$ psql -Usampledb
sampledb=> select * from todo;
```
