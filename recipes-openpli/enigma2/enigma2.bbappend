FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC := "${@int(PRINC) + 3}"

SRC_URI += "\
            file://0001-build-support-for-giflib-5.x.x.patch \
"