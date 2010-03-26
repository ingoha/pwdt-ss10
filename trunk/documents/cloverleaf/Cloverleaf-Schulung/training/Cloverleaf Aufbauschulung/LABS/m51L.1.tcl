# 51L.1

######################################################################
# Name:		setSFPpriority
# Purpose:	Change the priority of SFP messages to 60000.
# UPoC type:	tps
# Args: 	tps keyedlist containing the following keys:
#       	MODE    run mode ("start", "run" or "test")
#       	MSGID   message handle
#
# Returns: tps disposition list:
#          CONTINUE         van alle ingaande berichten
#

proc setSFPpriority { args } {
    keylget args MODE mode              	;# Fetch mode
    set dispList {}

    switch -exact -- $mode {
        start {
            # Perform special init functions
	    # N.B.: there may or may not be a MSGID key in args
        }

        run {
	    # 'run' mode always has a MSGID; fetch and process it
            keylget args MSGID mh
            set gh [grmcreate -msg $mh frl SFPmsg.frl]
            set dh [grmfetch $gh MsgType]
            set type [datget $dh VALUE]
            grmdestroy $gh
            datdestroy $dh
            if {[cequal $type "SFP"]} {
               msgmetaset $mh  PRIORITY 6000 
            }
            lappend dispList "CONTINUE $mh"
        }

        default {
	    error "Unknown mode '$mode' in setSFPpriority"
        }
    }
    return $dispList
}

