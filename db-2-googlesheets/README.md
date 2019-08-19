# Twitter to GMail using a template

In this quickstart we will demonstrate setting up an integration between a Database and GoogleSheets. There is one prerequisite which is that you set up your credentials for google sheets.
  * Please follow [Google API Creds](GoogleSheetsCredentials.md). 

## Screencast of this Quickstart

Link to a screencast of this quickstart on our youtube channel:

[![GoogleSheets Quickstart](https://img.youtube.com/vi/DN5dfDP7Tkw/0.jpg)](https://youtu.be/DN5dfDP7Tkw)


## Getting Started

First we need to set up a `Connection` using the GoogleSheets `Connector`. For that you need to go to connections and click the `Create Connection` button. 

![Create Connection](img/3_newconnection.png)
*Figure 1. Create Connection*

![Select Connector](img/4_connector.png)
*Figure 2. Configure Connection*

![Select Google Account](img/5_connect.png)
*Figure 3. Google OAuth Authorization*

![Name Connection](img/6_save.png)
*Figure 4. Name and Save the Connection*


## Setting up the Integration

You can follow with the video above to build the integration or you can import the [Twitter2Gmail-export.zip](Twitter2Gmail-export.zip?raw=true), or you can create the a brand new integration.

The Start is a Twitter connecton that executes a search every 5 seconds for 

```
syndesis
```
also set a checkmark at `Ignore tweets previously found`.

![Configure Twitter Start Connection](img/a_twitter.png)
*Figure 6. Twitter Start Connection*

![Configure Gmail Finish Connection](img/b_gmail.png)
*Figure 7. Gmail Finish Connection*

add a Template Step between Start and Finish and import the `gmail.tmpl` template file.

![Add Template Step](img/c_template.png)
*Figure 8. Add Template Step*

then insert two datamapper steps

![Datamapper Step 1](img/e_twitter2template.png)
*Figure 9. Add Twitter to Template Datamapper Step*

```
CreatedAt -> body.time
text -> body.text
user/screenname -> body.name
```

![Datamapper Step 2](img/d_template2email.png)
*Figure 10. Add Template to GMail Datamapper Step*

```
message -> text
```

You are now ready to deploy the integration, and you should start receiving emails at the address you configured in the Finish Connection.

## What did we learn?

* We learned how to use OAuth Credentials to create a Twitter Connection.
* We learned how to use OAuth Credentials to create a GMail Connection.
* We learned how to use a Twitter Search.
* We learned how to use a Template Step.
* We learned how to use a GMail Connection to send out emails.
* We learned to use the datamapper to map data from the Twitter search response into the Template Step.
* We learned to use the datamapper to map data from the Template Step to an email body.


