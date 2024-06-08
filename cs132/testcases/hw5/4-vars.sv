func main()
s6 = 4
s5 = alloc(s6)
if0 s5 goto null_err
s6 = 8
s4 = alloc(s6)
if0 s4 goto null_err
s6 = @Ahelper
[s4 + 0] = s6
s6 = @Arun
[s4 + 4] = s6
[s5 + 0] = s4
if0 s5 goto null_err
s4 = [s5 + 0]
s4 = [s4 + 4]
a2 = s5
s6 = call s4()
print(s6)
goto main_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
main_end:
s4 = 0
id6 = s4
      return id6

func Arun()
t4 = a2
if0 t4 goto null_err
s2 = [t4 + 0]
s2 = [s2 + 0]
t5 = 12
this = t4
a2 = t4
a3 = t5
s1 = call s2()
t4 = this
s2 = s1
if0 t4 goto null_err
s1 = [t4 + 0]
s1 = [s1 + 0]
s3 = 15
id7 = s2
a2 = t4
a3 = s3
t5 = call s1()
s2 = id7
t4 = t5
t5 = s2 + t4
goto Arun_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
Arun_end:
id15 = t5
      return id15

func Ahelper()
t0 = a2
t1 = a3
t0 = t1
t2 = 1
t3 = t1 + t2
t1 = t3
print(t0)
goto Ahelper_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
Ahelper_end:
id17 = t0
      return id17


