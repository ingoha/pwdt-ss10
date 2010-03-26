# M41.1 (HC/B)

# (HC) Set vars for the healthcare related lab:
set filename /tmp/class/java_sfp.dat
set frlname jka_sfp
set field1 "PatientId"
set field2 "Dietician"

# (B) Set vars for the banking related lab: 
set filename /tmp/class/deal_oe.dat
set frlname deal_oe
set field1 "ValueDate"
set field2 "DealDetails"

# set required globals & default paths
setHciDirs

# create msg object
set mh [msgcreate -type data]

# set content equal to 1st line in file...
set fh [ open $filename ]
msgread nl $mh $fh
close $fh

# jka_sfp is 'my' FRL name
set gh [grmcreate -msg $mh frl $frlname]

# "field1" is a name of an FRL field. This field contains 1
# subfield, a grmfetch will result in 1 datum handle.
set dh [grmfetch $gh $field1 ] 
echo [datget $dh TYPE]
echo [datget $dh VALUE]
datdestroy $dh

# "field2" is a name of an FRL field. This field contains multiple
# subfields, grmfetch willl result in multiple datum handles.
set dhlist [grmfetch $gh $field2 ]  
foreach dh $dhlist {
  echo [datget $dh TYPE] [datget $dh VALUE]
}
datdestroy -list $dhlist

grmdestroy $gh
msgdestroy $mh
