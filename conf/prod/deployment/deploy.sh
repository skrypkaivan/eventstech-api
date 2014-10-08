#!/bin/bash
chmod 400 conf/prod/deployment/eventstech-key.key

ssh -i conf/prod/deployment/eventstech-key.key ${SYSTEM_USERNAME}@${APPLICATION_SERVER_IP} 'bash -s' <<'ENDSSH'
    bash ${APPLICATION_HOME}/tomcat/bin/shutdown.sh
    rm -rf ${APPLICATION_HOME}/tomcat/webapps/eventstech
    rm -rf ${APPLICATION_HOME}/tomcat/work/localhost/eventstech
    rm ${APPLICATION_HOME}/tomcat/conf/context.xml
    rm -r ${APPLICATION_HOME}/tomcat/logs/*
    rm -f ${APPLICATION_HOME}/tomcat/webapps/eventstech.war
    rm -r ${APPLICATION_HOME}/conf/*
    rm -rf ${APPLICATION_HOME}/tools
    mkdir ${APPLICATION_HOME}/tools
ENDSSH

scp -r -i conf/prod/deployment/eventstech-key.pem conf/prod/properties/* ${SYSTEM_USERNAME}@${APPLICATION_SERVER_IP}:${APPLICATION_HOME}/conf
scp -r -i conf/prod/deployment/eventstech-key.pem conf/prod/tomcat/context.xml ${SYSTEM_USERNAME}@${APPLICATION_SERVER_IP}:${APPLICATION_HOME}/tomcat/conf
scp -r -i conf/prod/deployment/eventstech-key.pem tools/build/distributions/eventstech-tools.zip ${SYSTEM_USERNAME}@${APPLICATION_SERVER_IP}:${APPLICATION_HOME}/tools
scp -r -i conf/prod/deployment/eventstech-key.pem rest-api-aggregator/build/libs/eventstech.war ${SYSTEM_USERNAME}@${APPLICATION_SERVER_IP}:${APPLICATION_HOME}/tomcat/webapps

ssh -i conf/prod/deployment/eventstech-key.key ${SYSTEM_USERNAME}@${APPLICATION_SERVER_IP} 'bash -s' <<'ENDSSH'
    unzip ${APPLICATION_HOME}/tools/eventstech-tools.zip
    rm ${APPLICATION_HOME}/tooks/eventstech-tools.zip
    bash ${APPLICATION_HOME}/tomcat/bin/startup.sh
ENDSSH

chmod 666 conf/prod/deployment/eventstech-key.key
