FROM maven:3.8.6-eclipse-temurin-17-alpine as build
COPY pom.xml /tmp/pom.xml
COPY src /tmp/src
RUN mvn -B -f /tmp/pom.xml package


FROM eclipse-temurin:17-alpine as jre-build
RUN $JAVA_HOME/bin/jlink \
  --add-modules java.base,java.compiler,java.desktop,java.instrument,java.net.http,java.prefs,java.scripting,java.security.jgss,java.security.sasl,java.sql.rowset,jdk.jfr,jdk.management,jdk.unsupported,jdk.crypto.ec,jdk.crypto.cryptoki \
  --strip-java-debug-attributes \
  --no-man-pages \
  --no-header-files \
  --compress=2 \
  --output /javaruntime

FROM alpine:3.17.0
RUN mkdir /opt/producer
RUN chown -R 1001:1001 /opt/producer $JAVA_HOME
USER 1001:1001
ENV JAVA_HOME=/opt/java/openjdk
ENV PRODUCER_ID=0
COPY --chown=1001:1001 --from=jre-build /javaruntime $JAVA_HOME

ENV JAVA_OPTS="-Xms64M -Xmx128M"
COPY --chown=1001:1001 --from=build /tmp/target/*jar-with-dependencies.jar /opt/producer/app.jar
COPY --chown=1001:1001 docker/entrypoint.sh /opt/producer/entrypoint.sh
RUN chmod ug+xw /opt/producer/entrypoint.sh
ENTRYPOINT ["/opt/producer/entrypoint.sh"]