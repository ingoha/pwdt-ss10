# M41L.2 - EDI

#set default globals & paths
setHciDirs

# create msg object
set mh [msgcreate -type data]

# read 1st line of file...
msgset $mh [lindex [split [read_file /tmp/class/diet_meddie.dat] \n] 0]

# "ds" is 'my' name for the EDI variant used by the dietician system 
set gh [grmcreate -msg $mh edifact 95B ds MEDDIE ]

# "3(0).0(0).PNA(0).2#C206(0).[0]" is a pathname (Identification Number) in EDI.
# This fields contains 1 subfield, grmfetch results in 1 datum handle.
set dh [grmfetch $gh {3(0).0(0).PNA(0).2#C206(0).[0]} ] 
echo [datget $dh TYPE]
echo [datget $dh VALUE]
datdestroy $dh

# M41L 2.1

# "1(0).NAD(0).4#C080" is a pathname (Dietician) in Edifact.
# This field contains the name of the Dietician and contains
# multiple subfields. Grmfetch will return a list of datum handles.
set dhlist [grmfetch $gh 1(0).NAD(0).4#C080 ]  
set n 0
foreach dh $dhlist {
  echo Subfield $n = [datget $dh TYPE] [datget $dh VALUE]
  incr n 1
}
datdestroy -list $dhlist

# M41L 2.2

set newInitials "X.Y.Z."
grmstore $gh {1(0).NAD(0).4#C080.[1]} c $newInitials
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
