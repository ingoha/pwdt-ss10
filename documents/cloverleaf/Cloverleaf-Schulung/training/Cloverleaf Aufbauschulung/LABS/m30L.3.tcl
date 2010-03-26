# M30L.3

######################################################################
# Name:         ZIPcodeReFormat
# Purpose:      Reformat the ZIP field.
#               9999 AA -> NL-9999AA
#               99999   -> D-99999
#               99-999  -> PL-99-999 
#               9999    -> DK-9999
# UPoC type:	xltp Pre-Xlt-Proc
# Args:		none
# Notes:	All data is presented through special variables.  The initial
#		upvar in this proc provides access to the required variables.
#
#		This proc style only works when called from a code fragment
#		within an XLT.
#

proc ZIPcodeReformat {} {
    upvar xlateId       xlateId		\
	  xlateInList   xlateInList	\
	  xlateInTypes  xlateInTypes	\
	  xlateInVals   xlateInVals	\
	  xlateOutList  xlateOutList	\
	  xlateOutTypes xlateOutTypes	\
	  xlateOutVals  xlateOutVals

set zipcode [lindex $xlateInVals 0]
set rzip ""

regsub -all " " $zipcode "" zipcode  ;# strip all spaces

# zipcode either 9999AA (=NL), 99999 (=D), 99-999 (=PL), 9999 (=DK) or other.

switch [clength $zipcode] {
  4 { # If Danish  ZIP, then all chars are numeric and length of zip = 4
      if {[ctype digit $zipcode] } {
          set rzip "DK-$zipcode"
      } 
    }
  5 { # If German ZIP, then all chars are numeric and length of zip = 5
      if {[ctype digit $zipcode] } {
          set rzip "D-$zipcode"
      } 
    }
  6 { # NL or PL ?
      if [string match {[0-9][0-9][0-9][0-9][A-Z][A-Z]} $zipcode] {
          set rzip "NL-$zipcode"
      }
      if [string match {[0-9][0-9]-[0-9][0-9][0-9]} $zipcode] {
        set zipcode "PL-$zipcode"
      }       
    }
   default {
      # unknown zip
   }
}

# Change value of 1st outbound field 
lvarpop xlateOutVals 0 $rzip
}
