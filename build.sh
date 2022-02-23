#!/usr/bin/env bash

ver=$(cat gradle.properties | grep mod_version | awk '{print $3}')
sandboxDir="/home/adam/.multimc/instances/20w19a1/.minecraft/mods"
playDir="/home/adam/.multimc/instances/20w20a/.minecraft/mods"
build=$(date "+%m%d_%H%M")

copyBuild() {
    rm ${sandboxDir}/berserker*
    rm ${playDir}/berserker*
    cp ./build/libs/berserker-${ver}.${build}-dev.jar ${sandboxDir}/
    cp ./build/libs/berserker-${ver}.${build}.jar ${playDir}/
}

./gradlew build

mv ./build/libs/berserker-${ver}.jar ./build/libs/berserker-${ver}.${build}.jar
mv ./build/libs/berserker-${ver}-dev.jar ./build/libs/berserker-${ver}.${build}-dev.jar
rm ./build/libs/berserker-${ver}-sources*

ls -1 ./build/libs

copyBuild
