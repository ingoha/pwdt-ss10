# M20L.3

# create a new message and display FLAGS
set mh [msgcreate "msg-content"]
echo Current Flags: [msgmetaget $mh FLAGS]

# change the value of some flags, dump again
msgmetaset $mh FLAGS {{proto_nak 1} {last_in_group TRUE}}
echo New Flags: [msgmetaget $mh FLAGS]

# change the value of a flag, dump again
msgmetaset $mh FLAGS {{last_in_group 0}}
echo Latest Flags: [msgmetaget $mh FLAGS]

msgdestroy $mh
