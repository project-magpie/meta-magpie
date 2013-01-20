require ffmpeg.inc

LIC_FILES_CHKSUM = "file://COPYING.GPLv2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.GPLv3;md5=d32239bcb673463ab874e80d47fae504 \
                    file://COPYING.LGPLv2.1;md5=e344c8fa836c3a41c4cbd79d7bd3a379 \
                    file://COPYING.LGPLv3;md5=e6a600fd5e1d9cbde2d983680233ad02 \
"

PV = "0.10"
PR = "${INC_PR}.1"

SRC_URI = " http://www.ffmpeg.org/releases/ffmpeg-${PV}.tar.bz2\
"
SRC_URI[md5sum] = "dc665cc599a739e3c5262ccdac13d129"
SRC_URI[sha256sum] = "43596aa76d1a0fab6d42927bf60383405de2bca8fbc740c2012877559ae17ff9"



S = "${WORKDIR}/ffmpeg-${PV}"


