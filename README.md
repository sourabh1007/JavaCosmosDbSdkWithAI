## Sample Program to Get Cosmos Db Events in AppInsights

### How to Run this Program:
Add java agent in run configuration as mentioned in https://docs.microsoft.com/en-us/azure/azure-monitor/app/java-in-process-agent#enable-azure-monitor-application-insights

You should see something like this in your logs/console
![image](https://user-images.githubusercontent.com/6362382/159881049-6653b646-44a9-4b99-933e-88097ad7123c.png)

It means jar is configured correctly.

### Output

You should see all types of logs coming in AppInsights

![image](https://user-images.githubusercontent.com/6362382/159881466-81a929b9-9496-49c6-9c62-0ee80bfd8d0e.png)

#### Logs will look like this: 
![image](https://user-images.githubusercontent.com/6362382/159883376-1e1d3265-9bb2-4fd4-84bc-49264af85d15.png)

![image](https://user-images.githubusercontent.com/6362382/159881893-1c7f116f-db36-4335-8f8f-c2b6e60bca00.png)

#### HTTP calls

##### Successful Call

![image](https://user-images.githubusercontent.com/6362382/159885182-f09a9b99-698b-4f13-a57e-fe470799a664.png)

Timeline View of same call

![image](https://user-images.githubusercontent.com/6362382/159885572-205cf5e8-a09b-4543-9c6d-3448ba1f7bbd.png)

##### Failed Call

![image](https://user-images.githubusercontent.com/6362382/159884939-8f796502-1a9b-46a8-8546-92a89e604b9c.png)

#### Create Item Call

We are not capturing any attribute at cosmosdb call level

![image](https://user-images.githubusercontent.com/6362382/159886392-c8a5ccf2-4020-4639-8944-c6e03e38e676.png)

In timeline format, we can see different things (happened during a call) with their value
![image](https://user-images.githubusercontent.com/6362382/159886855-9a183177-b9b3-49ab-b8fa-c752105a008d.png)

#### Query Call

Looks like Query call event is there but there is not attribute for that.

![image](https://user-images.githubusercontent.com/6362382/159887325-4eba4da4-292e-47f6-882c-aa671fddb299.png)


