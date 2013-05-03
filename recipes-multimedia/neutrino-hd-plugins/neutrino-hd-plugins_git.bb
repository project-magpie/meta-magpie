DESCRIPTION = "tuxbox plugins, ported to neutrino-hd \
"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://tuxcom/tuxcom.c;beginline=10;endline=24;md5=8cfd78763de33face1d26b11904e84d5"
HOMEPAGE = "https://gitorious.org/neutrino-hd/neutrino-hd-plugins" 


PV = "0.0+git${SRCPV}"
PR = "r1.5"


SRCREV = "5cdfbf3c9ffc970decbc3ed4332e00022e8339e2"
SRC_URI = "git://gitorious.org/neutrino-hd/neutrino-hd-plugins.git \
           file://0001-fixed-framebuffer-device.patch \
           file://0002-fixed-font-path.patch \
          "
S = "${WORKDIR}/git"

inherit autotools pkgconfig 

EXTRA_OECONF += " \
                     --enable-maintainer-mode \
                     --with-target=cdk \
                     --with-boxtype=spark \
                     --with-plugindir=/var/tuxbox/plugins \
                     --with-configdir=/var/tuxbox/config \
                     --with-fontdir=/usr/share/fonts \
"


N_CFLAGS = "-Wall -W -Wshadow -g -O2 -fno-strict-aliasing -funsigned-char -rdynamic -DNEW_LIBCURL"
N_CXXFLAGS = "${N_CFLAGS}"
N_LDFLAGS += "-Wl,-rpath-link,${STAGING_DIR_HOST}/usr/lib -lavformat"


PLUGINS_TO_BUILD = "tuxcom"

do_compile () {
        unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
        oe_runmake CFLAGS="${N_CFLAGS}" CXXFLAGS="${N_CXXFLAGS}" LDFLAGS="${N_LDFLAGS}" SUBDIRS="${PLUGINS_TO_BUILD}"
}


do_install () {
	for i in ${PLUGINS_TO_BUILD}; do
		oe_runmake install SUBDIRS="$i" DESTDIR=${D}	
	done
}


FILES_${PN}-dbg += "/var/tuxbox/plugins/.debug"
FILES_${PN} += "/var/uxbox/"

