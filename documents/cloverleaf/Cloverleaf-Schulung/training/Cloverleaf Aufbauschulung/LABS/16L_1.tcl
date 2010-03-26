# 16L.1 

# procedure declaration

proc returnListElement { index list } {
  set result [lindex $list $index]
  return $result
}

# test the procedure, call it, 
# echo response to screen

set l {a b c dd eee f}
set element [returnListElement 5 $l]
echo $element




# 16L.2

proc numberList { number } {
  for {set i 1} {$i < $number} {incr i} {
    lappend result $i
  }
  return $result
}


set n 12
set l [numberList $n]
echo $l

