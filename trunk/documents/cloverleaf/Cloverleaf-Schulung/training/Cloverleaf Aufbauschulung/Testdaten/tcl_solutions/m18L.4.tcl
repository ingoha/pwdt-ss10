# M18L.4

set a 6
set b 0001

set c $b$a

# or, alternitively:
# set c $b
# append c $a

if { $c == [ expr { 4 * 4 } ] } {
  echo equal
} else {
  echo huh ? not equal.
  echo c = $c
  echo 4 * 4 = [ expr { 4 * 4 } ]
}

# To clarify the problem, try the following:

# Now try increasing the value of c with 1
echo 1st attempt: $c + 1 = [ expr { $c + 1 } ]

# trim all leading zeroes, take care not to trim 'too much'.
set c [ string trimleft $c "0" ]
if [cequal $c {}] {set c 0}
echo 2nd attempt: $c + 1 = [ expr { $c + 1 } ]
