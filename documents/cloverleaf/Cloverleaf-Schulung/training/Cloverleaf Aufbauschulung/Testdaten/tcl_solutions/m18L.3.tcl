# M18L.3

# In order to use the lgets command, we'll need to
# have a file handle.
set fh [open /tmp/class/keyl18.dat r]
lgets $fh list18
close $fh

# The list is nested "one level too deep"
# We simply select the first element in the outermost
# list to get rid of the superfluous top level.
set list18 [ lindex $list18 0 ]
echo $list18

# The key in the keyl18 file is a productname.
# by alphabetically sorting the names of the keys
# we can create the desired list of product names.
set elnameList [ lsort [ keylkeys list18 ]]

foreach el $elnameList {
  echo [format "%-20s %5s" $el [keylget list18 $el.ORDERNUMBER ] ]
}
