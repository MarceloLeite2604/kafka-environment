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
ENV JAVA_HOME=/opt/java/openjdk
ENV PRODUCER_ID=0
COPY --from=jre-build /javaruntime $JAVA_HOME

RUN mkdir /opt/producer
COPY --from=build /tmp/target/*jar-with-dependencies.jar /opt/producer/app.jar
# CMD ["sh", "-c", "echo", "/opt/java/openjdk/bin/java", "-jar", "/opt/producer/app.jar", "producerId=$PRODUCER_ID"]
CMD ["sh", "-c", "echo", "Hi!"]