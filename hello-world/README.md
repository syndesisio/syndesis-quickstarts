# Hello World!

Introduction
This scenario send a Hello World message to the Log Connector. 

## Screencast of this Quickstart

Link to a screencast of this quickstart on our youtube channel:

[![Hello World Quickstart](https://www.youtube.com/upload_thumbnail?v=Z81TyyvBxy0&t=2&ts=1539779298367)](https://youtu.be/Z81TyyvBxy0)


## Getting Started

You can follow with the video above to build the integration or you can import the HelloWorld-export.zip

The start action is a Simple Timer that fires every minute. The (empty) message is then send to a logger using the LogConnector. Unfortunately the current LogConnector does not allow adding a custom message, the way our Step Logger does.

