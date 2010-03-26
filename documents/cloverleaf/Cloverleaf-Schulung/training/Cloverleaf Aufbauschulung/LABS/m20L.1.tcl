# M20L.1

set mh [ msgcreate "red and green" ]

# dump msg content
set msgcontents [ msgget $mh ] ; echo $msgcontents

# dump character range 0 to 2
# a range of 0 to 2 is equivalent to offset 0, length 3
set msgcontents [ msgget $mh 0 3 ] ; echo $msgcontents

# insert a string into message content
msginsert $mh ",orange" 3
# equivalent to  msgset $mh ",orange" 3 0

# dump message
msgdump $mh

msgdestroy $mh
