func main()
t3 = 4
t2 = alloc(t3)
if0 t2 goto null_err
t3 = 4
t1 = alloc(t3)
if0 t1 goto null_err
t3 = @Arun
[t1 + 0] = t3
[t2 + 0] = t1
if0 t2 goto null_err
t3 = [t2 + 0]
t3 = [t3 + 0]
a2 = t2
t1 = call t3()
print(t1)
goto main_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
main_end:
t1 = 0
id5 = t1
      return id5

func Arun()
t0 = a2
t0 = 42
print(t0)
t0 = 99
goto Arun_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
Arun_end:
id7 = t0
      return id7


