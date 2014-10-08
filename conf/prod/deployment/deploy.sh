#!/bin/bash
APPLICATION_SERVER_IP=$1
chmod 400 conf/prod/deployment/eventstech-key.key

ssh -i conf/prod/deployment/eventstech-key.key ubuntu@${APPLICATION_SERVER_IP} 'bash -s' <<'ENDSSH'
    bash /home/ubuntu/tomcat/bin/shutdown.sh
    rm -rf /home/ubuntu/tomcat/webapps/eventstech
    rm -rf /home/ubuntu/tomcat/work/localhost/eventstech
    rm /home/ubuntu/tomcat/conf/context.xml
    rm -r /home/ubuntu/tomcat/logs/*
    rm -f /home/ubuntu/tomcat/webapps/eventstech.war
    rm -r /home/ubuntu/conf/*
    rm -rf /home/ubuntu/tools
    mkdir /home/ubuntu/tools
ENDSSH

scp -r -i conf/prod/deployment/eventstech-key.pem conf/prod/properties/* ubuntu@${APPLICATION_SERVER_IP}:/home/ubuntu/conf
scp -r -i conf/prod/deployment/eventstech-key.pem conf/prod/tomcat/context.xml ubuntu@${APPLICATION_SERVER_IP}:/home/ubuntu/tomcat/conf
scp -r -i conf/prod/deployment/eventstech-key.pem tools/build/distributions/eventstech-tools.zip ubuntu@${APPLICATION_SERVER_IP}:/home/ubuntu/tools
scp -r -i conf/prod/deployment/eventstech-key.pem rest-api-aggregator/build/libs/eventstech.war ubuntu@${APPLICATION_SERVER_IP}:/home/ubuntu/tomcat/webapps

ssh -i conf/prod/deployment/eventstech-key.key ubuntu@${APPLICATION_SERVER_IP} 'bash -s' <<'ENDSSH'
    unzip /home/ubuntu/tools/eventstech-tools.zip
    rm /home/ubuntu/tooks/eventstech-tools.zip
    bash /home/ubuntu/tomcat/bin/startup.sh
ENDSSH

chmod 666 conf/prod/deployment/eventstech-key.key
