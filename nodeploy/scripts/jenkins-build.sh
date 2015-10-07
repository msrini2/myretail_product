#!/bin/bash
# This script adds build information into WAR when used by Jenkins build system
# Current directory is assumed to be top project (i.e. ../../ from where this script is located)
# This script requires one argument: environment name, case sensitive, as one of actual folder names in conf/
# for example: OFFERS-INTEG, OFFERS-QA

ENV=$1

ALLOWES_ENVS="BATCH-PROD-3 BATCH-QA ECOUPON-PROD-4 ECOUPON-STRESS OFFERS-INTEG-X OFFERS-PROD-3 OFFERS-QA PROMO-PROD-3 PROMO-PROD-4 PROMO-STRESS"
ALLOWES_ENVS=$ALLOWES_ENVS" BATCH-PROD-4 ECOUPON-PROD-3 ECOUPON-QA OFFERS-INTEG ECOUPON-INTEG PROMO-INTEG"
ALLOWES_ENVS=$ALLOWES_ENVS" OFFERS-PROD-4 OFFERS-STAGE OFFERS-STRESS PROMO-QA"
if [ "A$ENV" = "A" ]; then
	echo "Missing required parameter: environment name, possible values are: $ALLOWES_ENVS"
	exit 1
fi

# check
FOUND=0
for allowed in $ALLOWES_ENVS; do
	if [ "$ENV" = $allowed ]; then
		FOUND=1
	fi
done

if [ $FOUND = 0 ]; then
	echo "ENV is unknown: $ENV."
	echo "Allowed environments: $ALLOWES_ENVS"
	exit 1
fi

BUILD_PROPERTIES=offersapi-war/src/main/resources/build.properties
SVN_INFO=offersapi-war/src/main/resources/svn.info

CURR_PATH=`pwd`
echo "Current Path: $CURR_PATH"

SCRIPT_PATH=`dirname $0`
echo "Script Path: $SCRIPT_PATH"

echo "== Updating build properties with current build info =="

rm -f $BUILD_PROPERTIES
rm -f $SVN_INFO
svn info > $SVN_INFO
SVN_REV=`grep "Last Changed Rev:" $SVN_INFO | sed 's/^.*: //'`
SVN_PATH=`grep "URL:" $SVN_INFO | sed 's/URL: //'`
SVN_DATE=`grep "Last Changed Date:" $SVN_INFO | sed 's/^.*: //'`
echo "svn.revision = ${SVN_REV}" > $BUILD_PROPERTIES
echo "svn.path = ${SVN_PATH}" >> $BUILD_PROPERTIES
echo "svn.date = ${SVN_DATE}" >> $BUILD_PROPERTIES
echo "build.name = ${JOB_NAME}" >> $BUILD_PROPERTIES
echo "build.number = ${BUILD_NUMBER}" >> $BUILD_PROPERTIES
echo "build.id = ${BUILD_ID}" >> $BUILD_PROPERTIES
echo "offersapi.env = $ENV" >> $BUILD_PROPERTIES

cat $BUILD_PROPERTIES
