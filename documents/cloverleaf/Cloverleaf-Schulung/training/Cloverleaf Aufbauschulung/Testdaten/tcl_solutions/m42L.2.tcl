# M42L.2

######################################################################
# Name:         ageFilter
# Purpose:      If age < 5, abort the Xlate.
# UPoC type:	xltp
# Args:		none
# Notes:	All data is presented through special variables.
#               The initial upvar in this proc provides access to
#               the required variables.
#
#		This proc style only works when called from a code
#               fragment within an XLT.
#

proc ageFilter {} {
    upvar xlateId       xlateId		\
	  xlateInList   xlateInList	\
	  xlateInTypes  xlateInTypes	\
	  xlateInVals   xlateInVals	\
	  xlateOutList  xlateOutList	\
	  xlateOutTypes xlateOutTypes	\
	  xlateOutVals  xlateOutVals

   # Inbound fields:
   #  0. date of birth
   # Outbound fields:
   #  - (none)

   set dh [ xpmfetch -warn w $xlateId [ lindex $xlateInList  0 ] ]
   set dob [ datget $dh VALUE ]
   datdestroy $dh
 
   if { $dob > "19930000" } {
      catch { xpmerror $xlateId curdetail \
                       "Patient younger than 5 yrs." }
   }

   # if you want to calculate the exact age of the patient, use
   # gc_calcAge in gen_common.tcl
}
