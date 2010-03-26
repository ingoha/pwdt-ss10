######################################################################
# getHL7Segment - generic routine that returns an entire segment
# Args: msg        the actual HL7 message data
#       segmentID  the name of the segment to get
# Returns:  the actual HL7 segemnt dat
#
proc getHL7Segment {msg segmentID} {

    set newSegment ""
    set segmentList [split $msg \r]
    foreach segment $segmentList {
        if {[crange $segment 0 2] == $segmentID} {
            set newSegment $segment
            break
	}
    }
    return $newSegment
}
######################################################################
# getHL7Field - generic routine that returns an entire field
# Args: segment       the actual HL7 segment data (not the whole message)
#       fieldNumber   the index of the field to get
#
# Returns: the actual HL7 field data
#
proc getHL7Field {segment fieldNumber {fieldSeparator "|"}} {

    set newField ""
    set fieldList [split $segment $fieldSeparator]
    set newField [lindex $fieldList $fieldNumber]
    return $newField
}
######################################################################
# replaceHL7Field - generic routine to replace the data in an HL7 field
# Args: msg       the entire HL7 message
#       segmentID the name of the segment containing the field to replace
#       fieldID   the index of the field to replace
#       data      the data that will replace the current contents of the field
#
# Returns: the an entire HL7 message
#
proc replaceHL7Field {msg segmentID fieldID data {fieldSeparator "|"} } {

    set segmentList [split $msg \r]
    set newSegmentList ""
    foreach segment $segmentList {
        if {[crange $segment 0 2] == $segmentID} {
            set fieldList [split $segment $fieldSeparator]
            set fieldList [lreplace $fieldList $fieldID $fieldID $data]
            set segment [join $fieldList $fieldSeparator]
        }
        set newSegmentList [lappend newSegmentList $segment]
    }
    return [join $newSegmentList \r]
}
######################################################################
# tpsChangeHL7Field - change any hl7 field to an arg
# Args: tps keyedlist containing:
#       MODE    run mode ("start" or "run")
#       MSGID   message handle
#       ARGS    keyed list of user arguments containing:
#               DATA    the data to put into the HL7 field
#               SEGID   the name of the segment that contains the field to change
#               INDEX   the HL7 index of the field to change
#
# Returns: tps keyed list containing
#       <describe dispositions used here>
#
proc tpsChangeHL7Field { args } {

    keylget args MODE mode              ;# What mode are we in

    switch -exact -- $mode {
        start {
            # Perform special init functions
        }

        run {
            keylget args MSGID mh
            set msg [msgget $mh]
            set data [keylget [keylget args ARGS] DATA]
            set segid [keylget [keylget args ARGS] SEGID]
            set index [keylget [keylget args ARGS] INDEX]
            set newMsg [replaceHL7Field $msg $segid $index $data]
            msgset $mh $newMsg
            return "{CONTINUE $mh}"
        }

        default {
            echo "Unknown mode in <hciChangeHL7Field>: '$mode'"
            return ""                   ;# Dont know what to do
        }
    }
}