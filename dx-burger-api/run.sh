#!/bin/bash
# @author       Anthony Vilarim Caliani
# @contact      github.com/avcaliani
#
# @Description
# DX Burger Spring Boot Application runner.
#
# Thanks

PROJECT_PATH="."
JAR_FILE="$PROJECT_PATH/target/dx-burger-api-19.2.0.jar"
JVM_OPTIONS="-Xmx2048"
DOCKER_API="dx-burger-api"
DOCKER_NGINX="dx-nginx"


  # # #    #   #    #   #    # # #    # # #    # # #    # # #    #   #    # # #
  #        #   #    ##  #    #          #        #      #   #    ##  #    #
  # #      #   #    # # #    #          #        #      #   #    # # #    # # #
  #        #   #    #  ##    #          #        #      #   #    #  ##        #
  #        # # #    #   #    # # #      #      # # #    # # #    #   #    # # #


fnBuild() {

  if [ ! -d "$PROJECT_PATH/.mvn" ]; then
    echo "FATAL: Maven folder doesn't exist in project directory ($PROJECT_PATH/.mvn)."
    exit 0
  fi
  if [ ! -f "$PROJECT_PATH/DockerFile" ]; then
      echo "FATAL: Docker File doesn't exist in project directory ($PROJECT_PATH/DockerFile)."
      exit 0
  fi
  if [ ! -d "$PROJECT_PATH/nginx" ]; then
      echo "FATAL: Nginx folder doesn't exist in project directory ($PROJECT_PATH/nginx)."
      exit 0
  fi
  if [ ! -f "$PROJECT_PATH/nginx/DockerFile" ]; then
        echo "FATAL: Nginx Docker File doesn't exist in project directory ($PROJECT_PATH/nginx/DockerFile)."
        exit 0
  fi

  mvn clean install
  docker build -f DockerFile -t $DOCKER_API .
  cd nginx && docker build -f DockerFile -t $DOCKER_NGINX .
}

fnStart() {
    echo "INFO: Starting project..."
    docker run -d -p 8080:8080 --name $DOCKER_API $DOCKER_API
    docker run -d -p 80:80 -p 443:443 --name $DOCKER_NGINX --link=$DOCKER_API $DOCKER_NGINX
}

fnStop() {
    docker stop $(docker ps -a -q)
    docker rm $(docker ps -a -q)
}


  # # #    #   #    #   #    #   #    # # #    #   #    # # #
  #   #    #   #    ##  #    ##  #      #      ##  #    #
  # #      #   #    # # #    # # #      #      # # #    #  ##
  #  #     #   #    #  ##    #  ##      #      #  ##    #   #
  #   #    # # #    #   #    #   #    # # #    #   #    # # #


echo "\n"
echo "                                  .--."
echo "  The Best Bash Runner           |o_o |"
echo "  That you have ever seen!       |:_/ |"
echo "                                //   \\ \\"
echo "                               (|     | )"
echo "                              /'\\_   _/\`\\"
echo "  Made by @avcaliani          \\___)=(___/\n\n"

if [ -z $(command -v java) ]; then
  echo "FATAL: Java is not installed, please try to install it first."
  exit 0
fi

if [ -z $(command -v mvn) ]; then
  echo "FATAL: Maven is not installed, please try to install it first."
  exit 0
fi

if [ -z $(command -v docker) ]; then
  echo "FATAL: Docker is not installed, please try to install it first."
  exit 0
fi

case "$1" in
  start)
    echo "INFO: Trying to start project..."
    fnStart
    ;;

  build)
    echo "INFO: Trying to build project..."
    fnBuild
    ;;

  build-start)
    echo "INFO: Trying to build project..."
    fnBuild
    echo "INFO: Trying to start project..."
    fnStart
    ;;

  stop)
    echo "INFO: Trying to stop project..."
    fnStop
    ;;

  -h)
    echo "  Available Commands:"
    echo "   - start       : Start Application"
    echo "   - build       : Build Application"
    echo "   - build-start : Build and Start Application"
    echo "   - stop        : Stop Application"
    echo ""
    echo "  Enjoy it :)"
    ;;

  *)
    echo "See available options running with \"-h\" flag :)"
    ;;

esac
exit 1