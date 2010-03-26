######################################################################
# Name:		caesarEncrypt
# Purpose:	<description>
# UPoC type:	tps
# Args: 	tps keyedlist containing the following keys:
#       	MODE    run mode ("start", "run" or "time")
#       	MSGID   message handle
#       	ARGS    user-supplied arguments:
#               	<describe user-supplied args here>
#
# Returns: tps disposition list:
#          <describe dispositions used here>
#

proc caesarEncrypt { args } {
    keylget args MODE mode              	;# Fetch mode

    set dispList {}				;# Nothing to return

    switch -exact -- $mode {
        start {
            # Perform special init functions
	    # N.B.: there may or may not be a MSGID key in args
        keylget args ARGS uargs
        keylget uargs SHIFT shiftValue

 echo "Encrypt with shift $shiftValue"

        for { set i 0 } { $i < 256 } { incr i } {
            set ind [ expr { $i + $shiftValue } ]
            if { $ind > 255 } { 
                set ind [ expr { $ind - 256 } ]
            } 
            lappend clist $ind
        }
        adddatamap encrypt $clist            

        }

        run {
	    # 'run' mode always has a MSGID; fetch and process it
            keylget args MSGID mh
            msgmapdata $mh encrypt
            lappend dispList "CONTINUE $mh"
        }

        time {
            # Timer-based processing
	    # N.B.: there may or may not be a MSGID key in args
        }

        default {
	    error "Unknown mode '$mode' in caesarEncrypt"
        }
    }

    return $dispList
}
######################################################################
# Name:		caesarDecrypt
# Purpose:	<description>
# UPoC type:	tps
# Args: 	tps keyedlist containing the following keys:
#       	MODE    run mode ("start", "run" or "time")
#       	MSGID   message handle
#       	ARGS    user-supplied arguments:
#               	<describe user-supplied args here>
#
# Returns: tps disposition list:
#          <describe dispositions used here>
#

proc caesarDecrypt { args } {
    keylget args MODE mode              	;# Fetch mode

    set dispList {}				;# Nothing to return

    switch -exact -- $mode {
        start {
            # Perform special init functions
	    # N.B.: there may or may not be a MSGID key in args
        keylget args ARGS uargs
       keylget uargs SHIFT shiftValue
   echo "Using shiftvalue $shiftValue"

       for { set i 0 } { $i < 256 } { incr i } {
       set ind [ expr { $i - $shiftValue } ]
       if { $ind < 0 } {
          set ind [ expr { $ind + 256 } ]    
           }     
       lappend clist $ind
       } 
       adddatamap cesar $clist 
       }

        run {
	    # 'run' mode always has a MSGID; fetch and process it
            keylget args MSGID mh
            msgmapdata $mh cesar
            lappend dispList "CONTINUE $mh"
        }

        time {
            # Timer-based processing
	    # N.B.: there may or may not be a MSGID key in args
        }

        default {
	    error "Unknown mode '$mode' in caesarDecrypt"
        }
    }

    return $dispList
}
