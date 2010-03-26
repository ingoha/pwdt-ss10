######################################################################
# Name:		LiquidFoodCode
# Purpose:	Fill the three fields Liquid_of_choice, Milk and Sugar
#		in the JAVA ADM messages from the one source field
#		Liquid_Food_Code in the HL7 variant JAVA message A01.
# UPoC type:	Call xltp
# Args:		none
# Notes:	All data is presented through special variables.  The initial
#		upvar in this proc provides access to the required variables.
#
#		This proc style only works when called from a code fragment
#		within an XLT.
#
# Source fields:
# 0 - 0(0).ZDI(0).95003 - LiquidFoodCode
#
# Destination fields:
# 0 - Liquid_of_choice
# 1 - Milk
# 2 - Sugar

proc LiquidFoodCode {} {
    upvar xlateId       xlateId		\
	  xlateInList   xlateInList	\
	  xlateInTypes  xlateInTypes	\
	  xlateInVals   xlateInVals	\
	  xlateOutList  xlateOutList	\
	  xlateOutTypes xlateOutTypes	\
	  xlateOutVals  xlateOutVals
	  
	  set checkval [lindex $xlateInVals 0]
	  set newlist [split [tbllookup LiquidFoodCode $checkval] /]

	  for {set i00 0} {$i00 < [llength $XlateOutList]} {incr i00} {
	      xpmstore $xlateId [lindex $xlateOutList $i00] c [lindex $newlist $i00]
	  }
}