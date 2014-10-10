#!/bin/bash
chmod 400 conf/prod/deployment/eventstech-key.key

ssh -i conf/prod/deployment/eventstech-key.key ${SYSTEM_USERNAME}@${APPLICATION_SERVER_IP} 'bash -s' <<'ENDSSH'
    bash /home/ubuntu/tomcat/bin/shutdown.sh
    rm -rf /home/ubuntu/tomcat/webapps/eventstech
    rm -rf /home/ubuntu/tomcat/work/localhost/eventstech
    rm -f /home/ubuntu/tomcat/conf/context.xml
    rm -r /home/ubuntu/tomcat/logs/*
    rm -f /home/ubuntu/tomcat/webapps/eventstech.war
    rm -r /home/ubuntu/conf/*
    rm -rf /home/ubuntu/tools
    mkdir /home/ubuntu/tools
ENDSSH

scp -r -i conf/prod/deployment/eventstech-key.key conf/prod/properties/* ${SYSTEM_USERNAME}@${APPLICATION_SERVER_IP}:/home/ubuntu/conf
scp -r -i conf/prod/deployment/eventstech-key.key conf/prod/tomcat/context.xml ${SYSTEM_USERNAME}@${APPLICATION_SERVER_IP}:/home/ubuntu/tomcat/conf
scp -r -i conf/prod/deployment/eventstech-key.key tools/build/distributions/eventstech-tools.zip ${SYSTEM_USERNAME}@${APPLICATION_SERVER_IP}:/home/ubuntu/tools
scp -r -i conf/prod/deployment/eventstech-key.key rest-api-aggregator/build/libs/eventstech.war ${SYSTEM_USERNAME}@${APPLICATION_SERVER_IP}:/home/ubuntu/tomcat/webapps

ssh -i conf/prod/deployment/eventstech-key.key ${SYSTEM_USERNAME}@${APPLICATION_SERVER_IP} 'bash -s' <<'ENDSSH'
    unzip /home/ubuntu/tools/eventstech-tools.zip -d /home/ubuntu/tools
    rm -f /home/ubuntu/tools/eventstech-tools.zip
    bash /home/ubuntu/tomcat/bin/startup.sh
ENDSSH

chmod 666 conf/prod/deployment/eventstech-key.key
