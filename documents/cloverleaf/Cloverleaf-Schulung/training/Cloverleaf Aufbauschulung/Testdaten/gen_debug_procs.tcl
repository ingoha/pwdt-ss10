##############################################################################
# File: gen_debug_procs
#
# This library is copyright (c) HIE Europe GmbH, 1998. 
# Use of these libraries is covered by laws of the Federal Republic of
# Germany. The copyright of individual proceduredes resides with its author(s)
# and/or their employers. See the comments in the procedure code. 
#
# Please note: you're welcome to use the procedures in this library, 
# however HIE Europe doesn't guarantee that it'll actually work given
# your particular environment. 
# We appreciate any comments, additions or enhancements you may have.
# Rene Spronk, e-mail rene@healthcare.com, rene.spronk@wxs.nl
# 
# Date  : 981113
#

######################################################################
# Name:		gc_echoMsg
# Purpose:      Dump basis msg info for debugging purposes
# UPoC type:	tps
# Args: 	tps keyedlist containing the following keys:
#       	MODE    run mode ("start" or "run")
#       	MSGID   message handle
#       	ARGS    keyed list of user-supplied arguments:
#               	LABEL - additional info to echo before displaying the msg contents
#                       DUMP  - if present, do a msgdump instead of echoing msg text.
# Example:
#               gc_echoMsg
#               gc_echoMsg {LABEL "msg echoed at moment Y"}
#               gc_echoMsg {{LABEL "dump of msg at moment X"} {DUMP 1}}
# Returns: tps disposition list:
#          CONTINUE
#
# tested 970212

proc gc_echoMsg { args } {
    global HciConnName

    keylget args MODE mode              	;# Fetch mode
    keylget args ARGS uarg

    switch -exact -- $mode {
        start {
            # Perform special init functions
            return ""
        }

        run {
	    # 'run' mode always has a MSGID; fetch and process it
            keylget args MSGID mh
            keylget args CONTEXT ctx

            if {[keylget uarg LABEL label]} {
                 echo echoMSG($HciConnName/$ctx): $label 
            }
            if {[keylget uarg DUMP dump]} {
               echo echoMSG($HciConnName/$ctx): dumping msg..
               msgdump $mh
            } else {
               echo echoMSG($HciConnName/$ctx): msg contents.. \n[msgget $mh]
               echo echoMSG($HciConnName/$ctx): msgmeta(USERDATA) = [ msgmetaget $mh USERDATA ]
               echo echoMSG($HciConnName/$ctx): msgmeta(FLAGS)= [msgmetaget $mh FLAGS ]
            }
            return "{CONTINUE $mh}"
        }

        default {
	    error "Unknown mode '$mode' in gc_echoMsg"
        }
    }
}

######################################################################
# Name:         gc_writeMsgToFile
# Purpose:      Dump basis msg info for debugging purposes
# UPoC type:    tps
# Args:         tps keyedlist containing the following keys:
#               MODE    run mode ("start" or "run")
#               MSGID   message handle
#               ARGS    keyed list of user-supplied arguments:
#                       FILENAME - name of the file to write to. If not found, this proc
#                                  will simply CONTINUE the msg. If the filename is not fully-qualified,
#                                  this proc will write to the process dir.
#                       MODE70   - if set to 1, the message contents will be written in
#                                  a special 70 column debugging mode.
#
# Returns: tps disposition list:
#          CONTINUE
#
# tested 970212, mode70 to be tested
#
proc gc_writeMsgToFile { args } {
    global HciConnName

    keylget args MODE mode                      ;# Fetch mode
    keylget args ARGS uarg

    switch -exact -- $mode {
        start {
            # Perform special init functions
            return ""
        }

        run {
            # 'run' mode always has a MSGID; fetch and process it
            keylget args MSGID mh
            set mode70 0
            if {[keylget uarg MODE70 mode70]} { set mode70 1 }
            if {[keylget uarg FILENAME save_file]} {
               set fd [open $save_file a+]
               if [$mode70] {
 			# Create a 70-byte header
			puts -nonewline $fd "    |"
			loop i 0 7 {
				puts -nonewline $fd [replicate $i 9]
				puts -nonewline $fd [expr $i + 1]
			}
			puts $fd "\n    |[replicate "1234567890" 7]"
			puts $fd   "----+[replicate - 70]"

			# Write the msg in 70-byte chunks
			loop i 0 [msglength $mh] 70 {
				puts $fd "[format %04d [expr $i + 1]]|[msgget $mh $i 70]"
			}
               } else {
                   puts $fd [msgget $mh]
               }
               close $fd
            } else {
               echo gc_writeMsgToFile($HciConnName): No FILENAME given in list of user-supplied arguments. 
            }
            return "{CONTINUE $mh}"
        }

        default {
            error "Unknown mode '$mode' in gc_echoMsg"
        }
    }
}

######################################################################
# Name:		gc_echoMarker
# Purpose:	Displays a row of "-" characters each time a msg is continued.
#               Can be used at a position within a TPS stack to mark a new section of
#               output in the engine log.
# UPoC type:	tps
# Args: 	tps keyedlist containing the following keys:
#       	MODE    run mode ("start", "run" or "test")
#       	MSGID   message handle
#
# Returns: tps disposition list:
#          CONTINUE
#
# tested 970212

proc gc_echoMarker { args } {
    keylget args MODE mode              	;# Fetch mode

    switch -exact -- $mode {
        start {
            # Perform special init functions
	    # N.B.: there may or may not be a MSGID key in args
        }

        run {
	    # 'run' mode always has a MSGID; fetch and process it
            keylget args MSGID mh
            echo "--------------------------------------------------------------------"
            return "{CONTINUE $mh}"
        }

        time {
            # Timer-based processing
	    # N.B.: there may or may not be a MSGID key in args
        }

        default {
	    error "Unknown mode '$mode' in gc_echoMarker"
        }
    }
}

######################################################################
# Name:		gc_dumpPreProcArgs
# Purpose:	Dumps the contents of the global parameters supplied
#               to an xlt-code fragment.
# UPoC type:	xltp
# Args:		none
# Notes:	All data is presented through special variables.  The initial
#		upvar in this proc provides access to the required variables.
#
#		This proc style only works when called from a code fragment
#		within an XLT.
#

proc gc_dumpPreProcArgs {} {
    upvar xlateId       xlateId		\
	  xlateInList   xlateInList	\
	  xlateInTypes  xlateInTypes	\
	  xlateInVals   xlateInVals	\
	  xlateOutList  xlateOutList	\
	  xlateOutTypes xlateOutTypes	\
	  xlateOutVals  xlateOutVals

echo gc_dumpPreprocArgs(gen_debug_procs.tcl)/1: xlateId = $xlateId
echo gc_dumpPreprocArgs(gen_debug_procs.tcl)/2: xlateInList = $xlateInList
echo gc_dumpPreprocArgs(gen_debug_procs.tcl)/3: xlateInTypes = $xlateInTypes
echo gc_dumpPreprocArgs(gen_debug_procs.tcl)/4: xlateInVals = $xlateInVals
echo gc_dumpPreprocArgs(gen_debug_procs.tcl)/5: xlateOutList = $xlateOutList
echo gc_dumpPreprocArgs(gen_debug_procs.tcl)/6: xlateOutTypes = $xlateOutTypes
echo gc_dumpPreprocArgs(gen_debug_procs.tcl)/7: xlateOutVals = $xlateOutVals
}

######################################################################
# Name:         gc_endprocTPSshowMsg
# Purpose:      show msg contents and disposition 
# UPoC type:    hcitpstest
# Args:         hcitpstest keyedlist containing the following keys:
#               MSGID   message handle
#               DISP    message disposition (e.g., SEND, CONTINUE, etc.)
#               ARGS    user-supplied arguments
#                       <describe user-supplied args here>
#
# Returns:      Nothing useful (i.e., hcitpstest disregards it)
# Notes:        Destroy the message before returning so hcitpstest does
#               not waste VM resources (especially important if processing
#               many and/or large messages).
#

proc gc_endprocTPSshowMsg { args } {
    set msgId [keylget args MSGID]      ;# fetch message handle      or error
    set disp ""
    keylget args DISP disp              ;# fetch message disposition or error
    set uarg  [keylget args ARGS]       ;# fetch user args           or error

    #
    # Processing, display, etc.
    echo [format "%-8s %s" $disp [msgget $msgId] ]
    #

    msgdestroy $msgId
}

######################################################################
# Name:		gc_eoEchoMsg
# Purpose:	echo message in EO-style
# UPoC type:	other
# Args:		sub  = Submodule (will be padded/truncated to 4 chars)
#      		type = (INFO/DBUG/WARN/ERR)   (will be padded/truncated to 4 chars)
#      		sev  = Severity (0-4)
#      		msg  = Text message to be displayed
#      		mh   = (Optional) Message handle; if specified, msg ID will display
# Notes:	
#
proc gc_eoEchoMsg { sub type sev msg {mh ""} } {
	global HciConnName

	set fd   stdout

	# execute 'return' if type should not be echoed
	switch -exact -- $type {
		DBUG {
			# Comment out the 'return' if you want DBUG messages
			return
		}

		INFO {
			# Comment out the 'return' if you want INFO messages
			#return
		}

		WARN -
		ERR {
			# Warnings and errors go to standard error
			set fd stderr
		}
		default {
			echo "### Unknown type '$type'.  Should be INFO/DBUG/WARN/ERR"
		}
	}

	set mod  "upoc"
	set sub  [csubstr [format %-4s $sub] 0 4]
	set type [csubstr [format %-4s $type] 0 4]
	set thd  [format %13s $HciConnName]

	set lvl  [expr [info level] - 1]
	set proc [lindex [info level $lvl] 0]

	if [cequal $mh ""] {
		puts $fd "\[$mod:$sub:$type/$sev:$thd\] \[$proc\] $msg"
	} else {
		puts $fd "\[$mod:$sub:$type/$sev:$thd\] \[$proc\] [gc_getMid $mh] $msg"
	}
}
