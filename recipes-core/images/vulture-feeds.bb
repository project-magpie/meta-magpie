DESCRIPTION = "Merge machine and distro options to create a project magpie vulture image feeds machine task/package"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"


PV = "1.0"
PR = "r5"


ALLOW_EMPTY_${PN} = "1"

RRECOMMENDS_${PN} = "\
        strace \
        rsync \
        djmount \
        avahi \
        openvpn \
        mtd-utils \
        iperf \
        tcpdump \
        gdbserver \
        dvbsnoop \
        compat-wireless-all \
        iw \
        wpa-supplicant \
        firmware-htc7010 \
        firmware-htc9271 \
        crda \
        git \
        edid-decode \
        mtd-utils \
        neutrino-mp \
        neutrino-hd-plugins \
"
