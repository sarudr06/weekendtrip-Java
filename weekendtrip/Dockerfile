FROM camunda/zeebe-simple-monitor:0.17.0-alpha1 as source

FROM openjdk:11-jre

VOLUME /tmp
ADD target/weekendtrip.jar weekendtrip.jar

ENTRYPOINT ["java","-jar","weekendtrip.jar"]