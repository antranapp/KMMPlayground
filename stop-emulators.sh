#!/bin/sh

adb devices | grep "emulator-" | while read -r emulator device; do
  adb -s $emulator emu kill
done
