# M18L.2
set llist ""
set content [read_file /etc/passwd]
set clist [split $content \n]

# iterate over each line in the /etc/passwd file
foreach l $clist {
 # each line is formatted as login_name:field:UID:other_fields
 set uid [lindex [split $l ":"] 2]
 if {[string length $uid] > 0} { 
    keylset llist $uid [ lindex [split $l ":"] 0]  
 }
}
# display the resulting keyed list
echo $llist
