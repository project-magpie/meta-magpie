SUMMARY = "Neutrino HD2 is based on multiple projects. Its main feature is the multituner feature"
DESCRIPTION = "Multi tuner enabled Neutrino HD with an alternative GUI design. Mostly based on the tdt Neutrino"
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
" 

RDEPENDS_${PN} += " \
             ffmpeg \
             pic2m2v \
             tzdata \
             fbshot \
"


SRCREV = "1309"
PV = "0.0+svn${SRCPV}"
PR = "r1.1"

SRC_URI = " \
            svn://neutrinohd2.googlecode.com/svn/branches;module=nhd2-exp;proto=http \
	    file://COPYING.GPL \
	    file://0001-libungif.patch \
        file://neutrino.init \
        file://40-event.rules \
"

S = "${WORKDIR}/nhd2-exp"

inherit autotools pkgconfig update-rc.d 

INITSCRIPT_PACKAGES   = "${PN}"
INITSCRIPT_NAME_${PN} = "neutrino"
INITSCRIPT_PARAMS_${PN} = "start 99 5 2 . stop 20 0 1 6 ."


#CFLAGS_append = "-Wall -W -Wshadow -g -O2 -fno-strict-aliasing -rdynamic -DNEW_LIBCURL"

#CXXFLAGS += "${CFLAGS}"

#LDFLAGS += " -Wl,-rpath-link,${STAGING_DIR_HOST}/usr/lib -lavformat"


EXTRA_OEMAKE += " 'LIBS=-lavformat' "


EXTRA_OECONF += " \
                     --enable-libeplayer3 \
                     --with-boxtype=${MACHINE} \
"



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
        install -d ${D}/var/etc
        install -d ${D}/var/tuxbox/config/
#        install -m 755 ${WORKDIR}/standby.on ${D}/var/tuxbox/config/
#        install -m 755 ${WORKDIR}/standby.on ${D}/var/tuxbox/config/deepstandby.on 
#        install -m 644 ${WORKDIR}/timezone.xml ${D}/${sysconfdir}

        # generate /.version
        echo "version=${SRCREV}" > ${D}/var/etc/.version
        echo "creator=${MAINTAINER}" >> ${D}/var/etc/.version
        echo "imagename=${DISTRO_NAME}" >> ${D}/var/etc/.version
        echo "homepage=http://neutrinohd2.googlecode.com" >> ${D}/var/etc/.version
}

FILES_${PN} += "\
               /.version \
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
	
#        which pic2m2v >/dev/null 2>&1 
#	#
#	# neutrino icon path
#	I=/usr/share/tuxbox/neutrino/icons
#	pic2m2v $I/mp3.jpg $I/radiomode.jpg $I/scan.jpg $I/shutdown.jpg $I/start.jpg

}


