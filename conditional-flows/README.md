# API Provider with FTP

## Introduction
In this scenario we demonstrate how to use conditional flows.

## Screencast of this Quickstart

Link to a screencast of this quickstart on our youtube channel:

[![Conditional Flows Quickstart](https://img.youtube.com/vi/QltvakZ1hGo/mq2.jpg)](https://youtu.be/QltvakZ1hGo)

*`<<Click to Play>>`*


## Getting Started

Here we describe using the export so you can get a feel for how things work quickly. If you want to set up your own flows you can follow the video tutorial from above. In the Syndesis UI navigate to `Integrations` and click on the `Import` button in the right top corner. Now you can select the [conditional-export.zip](conditional-export.zip?raw=true) file and start the import. 

You can start create a db-2-log flow, using `Select * from todo`. Next, add a splitter so that each record turns into message as conditional flows work on single messages. After the splitter is inserted you can now add a `Condition Flow`.

![Adding Splitter and Conditional Flow](img/fig1-split-and-add-conditions.png)
*Figure 1. Adding Splitter and Conditional Flows*

Create a condition for `task contains dog` and `task contains cat` as well as a default flow.

![Main flow with conditional subflows](img/fig2-mainflow.png)
*Figure 2. Main Flow with subflows*

![Sub flow](img/fig3-subflow.png)
*Figure 3. Subflow*


## Running the example

Log into the SampleDB using

```
oc rsh syndesis-db-<name>
psql -Usampledb
insert into todo values (1, "Walk the dog!",0);
insert into todo values (2, "Feed the cat!",0);
insert into todo values (3, "Watch Lucifer Morningstar on Netflix!");
```
Now in the Activity view we should now see messages being split and processed. You should see the printout `DOG FLOW` for tasks containing `dog` and `CAT FLOW` for tasks containing `cat`. 

![Activity Logging](img/fig4-activity-logging.png)
*Figure 4. Activity Logging*

## What did we learn?
* We learned to use the Splitter
* We learned to use Conditional Flows
