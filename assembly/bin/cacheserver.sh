#!/usr/bin/env bash

BIN=`dirname "$0"`
BIN=`cd $BIN; pwd`

CLASSPATH="$BIN/../conf"

for f in $BIN/../lib/*.jar; do
  CLASSPATH=${CLASSPATH}:$f;
done

java -server -classpath ${CLASSPATH} com.shvid.klod.net.CacheServer $BIN $1

