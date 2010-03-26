# M51L.2

######################################################################
# Name:		filterIB_942
# Purpose:	KILLs messages with a LFC equal to 942
# UPoC type:	tps
# Args: 	tps keyedlist containing the following keys:
#       	MODE    run mode ("start", "run" or "test")
#       	MSGID   message handle
#
# Returns: tps disposition list:
#          KILL        indien de solid food code 677844 voorkomt
#          CONTINUE    in alle andere gevallen
#

proc filterIB_942 { args } {
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
            set gh [grmcreate -msg $mh hl7 2.2 ATT ADT_A01]
            set dh [grmfetch $gh 2(0).ZDI(0).95003(0)]
            set LFC [datget $dh VALUE]
            datdestroy $dh
            grmdestroy $gh

            if {[cequal $LFC "942"]} {
              lappend dispList "KILL $mh"
             } else {
              lappend dispList "CONTINUE $mh"
            }
        }

        default {
	    error "Unknown mode '$mode' in filterIB_942"
        }
    }
    return $dispList
}

######################################################################
# Name:		filterOB_123999
# Purpose:	KILLs messages with a foodcode equal to 123999
# UPoC type:	tps
# Args: 	tps keyedlist containing the following keys:
#       	MODE    run mode ("start", "run" or "test")
#       	MSGID   message handle
#
# Returns: tps disposition list:
#          KILL  voor berichten met een foodcode gelijk aan 123999
#          CONTINUE in alle overige gevallen
#

proc filterOB_123999 { args } {
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
            set dh [grmfetch $gh Solid_food_reference]
            set Foodcode [datget $dh VALUE]
            datdestroy $dh
            grmdestroy $gh
            if { [cequal $Foodcode "123999"] } {
              lappend dispList "KILL $mh"
            }  else {
              lappend dispList "CONTINUE $mh"
	    }
        }

        default {
	    error "Unknown mode '$mode' in filterOB_123999"
        }
    }
}
