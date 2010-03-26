# M41L.2 - HL7

#set default globals & paths
setHciDirs

# create msg object
set mh [msgcreate -type data]

# read 1st line of file...
set fh [ open /tmp/class/hl7_adm_zdi.dat]
msgread nl $mh $fh
close $fh

# "his" is 'my' name for the HL7 variant used by the HIS. 
set gh [grmcreate -msg $mh hl7 2.2 his ADT_A01 ]

# "0(0).PID(0).00106(0)" is a pathname (Internal Patient ID) in HL7.
# This fields contains 1 subfield, grmfetch results in 1 datum handle.
set dh [grmfetch $gh 0(0).PID(0).00106(0) ] 
echo [datget $dh TYPE]
echo [datget $dh VALUE]
datdestroy $dh

# M41L 2.1

# "0(0).ZDI(0).95001(0)" is a pathname (Dietician) in HL7.
# This field contains the name of the Dietician and contains
# multiple subfields. Grmfetch will return a list of datum handles.
set dhlist [grmfetch $gh 0(0).ZDI(0).95001(0) ]  
set n 0
foreach dh $dhlist {
  echo Subfield $n = [datget $dh TYPE] [datget $dh VALUE]
  incr n 1
}
datdestroy -list $dhlist

# M41L 2.2

set newInitials "X.Y.Z."
grmstore $gh {0(0).ZDI(0).95001(0).[2]} c $newInitials
set mh2 [grmencode -warn wmsgs $gh]
echo grmencode warnings: $wmsgs
set msgcontent [msgget $mh2]
regsub -all \r $msgcontent \n msgcontent
echo $msgcontent
msgdestroy $mh2

grmdestroy $gh
msgdestroy $mh

# check for 'leaked' handles..
echo Leaked Handles:
datlist
grmlist
msglist
