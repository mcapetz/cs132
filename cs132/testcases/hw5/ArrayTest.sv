func main()
t1 = 0
t0 = t1
t2 = 6
t1 = 0
t3 = t2 < t1
t1 = 1
t3 = t1 - t3
if0 t3 goto array_err
t3 = t2 + t1
t1 = 4
t3 = t1 * t3
t1 = alloc(t3)
if0 t1 goto null_err
[t1 + 0] = t2
t2 = t1
if0 t2 goto null_err
t3 = [t2 + 0]
t1 = t3
loopid10:
t3 = t0 < t1
if0 t3 goto endid10
if0 t2 goto null_err
t4 = [t2 + 0]
t3 = 1
s1 = 0
s1 = s1 - t3
t5 = s1 < t0
if0 t5 goto array_err
t5 = t0 < t4
if0 t5 goto array_err
t5 = 4
t4 = t0 + t3
t4 = t4 * t5
t3 = t2 + t4
[t3 + 0] = t0
t3 = 1
t4 = t0 + t3
t0 = t4
goto loopid10
endid10:
t3 = 1
t4 = t1 - t3
t0 = t4
t3 = 0
t5 = 1
t4 = t3 - t5
t1 = t4
loopid26:
t3 = t1 < t0
if0 t3 goto endid26
if0 t2 goto null_err
s1 = [t2 + 0]
t4 = 1
t3 = 0
t3 = t3 - t4
t5 = t3 < t0
if0 t5 goto array_err
t5 = t0 < s1
if0 t5 goto array_err
t3 = 4
t5 = t0 + t4
t5 = t5 * t3
t3 = t2 + t5
t4 = [t3 + 0]
print(t4)
t3 = 1
t4 = t0 - t3
t0 = t4
goto loopid26
endid26:
t5 = 10
if0 t2 goto null_err
t0 = [t2 + 0]
t3 = 1
t1 = 0
t1 = t1 - t3
t4 = t1 < t5
if0 t4 goto array_err
t4 = t5 < t0
if0 t4 goto array_err
t0 = 4
t1 = t5 + t3
t1 = t1 * t0
t0 = t2 + t1
t1 = [t0 + 0]
print(t1)
goto main_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
main_end:
t0 = 0
id47 = t0
      return id47


