FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PRINC := "${@int(PRINC) + 1}"

SRC_URI += "\
            file://0001-build-support-for-giflib-5.x.x.patch \
"