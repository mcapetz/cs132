func main()
t4 = 4
t3 = alloc(t4)
if0 t3 goto null_err
//t4 = 4
t2 = alloc(t4)
if0 t2 goto null_err
t4 = @Arun
[t2 + 0] = t4
[t3 + 0] = t2
if0 t3 goto null_err
t4 = [t3 + 0]
t4 = [t4 + 0]
id0 = t3
t2 = call t4(id0)
print(t2)
goto main_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
main_end:
t2 = 0
id5 = t2
      return id5

func Arun(this)
stack_1 = t2
// t2 = this
t2 = 42
print(t2)
t2 = 99
goto Arun_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
Arun_end:
id7 = t2
t2 = stack_1
      return id7


