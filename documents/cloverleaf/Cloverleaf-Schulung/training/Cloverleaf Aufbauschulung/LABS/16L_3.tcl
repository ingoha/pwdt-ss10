# 16L.3

proc squareList { number } {
  for {set i 1} {$i < $number} {incr i} {
    lappend result [expr {$i * $i}]
  }
  return $result
}


set n 12
set l [squareList $n]
echo $l




# 16L.4

proc nameMonth { number } {
  set nameList {Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec}
  # list index ranges from 0 to 11, nu,mber parameter from 1 to 12
  # decrease value of parameter by 1 to compensate
  incr number -1
  set month [lindex $nameList $number]
  return $month
}


set n 5
set m [nameMonth $n]
echo $m

