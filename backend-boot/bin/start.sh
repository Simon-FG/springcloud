#!/bin/sh

rm -f tpid

nohup java -jar backend-boot.war --spring.config.location=application.yml> /dev/null 2>&1 &

echo $! > tpid

echo Start Success!