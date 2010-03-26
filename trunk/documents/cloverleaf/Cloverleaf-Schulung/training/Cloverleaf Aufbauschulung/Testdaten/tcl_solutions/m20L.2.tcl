# M20L.2

# create msg of type data and class engine
set mh [msgcreate -meta {CLASS engine TYPE data}]

# display the MID metadata field
echo MID= [msgmetaget $mh MID]

# change the value of the USERDATA metadata field
msgmetaset $mh USERDATA {filter {A & B}}

# dump the msg contents & metadata
msgdump $mh

# the following proc dumps the values of all RW metadata
# fields associated with a specific message handle.
proc dumpRWmetadata { mh } {
  set metafields [lsort [ msgmetaget -rw $mh ]]
  foreach f $metafields {
    echo [format "%-12s = %s" $f [msgmetaget $mh $f] ]
  }
}

# Call procedure in order to test it
dumpRWmetadata $mh

msgdestroy $mh
