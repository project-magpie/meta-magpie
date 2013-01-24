SUMMARY = "Port of neutrino to multiple platforms, including (but not limited to) the Tripledragon, AZbox ME/miniME and Fulan Spark/Spark7162 settop boxes."
DESCRIPTION = "Port of neutrino to multiple platforms."
HOMEPAGE = "https://gitorious.org/neutrino-mp/neutrino-mp"
SECTION = "libs"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${WORKDIR}/COPYING.GPL;md5=751419260aa954499f7abaabaa882bbe \
"

DEPENDS = " libass libstb-hal curl libid3tag libmad freetype boost libungif libdvbsi++ ffmpeg flac tremor libvorbis openthreads" 

SRCREV = "c1dd39b42ed1bb555cb80433c1d7d86ffc490fc9"
PV = "0.0+git${SRCPV}"
PR = "r4"

SRC_URI = " \
            git://gitorious.org/neutrino-mp/neutrino-mp.git;protocol=git \
            file://COPYING.GPL \
            file://0001-removed-the-not-needed-subdirectory-from-device.patch \
            file://neutrino.init \
"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

CFLAGS_append = "-Wall -W -Wshadow -g -O2 -fno-strict-aliasing -rdynamic -DNEW_LIBCURL"
CFLAGS_spark += "-funsigned-char \
"
CXXFLAGS += "${CFLAGS}"

LDFLAGS += " -Wl,-rpath-link,${STAGING_DIR_HOST}/usr/lib -lavformat"


EXTRA_OEMAKE += " 'LIBS=-lavformat' "

EXTRA_OECONF += "\
                     --enable-maintainer-mode \
                     --with-target=cdk \
                     --enable-silent-rules \
                     --with-tremor --disable-upnp \
"

EXTRA_OECONF_spark += "\
                     --with-boxtype=spark \
                     --with-stb-hal-includes=${STAGING_DIR_HOST}/usr/include/libstbhal/libstbhal \
		     --with-stb-hal-build=${STAGING_DIR_HOST}/usr/lib \
"


EXTRA_OECONF_spark7162 += "\
                     --with-boxtype=spark \
"

do_install_append () {

	install -d ${D}/${sysconfdir}/init.d
        install -m 755 ${WORKDIR}/neutrino.init ${D}/${sysconfdir}/init.d/neutrino
}

FILES_${PN} += "\
               ${sysconfdir} \
               /usr/share \
               /usr/share/tuxbox \
               /usr/share/iso-codes \
               /usr/share/fonts \
               /usr/share/tuxbox/neutrino \
               /usr/share/iso-codes \
               /usr/share/fonts \
"
