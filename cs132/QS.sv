func main()
t3 = 12
t2 = alloc(t3)
if0 t2 goto null_err
t3 = 16
t4 = alloc(t3)
if0 t4 goto null_err
t3 = @QSPrint
[t4 + 0] = t3
t3 = @QSInit
[t4 + 4] = t3
t3 = @QSStart
[t4 + 8] = t3
t3 = @QSSort
[t4 + 12] = t3
[t2 + 0] = t4
if0 t2 goto null_err
t3 = [t2 + 0]
t3 = [t3 + 8]
t4 = 10
id0 = t2
id8 = t4
t5 = call t3(id0 id8)
print(t5)
goto main_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
main_end:
t2 = 0
id9 = t2
      return id9

func QSStart(this id10)
stack_1 = t2
stack_2 = t3
stack_3 = t4
stack_4 = t5
stack_5 = s1
t2 = this
t3 = id10
if0 t2 goto null_err
t4 = [t2 + 0]
t4 = [t4 + 4]
this = t2
id10 = t3
t5 = call t4(this id10)
t4 = t5
if0 t2 goto null_err
t5 = [t2 + 0]
t5 = [t5 + 0]
this = t2
t3 = call t5(this)
t4 = t3
t3 = 9999
print(t3)
s1 = [t2 + 8]
t5 = 1
t3 = s1 - t5
t4 = t3
if0 t2 goto null_err
t3 = [t2 + 0]
t3 = [t3 + 12]
t5 = 0
this = t2
id22 = t5
id11 = t4
s1 = call t3(this id22 id11)
t4 = s1
if0 t2 goto null_err
t5 = [t2 + 0]
t5 = [t5 + 0]
this = t2
t3 = call t5(this)
t4 = t3
t2 = 0
goto QSStart_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
QSStart_end:
id25 = t2
t2 = stack_1
t3 = stack_2
t4 = stack_3
t5 = stack_4
s1 = stack_5
      return id25

func QSSort(this id26 id27)
stack_1 = s3
stack_2 = s8
stack_3 = s9
stack_4 = t3
stack_5 = s1
stack_6 = t4
stack_7 = t5
stack_8 = s5
stack_9 = s4
stack_10 = s11
stack_11 = s7
stack_12 = s2
stack_13 = s6
stack_14 = t2
stack_15 = s10
s6 = this
t5 = id26
t3 = id27
t2 = 0
s3 = t2
t2 = t5 < t3
if0 t2 goto elseid37
s4 = [s6 + 4]
if0 s4 goto null_err
t2 = [s4 + 0]
s1 = 1
t4 = 0
t4 = t4 - s1
s2 = t4 < t3
if0 s2 goto array_err
s2 = t3 < t2
if0 s2 goto array_err
t2 = 4
t4 = t3 + s1
t4 = t4 * t2
t2 = s4 + t4
s1 = [t2 + 0]
t4 = s1
t2 = 1
s1 = t5 - t2
t2 = s1
s5 = t3
s1 = 1
s4 = s1
loopid50:
if0 s4 goto endid50
s2 = 1
s1 = s2
loopid52:
if0 s1 goto endid52
s7 = 1
s2 = t2 + s7
t2 = s2
s8 = [s6 + 4]
if0 s8 goto null_err
s7 = [s8 + 0]
s10 = 1
s9 = 0
s9 = s9 - s10
s2 = s9 < t2
if0 s2 goto array_err
s2 = t2 < s7
if0 s2 goto array_err
s7 = 4
s2 = t2 + s10
s2 = s2 * s7
s7 = s8 + s2
s8 = [s7 + 0]
s2 = s8
s9 = s2 < t4
s8 = 1
s7 = s8 - s9
if0 s7 goto elseid64
s7 = 0
s1 = s7
goto endid64
elseid64:
s7 = 1
s1 = s7
endid64:
goto loopid52
endid52:
s7 = 1
s1 = s7
loopid70:
if0 s1 goto endid70
s8 = 1
s7 = s5 - s8
s5 = s7
s8 = [s6 + 4]
if0 s8 goto null_err
s7 = [s8 + 0]
s10 = 1
s9 = 0
s9 = s9 - s10
s11 = s9 < s5
if0 s11 goto array_err
s11 = s5 < s7
if0 s11 goto array_err
s7 = 4
s9 = s5 + s10
s9 = s9 * s7
s10 = s8 + s9
s7 = [s10 + 0]
s2 = s7
s9 = t4 < s2
s8 = 1
s7 = s8 - s9
if0 s7 goto elseid82
s7 = 0
s1 = s7
goto endid82
elseid82:
s7 = 1
s1 = s7
endid82:
goto loopid70
endid70:
s7 = [s6 + 4]
if0 s7 goto null_err
s8 = [s7 + 0]
s1 = 1
t4 = 0
t4 = t4 - s1
s2 = t4 < t2
if0 s2 goto array_err
s2 = t2 < s8
if0 s2 goto array_err
t4 = 4
s2 = t2 + s1
s2 = s2 * t4
t4 = s7 + s2
s1 = [t4 + 0]
s3 = s1
t4 = [s6 + 4]
s9 = [s6 + 4]
if0 s9 goto null_err
s8 = [s9 + 0]
s2 = 1
s1 = 0
s1 = s1 - s2
s7 = s1 < s5
if0 s7 goto array_err
s7 = s5 < s8
if0 s7 goto array_err
s1 = 4
s7 = s5 + s2
s7 = s7 * s1
s1 = s9 + s7
s9 = [s1 + 0]
if0 t4 goto null_err
s2 = [t4 + 0]
s1 = 1
s8 = 0
s8 = s8 - s1
s7 = s8 < t2
if0 s7 goto array_err
s7 = t2 < s2
if0 s7 goto array_err
s2 = 4
s7 = t2 + s1
s7 = s7 * s2
s1 = t4 + s7
[s1 + 0] = s9
s2 = [s6 + 4]
if0 s2 goto null_err
s1 = [s2 + 0]
s8 = 1
s7 = 0
s7 = s7 - s8
t4 = s7 < s5
if0 t4 goto array_err
t4 = s5 < s1
if0 t4 goto array_err
t4 = 4
s1 = s5 + s8
s1 = s1 * t4
t4 = s2 + s1
[t4 + 0] = s3
t4 = 1
s1 = t2 + t4
t4 = s5 < s1
if0 t4 goto elseid121
t4 = 0
s4 = t4
goto endid121
elseid121:
t4 = 1
s4 = t4
endid121:
goto loopid50
endid50:
t4 = [s6 + 4]
s8 = [s6 + 4]
if0 s8 goto null_err
s7 = [s8 + 0]
s4 = 1
s2 = 0
s2 = s2 - s4
s1 = s2 < t2
if0 s1 goto array_err
s1 = t2 < s7
if0 s1 goto array_err
s2 = 4
s1 = t2 + s4
s1 = s1 * s2
s2 = s8 + s1
s8 = [s2 + 0]
if0 t4 goto null_err
s2 = [t4 + 0]
s7 = 1
s4 = 0
s4 = s4 - s7
s1 = s4 < s5
if0 s1 goto array_err
s1 = s5 < s2
if0 s1 goto array_err
s1 = 4
s2 = s5 + s7
s2 = s2 * s1
s1 = t4 + s2
[s1 + 0] = s8
t4 = [s6 + 4]
s4 = [s6 + 4]
if0 s4 goto null_err
s1 = [s4 + 0]
s5 = 1
s7 = 0
s7 = s7 - s5
s2 = s7 < t3
if0 s2 goto array_err
s2 = t3 < s1
if0 s2 goto array_err
s1 = 4
s2 = t3 + s5
s2 = s2 * s1
s1 = s4 + s2
s7 = [s1 + 0]
if0 t4 goto null_err
s2 = [t4 + 0]
s1 = 1
s5 = 0
s5 = s5 - s1
s4 = s5 < t2
if0 s4 goto array_err
s4 = t2 < s2
if0 s4 goto array_err
s4 = 4
s2 = t2 + s1
s2 = s2 * s4
s1 = t4 + s2
[s1 + 0] = s7
s2 = [s6 + 4]
if0 s2 goto null_err
s1 = [s2 + 0]
s5 = 1
s4 = 0
s4 = s4 - s5
t4 = s4 < t3
if0 t4 goto array_err
t4 = t3 < s1
if0 t4 goto array_err
t4 = 4
s1 = t3 + s5
s1 = s1 * t4
t4 = s2 + s1
[t4 + 0] = s3
if0 s6 goto null_err
s2 = [s6 + 0]
s2 = [s2 + 12]
t4 = 1
s1 = t2 - t4
this = s6
id26 = t5
id170 = s1
t4 = call s2(this id26 id170)
s2 = t4
if0 s6 goto null_err
t5 = [s6 + 0]
t5 = [t5 + 12]
t4 = 1
s1 = t2 + t4
this = s6
id174 = s1
id27 = t3
t2 = call t5(this id174 id27)
s2 = t2
goto endid37
elseid37:
t2 = 0
s2 = t2
endid37:
t2 = 0
goto QSSort_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
QSSort_end:
id177 = t2
s3 = stack_1
s8 = stack_2
s9 = stack_3
t3 = stack_4
s1 = stack_5
t4 = stack_6
t5 = stack_7
s5 = stack_8
s4 = stack_9
s11 = stack_10
s7 = stack_11
s2 = stack_12
s6 = stack_13
t2 = stack_14
s10 = stack_15
      return id177

func QSPrint(this)
stack_1 = s3
stack_2 = s2
stack_3 = t2
stack_4 = t3
stack_5 = t4
stack_6 = t5
stack_7 = s1
s3 = this
t2 = 0
t4 = t2
loopid180:
t2 = [s3 + 8]
t3 = t4 < t2
if0 t3 goto endid180
s2 = [s3 + 4]
if0 s2 goto null_err
s1 = [s2 + 0]
t3 = 1
t2 = 0
t2 = t2 - t3
t5 = t2 < t4
if0 t5 goto array_err
t5 = t4 < s1
if0 t5 goto array_err
t2 = 4
t5 = t4 + t3
t5 = t5 * t2
t3 = s2 + t5
t2 = [t3 + 0]
print(t2)
t2 = 1
t3 = t4 + t2
t4 = t3
goto loopid180
endid180:
t2 = 0
goto QSPrint_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
QSPrint_end:
id194 = t2
s3 = stack_1
s2 = stack_2
t2 = stack_3
t3 = stack_4
t4 = stack_5
t5 = stack_6
s1 = stack_7
      return id194

func QSInit(this id195)
stack_1 = s4
stack_2 = s3
stack_3 = s2
stack_4 = t2
stack_5 = t3
stack_6 = t4
stack_7 = t5
stack_8 = s1
t2 = this
t5 = id195
t3 = [t2 + 8]
[t2 + 8] = t5
t3 = [t2 + 4]
t4 = 0
t3 = t5 < t4
t4 = 1
t3 = t4 - t3
if0 t3 goto array_err
t3 = t5 + t4
t4 = 4
t3 = t4 * t3
t4 = alloc(t3)
if0 t4 goto null_err
[t4 + 0] = t5
[t2 + 4] = t4
s2 = [t2 + 4]
s4 = 0
s3 = 20
if0 s2 goto null_err
t3 = [s2 + 0]
t5 = 1
s1 = 0
s1 = s1 - t5
t4 = s1 < s4
if0 t4 goto array_err
t4 = s4 < t3
if0 t4 goto array_err
t3 = 4
t4 = s4 + t5
t4 = t4 * t3
t3 = s2 + t4
[t3 + 0] = s3
s2 = [t2 + 4]
s1 = 1
s4 = 7
if0 s2 goto null_err
s3 = [s2 + 0]
t4 = 1
t3 = 0
t3 = t3 - t4
t5 = t3 < s1
if0 t5 goto array_err
t5 = s1 < s3
if0 t5 goto array_err
t5 = 4
t3 = s1 + t4
t3 = t3 * t5
t4 = s2 + t3
[t4 + 0] = s4
t3 = [t2 + 4]
s2 = 2
s1 = 12
if0 t3 goto null_err
s4 = [t3 + 0]
s3 = 1
t5 = 0
t5 = t5 - s3
t4 = t5 < s2
if0 t4 goto array_err
t4 = s2 < s4
if0 t4 goto array_err
t4 = 4
t5 = s2 + s3
t5 = t5 * t4
t4 = t3 + t5
[t4 + 0] = s1
t5 = [t2 + 4]
t3 = 3
s2 = 18
if0 t5 goto null_err
s1 = [t5 + 0]
s4 = 1
s3 = 0
s3 = s3 - s4
t4 = s3 < t3
if0 t4 goto array_err
t4 = t3 < s1
if0 t4 goto array_err
s1 = 4
t4 = t3 + s4
t4 = t4 * s1
t3 = t5 + t4
[t3 + 0] = s2
t5 = [t2 + 4]
t3 = 4
s1 = 2
if0 t5 goto null_err
t4 = [t5 + 0]
s3 = 1
s4 = 0
s4 = s4 - s3
s2 = s4 < t3
if0 s2 goto array_err
s2 = t3 < t4
if0 s2 goto array_err
s2 = 4
t4 = t3 + s3
t4 = t4 * s2
t3 = t5 + t4
[t3 + 0] = s1
t5 = [t2 + 4]
t4 = 5
s1 = 11
if0 t5 goto null_err
t3 = [t5 + 0]
s3 = 1
s2 = 0
s2 = s2 - s3
s4 = s2 < t4
if0 s4 goto array_err
s4 = t4 < t3
if0 s4 goto array_err
s2 = 4
t3 = t4 + s3
t3 = t3 * s2
t4 = t5 + t3
[t4 + 0] = s1
s2 = [t2 + 4]
t3 = 6
t5 = 6
if0 s2 goto null_err
t4 = [s2 + 0]
s1 = 1
s4 = 0
s4 = s4 - s1
s3 = s4 < t3
if0 s3 goto array_err
s3 = t3 < t4
if0 s3 goto array_err
s3 = 4
t4 = t3 + s1
t4 = t4 * s3
t3 = s2 + t4
[t3 + 0] = t5
s3 = [t2 + 4]
s2 = 7
t5 = 9
if0 s3 goto null_err
t3 = [s3 + 0]
s1 = 1
t4 = 0
t4 = t4 - s1
s4 = t4 < s2
if0 s4 goto array_err
s4 = s2 < t3
if0 s4 goto array_err
t3 = 4
t4 = s2 + s1
t4 = t4 * t3
t3 = s3 + t4
[t3 + 0] = t5
s2 = [t2 + 4]
s4 = 8
s3 = 19
if0 s2 goto null_err
t4 = [s2 + 0]
t3 = 1
s1 = 0
s1 = s1 - t3
t5 = s1 < s4
if0 t5 goto array_err
t5 = s4 < t4
if0 t5 goto array_err
t5 = 4
t4 = s4 + t3
t4 = t4 * t5
t3 = s2 + t4
[t3 + 0] = s3
s1 = [t2 + 4]
t5 = 9
s3 = 5
if0 s1 goto null_err
s2 = [s1 + 0]
t3 = 1
t2 = 0
t2 = t2 - t3
t4 = t2 < t5
if0 t4 goto array_err
t4 = t5 < s2
if0 t4 goto array_err
t2 = 4
t4 = t5 + t3
t4 = t4 * t2
t2 = s1 + t4
[t2 + 0] = s3
t2 = 0
goto QSInit_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
QSInit_end:
id302 = t2
s4 = stack_1
s3 = stack_2
s2 = stack_3
t2 = stack_4
t3 = stack_5
t4 = stack_6
t5 = stack_7
s1 = stack_8
      return id302


