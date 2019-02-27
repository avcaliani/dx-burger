#!/bin/bash
# @author       Anthony Vilarim Caliani
# @contact      github.com/avcaliani
#
# @Description
# DX Burger Angular Application runner.
#
# Thanks

PROJECT_PATH="."
DOCKER_WEB="dx-burger-web"


  # # #    #   #    #   #    # # #    # # #    # # #    # # #    #   #    # # #
  #        #   #    ##  #    #          #        #      #   #    ##  #    #
  # #      #   #    # # #    #          #        #      #   #    # # #    # # #
  #        #   #    #  ##    #          #        #      #   #    #  ##        #
  #        # # #    #   #    # # #      #      # # #    # # #    #   #    # # #


fnBuild() {

  if [ ! -f "$PROJECT_PATH/DockerFile" ]; then
      echo "FATAL: Docker File doesn't exist in project directory ($PROJECT_PATH/DockerFile)."
      exit 0
  fi

  ng build --prod
  docker build -f DockerFile -t $DOCKER_WEB .
}

fnStart() {
  echo "INFO: Starting project..."
  docker run -d -p 3000:80 --name $DOCKER_WEB $DOCKER_WEB
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

if [ -z $(command -v docker) ]; then
  echo "FATAL: Docker is not installed, please try to install it first."
  exit 0
fi

if [ -z $(command -v ng) ]; then
  echo "FATAL: Ng CLI is not installed, please try to install it first."
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