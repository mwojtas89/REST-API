#!/usr/bin/env bash

export CATALINA_HOME=/Users/humptydumpty/dev/apache-tomcat-9.0.65

stop_tomcat()
{
   $CATALINA_HOME/bin/catalina.sh stop
}

start_tomcat()
{
   $CATALINA_HOME/bin/catalina.sh start
   end
}

rename() {
   rm build/libs/crud.war
   if mv build/libs/task-0.0.1-SNAPSHOT.war build/libs/crud.war; then
      echo "Successfully renamed file"
   else
      echo "Cannot rename file"
      fail
   fi
}

copy_file() {
   if cp build/libs/crud.war $CATALINA_HOME/webapps; then
      start_tomcat
   else
      fail
   fi
}

fail() {
   echo "There were errors"
}

end() {
   echo "Work is finished"
}

if ./gradlew build; then
   rename
   copy_file
else
   stop_tomcat
   fail
fi