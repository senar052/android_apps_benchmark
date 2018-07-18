Benchmark Androtest-on-docker
=============================

# Requirements
 * The platform Androtest-on-docker and its requirements
 
# API
To do this benchmark it has been choosen to use API 16.
This is because the applications inherited from the Master Thesis `Testing of Android Testing Tools: Development of a Benchmark for the Evaluation` did not compile with an API bellow 11 and because it was necessary to change from the API 10 of the project to another higher than the one from the platform.
Once it was choosen that it was necessary to change the API and knowing that the API minimum of the platform(Android Studio) chose to develop the new applications it is API 15 and because we use templates for x86 architecture and we have only the ones for API 10, 16 and 19, it was choosen the API 16.

# How to run
To run an application we have two options:

## Use the branch
The first one it would be to go to the branch `feature_Benchmark` of the platform Androtest-on-docker and with this we will be able to run all the apps that works with `ant` launching them on a normal way.

```shell
./run_res.sh -t monkey -a PlayAudio -m 5 -n 1 -d -g --mode-testing
```

## Create
If we do not want to use the branch we can start from the branch called `dev` of Androtest-on-docker and do the next steps:

* On the file `run_res.sh` on the function getToolVars it will be necessary to change the next vars:

    * The value of TOOL_API for all the tools that TOOL_API is smaller than 16 except for acteve and dynodroid.
    The value of API_array from 10 to 16 on all cases.
    
    Note be carefull with the value of ANDROTEST_MODE_ON.

### Ant
If the application that we want to launch it is build using `ant` it will not be necessary to do more to launch the app.

```shell
./run_res.sh -t monkey -a PlayAudio -m 5 -n 1 -d -g --mode-testing
```

### Gradle
if we are using an application that it is build using gradle it will be necessary to do the next steps:

build the .apk: this can be done going to the directory of app and doing the next command:

```shell
./gradlew assembleDebug
```

Once done the apk will be on the next path: app/build/outputs/apk/debug/app-debug.apk

Next we will create a directory inside the directory called subjects_test with the name that we want and copy the apk inside of it.

On the next step we will enter inside the file called `run_main.sh` that it is inside the directory of scripts and we will add the next lines:

```shell
sleep 99999999999
exit 1
```

above the next lines:

```shell
# Take the TOOL SCRIPT FILE
source ${ANDROTEST_SCRIPTS_SETUPTOOL_FILEPATH} ${TOOL}
[[ $? -ne 0 ]] && echo "${NAME}: ERROR: ${ANDROTEST_SCRIPTS_SETUPTOOL_FILEPATH} ERROR." && exit 1
```

Next we will run the apllication on a normal way:

```shell
./run_res.sh -t monkey -a <directory_with_apk> -m 65 - n 1 -d -g
```

And will enter inside the container once it has been build.
this can be know going to the results directory and going down by the tree of directories of machine_name, tool, architecture, api, application and run<i> and read the file `makefile.log` and when we read the next text, the container will be builded:

```shell
AoD: androtest-testing-<api>-<arch>-<tool>-testing-<app>-<runi>: ready
```

To enter inside the container can be done on the next way:

```shell
docker exec -it androtest-testing-<api>-<arch>-<tool>-testing-<app>-<runi> /bin/bash
```

With this last command we will have a shell inside the container that we will use to launch the next commands:
First we will install the apk, this can be done with the next command from the directory where the apk is.

```shell
cd /home/vagrant/subjects/<directory_with_apk>/
```

```shell
export app_apk=$(ls *.apk)
export package=$(aapt d xmltree ${app_apk} AndroidManifest.xml | grep package | awk 'BEGIN {FS="\""}{print $2}')
```

```shell
adb install ${app_apk}
```

Next to create the file called `06_tool_logcat` we need to use the next command:

```shell
${ANDROTEST_SCRIPTS_COMMON_LOGCAT_DOCKER_PATHFILE} /home/vagrant/results/
```

Next we will launch the tool with the commands that we can find on the file `run_<tool_name>.sh`

Next we can watch an example of launching monkey:

```shell 
CMD="adb shell monkey -p ${package}"
CMD="${CMD} -v 1000000 --throttle 200"
CMD="${CMD} --ignore-crashes --ignore-timeouts"
CMD="${CMD} --ignore-security-exceptions --ignore-security-exceptions"
${ANDROTEST_SCRIPTS_COMMON_CMD_TOOL_ENTRYSCRIPT_DOCKER_PATHFILE} -c "${CMD}" -p "/home/vagrant/results/"
```

To stop monkey we can use the next command

```shell 
adb shell kill -9 $(adb shell ps | grep monkey | awk '{print $2}')
```

Next we can watch an example of launching sapienz:

```shell 
TOOL=${TOOL:-sapienz}
TOOL_FOLDERNAME=${TOOL_FOLDERNAME:-sapienz}
TOOLDIR=${ANDROTEST_TOOLS_DOCKER_PATH}/${TOOL_FOLDERNAME}/
APPDIR=${ANDROTEST_SUBJECTS_DOCKER_PATH}/${app}
bin_app_dir="${APPDIR}/bin/"
export ANDROID_HOME=${ANDROID_HOME}
export SAPIENZ_TOOLFOLDER=${TOOLDIR}
tooldir_apks_folderpath="${TOOLDIR}/apks/"
```

From the directory of the apk .

```shell
apkname=`basename ${app_apk}`
cp <apk_file> ../../tools/sapienz/apks/
```

From the directory of the tool (/home/vagrant/tools/sapienz)

```shell
CMD="pip install -r requirements.txt &> /home/vagrant/${ANDROTEST_SCRIPTS_COMMON_TOOLBUILD_LOG_DOCKER_FILENAME}"
${ANDROTEST_SCRIPTS_COMMON_CMD_DOCKER_PATHFILE} "${CMD}"

AVD_NAME=${API_NAME}_${ARCH}
cp settings_example.py settings.py
echo "AVD_SERIES = '${AVD_NAME}'" >> settings.py

CMD="python ${TOOLDIR}/main.py ${tooldir_apks_folderpath}/${apkname}"
${ANDROTEST_SCRIPTS_COMMON_CMD_TOOL_ENTRYSCRIPT_DOCKER_PATHFILE} -c "${CMD}" -p "/home/vagrant/results"
```

To exit the from the shell of the container 

```shell 
exit
```

To watch the running containers

```shell 
docker ps
```

To kill a container

```shell 
docker kill androtest-testing-<api>-<arch>-<tool>-testing-<app>-<runi>
```