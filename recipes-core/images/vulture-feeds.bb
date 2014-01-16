DESCRIPTION = "Merge machine and distro options to create a project magpie vulture image feeds machine task/package"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"


PV = "1.0"
PR = "r4"

inherit packagegroup

RRECOMMENDS = "\
	strace \
	rsync \
	djmount \
	zeroconf \
	avahi \
	vim \
	openvpn \
	mtd-utils \
	iperf \
	ntp \
	tcpdump \
        gdbserver \
        gdb \
        directfb \
        directfb-examples \
	dvbsnoop \
        libstb-hal \
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
        neutrino-hd2 \
"
