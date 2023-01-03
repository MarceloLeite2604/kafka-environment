#!/bin/sh

set -e

#shellcheck disable=SC2086
/opt/java/openjdk/bin/java $JAVA_OPTS -jar /opt/producer/app.jar producerId="$PRODUCER_ID,java.lang.Integer,true"