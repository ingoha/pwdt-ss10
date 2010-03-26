# 42L.1

######################################################################
# Name:         phonePatch
# Purpose:      Chnages phonenbr for dietician 100 
# UPoC type:	XLT CALL
# Args:		none
# Notes:	All data is presented through special variables.
#               The initial upvar in this proc provides access to
#               the required variables.
#
#		This proc style only works when called from a code
#               fragment within an XLT.
#

proc phonePatch {} {
    upvar xlateId       xlateId		\
	  xlateInList   xlateInList	\
	  xlateInTypes  xlateInTypes	\
	  xlateInVals   xlateInVals	\
	  xlateOutList  xlateOutList	\
	  xlateOutTypes xlateOutTypes	\
	  xlateOutVals  xlateOutVals

# To be used from the Xlate GUI as follows:
#      CALL phonePatch
# Inbound Field:  
#   0. The code associated with the dietician
# Outbound Field: 
#   0. DieticanPhoneNbr (The exact fieldname depends on your FRL)

# As a result of this this procedure is provided with the
# following values:
#  [lindex $xlateInVals 0] - the value of the dietician code
#  [lindex $xlateInList 0] - the pathname of the inbound field 
#  [lindex $xlateOutList 0] - the pathname of the outbound field

set dietician_code [ lindex $xlateInVals 0 ]

set nw_phnr "+333(9)255 635987"
if { $dietician_code == 100} {
  # Changing the xlateOutVals list doesn't work within a Xlt CALL.
  # .. we use an xpmstore instead.
  set phone_path [ lindex $xlateOutList 0 ]
  xpmstore $xlateId $phone_path c $nw_phnr 
}

# Note that if the dietician_code is unequal to 100, nothing is
# xpmstored and hence the current value of the phone number is
# unchanged.

}
