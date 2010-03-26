###########################################################
# Name:		determineHL7TrxId
# Purpose:	Determine the TrxId of HL7 messages, using
#               ADT_A01X for a special subset of ADT_A01
#               messages. 
# UPoC type:	trxid
# Args:		msgId	= message handle
# Returns:	The message's transaction ID
#
# Notes:
#	The message is both modify- and destroy-locked -- 
#       attempts to modify
#	or destroy it will error out.
#

proc determineHl7TrxId { msgId } {
  
    set gh [grmcreate -msg $msgId hl7 2.2 {} {}]
    set trxId [join [grmfetchStr $gh 0(0).MSH(0).00009(0)] "_"]
    if { [cequal $trxId "ADT_A01"] } {
      set Liquid [grmfetchStr $gh 0(0).ZDI(0).95003(0)]
      if { [cequal $Liquid "942"] } {
         append trxId "X"
      } 
   }
   grmdestroy $gh
   return $trxId				;# return it
}

# The above proc uses the following helper procedure, defined in
# gen_grm_procs.tcl

################################################################
# grmfetchStr : variation on grmfetch, returns a list of strings 
#               instead of a list of datum handles.
#
proc grmfetchStr { grmId field } {
   if [catch {set dhList [grmfetch $grmId ${field}]} err] {
     error $err
   }
   set fieldvalue {}
   foreach dh $dhList {
     lappend fieldvalue [datget $dh VALUE]
     datdestroy $dh
   }   
   return $fieldvalue
}

