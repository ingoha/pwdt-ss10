######################################################################
# tpsGenBasicHL7Reply - generate an acknowledgment to a remote system
#
# Args: tps keyedlist containing:
#       MODE    run mode ("start" or "run")
#       MSGID   message handle
#       ARGS
#               FIXEDFIELDS   a list of the fixed fields
#
# Returns: tps keyed list containing
#       CONTINUE = msg received
#	OVER     = acknowledgement
#
# Notes:
#	Use this proc as an inbound data TPS module!
#
proc tpsGenBasicHL7Reply { args } {

    global HciConnName

    set module "(tpsGenBasicHL7Reply/$HciConnName)"

    set dispList {}

    keylget args MODE mode              ;# What mode are we in
    switch -exact -- $mode {
        start {
            # Perform special init functions, if any
        }

        run {
            keylget args MSGID mh

            set reply_mh [msgcreate -type reply]

            msgappend $reply_mh "MSH|^~\\&|||"
            msgappend $reply_mh "||||"
            msgappend $reply_mh "ACK||P|"
            msgappend $reply_mh "2.3\r"
            msgappend $reply_mh "MSA|AA\r"

            #echo $module: replying to message with <[msgget $reply_mh]>

            lappend dispList "CONTINUE $mh"
            lappend dispList "OVER $reply_mh"
        }

        default {
            echo "Unknown mode in $module: '$mode'"
            return ""                   ;# Dont know what to do
        }
    }
}