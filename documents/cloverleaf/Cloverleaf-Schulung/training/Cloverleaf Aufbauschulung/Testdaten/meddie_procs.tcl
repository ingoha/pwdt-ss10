######################################################################
# Name:		copyIfQualifiersMet
# Purpose:	Copies the first inbound field to the first outbound field
#               IF: 2nd and 3rd inbound values equal, ... nth and n+1th inbound value equal. 
# UPoC type:	xltp CALL
# Args:		none
# Notes:	All data is presented through special variables.  The initial
#		upvar in this proc provides access to the required variables.
#
#		This proc style only works when called from a code fragment
#		within an XLT.
#

proc copyIfQualifiersMet {} {
    upvar xlateId       xlateId		\
	  xlateInVals   xlateInVals	\
	  xlateOutList  xlateOutList	

set qualval [lassign $xlateInVals val]

set qualmet 1
while { [llength $qualval] > 1 } {
  set qualval [lassign $qualval q v]
  set qualmet [ expr { [cequal $q $v] && $qualmet } ]
}

if $qualmet { xpmstore $xlateId [lindex $xlateOutList 0] c $val }

}
