#!/bin/bash
APPLICATION_SERVER_IP=$1
chmod 400 conf/prod/deployment/eventstech-key.pem

ssh -i conf/prod/deployment//eventstech-key.pem ubuntu@${APPLICATION_SERVER_IP} 'bash -s' <<'ENDSSH'
    bash /home/ubuntu/tomcat/bin/shutdown.sh
    rm -rf /home/ubuntu/tomcat/webapps/eventstech
    rm -rf /home/ubuntu/tomcat/work/localhost/eventstech
    rm /home/ubuntu/tomcat/conf/context.xml
    rm -r /home/ubuntu/tomcat/logs/*
    rm -f /home/ubuntu/tomcat/webapps/eventstech.war
    rm -r /home/ubuntu/conf/*
ENDSSH

scp -r -i conf/prod/deployment/eventstech-key.pem ubuntu@${APPLICATION_SERVER_IP} conf/prod/properties/* ubuntu@${APPLICATION_SERVER_IP}:/home/ubuntu/conf
scp -r -i conf/prod/deployment/eventstech-key.pem ubuntu@${APPLICATION_SERVER_IP} conf/prod/tomcat/context.xml ubuntu@${APPLICATION_SERVER_IP}:/home/ubuntu/tomcat/conf
scp -r -i conf/prod/deployment/eventstech-key.pem ubuntu@${APPLICATION_SERVER_IP} rest-api-aggregator/build/libs/eventstech.war ubuntu@${APPLICATION_SERVER_IP}:/home/ubuntu/tomcat/webapps

ssh -i conf/prod/deployment//eventstech-key.pem ubuntu@${APPLICATION_SERVER_IP} 'bash -s' <<'ENDSSH'
    bash /home/ubuntu/tomcat/bin/startup.sh
ENDSSH

chmod 666 conf/prod/deployment/eventstech-key.pem
