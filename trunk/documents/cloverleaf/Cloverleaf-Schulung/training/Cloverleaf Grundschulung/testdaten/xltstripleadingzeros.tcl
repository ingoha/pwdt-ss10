######################################################################
# Name:		xltStripLeadingZeros
# Purpose:	<description>
# UPoC type:	xltp
# Args:		none
# Notes:	All data is presented through special variables.  The initial
#		upvar in this proc provides access to the required variables.
#
#		This proc style only works when called from a code fragment
#		within an XLT.
#

proc xltpStripLeadingZeros {} {
    upvar xlateId       xlateId		\
	  xlateInList   xlateInList	\
	  xlateInTypes  xlateInTypes	\
	  xlateInVals   xlateInVals	\
	  xlateOutList  xlateOutList	\
	  xlateOutTypes xlateOutTypes	\
	  xlateOutVals  xlateOutVals

    lassign $xlateInVals sourceField

    set destinationField [string trimleft $sourceField 0]
    set xlateOutVals $destinationField
}