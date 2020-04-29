#!/bin/sh
isExist=`ps -ef | grep BootApplication | grep -v grep | awk '{print $2}'`
if [ ! -z "$isExist" ]; then
    echo "process already running!!! pid is "$isExist
    exit -1
fi
PRGDIR=`dirname "$0"`
BASEDIR=`cd "$PRGDIR" ; cd ../runtime ; pwd`

#nohup java -Xms512m -Xmx512m -jar wx-api.jar > log/out.log 2>& 1 &

#nohup java -Xms512m -Xmx512m -jar wx-api.jar > log/out.log 2>& 1


CLASSPATH=$BASEDIR/config:$BASEDIR/lib/*
nohup java  -Xmx2g -Xms2g -classpath "$CLASSPATH" \
                com.github.niefy.BootApplication \

echo $! > progressId.pid
