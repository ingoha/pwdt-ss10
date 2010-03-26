# M19L.1

set dbname "/tmp/m19db.gnudb"

# Fill database with data in keyl18.dat file
# The framework of the code below is based on
# Lab 18.

if {[catch {set fh [open /tmp/class/keyl18.dat r]}]} {
 echo Error: file doesn't exist
 exit
} else {
  lgets $fh list18
  set list18 [ lindex $list18 0 ]
  echo $list18
  
  gdbm_open $dbname wc

  set elnameList [ keylkeys list18 ]

  foreach el $elnameList {
    set ordernumber "---"
    keylget list18 $el.ORDERNUMBER ordernumber
    set stock 0
    keylget list18 $el.STOCK stock
    # store Tcl-list and productname/stock in db
    set dbrecord [list $el $stock]
    if {![cequal $ordernumber "---"]} { 
       gdbm_store $dbname $ordernumber $dbrecord
       echo [format "storing %5s %-20s" $ordernumber $dbrecord ]
    }
  }
  gdbm_close $dbname
  close $fh
}

# M19L 1.1

proc getCurrentStock { ordernumber } {
global dbname

gdbm_open $dbname r 
set result {default -1}
gdbm_fetch $dbname $ordernumber result
gdbm_close $dbname

return [lindex $result 1]
}

# M19L 1.2

proc processOrder { ordernumber orderamount } {
global dbname

gdbm_open $dbname w
set result {default -1}
gdbm_fetch $dbname $ordernumber record
set stock [lindex $record 1]

if { $orderamount > $stock } {
   set processed FALSE
} else {
   set processed TRUE
   set newrecord [list [lindex $record 0] \
       [expr { $stock - $orderamount } ] ]
   gdbm_store $dbname $ordernumber $newrecord
}

gdbm_close $dbname
return $processed
}

# Calls in order to test the various procedures...

echo Current stock: [getCurrentStock 947]
echo Order 1200: [processOrder 947 1200]
echo New stock: [getCurrentStock 947]
echo \n
echo Current stock: [getCurrentStock 955]
echo Order 100 stuks: [processOrder 955 100]
echo New stock: [getCurrentStock 955]
