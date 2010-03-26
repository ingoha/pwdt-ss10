# 16L.5

proc smallerNumbers { l max } {
  set outlist {}
  foreach e $l {
    if {$e < $max} { 
      lappend outlist $e 
    }
  }
  return $outlist
}

# test it

set numlist {1 56 23 89 56 13 05 13 59 28 46}
echo 40 - [smallerNumbers $numlist 40]
echo 20 - [smallerNumbers $numlist 20]

