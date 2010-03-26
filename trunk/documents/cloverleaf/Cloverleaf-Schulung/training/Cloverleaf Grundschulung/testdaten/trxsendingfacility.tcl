######################################################################
# Name:		trxHL7
# Purpose:	overcome no evn segment
# UPoC type:	trxid
# Args:		msgId	= message handle
# Returns:	The message's transaction ID
#
# Notes:
#	The message is both modify- and destroy-locked -- attempts to modify
#	or destroy it will error out.
#

proc trxSendingFacility { msgId } {

    set msg [msgget $msgId]

    set fieldSeparator [crange $msg 3 3]
    set subfieldSeparator [crange $msg 4 4]

    set mshSegment [getHL7Segment $msg MSH]
    set msgTypeField [getHL7Field $mshSegment 8 $fieldSeparator]
    set sendingFacility [getHL7Field $mshSegment 3 $fieldSeparator]
    set msgTypeList [split $msgTypeField $subfieldSeparator]

    set listCount [llength $msgTypeList]
    if { [cequal $listCount 1] } {
        set newSeparator ""
    } else {
        set newSeparator _
    }

    set trxId \
[lindex $msgTypeList 0]$newSeparator[lindex $msgTypeList 1]_$sendingFacility

    return $trxId				;# return it
}