func main()
s3 = 4
s4 = alloc(s3)
if0 s4 goto null_err
s3 = 8
s2 = alloc(s3)
if0 s2 goto null_err
s3 = @MT4Start
[s2 + 0] = s3
s3 = @MT4Change
[s2 + 4] = s3
[s4 + 0] = s2
if0 s4 goto null_err
s5 = [s4 + 0]
s5 = [s5 + 0]
s6 = 1
s9 = 2
s8 = 3
s10 = 4
s3 = 5
s2 = 6
a2 = s4
a3 = s6
a4 = s9
a5 = s8
a6 = s10
a7 = s3
id11 = s2
s7 = call s5(id11)
print(s7)
goto main_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
main_end:
s2 = 0
id12 = s2
      return id12

func MT4Start(id18)
t2 = a2
t5 = a3
s2 = a4
s1 = a5
t0 = a6
s11 = a7
t1 = id18
print(t5)
print(s2)
print(s1)
print(t0)
print(s11)
print(t1)
if0 t2 goto null_err
t3 = [t2 + 0]
t3 = [t3 + 4]
a2 = t2
a3 = t1
a4 = s11
a5 = t0
a6 = s1
a7 = s2
id13 = t5
t4 = call t3(id13)
s11 = t4
goto MT4Start_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
MT4Start_end:
id19 = s11
      return id19

func MT4Change(id27)
t1 = a2
t2 = a3
t4 = a4
t3 = a5
s1 = a6
t5 = a7
t0 = id27
print(t2)
print(t4)
print(t3)
print(s1)
print(t5)
print(t0)
t0 = 0
goto MT4Change_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
MT4Change_end:
id28 = t0
      return id28


