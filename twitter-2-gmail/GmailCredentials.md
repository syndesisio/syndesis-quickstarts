# Obtaining Credentials to use the GMail API
The absolute first step is to have a gmail account. I don't think I need to help you with that but after that things get a bit more complicated. 

## Syndesis GMail Settings
Syndesis is using the [OAuth](https://en.wikipedia.org/wiki/OAuth) Standard to connect to the GMail API. On the `Settings` section on Syndesis it requires you to enter a Consumer Secret and Consumer Key (see Figure 1)

![Syndesis GMail Settings](img/0_syndesis-settings-gmail.png)
*Figure 1. Syndesis GMail Settings*

## New Google API Project
In order to obtain access to the GMail API you first need create a new project at [https://console.developers.google.com].


![Google API](img/1_selectproject.png)
*Figure 2. Select Project*


![Google API](img/2_createproject.png)
*Figure 3. Create Project `SyndesisQS`*

## Enable GMail API Service

![API Service](img/3_APIServices.png)
*Figure 4. API Services*

![API Service](img/4_enable-api.png)
*Figure 5. Enable GMail API*

## Create Credentials

![Gmail Credentials](img/5_createcredentials.png)
*Figure 6. Create Credentials*

![Gmail Credentials](img/6_createcredentials2.png)
*Figure 7. Click 'Configure consent screen'*

![Gmail Credentials](img/7_create-oauth-settings.png)
*Figure 8. Configure consent screen*

Add the Application Name and Support Email, and make sure to add the `nip.io` as an Authorized Domain!

![Gmail Credentials](img/8_web-app.png)
*Figure 9. Configure Web App and Callback URL*

![Gmail Credentials](img/9_client-key-id.png)
*Figure 10. Copy from Client Credentials Popup*

![Gmail Credentials](img/10_copy-paste.png)
*Figure 11. Paste Client Credentials into Syndesis*

You can now create a GMail Connection and these credentials will be used.


