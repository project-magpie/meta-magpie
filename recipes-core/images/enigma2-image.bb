#
# This is based on the neutrino image with some extra sugar
#

include recipes-images/images/neutrino-image-base.inc

ENIGMA2_PLUGINS = " \
	enigma2-plugin-extensions-audiosync \
	enigma2-plugin-extensions-autobackup \
	enigma2-plugin-extensions-cutlisteditor \
	enigma2-plugin-extensions-graphmultiepg \
	enigma2-plugin-extensions-mediaplayer \
	enigma2-plugin-extensions-mediascanner \
	enigma2-plugin-extensions-openwebif \
	enigma2-plugin-extensions-pictureplayer \
	enigma2-plugin-systemplugins-fastscan \
	enigma2-plugin-systemplugins-hotplug \
	enigma2-plugin-systemplugins-networkbrowser \
	enigma2-plugin-systemplugins-positionersetup \
	enigma2-plugin-systemplugins-satfinder \
	enigma2-plugin-systemplugins-skinselector \
	enigma2-plugin-systemplugins-softwaremanager \
	enigma2-plugin-systemplugins-videomode \
	enigma2-plugin-systemplugins-videotune \
	\
	${@base_contains("MACHINE_FEATURES", "3dtv", "enigma2-plugin-systemplugins-osd3dsetup" , "", d)} \
	${@base_contains("MACHINE_FEATURES", "dvb-c", "enigma2-plugin-systemplugins-cablescan" , "", d)} \
	${@base_contains("MACHINE_FEATURES", "frontprocessor", "enigma2-plugin-systemplugins-frontprocessorupgrade" , "", d)} \
	${@base_contains("MACHINE_FEATURES", "hdmicec", "enigma2-plugin-systemplugins-hdmicec" , "", d)} \
	${@base_contains("MACHINE_FEATURES", "osdposition", "enigma2-plugin-systemplugins-osdpositionsetup" , "", d)} \
	${@base_contains("MACHINE_FEATURES", "wlan", "enigma2-plugin-systemplugins-wirelesslan", "", d)} \
	\
	${@base_contains('OPENPLI_FEATURES', 'ci', 'enigma2-plugin-systemplugins-commoninterfaceassignment', '', d)} \
	${@base_contains('OPENPLI_FEATURES', 'dvd', 'enigma2-plugin-extensions-cdinfo enigma2-plugin-extensions-dvdplayer', '', d)} \
	${@base_contains('OPENPLI_FEATURES', 'fan', 'enigma2-plugin-systemplugins-tempfancontrol', '', d)} \
	"

DEPENDS += " \
	enigma2 \
	enigma2-pliplugins \
	enigma2-plugins \
	"



IMAGE_INSTALL += " \
        task-core-ssh-dropbear \
        openssh-sftp \
        portmap \
        nfs-utils \
        evremote2 \
        enigma2 \
        ${ENIGMA2_PLUGINS} \
       	tuxbox-common \
"

DEPENDS="vulture-feeds"

IMAGE_FEATURES += " package-management "

# set root password to "spark" without quotes ;-)
ROOTFS_POSTPROCESS_COMMAND += "\
sed 's%^root:[^:]*:%root:XdpstHmIRdvUo:%' \
< ${IMAGE_ROOTFS}/etc/passwd \
> ${IMAGE_ROOTFS}/etc/passwd.new;\
mv ${IMAGE_ROOTFS}/etc/passwd.new ${IMAGE_ROOTFS}/etc/passwd ; \
"
