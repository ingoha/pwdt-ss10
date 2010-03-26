# M18L.1

set llist ""
# read the contents of the /etc/passwd file and
# store the contents into the content variable
set content [read_file /etc/passwd]

# the lines in the /etc/passwd file are newline terminated
# so we split the content variable over the newline character
set clist [split $content \n]

# iterate over each line
foreach l $clist {
 # a line in /etc/passwd is formatted as
 # login_name:other_field:other_fields.
 set lname [lindex [split $l ":"] 0]
 # add the login name found to a list
 if {[string length $lname] > 0} { lappend llist $lname }
}
# alphabetically sort the list of login names
lsort $llist
