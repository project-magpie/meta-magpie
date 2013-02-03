DESCRIPTION = "Wireless Central Regulatory Domain Agent"
HOMEPAGE = "http://wireless.kernel.org/en/developers/Regulatory/CRDA"
SECTION = "base"
PRIORITY = "optional"
LICENSE = "ISC"
LIC_FILES_CHKSUM="file://LICENSE;md5=07c4f6dea3845b02a18dc00c8c87699c"

PR = "r1"

DEPENDS = "libnl"
RDEPENDS_${PN} = "udev"

SRC_URI = "http://wireless.kernel.org/download/crda/${P}.tar.bz2;name=crda \
	   http://wireless.kernel.org/download/wireless-regdb/regulatory.bins/2013.01.11-regulatory.bin;name=reg \
           file://101-make_crypto_use_optional.patch \
	"

CFLAGS += " -DCONFIG_LIBNL20"

EXTRA_OEMAKE = "MAKEFLAGS="


do_compile() {
        oe_runmake all_noverify
}


do_install() {
	oe_runmake DESTDIR=${D} install
	install -d ${D}/usr/lib/crda/
	install -m 0644 ${WORKDIR}/2013.01.11-regulatory.bin  ${D}/usr/lib/crda/regulatory.bin
}

SRC_URI[crda.md5sum] = "29579185e06a75675507527243d28e5c"
SRC_URI[crda.sha256sum] = "aa8a7fe92f0765986c421a5b6768a185375ac210393df0605ee132f6754825f0"
SRC_URI[reg.md5sum] = "e0c8a5ca63fb8bf803213f9a0c90b50b"
SRC_URI[reg.sha256sum] = "b1ee0b20c123c612dfdb6851ab42c01666f66fb583e0e590942f19bb54cf84be"

FILES_${PN} += "\
	/lib/udev/rules.d/85-regulatory.rules \
	/usr/lib/crda/regulatory.bin \
	"
