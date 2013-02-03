FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}:"

DEPENDS = "openssl libnl"

SRC_URI += "file://defconfig-stlinux \
"

do_configure () {
        install -m 0755 ${WORKDIR}/defconfig-stlinux .config
}

