FROM anapsix/alpine-java:jre8

MAINTAINER ndonthi@next-level-intgegration.com

ADD /target/monitoring-backend-application-1.0*.jar /monitoring-backend-application-1.0.jar
RUN sh -c 'touch /monitoring-backend-application-1.0.jar'

EXPOSE 8089

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/monitoring-backend-application-1.0.jar"]
