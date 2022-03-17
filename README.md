# demo-event-notification
Demo event notification

## Prerequisite

- docker-compose
- jq
- maven

## How to start ?

``` bash
./start.sh
```

Visit http://localhost:8082

Produce one alert into `alert` topic :
``` json
{
  "name": "Alert HTV",
  "type": "HIGH",
  "ruleId": 1,
  "device": "iPhone X"
}
```

See on notification, the inner-join works.

Visit http://localhost:8080/swagger-ui.html for update rule in ktable