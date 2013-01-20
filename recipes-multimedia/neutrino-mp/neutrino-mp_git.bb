SUMMARY = "Port of neutrino to multiple platforms, including (but not limited to) the Tripledragon, AZbox ME/miniME and Fulan Spark/Spark7162 settop boxes."
DESCRIPTION = "Port of neutrino to multiple platforms."
HOMEPAGE = "https://gitorious.org/neutrino-mp/neutrino-mp"
SECTION = "libs"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${WORKDIR}/COPYING.GPL;md5=751419260aa954499f7abaabaa882bbe \
"

DEPENDS = " libstb-hal curl libid3tag libmad freetype boost libungif libdvbsi++ ffmpeg flac tremor libvorbis" 

SRCREV = "c1dd39b42ed1bb555cb80433c1d7d86ffc490fc9"
PV = "0.0+git${SRCPV}"
PR = "r0"

SRC_URI = " \
            git://gitorious.org/neutrino-mp/neutrino-mp.git;protocol=git \
            file://COPYING.GPL \
"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

EXTRA_OECONF += "\
                     --enable-maintainer-mode \
                     --with-target=cdk \
                     --enable-silent-rules \
"

EXTRA_OECONF_spark += "\
                     --with-boxtype=spark \
"
EXTRA_OECONF_spark7162 += "\
                     --with-boxtype=spark \
"
