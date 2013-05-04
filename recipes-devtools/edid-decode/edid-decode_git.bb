SUMMARY = "EDID decode" 
DESCRIPTION = " STM ASCII format enabled edid decoder"
HOMEPAGE = "https://bitbucket.org/graugans/stm-edid-decode/src"
SECTION = "libs"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://edid-decode.c;beginline=1;endline=22;md5=32cb877c9d060dd18f49d66a9dd0b624"

SRCREV = "9ec929f90d69339240d2e8b9f22ffe2d31755bfd"
PV = "0.0+git${SRCPV}"
PR = "r0.4"

SRC_URI = " \
            git://bitbucket.org/graugans/stm-edid-decode.git;protocol=https \
"

S = "${WORKDIR}/git"

inherit autotools pkgconfig  

