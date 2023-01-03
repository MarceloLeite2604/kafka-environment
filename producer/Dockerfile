FROM maven:3.8.6-eclipse-temurin-17-alpine as build
COPY pom.xml /tmp/pom.xml
COPY src /tmp/src
RUN mvn -B -f /tmp/pom.xml package


FROM eclipse-temurin:17-alpine as jre-build

RUN $JAVA_HOME/bin/jlink \
  --add-modules java.base,java.compiler,java.desktop,java.instrument,java.net.http,java.prefs,java.scripting,java.security.jgss,java.security.sasl,java.sql.rowset,jdk.jfr,jdk.management,jdk.unsupported \
  --strip-java-debug-attributes \
  --no-man-pages \
  --no-header-files \
  --compress=2 \
  --output /javaruntime

FROM alpine:3.17.0
ENV JAVA_HOME=/opt/java/openjdk
COPY --from=jre-build /javaruntime $JAVA_HOME

RUN mkdir /opt/producer
COPY --from=build /tmp/target/*jar-with-dependencies.jar /opt/producer/app.jar
CMD ["/opt/java/openjdk/bin/java", "-Dhttps.protocols=TLSv1.2,TLSv1.1,TLSv1", "-Djavax.net.debug=all", "-jar", "/opt/producer/app.jar"]