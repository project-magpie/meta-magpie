SUMMARY = "DVB / MPEG stream analyzer"
AUTHOR = "Rainer Scherg <rasc@users.sourceforge.net>"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

PV = "0.0-git${SRCPV}"
PR = "r2"

SRC_URI = "git://gitorious.org/tuxbox-cvs/apps.git"
SRCREV = "5138f15c66dba10642376f01cbc799bdbaaebc4a"

S = "${WORKDIR}/git/dvb/dvbsnoop"

inherit autotools
