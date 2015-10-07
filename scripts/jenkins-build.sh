#!/bin/bash
# This script adds build information into WAR when used by Jenkins build system
# Current directory is assumed to be top project (i.e. ../ from where this script is located)

BUILD_PROPERTIES=offersapi-war/src/main/resources/build.properties
SVN_INFO=offersapi-war/src/main/resources/svn.info

CURR_PATH=`pwd`

SCRIPT_PATH=`dirname $0`
cd $SCRIPT_PATH
cd ..

rm -f $BUILD_PROPERTIES
rm -f $SVN_INFO
svn info > $SVN_INFO
SVN_REV=`grep "Last Changed Rev:" $SVN_INFO | sed 's/^.*: //'`
SVN_PATH=`grep "URL:" $SVN_INFO | sed 's/URL: //'`
SVN_DATE=`grep "Last Changed Date:" $SVN_INFO | sed 's/^.*: //'`
echo "svn.revision = ${SVN_REV}" > $BUILD_PROPERTIES
echo "svn.path = ${SVN_PATH}" >> $BUILD_PROPERTIES
echo "svn.date = ${SVN_DATE}" >> $BUILD_PROPERTIES
echo "hudson.build.name = ${JOB_NAME}" >> $BUILD_PROPERTIES
echo "hudson.build.number = ${BUILD_NUMBER}" >> $BUILD_PROPERTIES
echo "hudson.build.id = ${BUILD_ID}" >> $BUILD_PROPERTIES

cd $CURR_PATH
