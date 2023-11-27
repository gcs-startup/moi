FROM openjdk:17

WORKDIR /home/ubuntu/

COPY /build/libs/moi-0.0.1-SNAPSHOT.jar .

#CMD java -jar -javaagent:/pinpoint/pinpoint-bootstrap-2.2.3-NCP-RC1.jar -Dpinpoint.applicationName=moi -Dpinpoint.agentId=moi-agent moi-0.0.1-SNAPSHOT.jar

CMD java -jar moi-0.0.1-SNAPSHOT.jar
