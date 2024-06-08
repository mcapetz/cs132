func main()
s5 = 12
t1 = alloc(s5)
if0 t1 goto null_err
s5 = 16
t3 = alloc(s5)
if0 t3 goto null_err
s5 = @QSPrint
[t3 + 0] = s5
s5 = @QSInit
[t3 + 4] = s5
s5 = @QSStart
[t3 + 8] = s5
s5 = @QSSort
[t3 + 12] = s5
[t1 + 0] = t3
if0 t1 goto null_err
s5 = [t1 + 0]
s5 = [s5 + 8]
t3 = 10
a2 = t1
a3 = t3
s7 = call s5()
print(s7)
goto main_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds 1")
main_end:
t1 = 0
id9 = t1
      return id9

func QSStart()
s10 = a2
s11 = a3
if0 s10 goto null_err
s9 = [s10 + 0]
s9 = [s9 + 4]
this = s10
a2 = s10
a3 = s11
s1 = call s9()
s10 = this
s9 = s1
if0 s10 goto null_err
s1 = [s10 + 0]
s1 = [s1 + 0]
this = s10
id11 = s9
a2 = s10
s11 = call s1()
s10 = this
s9 = id11
s9 = s11
s11 = 9999
print(s11)
s4 = [s10 + 8]
s1 = 1
s11 = s4 - s1
s9 = s11
if0 s10 goto null_err
s11 = [s10 + 0]
s11 = [s11 + 12]
s1 = 0
this = s10
id11 = s9
a2 = s10
a3 = s1
a4 = s9
s4 = call s11()
s10 = this
s9 = id11
s9 = s4
if0 s10 goto null_err
s1 = [s10 + 0]
s1 = [s1 + 0]
id11 = s9
a2 = s10
s11 = call s1()
s9 = id11
s9 = s11
s10 = 0
goto QSStart_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds 2")
QSStart_end:
id25 = s10
      return id25

func QSSort()
s5 = a2
t3 = a3
t1 = a4
t0 = 0
s1 = t0
t0 = t3 < t1
if0 t0 goto elseid37
s2 = [s5 + 4]
if0 s2 goto null_err
t0 = [s2 + 0]
t4 = 1
t2 = 0
t2 = t2 - t4
t5 = t2 < t1
if0 t5 goto array_err1
t5 = t1 < t0
if0 t5 goto array_err1
t0 = 4
t2 = t1 + t4
t2 = t2 * t0
t0 = s2 + t2
t4 = [t0 + 0]
t2 = t4
t0 = 1
t4 = t3 - t0
t0 = t4
s4 = t1
t4 = 1
s2 = t4
loopid50:
if0 s2 goto endid50
t5 = 1
t4 = t5
loopid52:
if0 t4 goto endid52
s3 = 1
t5 = t0 + s3
t0 = t5
s6 = [s5 + 4]
if0 s6 goto null_err
s3 = [s6 + 0]
s8 = 1
s7 = 0
s7 = s7 - s8
t5 = s7 < t0
if0 t5 goto array_err2
t5 = t0 < s3
if0 t5 goto array_err2
s3 = 4
t5 = t0 + s8
t5 = t5 * s3
s3 = s6 + t5
s6 = [s3 + 0]
t5 = s6
s7 = t5 < t2
s6 = 1
s3 = s6 - s7
if0 s3 goto elseid64
s3 = 0
t4 = s3
goto endid64
elseid64:
s3 = 1
t4 = s3
endid64:
goto loopid52
endid52:
s3 = 1
t4 = s3
loopid70:
if0 t4 goto endid70
s6 = 1
s3 = s4 - s6
s4 = s3
s3 = [s5 + 4]
if0 s3 goto null_err
s6 = [s3 + 0]
print(s6)
s8 = 1
s7 = 0
s7 = s7 - s8
s9 = s7 < s4
if0 s9 goto array_err3
s9 = s4 < s6
if0 s9 goto array_err3
s6 = 4
s7 = s4 + s8
s7 = s7 * s6
s6 = s3 + s7
s3 = [s6 + 0]
t5 = s3
s7 = t2 < t5
s6 = 1
s3 = s6 - s7
if0 s3 goto elseid82
s3 = 0
t4 = s3
goto endid82
elseid82:
s3 = 1
t4 = s3
endid82:
goto loopid70
endid70:
s3 = [s5 + 4]
print(s3)
print(s3)
if0 s3 goto null_err
s6 = [s3 + 0]
print(s6)
t4 = 1
t2 = 0
t2 = t2 - t4
t5 = t2 < t0
if0 t5 goto array_err4
t5 = t0 < s6
if0 t5 goto array_err4
t2 = 4
t5 = t0 + t4
t5 = t5 * t2
t2 = s3 + t5
t4 = [t2 + 0]
s1 = t4
print(s1)
t2 = [s5 + 4]
s7 = [s5 + 4]
if0 s7 goto null_err
s6 = [s7 + 0]
t5 = 1
t4 = 0
t4 = t4 - t5
s3 = t4 < s4
if0 s3 goto array_err5
s3 = s4 < s6
if0 s3 goto array_err5
t4 = 4
s3 = s4 + t5
s3 = s3 * t4
t4 = s7 + s3
s7 = [t4 + 0]
if0 t2 goto null_err
t5 = [t2 + 0]
t4 = 1
s6 = 0
s6 = s6 - t4
s3 = s6 < t0
if0 s3 goto array_err6
s3 = t0 < t5
if0 s3 goto array_err6
t5 = 4
s3 = t0 + t4
s3 = s3 * t5
t4 = t2 + s3
[t4 + 0] = s7
t5 = [s5 + 4]
if0 t5 goto null_err
t4 = [t5 + 0]
s6 = 1
s3 = 0
s3 = s3 - s6
t2 = s3 < s4
if0 t2 goto array_err7
t2 = s4 < t4
if0 t2 goto array_err7
t2 = 4
t4 = s4 + s6
t4 = t4 * t2
t2 = t5 + t4
[t2 + 0] = s1
t2 = 1
t4 = t0 + t2
t2 = s4 < t4
if0 t2 goto elseid121
t2 = 0
s2 = t2
goto endid121
elseid121:
t2 = 1
s2 = t2
endid121:
goto loopid50
endid50:
t2 = [s5 + 4]
s6 = [s5 + 4]
if0 s6 goto null_err
s3 = [s6 + 0]
s2 = 1
t5 = 0
t5 = t5 - s2
t4 = t5 < t0
if0 t4 goto array_err8
t4 = t0 < s3
if0 t4 goto array_err8
t5 = 4
t4 = t0 + s2
t4 = t4 * t5
t5 = s6 + t4
s6 = [t5 + 0]
if0 t2 goto null_err
t5 = [t2 + 0]
s3 = 1
s2 = 0
s2 = s2 - s3
t4 = s2 < s4
if0 t4 goto array_err9
t4 = s4 < t5
if0 t4 goto array_err9
t4 = 4
t5 = s4 + s3
t5 = t5 * t4
t4 = t2 + t5
[t4 + 0] = s6
t2 = [s5 + 4]
s2 = [s5 + 4]
if0 s2 goto null_err
t4 = [s2 + 0]
s3 = 1
s4 = 0
s4 = s4 - s3
t5 = s4 < t1
if0 t5 goto array_err10
t5 = t1 < t4
if0 t5 goto array_err10
t4 = 4
t5 = t1 + s3
t5 = t5 * t4
t4 = s2 + t5
s4 = [t4 + 0]
if0 t2 goto null_err
t5 = [t2 + 0]
t4 = 1
s3 = 0
s3 = s3 - t4
s2 = s3 < t0
if0 s2 goto array_err11
s2 = t0 < t5
if0 s2 goto array_err11
s2 = 4
t5 = t0 + t4
t5 = t5 * s2
t4 = t2 + t5
[t4 + 0] = s4
t5 = [s5 + 4]
if0 t5 goto null_err
t4 = [t5 + 0]
s3 = 1
s2 = 0
s2 = s2 - s3
t2 = s2 < t1
if0 t2 goto array_err12
t2 = t1 < t4
if0 t2 goto array_err12
t2 = 4
t4 = t1 + s3
t4 = t4 * t2
t2 = t5 + t4
[t2 + 0] = s1
if0 s5 goto null_err
t5 = [s5 + 0]
t5 = [t5 + 12]
t2 = 1
t4 = t0 - t2
id27 = t1
id29 = t0
this = s5
a2 = s5
a3 = t3
a4 = t4
t2 = call t5()
t1 = id27
t0 = id29
s5 = this
t5 = t2
if0 s5 goto null_err
t3 = [s5 + 0]
t3 = [t3 + 12]
t2 = 1
t4 = t0 + t2
id31 = t5
a2 = s5
a3 = t4
a4 = t1
t0 = call t3()
t5 = id31
t5 = t0
goto endid37
elseid37:
t0 = 0
t5 = t0
endid37:
t0 = 0
goto QSSort_end
null_err:
error("null pointer")
array_err1:
error("array index out of bounds 100")
array_err2:
error("array index out of bounds 200")
array_err3:
error("array index out of bounds 300")
array_err4:
error("array index out of bounds 400")
array_err5:
error("array index out of bounds 500")
array_err6:
error("array index out of bounds 600")
array_err7:
error("array index out of bounds 700")
array_err8:
error("array index out of bounds 800")
array_err9:
error("array index out of bounds 900")
array_err10:
error("array index out of bounds 1000")
array_err11:
error("array index out of bounds 1100")
array_err12:
error("array index out of bounds 1200")
array_err:
error("array index out of bounds 3")
QSSort_end:
id177 = t0
      return id177

func QSPrint()
s2 = a2
s8 = 0
t5 = s8
loopid180:
s8 = [s2 + 8]
t2 = t5 < s8
if0 t2 goto endid180
s6 = [s2 + 4]
if0 s6 goto null_err
s3 = [s6 + 0]
t2 = 1
s8 = 0
s8 = s8 - t2
t0 = s8 < t5
if0 t0 goto array_err
t0 = t5 < s3
if0 t0 goto array_err
s8 = 4
t0 = t5 + t2
t0 = t0 * s8
t2 = s6 + t0
s8 = [t2 + 0]
print(s8)
s8 = 1
t2 = t5 + s8
t5 = t2
goto loopid180
endid180:
s8 = 0
goto QSPrint_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds 4")
QSPrint_end:
id194 = s8
      return id194

func QSInit()
t4 = a2
s11 = a3
s9 = [t4 + 8]
[t4 + 8] = s11
s9 = [t4 + 4]
s10 = 0
s9 = s11 < s10
s10 = 1
s9 = s10 - s9
if0 s9 goto array_err
s9 = s11 + s10
s10 = 4
s9 = s10 * s9
s10 = alloc(s9)
if0 s10 goto null_err
[s10 + 0] = s11
[t4 + 4] = s10
s4 = [t4 + 4]
t3 = 0
s5 = 20
if0 s4 goto null_err
s9 = [s4 + 0]
s11 = 1
s1 = 0
s1 = s1 - s11
s10 = s1 < t3
if0 s10 goto array_err
s10 = t3 < s9
if0 s10 goto array_err
s9 = 4
s10 = t3 + s11
s10 = s10 * s9
s9 = s4 + s10
[s9 + 0] = s5
s4 = [t4 + 4]
s1 = 1
t3 = 7
if0 s4 goto null_err
s5 = [s4 + 0]
s10 = 1
s9 = 0
s9 = s9 - s10
s11 = s9 < s1
if0 s11 goto array_err
s11 = s1 < s5
if0 s11 goto array_err
s11 = 4
s9 = s1 + s10
s9 = s9 * s11
s10 = s4 + s9
[s10 + 0] = t3
s9 = [t4 + 4]
s4 = 2
s1 = 12
if0 s9 goto null_err
t3 = [s9 + 0]
s5 = 1
s11 = 0
s11 = s11 - s5
s10 = s11 < s4
if0 s10 goto array_err
s10 = s4 < t3
if0 s10 goto array_err
s10 = 4
s11 = s4 + s5
s11 = s11 * s10
s10 = s9 + s11
[s10 + 0] = s1
s11 = [t4 + 4]
s9 = 3
s4 = 18
if0 s11 goto null_err
s1 = [s11 + 0]
t3 = 1
s5 = 0
s5 = s5 - t3
s10 = s5 < s9
if0 s10 goto array_err
s10 = s9 < s1
if0 s10 goto array_err
s1 = 4
s10 = s9 + t3
s10 = s10 * s1
s9 = s11 + s10
[s9 + 0] = s4
s11 = [t4 + 4]
s9 = 4
s1 = 2
if0 s11 goto null_err
s10 = [s11 + 0]
s5 = 1
t3 = 0
t3 = t3 - s5
s4 = t3 < s9
if0 s4 goto array_err
s4 = s9 < s10
if0 s4 goto array_err
s4 = 4
s10 = s9 + s5
s10 = s10 * s4
s9 = s11 + s10
[s9 + 0] = s1
s11 = [t4 + 4]
s10 = 5
s1 = 11
if0 s11 goto null_err
s9 = [s11 + 0]
s5 = 1
s4 = 0
s4 = s4 - s5
t3 = s4 < s10
if0 t3 goto array_err
t3 = s10 < s9
if0 t3 goto array_err
s4 = 4
s9 = s10 + s5
s9 = s9 * s4
s10 = s11 + s9
[s10 + 0] = s1
s4 = [t4 + 4]
s9 = 6
s11 = 6
if0 s4 goto null_err
s10 = [s4 + 0]
s1 = 1
t3 = 0
t3 = t3 - s1
s5 = t3 < s9
if0 s5 goto array_err
s5 = s9 < s10
if0 s5 goto array_err
s5 = 4
s10 = s9 + s1
s10 = s10 * s5
s9 = s4 + s10
[s9 + 0] = s11
s5 = [t4 + 4]
s4 = 7
s11 = 9
if0 s5 goto null_err
s9 = [s5 + 0]
s1 = 1
s10 = 0
s10 = s10 - s1
t3 = s10 < s4
if0 t3 goto array_err
t3 = s4 < s9
if0 t3 goto array_err
s9 = 4
s10 = s4 + s1
s10 = s10 * s9
s9 = s5 + s10
[s9 + 0] = s11
s4 = [t4 + 4]
t3 = 8
s5 = 19
if0 s4 goto null_err
s10 = [s4 + 0]
s9 = 1
s1 = 0
s1 = s1 - s9
s11 = s1 < t3
if0 s11 goto array_err
s11 = t3 < s10
if0 s11 goto array_err
s11 = 4
s10 = t3 + s9
s10 = s10 * s11
s9 = s4 + s10
[s9 + 0] = s5
s1 = [t4 + 4]
s11 = 9
s5 = 5
if0 s1 goto null_err
s4 = [s1 + 0]
s9 = 1
t4 = 0
t4 = t4 - s9
s10 = t4 < s11
if0 s10 goto array_err
s10 = s11 < s4
if0 s10 goto array_err
t4 = 4
s10 = s11 + s9
s10 = s10 * t4
t4 = s1 + s10
[t4 + 0] = s5
t4 = 0
goto QSInit_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds 5")
QSInit_end:
id302 = t4
      return id302


