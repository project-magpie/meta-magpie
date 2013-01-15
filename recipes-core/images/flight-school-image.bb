#
# Copyright (C) 2007 OpenedHand Ltd.
#
DESCRIPTION = "A small image just capable of allowing a device to boot."

IMAGE_INSTALL = "task-core-boot task-core-ssh-dropbear  ${ROOTFS_PKGMANAGE_BOOTSTRAP} ${CORE_IMAGE_EXTRA_INSTALL}" 
IMAGE_INSTALL += "jpeg freetype" 
IMAGE_INSTALL += "libcrypto libssl" 
#magpie-feed-config-opkg


IMAGE_FEATURES += "package-management"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit core-image

IMAGE_ROOTFS_SIZE = "81920"

# remove not needed ipkg informations
#ROOTFS_POSTPROCESS_COMMAND += "remove_packaging_data_files ; "
