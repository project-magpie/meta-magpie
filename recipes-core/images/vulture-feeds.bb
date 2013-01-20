DESCRIPTION = "Merge machine and distro options to create a project magpie vulture image feeds machine task/package"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"


PV = "1.0"
PR = "r4"

inherit task

#
#	djmount \
#	dvdfs \
#	hdparm \
#	htop \
#	minidlna \
#	nano \
#	ntfs-3g \
#	ntp \
#	openresolv \
#	openssh \
#	parted \
#	procps \
#	rsync \
#	samba \
#	smartmontools \
#	strace \
#	ushare \
#	wakelan \
#	zeroconf \
#	dosfstools \
#	avahi-ui \




RRECOMMENDS = "\
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
"
