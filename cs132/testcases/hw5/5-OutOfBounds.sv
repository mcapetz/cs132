func main()
s3 = 4
s2 = alloc(s3)
if0 s2 goto null_err
s3 = 4
s1 = alloc(s3)
if0 s1 goto null_err
s3 = @Arun
[s1 + 0] = s3
[s2 + 0] = s1
if0 s2 goto null_err
s3 = [s2 + 0]
s3 = [s3 + 0]
a2 = s2
s1 = call s3()
print(s1)
goto main_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
main_end:
s1 = 0
id5 = s1
      return id5

func Arun()
t0 = a2
t1 = 20
t2 = 0
t0 = t1 < t2
t2 = 1
t0 = t2 - t0
if0 t0 goto array_err
t0 = t1 + t2
t2 = 4
t0 = t2 * t0
t2 = alloc(t0)
if0 t2 goto null_err
[t2 + 0] = t1
t0 = t2
t1 = 10
if0 t0 goto null_err
t3 = [t0 + 0]
t2 = 1
t5 = 0
t5 = t5 - t2
t4 = t5 < t1
if0 t4 goto array_err
t4 = t1 < t3
if0 t4 goto array_err
t4 = 4
t3 = t1 + t2
t3 = t3 * t4
t2 = t0 + t3
t1 = [t2 + 0]
print(t1)
t3 = 40
if0 t0 goto null_err
t1 = [t0 + 0]
t4 = 1
t5 = 0
t5 = t5 - t4
t2 = t5 < t3
if0 t2 goto array_err
t2 = t3 < t1
if0 t2 goto array_err
t2 = 4
t1 = t3 + t4
t1 = t1 * t2
t2 = t0 + t1
t0 = [t2 + 0]
goto Arun_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
Arun_end:
id21 = t0
      return id21


