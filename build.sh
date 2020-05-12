#!/usr/bin/env bash

ver=$(cat gradle.properties | grep mod_version | awk '{print $3}')
modsDir="/home/adam/.multimc/instances/20w19a1/.minecraft/mods"

if ls -l ./build/libs/berserker-$ver.jar; then
    read -p "WARNING: Build already exists for v$ver. Continue? (N/y) " answer
    case $answer in
        yes|y|Y|Yes|YES) echo "Building anyway...";;
        *) echo "Exiting."; exit 1;;
    esac
fi

copyBuild() {
    rm $modsDir/berserker*
    cp ./build/libs/berserker-$ver.jar $modsDir/
}

./gradlew build && copyBuild
