func Main()
s3 = 4
s2 = alloc(s3)
s1 = alloc(s3)
s3 = @FacComputeFac
[s1 + 0] = s3
s3 = s1
[s2 + 0] = s3
if0 s2 goto null1
s1 = [s2 + 0]
s1 = [s1 + 0]
s3 = 6
v0 = s3
a2 = s2
a3 = s3
s4 = call s1()
s3 = v0
print(s4)
goto main_end
null1:
error("null pointer")
main_end:
v0 = s3
      return v0

func FacComputeFac()
t1 = a2
t0 = a3
t3 = 1
t2 = t0 < t3
if0 t2 goto if1_else
t2 = 1
goto if1_end
if1_else:
t4 = [t1 + 0]
t4 = [t4 + 0]
t3 = 1
t5 = t0 - t3
num = t0
num_aux = t2
a2 = t1
a3 = t5
t3 = call t4()
t0 = num
t2 = num_aux
t2 = t0 * t3
if1_end:
num_aux = t2
      return num_aux


