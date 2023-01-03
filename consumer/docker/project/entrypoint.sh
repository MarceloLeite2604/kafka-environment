#!/bin/sh

set -e

#shellcheck disable=SC2086
/opt/java/openjdk/bin/java $JAVA_OPTS -jar /opt/project/app.jar