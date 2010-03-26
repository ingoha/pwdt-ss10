# M30L.2 (B)

######################################################################
# Name:         dealerIdFix
# Purpose:      If the dealerId starts with "20", replace 
#               it with "90".
# UPoC type:	xltp PRE-Xlt-Proc
# Args:		none
# Notes:	All data is presented through special variables.  The initial
#		upvar in this proc provides access to the required variables.
#
#		This proc style only works when called from a code fragment
#		within an XLT.
#

proc dealerIdFix {} {
    upvar xlateId       xlateId		\
	  xlateInList   xlateInList	\
	  xlateInTypes  xlateInTypes	\
	  xlateInVals   xlateInVals	\
	  xlateOutList  xlateOutList	\
	  xlateOutTypes xlateOutTypes	\
	  xlateOutVals  xlateOutVals

set dealerId [lindex $xlateInVals 0]
set check [string range $dealerId 0 1]

if {[cequal $check "20"]} { 
  set newDealerId "90" 
  append newDealerId [string range $dealerId 2 end]
  lvarpop xlateOutVals 0 $newDealerId
}

}


# M30L.2 (HC)

######################################################################
# Name:         foodCodeFix
# Purpose:      If the foodcode starts with "99", replace 
#               it with "70".
# UPoC type:	xltp PRE-Xlt-Proc
# Args:		none
# Notes:	All data is presented through special variables.  The initial
#		upvar in this proc provides access to the required variables.
#
#		This proc style only works when called from a code fragment
#		within an XLT.
#

proc foodCodeFix {} {
    upvar xlateId       xlateId		\
	  xlateInList   xlateInList	\
	  xlateInTypes  xlateInTypes	\
	  xlateInVals   xlateInVals	\
	  xlateOutList  xlateOutList	\
	  xlateOutTypes xlateOutTypes	\
	  xlateOutVals  xlateOutVals

set foodcode [lindex $xlateInVals 0]
set check [string range $foodcode 0 1]

if {[cequal $check "99"]} { 
  set newfoodcode "70" 
  append newfoodcode [string range $foodcode 2 end]
  lvarpop xlateOutVals 0 $newfoodcode
}

}

