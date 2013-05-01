SUMMARY = "Port of neutrino to multiple platforms, including (but not limited to) the Tripledragon, AZbox ME/miniME and Fulan Spark/Spark7162 settop boxes."
DESCRIPTION = "Port of neutrino to multiple platforms."
HOMEPAGE = "https://gitorious.org/neutrino-mp/neutrino-mp"
SECTION = "libs"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${WORKDIR}/COPYING.GPL;md5=751419260aa954499f7abaabaa882bbe \
"

DEPENDS += " libass \
             libstb-hal \
             curl \
             libid3tag \
             libmad \
             freetype \
             boost \
             libungif \
             libdvbsi++ \
             ffmpeg \
             flac \
             tremor \
             libvorbis \
             openthreads \
             lua5.2 \
             luaposix \
" 

RDEPENDS_${PN} += " \
             ffmpeg \
             pic2m2v \
             tzdata \
             fbshot \
             lua5.2 \
             luaposix \
"

RCONFLICTS_${PN} = "neutrino-hd2"

#SRCREV = "c7a4927b53f674323931471b05b83a455a0e6506"
SRCREV = "e92afd2b420f2e53cf45a79b29b9898df406fe2b"
PV = "0.0+git${SRCPV}"
PR = "r15"

SRC_URI = " \
            git://gitorious.org/neutrino-mp/neutrino-mp.git;protocol=git \
            file://COPYING.GPL \
            file://0001-removed-the-not-needed-subdirectory-from-device.patch \
            file://0001-fixed-nhttpd-web-root-path.patch \
            file://neutrino.init \
            file://standby.on \
            file://timezone.xml \
            file://40-event.rules \
"

S = "${WORKDIR}/git"

inherit autotools pkgconfig update-rc.d 

INITSCRIPT_PACKAGES   = "${PN}"
INITSCRIPT_NAME_${PN} = "neutrino"
INITSCRIPT_PARAMS_${PN} = "start 99 5 2 . stop 20 0 1 6 ."


N_CFLAGS = "-Wall -W -Wshadow -g -O2 -fno-strict-aliasing -funsigned-char -rdynamic -DNEW_LIBCURL -DDYNAMIC_LUAPOSIX"
N_CXXFLAGS = "${N_CFLAGS}"
N_LDFLAGS += "-Wl,-rpath-link,${STAGING_DIR_HOST}/usr/lib -lavformat"


EXTRA_OEMAKE += " 'LIBS=-lavformat'" 


EXTRA_OECONF += " \
                     --enable-maintainer-mode \
                     --with-target=cdk \
                     --enable-silent-rules \
                     --with-tremor \
                     --disable-upnp \
"


EXTRA_OECONF_spark += "\
                     --with-boxtype=spark \
                     --with-stb-hal-includes=${STAGING_DIR_HOST}/usr/include/libstbhal/libstbhal \
                     --with-stb-hal-build=${STAGING_DIR_HOST}/usr/lib \
"


EXTRA_OECONF_spark7162 += "\
                     --with-boxtype=spark \
                     --with-stb-hal-includes=${STAGING_DIR_HOST}/usr/include/libstbhal/libstbhal \
                     --with-stb-hal-build=${STAGING_DIR_HOST}/usr/lib \
"

do_compile () {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake CFLAGS="${N_CFLAGS}" CXXFLAGS="${N_CXXFLAGS}" LDFLAGS="${N_LDFLAGS}"
}


do_install_prepend () {
        install -d ${D}/${sysconfdir}/init.d
        install -d ${D}/${sysconfdir}/udev/rules.d
        install -m 755 ${WORKDIR}/40-event.rules ${D}/${sysconfdir}/udev/rules.d
        install -m 755 ${WORKDIR}/neutrino.init ${D}/${sysconfdir}/init.d/neutrino
        install -d ${D}/share/tuxbox/neutrino/httpd-y
        install -d ${D}/share/tuxbox/neutrino/httpd
        install -d ${D}/share/fonts
        install -d ${D}/share/tuxbox/neutrino/icons
        install -d ${D}/var/cache
        install -d ${D}/var/tuxbox/config/
        install -m 755 ${WORKDIR}/standby.on ${D}/var/tuxbox/config/
        install -m 755 ${WORKDIR}/standby.on ${D}/var/tuxbox/config/deepstandby.on 
        install -m 644 ${WORKDIR}/timezone.xml ${D}/${sysconfdir}
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
               /share/tuxbox/neutrino/httpd-y \
               /share/tuxbox/neutrino/httpd \
               /share/fonts \
               /share/tuxbox \
               /var/cache \
"

pkg_postinst_${PN} () {
        update-alternatives --install /bin/backup.sh backup.sh /usr/bin/backup.sh 100
        update-alternatives --install /bin/install.sh install.sh /usr/bin/install.sh 100
        update-alternatives --install /bin/restore.sh restore.sh /usr/bin/restore.sh 100
	
        which pic2m2v >/dev/null 2>&1 
	#
	# neutrino icon path
	I=/usr/share/tuxbox/neutrino/icons
	pic2m2v $I/mp3.jpg $I/radiomode.jpg $I/scan.jpg $I/shutdown.jpg $I/start.jpg

}


