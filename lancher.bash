#!/bin/bash

app_jar=divide2-ying-0.0.1-SNAPSHOT.jar

psid=0

checkpid() {
   # Using export here to make sure the $? is 0, otherwise when there is no
   #output the $? is 1 and the 'set -ex' will make the script abort
   export javaps=`ps -ef|grep ${app_jar}|grep -v grep|awk '{print $2}'`
   if [[ -n "$javaps" ]]; then
      psid=`echo ${javaps} | awk '{print $1}'`
   else
      psid=0
   fi
   echo "psid: $psid"
}

start() {
     checkpid
      if [[ ${psid} -ne 0 ]]; then
         echo "================================"
         echo "warn: $app_jar already started! (pid=$psid)"
         echo "================================"
      else
         echo "=========== Start ============"

         JAVA_CMD="java -jar  ./target/${app_jar}"

         nohup ${JAVA_CMD} &

         checkpid
         if [ $psid -ne 0 ]; then
            echo "Server started successfully!"
         else
            echo "Server started failed!"
         fi
     fi
}

stop() {
   checkpid
    if [[ ${psid} -ne 0 ]]; then

       echo "Killing pid -> "${psid}
       kill -9 ${psid}

       echo "Server stopped successfully."
   else
      echo "================================"
      echo "warn: Server is not running!"
      echo "================================"
   fi

}

status() {
   checkpid

   if [[ ${psid} -ne 0 ]];  then
      echo "Server is running!"
   else
      echo "Server is not running!"
   fi
}

echo "Running $1 command as $USER"

case "$1" in
   'start')
      start
      ;;
   'stop')
     stop
     ;;
   'restart')
     stop
     start
     ;;
   'status')
     status
     ;;
  *)
     echo "Usage: $0 {start|stop|restart|status}"
     exit 1
esac
exit 0
