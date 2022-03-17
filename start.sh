#!/bin/bash

docker-compose up -d

echo "Waiting schema-registry is up ..."

return=$(curl -s http://localhost:8081/subjects | jq '. | length')
while [ "$return" == "" ]; do
  sleep 1
  return=$(curl -s http://localhost:8081/subjects | jq '. | length')
done

echo "schema-registry is up ..."

mvn compile

curl -X PUT -H "Content-Type: application/vnd.schemaregistry.v1+json" --data '{"compatibility": "BACKWARD"}' http://localhost:8081/config/alert-value
curl -X PUT -H "Content-Type: application/vnd.schemaregistry.v1+json" --data '{"compatibility": "BACKWARD"}' http://localhost:8081/config/event-notification-streams-alert-repartition-value
curl -X PUT -H "Content-Type: application/vnd.schemaregistry.v1+json" --data '{"compatibility": "BACKWARD"}' http://localhost:8081/config/rule-value
curl -X PUT -H "Content-Type: application/vnd.schemaregistry.v1+json" --data '{"compatibility": "BACKWARD"}' http://localhost:8081/config/notification-value
curl -X PUT -H "Content-Type: application/vnd.schemaregistry.v1+json" --data '{"compatibility": "BACKWARD"}' http://localhost:8081/config/event-notification-streams-rule-store-changelog-value

mvn exec:java