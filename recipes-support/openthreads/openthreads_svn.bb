SUMMARY = "OpenThreads is a cross platform, object orientated threading library."
DESCRIPTION = "OpenThreads is a cross platform, object orientated threading library."
SECTION = "libs"
LICENSE = "OSGPL"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=9226151d58bcdf987ed14e7dc8cedcbc \
"
DEPENDS = ""
SRCDATE = "${PV}"
PR = "r1"

SRC_URI = "svn://www.openscenegraph.org/svn/osg/OpenThreads;module=trunk;rev=13083;proto=http \
           file://0001-seifes-atomic_void_ptr_fix.patch;pnum=3 \
           file://002-omit-policy-cmp0014.patch;pnum=0 \
"

S = "${WORKDIR}/trunk"

inherit cmake 

EXTRA_OECMAKE += "-DCMAKE_BUILD_TYPE=Release \
                  -DCMAKE_SYSTEM_NAME=Linux \
                  -D_OPENTHREADS_ATOMIC_USE_GCC_BUILTINS_EXITCODE=1 \
                  -D_OPENTHREADS_ATOMIC_USE_GCC_BUILTINS=1 \
"

ARM_INSTRUCTION_SET = "arm"
