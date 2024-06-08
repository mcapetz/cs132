func main()
s9 = 12
s8 = alloc(s9)
if0 s8 goto null_err
s9 = 16
s7 = alloc(s9)
if0 s7 goto null_err
s9 = @BBSPrint
[s7 + 0] = s9
s9 = @BBSInit
[s7 + 4] = s9
s9 = @BBSStart
[s7 + 8] = s9
s9 = @BBSSort
[s7 + 12] = s9
[s8 + 0] = s7
if0 s8 goto null_err
s7 = [s8 + 0]
s7 = [s7 + 8]
s9 = 10
a2 = s8
a3 = s9
s10 = call s7()
print(s10)
goto main_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
main_end:
s7 = 0
id9 = s7
      return id9

func BBSStart()
t3 = a2
s6 = a3
if0 t3 goto null_err
s3 = [t3 + 0]
s3 = [s3 + 4]
this = t3
a2 = t3
a3 = s6
s4 = call s3()
t3 = this
s3 = s4
if0 t3 goto null_err
s4 = [t3 + 0]
s4 = [s4 + 0]
this = t3
id11 = s3
a2 = t3
s6 = call s4()
t3 = this
s3 = id11
s3 = s6
s6 = 99999
print(s6)
if0 t3 goto null_err
s6 = [t3 + 0]
s6 = [s6 + 12]
this = t3
id11 = s3
a2 = t3
s4 = call s6()
t3 = this
s3 = id11
s3 = s4
if0 t3 goto null_err
s4 = [t3 + 0]
s4 = [s4 + 0]
id11 = s3
a2 = t3
s6 = call s4()
s3 = id11
s3 = s6
t3 = 0
goto BBSStart_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
BBSStart_end:
id21 = t3
      return id21

func BBSSort()
t4 = a2
t2 = [t4 + 8]
t0 = 1
t1 = t2 - t0
t0 = t1
t1 = 0
t3 = 1
t2 = t1 - t3
t1 = t2
loopid37:
t2 = t1 < t0
if0 t2 goto endid37
t2 = 1
t5 = t2
loopid40:
t3 = 1
t2 = t0 + t3
t3 = t5 < t2
if0 t3 goto endid40
t2 = 1
t3 = t5 - t2
t2 = t3
s4 = [t4 + 4]
if0 s4 goto null_err
s3 = [s4 + 0]
s2 = 1
s1 = 0
s1 = s1 - s2
t3 = s1 < t2
if0 t3 goto array_err
t3 = t2 < s3
if0 t3 goto array_err
s1 = 4
t3 = t2 + s2
t3 = t3 * s1
t2 = s4 + t3
t3 = [t2 + 0]
s1 = t3
s2 = [t4 + 4]
if0 s2 goto null_err
t3 = [s2 + 0]
s4 = 1
s3 = 0
s3 = s3 - s4
t2 = s3 < t5
if0 t2 goto array_err
t2 = t5 < t3
if0 t2 goto array_err
t3 = 4
t2 = t5 + s4
t2 = t2 * t3
t3 = s2 + t2
t2 = [t3 + 0]
t3 = t2
t2 = t3 < s1
if0 t2 goto elseid64
t2 = 1
t3 = t5 - t2
s1 = t3
s3 = [t4 + 4]
if0 s3 goto null_err
s4 = [s3 + 0]
t3 = 1
t2 = 0
t2 = t2 - t3
s2 = t2 < s1
if0 s2 goto array_err
s2 = s1 < s4
if0 s2 goto array_err
t2 = 4
s2 = s1 + t3
s2 = s2 * t2
t2 = s3 + s2
s2 = [t2 + 0]
t3 = s2
t2 = [t4 + 4]
s3 = [t4 + 4]
if0 s3 goto null_err
s2 = [s3 + 0]
s4 = 1
s6 = 0
s6 = s6 - s4
s5 = s6 < t5
if0 s5 goto array_err
s5 = t5 < s2
if0 s5 goto array_err
s5 = 4
s2 = t5 + s4
s2 = s2 * s5
s4 = s3 + s2
s2 = [s4 + 0]
if0 t2 goto null_err
s3 = [t2 + 0]
s5 = 1
s4 = 0
s4 = s4 - s5
s6 = s4 < s1
if0 s6 goto array_err
s6 = s1 < s3
if0 s6 goto array_err
s4 = 4
s3 = s1 + s5
s3 = s3 * s4
s1 = t2 + s3
[s1 + 0] = s2
s1 = [t4 + 4]
if0 s1 goto null_err
s2 = [s1 + 0]
t2 = 1
s4 = 0
s4 = s4 - t2
s3 = s4 < t5
if0 s3 goto array_err
s3 = t5 < s2
if0 s3 goto array_err
s3 = 4
s2 = t5 + t2
s2 = s2 * s3
t2 = s1 + s2
[t2 + 0] = t3
goto endid64
elseid64:
t2 = 0
t3 = t2
endid64:
t2 = 1
t3 = t5 + t2
t5 = t3
goto loopid40
endid40:
t2 = 1
t3 = t0 - t2
t0 = t3
goto loopid37
endid37:
t0 = 0
goto BBSSort_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
BBSSort_end:
id106 = t0
      return id106

func BBSPrint()
s9 = a2
t2 = 0
s10 = t2
loopid109:
s8 = [s9 + 8]
t2 = s10 < s8
if0 t2 goto endid109
s7 = [s9 + 4]
if0 s7 goto null_err
s8 = [s7 + 0]
s11 = 1
t5 = 0
t5 = t5 - s11
t2 = t5 < s10
if0 t2 goto array_err
t2 = s10 < s8
if0 t2 goto array_err
t2 = 4
s8 = s10 + s11
s8 = s8 * t2
t2 = s7 + s8
s8 = [t2 + 0]
print(s8)
s8 = 1
t2 = s10 + s8
s10 = t2
goto loopid109
endid109:
t2 = 0
goto BBSPrint_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
BBSPrint_end:
id123 = t2
      return id123

func BBSInit()
s11 = a2
t1 = a3
t5 = [s11 + 8]
[s11 + 8] = t1
t5 = [s11 + 4]
t4 = 0
t5 = t1 < t4
t4 = 1
t5 = t4 - t5
if0 t5 goto array_err
t5 = t1 + t4
t4 = 4
t5 = t4 * t5
t4 = alloc(t5)
if0 t4 goto null_err
[t4 + 0] = t1
[s11 + 4] = t4
t4 = [s11 + 4]
t5 = 0
t0 = 20
if0 t4 goto null_err
t1 = [t4 + 0]
s2 = 1
s1 = 0
s1 = s1 - s2
s5 = s1 < t5
if0 s5 goto array_err
s5 = t5 < t1
if0 s5 goto array_err
s1 = 4
t1 = t5 + s2
t1 = t1 * s1
t5 = t4 + t1
[t5 + 0] = t0
s1 = [s11 + 4]
t5 = 1
t4 = 7
if0 s1 goto null_err
t0 = [s1 + 0]
t1 = 1
s5 = 0
s5 = s5 - t1
s2 = s5 < t5
if0 s2 goto array_err
s2 = t5 < t0
if0 s2 goto array_err
s2 = 4
t0 = t5 + t1
t0 = t0 * s2
t5 = s1 + t0
[t5 + 0] = t4
s2 = [s11 + 4]
s1 = 2
t4 = 12
if0 s2 goto null_err
t5 = [s2 + 0]
t0 = 1
t1 = 0
t1 = t1 - t0
s5 = t1 < s1
if0 s5 goto array_err
s5 = s1 < t5
if0 s5 goto array_err
t5 = 4
t1 = s1 + t0
t1 = t1 * t5
t5 = s2 + t1
[t5 + 0] = t4
s1 = [s11 + 4]
s5 = 3
s2 = 18
if0 s1 goto null_err
t4 = [s1 + 0]
t5 = 1
t0 = 0
t0 = t0 - t5
t1 = t0 < s5
if0 t1 goto array_err
t1 = s5 < t4
if0 t1 goto array_err
t1 = 4
t4 = s5 + t5
t4 = t4 * t1
t5 = s1 + t4
[t5 + 0] = s2
s1 = [s11 + 4]
t0 = 4
s5 = 2
if0 s1 goto null_err
s2 = [s1 + 0]
t4 = 1
t5 = 0
t5 = t5 - t4
t1 = t5 < t0
if0 t1 goto array_err
t1 = t0 < s2
if0 t1 goto array_err
t5 = 4
t1 = t0 + t4
t1 = t1 * t5
t5 = s1 + t1
[t5 + 0] = s5
s5 = [s11 + 4]
t0 = 5
t1 = 11
if0 s5 goto null_err
s2 = [s5 + 0]
s1 = 1
t4 = 0
t4 = t4 - s1
t5 = t4 < t0
if0 t5 goto array_err
t5 = t0 < s2
if0 t5 goto array_err
t4 = 4
t5 = t0 + s1
t5 = t5 * t4
t4 = s5 + t5
[t4 + 0] = t1
s5 = [s11 + 4]
s2 = 6
t1 = 6
if0 s5 goto null_err
t4 = [s5 + 0]
s1 = 1
t0 = 0
t0 = t0 - s1
t5 = t0 < s2
if0 t5 goto array_err
t5 = s2 < t4
if0 t5 goto array_err
t5 = 4
t4 = s2 + s1
t4 = t4 * t5
t5 = s5 + t4
[t5 + 0] = t1
s1 = [s11 + 4]
t0 = 7
s5 = 9
if0 s1 goto null_err
s2 = [s1 + 0]
t1 = 1
t5 = 0
t5 = t5 - t1
t4 = t5 < t0
if0 t4 goto array_err
t4 = t0 < s2
if0 t4 goto array_err
t5 = 4
t4 = t0 + t1
t4 = t4 * t5
t5 = s1 + t4
[t5 + 0] = s5
t4 = [s11 + 4]
s1 = 8
t0 = 19
if0 t4 goto null_err
s5 = [t4 + 0]
s2 = 1
t1 = 0
t1 = t1 - s2
t5 = t1 < s1
if0 t5 goto array_err
t5 = s1 < s5
if0 t5 goto array_err
t5 = 4
t1 = s1 + s2
t1 = t1 * t5
t5 = t4 + t1
[t5 + 0] = t0
t5 = [s11 + 4]
s11 = 9
t0 = 5
if0 t5 goto null_err
t1 = [t5 + 0]
s2 = 1
s1 = 0
s1 = s1 - s2
t4 = s1 < s11
if0 t4 goto array_err
t4 = s11 < t1
if0 t4 goto array_err
t4 = 4
t1 = s11 + s2
t1 = t1 * t4
s11 = t5 + t1
[s11 + 0] = t0
s11 = 0
goto BBSInit_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
BBSInit_end:
id231 = s11
      return id231


