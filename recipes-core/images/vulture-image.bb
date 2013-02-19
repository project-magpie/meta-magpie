#
# Copyright (C) 2007 OpenedHand Ltd.
#
DESCRIPTION = "A small image just capable of allowing a device to boot."

IMAGE_INSTALL = "task-core-boot task-core-ssh-dropbear  ${ROOTFS_PKGMANAGE_BOOTSTRAP} ${CORE_IMAGE_EXTRA_INSTALL}" 

IMAGE_INSTALL += " \
                  autofs \
                  portmap \
                  nfs-utils-client \
                  jpeg freetype \
                  libcrypto \
                  libssl \
                  ntp \
                  vsftpd \
                  neutrino-mp \
"

IMAGE_FEATURES += " package-management "

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

DEPENDS = " vulture-feeds"

IMAGE_ROOTFS_SIZE = "163840"

# set root password to "spark" without quotes ;-)
ROOTFS_POSTPROCESS_COMMAND += "\
sed 's%^root:[^:]*:%root:XdpstHmIRdvUo:%' \
< ${IMAGE_ROOTFS}/etc/shadow \
> ${IMAGE_ROOTFS}/etc/shadow.new;\
mv ${IMAGE_ROOTFS}/etc/shadow.new ${IMAGE_ROOTFS}/etc/shadow ; \
"

