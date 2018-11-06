# Hello World!

## Introduction

This scenario sends a Hello World message to the Log Connector. 

## Screencast of this Quickstart

Link to a screencast of this quickstart on our youtube channel:

[![Hello World Quickstart](https://img.youtube.com/vi/Z81TyyvBxy0/0.jpg)](https://youtu.be/Z81TyyvBxy0)


## Getting Started

You can follow with the video above to build the integration or you can import the HelloWorld-export.zip

The start action is a Simple Timer that fires every minute. The (empty) message is then sent to a logger using the LogConnector. Unfortunately the current LogConnector does not allow adding a custom message, the way our Step Logger does.

## What did we learn?

* We learned to build and deploy our first integration with as Start Connector a Timer, 
* and as Finish Connector a Logger. 

A message is send every minute and shows up in the Integration Activity screen.
