# M54L.1

######################################################################
# Name:		caesarEncrypt
# Purpose:      Encrypt the message using caesar's shift: 
#               "add 3 to each character (e.g. A becomes D)"
# UPoC type:	tps
# Args: 	tps keyedlist containing the following keys:
#       	MODE    run mode ("start", "run" or "test")
#       	MSGID   message handle
#
# Returns: tps disposition list:
#          CONTINUE      of all messages
#

proc caesarEncrypt { args } {
    keylget args MODE mode              	;# Fetch mode
    set dispList {}

    switch -exact -- $mode {
        start {
               # Perform special init functions
               for {set i 3} {$i < 256} {incr i} {lappend clist $i}
               lappend clist 0 1 2
               adddatamap caesar_encr $clist
         }

        run {
	    # 'run' mode always has a MSGID; fetch and process it
            keylget args MSGID mh
            msgmapdata $mh caesar_encr

            lappend dispList "CONTINUE $mh"
        }

        default {
	    error "Unknown mode '$mode' in caesarEncrypt"
        }
    }
    return $dispList
}

# M54L.1.1

######################################################################
# Name:		caesarDecrypt
# Purpose:      Decrypt a message that was encrypted using
#               Caesar's shift
# UPoC type:	tps
# Args: 	tps keyedlist containing the following keys:
#       	MODE    run mode ("start", "run" or "test")
#       	MSGID   message handle
#
# Returns: tps disposition list:
#          CONTINUE      of all messages
#

proc caesarDecrypt { args } {
    keylget args MODE mode              	;# Fetch mode
    set dispList {}

    switch -exact -- $mode {
        start {
               # Perform special init functions
               lappend dlist 253 254 255 
               for {set i 0} {$i < 253} {incr i} {lappend dlist $i}
               adddatamap caesar_decr $dlist
        }

        run {
	    # 'run' mode always has a MSGID; fetch and process it
            keylget args MSGID mh
            msgmapdata $mh caesar_decr

            lappend dispList "CONTINUE $mh"
        }

        default {
	    error "Unknown mode '$mode' in caesarDecrypt"
        }
    }
    return $dispList
}
