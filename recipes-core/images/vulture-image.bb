#
# This is based on the neutrino image with some extra sugar
#

include recipes-images/images/neutrino-image-base.inc


IMAGE_INSTALL += " \
        neutrino-mp \
        task-core-ssh-dropbear \
        openssh-sftp \
        portmap \
        nfs-utils \
"

DEPENDS="vulture-feeds"

# set root password to "spark" without quotes ;-)
ROOTFS_POSTPROCESS_COMMAND += "\
sed 's%^root:[^:]*:%root:XdpstHmIRdvUo:%' \
< ${IMAGE_ROOTFS}/etc/passwd \
> ${IMAGE_ROOTFS}/etc/passwd.new;\
mv ${IMAGE_ROOTFS}/etc/passwd.new ${IMAGE_ROOTFS}/etc/passwd ; \
"
