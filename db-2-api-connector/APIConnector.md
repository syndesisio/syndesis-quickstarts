# Create a Connector for the TODO REST API

## Introduction
Many application have a RESTful API. Syndesis allows you to create a *custom* connector specific for a certain application. In this case we pick the TODO REST API. To start creating such a API Client Connector navigate to `Customizations > API Client Connectors` and click on the blue `Create API Connector` button

![Create API Client Connector](img/1-create-api-connector.png)
*Figure 1. Create a Custom API Client Connector*

## OpenAPI/Swagger To Connector
To create such an API Client we need to provide it with an OpenAPI/Swagger document. When running on Minishift you can copy the output of the following command

```
echo http://todo-syndesis.`minishift ip`.nip.io/swagger.json
```
and past it into the Use a URL Field
![Create API Client From URL](img/2-from-url.png)
*Figure 2. Paste into the From URL field*

![Create API Client Review](img/3-review-actions.png)
*Figure 3. Review RESTful Actions*

Then upload the todo-icon.png from the quickstart directory and change the url from `https` to `http`, this because our certificated a selfsignedm and we want to avoid issues with that.

![Create API Client Edit](img/4-edit-icon-url.png)
*Figure 4. Edit Icon and URL*

![Create API Client Connectors](img/5-api-client-connectors.png)
*Figure 5. See the new API Client Connector called `Todo App API`*

## Connector to Connection
Finally we need to create a new TODO API Connection

![Create API Client Connection](img/6-create-connection.png)
*Figure 6. Create Connection*

![Create API Client Connection](img/7-select-todo-app-api.png)
*Figure 7. Select TODO App API Connector*

![Create API Client Connection](img/8-add-creds.png)
*Figure 8. Add Credentials*

![Create API Client Connection](img/9-connection-ready.png)
*Figure 9. Connection is ready for use*

