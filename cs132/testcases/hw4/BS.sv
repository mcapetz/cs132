func main()
stack_1 = t3
stack_2 = t2
stack_3 = s4
stack_4 = t5
t3 = 12
t2 = alloc(t3)
if0 t2 goto null_err
t3 = 16
t5 = alloc(t3)
if0 t5 goto null_err
t3 = @BBSPrint
[t5 + 0] = t3
t3 = @BBSInit
[t5 + 4] = t3
t3 = @BBSStart
[t5 + 8] = t3
t3 = @BBSSort
[t5 + 12] = t3
[t2 + 0] = t5
if0 t2 goto null_err
t5 = [t2 + 0]
t5 = [t5 + 8]
t3 = 10
id0 = t2
id8 = t3
s4 = call t5(id0 id8)
print(s4)
goto main_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
main_end:
s4 = 0
id9 = s4
t3 = stack_1
t2 = stack_2
s4 = stack_3
t5 = stack_4
      return id9

func BBSStart(this id10)
stack_1 = t3
stack_2 = s7
stack_3 = t5
stack_4 = s8
s7 = this
s8 = id10
if0 s7 goto null_err
t5 = [s7 + 0]
t5 = [t5 + 4]
this = s7
id10 = s8
t3 = call t5(this id10)
t5 = t3
if0 s7 goto null_err
t3 = [s7 + 0]
t3 = [t3 + 0]
this = s7
s8 = call t3(this)
t5 = s8
s8 = 99999
print(s8)
if0 s7 goto null_err
s8 = [s7 + 0]
s8 = [s8 + 12]
this = s7
t3 = call s8(this)
t5 = t3
if0 s7 goto null_err
t3 = [s7 + 0]
t3 = [t3 + 0]
this = s7
s8 = call t3(this)
t5 = s8
t5 = 0
goto BBSStart_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
BBSStart_end:
id21 = t5
t3 = stack_1
s7 = stack_2
t5 = stack_3
s8 = stack_4
      return id21

func BBSSort(this)
stack_1 = s1
stack_2 = t3
stack_3 = t4
stack_4 = s6
stack_5 = s5
stack_6 = t2
stack_7 = s4
stack_8 = s7
stack_9 = s2
stack_10 = t5
stack_11 = s3
stack_12 = s8
t2 = this
t3 = [t2 + 8] // t2 = this
t4 = 1
t5 = t3 - t4
t4 = t5
t5 = 0
t3 = 1
s1 = t5 - t3
t3 = s1
loopid37:
s1 = t3 < t4
if0 s1 goto endid37
s1 = 1
t5 = s1
loopid40:
s1 = 1
s2 = t4 + s1
s1 = t5 < s2
if0 s1 goto endid40
s1 = 1
s2 = t5 - s1
s1 = s2
t2 = this // i added this
s2 = [t2 + 4] // here is the error
if0 s2 goto null_err
s3 = [s2 + 0]
s4 = 1
s5 = 0
s5 = s5 - s4
s6 = s5 < s1
if0 s6 goto array_err
s6 = s1 < s3
if0 s6 goto array_err
s6 = 4
s3 = s1 + s4
s3 = s3 * s6
s6 = s2 + s3
s2 = [s6 + 0]
s6 = s2
s2 = [t2 + 4]
if0 s2 goto null_err
s3 = [s2 + 0]
s1 = 1
s4 = 0
s4 = s4 - s1
s5 = s4 < t5
if0 s5 goto array_err
s5 = t5 < s3
if0 s5 goto array_err
s5 = 4
s3 = t5 + s1
s3 = s3 * s5
s5 = s2 + s3
s2 = [s5 + 0]
s5 = s2
s2 = s5 < s6
if0 s2 goto elseid64
s2 = 1
s6 = t5 - s2
s2 = s6
s6 = [t2 + 4]
if0 s6 goto null_err
s5 = [s6 + 0]
s3 = 1
s1 = 0
s1 = s1 - s3
s4 = s1 < s2
if0 s4 goto array_err
s4 = s2 < s5
if0 s4 goto array_err
s4 = 4
s5 = s2 + s3
s5 = s5 * s4
s4 = s6 + s5
s6 = [s4 + 0]
s4 = s6
s6 = [t2 + 4]
s5 = [t2 + 4]
if0 s5 goto null_err
s3 = [s5 + 0]
s1 = 1
s7 = 0
s7 = s7 - s1
s8 = s7 < t5
if0 s8 goto array_err
s8 = t5 < s3
if0 s8 goto array_err
s8 = 4
s3 = t5 + s1
s3 = s3 * s8
s8 = s5 + s3
s5 = [s8 + 0]
if0 s6 goto null_err
s8 = [s6 + 0]
s3 = 1
s1 = 0
s1 = s1 - s3
s7 = s1 < s2
if0 s7 goto array_err
s7 = s2 < s8
if0 s7 goto array_err
s7 = 4
s8 = s2 + s3
s8 = s8 * s7
s7 = s6 + s8
[s7 + 0] = s5
s7 = [t2 + 4]
if0 s7 goto null_err
s5 = [s7 + 0]
s6 = 1
s8 = 0
s8 = s8 - s6
s3 = s8 < t5
if0 s3 goto array_err
s3 = t5 < s5
if0 s3 goto array_err
s3 = 4
s5 = t5 + s6
s5 = s5 * s3
s3 = s7 + s5
[s3 + 0] = s4
goto endid64
elseid64:
s4 = 0
s3 = s4
endid64:
s3 = 1
s4 = t5 + s3
t5 = s4
goto loopid40
endid40:
t5 = 1
t2 = t4 - t5
t4 = t2
goto loopid37
endid37:
t4 = 0
goto BBSSort_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
BBSSort_end:
id106 = t4
s1 = stack_1
t3 = stack_2
t4 = stack_3
s6 = stack_4
s5 = stack_5
t2 = stack_6
s4 = stack_7
s7 = stack_8
s2 = stack_9
t5 = stack_10
s3 = stack_11
s8 = stack_12
      return id106

func BBSPrint(this)
stack_1 = t3
stack_2 = s6
stack_3 = s5
stack_4 = s7
stack_5 = s2
stack_6 = s3
stack_7 = s8
s8 = this
t3 = 0
s7 = t3
loopid109:
t3 = [s8 + 8]
s6 = s7 < t3
if0 s6 goto endid109
s6 = [s8 + 4]
if0 s6 goto null_err
t3 = [s6 + 0]
s5 = 1
s3 = 0
s3 = s3 - s5
s2 = s3 < s7
if0 s2 goto array_err
s2 = s7 < t3
if0 s2 goto array_err
s2 = 4
t3 = s7 + s5
t3 = t3 * s2
s2 = s6 + t3
t3 = [s2 + 0]
print(t3)
t3 = 1
s2 = s7 + t3
s7 = s2
goto loopid109
endid109:
s8 = 0
goto BBSPrint_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
BBSPrint_end:
id123 = s8
t3 = stack_1
s6 = stack_2
s5 = stack_3
s7 = stack_4
s2 = stack_5
s3 = stack_6
s8 = stack_7
      return id123

func BBSInit(this id124)
stack_1 = t3
stack_2 = s6
stack_3 = t2
stack_4 = s5
stack_5 = s7
stack_6 = t5
stack_7 = s3
stack_8 = s8
t3 = this
t5 = id124
t2 = [t3 + 8]
[t3 + 8] = t5
t2 = [t3 + 4]
t2 = 0
s3 = t5 < t2
t2 = 1
s3 = t2 - s3
if0 s3 goto array_err
s3 = t5 + t2
t2 = 4
s3 = t2 * s3
t2 = alloc(s3)
if0 t2 goto null_err
[t2 + 0] = t5
[t3 + 4] = t2
t2 = [t3 + 4]
t5 = 0
s3 = 20
if0 t2 goto null_err
s7 = [t2 + 0]
s5 = 1
s6 = 0
s6 = s6 - s5
s8 = s6 < t5
if0 s8 goto array_err
s8 = t5 < s7
if0 s8 goto array_err
s8 = 4
s7 = t5 + s5
s7 = s7 * s8
s8 = t2 + s7
[s8 + 0] = s3
s8 = [t3 + 4]
s3 = 1
s7 = 7
if0 s8 goto null_err
t2 = [s8 + 0]
s5 = 1
t5 = 0
t5 = t5 - s5
s6 = t5 < s3
if0 s6 goto array_err
s6 = s3 < t2
if0 s6 goto array_err
s6 = 4
t2 = s3 + s5
t2 = t2 * s6
s6 = s8 + t2
[s6 + 0] = s7
s6 = [t3 + 4]
s7 = 2
t2 = 12
if0 s6 goto null_err
s8 = [s6 + 0]
s5 = 1
s3 = 0
s3 = s3 - s5
t5 = s3 < s7
if0 t5 goto array_err
t5 = s7 < s8
if0 t5 goto array_err
t5 = 4
s8 = s7 + s5
s8 = s8 * t5
t5 = s6 + s8
[t5 + 0] = t2
t5 = [t3 + 4]
t2 = 3
s8 = 18
if0 t5 goto null_err
s6 = [t5 + 0]
s5 = 1
s7 = 0
s7 = s7 - s5
s3 = s7 < t2
if0 s3 goto array_err
s3 = t2 < s6
if0 s3 goto array_err
s3 = 4
s6 = t2 + s5
s6 = s6 * s3
s3 = t5 + s6
[s3 + 0] = s8
s3 = [t3 + 4]
s8 = 4
s6 = 2
if0 s3 goto null_err
t5 = [s3 + 0]
s5 = 1
t2 = 0
t2 = t2 - s5
s7 = t2 < s8
if0 s7 goto array_err
s7 = s8 < t5
if0 s7 goto array_err
s7 = 4
t5 = s8 + s5
t5 = t5 * s7
s7 = s3 + t5
[s7 + 0] = s6
s7 = [t3 + 4]
s6 = 5
t5 = 11
if0 s7 goto null_err
s3 = [s7 + 0]
s5 = 1
s8 = 0
s8 = s8 - s5
t2 = s8 < s6
if0 t2 goto array_err
t2 = s6 < s3
if0 t2 goto array_err
t2 = 4
s3 = s6 + s5
s3 = s3 * t2
t2 = s7 + s3
[t2 + 0] = t5
t2 = [t3 + 4]
t5 = 6
s3 = 6
if0 t2 goto null_err
s7 = [t2 + 0]
s5 = 1
s6 = 0
s6 = s6 - s5
s8 = s6 < t5
if0 s8 goto array_err
s8 = t5 < s7
if0 s8 goto array_err
s8 = 4
s7 = t5 + s5
s7 = s7 * s8
s8 = t2 + s7
[s8 + 0] = s3
s8 = [t3 + 4]
s3 = 7
s7 = 9
if0 s8 goto null_err
t2 = [s8 + 0]
s5 = 1
t5 = 0
t5 = t5 - s5
s6 = t5 < s3
if0 s6 goto array_err
s6 = s3 < t2
if0 s6 goto array_err
s6 = 4
t2 = s3 + s5
t2 = t2 * s6
s6 = s8 + t2
[s6 + 0] = s7
s6 = [t3 + 4]
s7 = 8
t2 = 19
if0 s6 goto null_err
s8 = [s6 + 0]
s5 = 1
s3 = 0
s3 = s3 - s5
t5 = s3 < s7
if0 t5 goto array_err
t5 = s7 < s8
if0 t5 goto array_err
t5 = 4
s8 = s7 + s5
s8 = s8 * t5
t5 = s6 + s8
[t5 + 0] = t2
t5 = [t3 + 4]
t3 = 9
t2 = 5
if0 t5 goto null_err
s8 = [t5 + 0]
s6 = 1
s5 = 0
s5 = s5 - s6
s7 = s5 < t3
if0 s7 goto array_err
s7 = t3 < s8
if0 s7 goto array_err
s7 = 4
s8 = t3 + s6
s8 = s8 * s7
s7 = t5 + s8
[s7 + 0] = t2
t2 = 0
goto BBSInit_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
BBSInit_end:
id231 = t2
t3 = stack_1
s6 = stack_2
t2 = stack_3
s5 = stack_4
s7 = stack_5
t5 = stack_6
s3 = stack_7
s8 = stack_8
      return id231


