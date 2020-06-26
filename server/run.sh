#!/bin/bash
mvn package
cd ../jetty

export APP_ENV=development

if [ "$DOMAIN" = "" ] 
then 
  export DOMAIN=http://localhost:8080
fi

mvn exec:java -Dexec.args="../server/target/site.war"
