func main()
s11 = 4
s10 = alloc(s11)
if0 s10 goto null_err
s11 = 4
t4 = alloc(s11)
if0 t4 goto null_err
s11 = @TVStart
[t4 + 0] = s11
[s10 + 0] = t4
if0 s10 goto null_err
s11 = [s10 + 0]
s11 = [s11 + 0]
a2 = s10
t4 = call s11()
print(t4)
goto main_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
main_end:
t4 = 0
id5 = t4
      return id5

func TVStart()
t4 = a2
t1 = 28
t3 = alloc(t1)
if0 t3 goto null_err
t1 = 84
t4 = alloc(t1)
if0 t4 goto null_err
t1 = @TreeDelete
[t4 + 0] = t1
t1 = @TreeSetHas_Left
[t4 + 4] = t1
t1 = @TreeRemoveLeft
[t4 + 8] = t1
t1 = @TreeGetKey
[t4 + 12] = t1
t1 = @TreeSetRight
[t4 + 16] = t1
t1 = @TreeGetLeft
[t4 + 20] = t1
t1 = @TreeGetRight
[t4 + 24] = t1
t1 = @TreeRemove
[t4 + 28] = t1
t1 = @TreeSetLeft
[t4 + 32] = t1
t1 = @TreeInsert
[t4 + 36] = t1
t1 = @Treeaccept
[t4 + 40] = t1
t1 = @TreePrint
[t4 + 44] = t1
t1 = @TreeInit
[t4 + 48] = t1
t1 = @TreeGetHas_Right
[t4 + 52] = t1
t1 = @TreeGetHas_Left
[t4 + 56] = t1
t1 = @TreeRemoveRight
[t4 + 60] = t1
t1 = @TreeSearch
[t4 + 64] = t1
t1 = @TreeSetKey
[t4 + 68] = t1
t1 = @TreeCompare
[t4 + 72] = t1
t1 = @TreeSetHas_Right
[t4 + 76] = t1
t1 = @TreeRecPrint
[t4 + 80] = t1
[t3 + 0] = t4
t1 = t3
if0 t1 goto null_err
t4 = [t1 + 0]
t4 = [t4 + 48]
t3 = 16
id6 = t1
a2 = t1
a3 = t3
s1 = call t4()
t1 = id6
t4 = s1
if0 t1 goto null_err
s1 = [t1 + 0]
s1 = [s1 + 44]
id6 = t1
id7 = t4
a2 = t1
t3 = call s1()
t1 = id6
t4 = id7
t4 = t3
t3 = 100000000
print(t3)
if0 t1 goto null_err
s3 = [t1 + 0]
s3 = [s3 + 36]
s1 = 8
id6 = t1
id7 = t4
a2 = t1
a3 = s1
t3 = call s3()
t1 = id6
t4 = id7
t4 = t3
if0 t1 goto null_err
t3 = [t1 + 0]
t3 = [t3 + 36]
s1 = 24
id6 = t1
id7 = t4
a2 = t1
a3 = s1
s3 = call t3()
t1 = id6
t4 = id7
t4 = s3
if0 t1 goto null_err
s1 = [t1 + 0]
s1 = [s1 + 36]
s3 = 4
id6 = t1
id7 = t4
a2 = t1
a3 = s3
t3 = call s1()
t1 = id6
t4 = id7
t4 = t3
if0 t1 goto null_err
s3 = [t1 + 0]
s3 = [s3 + 36]
t3 = 12
id6 = t1
id7 = t4
a2 = t1
a3 = t3
s1 = call s3()
t1 = id6
t4 = id7
t4 = s1
if0 t1 goto null_err
t3 = [t1 + 0]
t3 = [t3 + 36]
s1 = 20
id6 = t1
id7 = t4
a2 = t1
a3 = s1
s3 = call t3()
t1 = id6
t4 = id7
t4 = s3
if0 t1 goto null_err
s1 = [t1 + 0]
s1 = [s1 + 36]
s3 = 28
id6 = t1
id7 = t4
a2 = t1
a3 = s3
t3 = call s1()
t1 = id6
t4 = id7
t4 = t3
if0 t1 goto null_err
t3 = [t1 + 0]
t3 = [t3 + 36]
s1 = 14
id6 = t1
id7 = t4
a2 = t1
a3 = s1
s3 = call t3()
t1 = id6
t4 = id7
t4 = s3
if0 t1 goto null_err
t3 = [t1 + 0]
t3 = [t3 + 44]
id6 = t1
id7 = t4
a2 = t1
s1 = call t3()
t1 = id6
t4 = id7
t4 = s1
t3 = 100000000
print(t3)
t3 = 12
s1 = alloc(t3)
if0 s1 goto null_err
t3 = 4
s3 = alloc(t3)
if0 s3 goto null_err
t3 = @MyVisitorvisit
[s3 + 0] = t3
[s1 + 0] = s3
s3 = s1
t3 = 50000000
print(t3)
if0 t1 goto null_err
s1 = [t1 + 0]
s1 = [s1 + 40]
id6 = t1
id7 = t4
a2 = t1
a3 = s3
t3 = call s1()
t1 = id6
t4 = id7
s1 = t3
t3 = 100000000
print(t3)
if0 t1 goto null_err
s1 = [t1 + 0]
s1 = [s1 + 64]
s3 = 24
id6 = t1
id7 = t4
a2 = t1
a3 = s3
t3 = call s1()
t1 = id6
t4 = id7
print(t3)
if0 t1 goto null_err
t3 = [t1 + 0]
t3 = [t3 + 64]
s1 = 12
id6 = t1
id7 = t4
a2 = t1
a3 = s1
s3 = call t3()
t1 = id6
t4 = id7
print(s3)
if0 t1 goto null_err
s1 = [t1 + 0]
s1 = [s1 + 64]
s3 = 16
id6 = t1
id7 = t4
a2 = t1
a3 = s3
t3 = call s1()
t1 = id6
t4 = id7
print(t3)
if0 t1 goto null_err
t3 = [t1 + 0]
t3 = [t3 + 64]
s3 = 50
id6 = t1
id7 = t4
a2 = t1
a3 = s3
s1 = call t3()
t1 = id6
t4 = id7
print(s1)
if0 t1 goto null_err
t3 = [t1 + 0]
t3 = [t3 + 64]
s1 = 12
id6 = t1
id7 = t4
a2 = t1
a3 = s1
s3 = call t3()
t1 = id6
t4 = id7
print(s3)
if0 t1 goto null_err
s1 = [t1 + 0]
s1 = [s1 + 0]
s3 = 12
id6 = t1
id7 = t4
a2 = t1
a3 = s3
t3 = call s1()
t1 = id6
t4 = id7
t4 = t3
if0 t1 goto null_err
t3 = [t1 + 0]
t3 = [t3 + 44]
id6 = t1
id7 = t4
a2 = t1
s1 = call t3()
t1 = id6
t4 = id7
t4 = s1
if0 t1 goto null_err
t3 = [t1 + 0]
t3 = [t3 + 64]
s1 = 12
a2 = t1
a3 = s1
t4 = call t3()
print(t4)
t4 = 0
goto TVStart_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TVStart_end:
id93 = t4
      return id93

func TreeInit()
s4 = a2
s9 = a3
t0 = [s4 + 24]
[s4 + 24] = s9
t0 = [s4 + 20]
t0 = 0
[s4 + 20] = t0
t0 = [s4 + 8]
t0 = 0
[s4 + 8] = t0
t0 = 1
goto TreeInit_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeInit_end:
id100 = t0
      return id100

func TreeSetRight()
s7 = a2
t5 = a3
s8 = [s7 + 16]
[s7 + 16] = t5
t5 = 1
goto TreeSetRight_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeSetRight_end:
id103 = t5
      return id103

func TreeSetLeft()
s2 = a2
s6 = a3
s11 = [s2 + 4]
[s2 + 4] = s6
s2 = 1
goto TreeSetLeft_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeSetLeft_end:
id106 = s2
      return id106

func TreeGetRight()
s2 = a2
s6 = [s2 + 16]
goto TreeGetRight_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeGetRight_end:
id107 = s6
      return id107

func TreeGetLeft()
s10 = a2
s11 = [s10 + 4]
goto TreeGetLeft_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeGetLeft_end:
id108 = s11
      return id108

func TreeGetKey()
s8 = a2
t2 = [s8 + 24]
goto TreeGetKey_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeGetKey_end:
id109 = t2
      return id109

func TreeSetKey()
t3 = a2
s10 = a3
s11 = [t3 + 24]
[t3 + 24] = s10
s10 = 1
goto TreeSetKey_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeSetKey_end:
id112 = s10
      return id112

func TreeGetHas_Right()
s7 = a2
t5 = [s7 + 8]
goto TreeGetHas_Right_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeGetHas_Right_end:
id113 = t5
      return id113

func TreeGetHas_Left()
s4 = a2
t0 = [s4 + 20]
goto TreeGetHas_Left_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeGetHas_Left_end:
id114 = t0
      return id114

func TreeSetHas_Left()
s9 = a2
s7 = a3
s8 = [s9 + 20]
[s9 + 20] = s7
s9 = 1
goto TreeSetHas_Left_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeSetHas_Left_end:
id117 = s9
      return id117

func TreeSetHas_Right()
t1 = a2
s1 = a3
t4 = [t1 + 8]
[t1 + 8] = s1
t1 = 1
goto TreeSetHas_Right_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeSetHas_Right_end:
id120 = t1
      return id120

func TreeCompare()
t0 = a2
t4 = a3
s4 = a4
s7 = 0
t0 = s7
s7 = 1
s8 = s4 + s7
t5 = s8
s7 = t4 < s4
if0 s7 goto elseid128
s4 = 0
t0 = s4
goto endid128
elseid128:
s4 = t4 < t5
t4 = 1
s7 = t4 - s4
if0 s7 goto elseid130
t4 = 0
t0 = t4
goto endid130
elseid130:
t4 = 1
t0 = t4
endid130:
endid128:
goto TreeCompare_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeCompare_end:
id123 = t0
      return id123

func TreeInsert()
t4 = a2
t2 = a3
t3 = 28
t1 = alloc(t3)
if0 t1 goto null_err
t3 = 84
t0 = alloc(t3)
if0 t0 goto null_err
t3 = @TreeDelete
[t0 + 0] = t3
t3 = @TreeSetHas_Left
[t0 + 4] = t3
t3 = @TreeRemoveLeft
[t0 + 8] = t3
t3 = @TreeGetKey
[t0 + 12] = t3
t3 = @TreeSetRight
[t0 + 16] = t3
t3 = @TreeGetLeft
[t0 + 20] = t3
t3 = @TreeGetRight
[t0 + 24] = t3
t3 = @TreeRemove
[t0 + 28] = t3
t3 = @TreeSetLeft
[t0 + 32] = t3
t3 = @TreeInsert
[t0 + 36] = t3
t3 = @Treeaccept
[t0 + 40] = t3
t3 = @TreePrint
[t0 + 44] = t3
t3 = @TreeInit
[t0 + 48] = t3
t3 = @TreeGetHas_Right
[t0 + 52] = t3
t3 = @TreeGetHas_Left
[t0 + 56] = t3
t3 = @TreeRemoveRight
[t0 + 60] = t3
t3 = @TreeSearch
[t0 + 64] = t3
t3 = @TreeSetKey
[t0 + 68] = t3
t3 = @TreeCompare
[t0 + 72] = t3
t3 = @TreeSetHas_Right
[t0 + 76] = t3
t3 = @TreeRecPrint
[t0 + 80] = t3
[t1 + 0] = t0
t3 = t1
if0 t3 goto null_err
t0 = [t3 + 0]
t0 = [t0 + 48]
this = t4
id136 = t3
id135 = t2
a2 = t3
a3 = t2
t1 = call t0()
t4 = this
t3 = id136
t2 = id135
t0 = t1
t1 = t4
t5 = 1
t4 = t5
loopid167:
if0 t4 goto endid167
if0 t1 goto null_err
t5 = [t1 + 0]
t5 = [t5 + 12]
id139 = t4
id136 = t3
id135 = t2
id138 = t1
id137 = t0
a2 = t1
s1 = call t5()
t4 = id139
t3 = id136
t2 = id135
t1 = id138
t0 = id137
t5 = s1
s1 = t2 < t5
if0 s1 goto elseid170
if0 t1 goto null_err
s1 = [t1 + 0]
s1 = [s1 + 56]
id139 = t4
id136 = t3
id135 = t2
id138 = t1
id137 = t0
a2 = t1
t5 = call s1()
t4 = id139
t3 = id136
t2 = id135
t1 = id138
t0 = id137
if0 t5 goto elseid172
if0 t1 goto null_err
s1 = [t1 + 0]
s1 = [s1 + 20]
id139 = t4
id136 = t3
id135 = t2
id138 = t1
id137 = t0
a2 = t1
t5 = call s1()
t4 = id139
t3 = id136
t2 = id135
t1 = id138
t0 = id137
t1 = t5
goto endid172
elseid172:
t5 = 0
t4 = t5
if0 t1 goto null_err
t5 = [t1 + 0]
t5 = [t5 + 4]
s1 = 1
id139 = t4
id136 = t3
id135 = t2
id138 = t1
id137 = t0
a2 = t1
a3 = s1
s2 = call t5()
t4 = id139
t3 = id136
t2 = id135
t1 = id138
t0 = id137
t0 = s2
if0 t1 goto null_err
s1 = [t1 + 0]
s1 = [s1 + 32]
id139 = t4
id136 = t3
id135 = t2
id138 = t1
id137 = t0
a2 = t1
a3 = t3
t5 = call s1()
t4 = id139
t3 = id136
t2 = id135
t1 = id138
t0 = id137
t0 = t5
endid172:
goto endid170
elseid170:
if0 t1 goto null_err
s1 = [t1 + 0]
s1 = [s1 + 52]
id139 = t4
id136 = t3
id135 = t2
id138 = t1
id137 = t0
a2 = t1
t5 = call s1()
t4 = id139
t3 = id136
t2 = id135
t1 = id138
t0 = id137
if0 t5 goto elseid182
if0 t1 goto null_err
t5 = [t1 + 0]
t5 = [t5 + 24]
id139 = t4
id136 = t3
id135 = t2
id138 = t1
id137 = t0
a2 = t1
s1 = call t5()
t4 = id139
t3 = id136
t2 = id135
t1 = id138
t0 = id137
t1 = s1
goto endid182
elseid182:
t5 = 0
t4 = t5
if0 t1 goto null_err
s1 = [t1 + 0]
s1 = [s1 + 76]
s2 = 1
id139 = t4
id136 = t3
id135 = t2
id138 = t1
id137 = t0
a2 = t1
a3 = s2
t5 = call s1()
t4 = id139
t3 = id136
t2 = id135
t1 = id138
t0 = id137
t0 = t5
if0 t1 goto null_err
t5 = [t1 + 0]
t5 = [t5 + 16]
id139 = t4
id136 = t3
id135 = t2
id138 = t1
id137 = t0
a2 = t1
a3 = t3
s1 = call t5()
t4 = id139
t3 = id136
t2 = id135
t1 = id138
t0 = id137
t0 = s1
endid182:
endid170:
goto loopid167
endid167:
t0 = 1
goto TreeInsert_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeInsert_end:
id191 = t0
      return id191

func TreeDelete()
s4 = a2
s3 = a3
s7 = s4
s8 = s4
s6 = 1
s5 = s6
s9 = 0
s6 = s9
s10 = 1
s9 = s10
loopid203:
if0 s5 goto endid203
if0 s7 goto null_err
s11 = [s7 + 0]
s11 = [s11 + 12]
id198 = s9
id194 = s8
id193 = s7
id196 = s6
id195 = s5
this = s4
id192 = s3
a2 = s7
s10 = call s11()
s9 = id198
s8 = id194
s7 = id193
s6 = id196
s5 = id195
s4 = this
s3 = id192
s2 = s10
s10 = s3 < s2
if0 s10 goto elseid206
if0 s7 goto null_err
s11 = [s7 + 0]
s11 = [s11 + 56]
id198 = s9
id199 = s2
id194 = s8
id193 = s7
id196 = s6
id195 = s5
this = s4
id192 = s3
a2 = s7
s10 = call s11()
s9 = id198
s2 = id199
s8 = id194
s7 = id193
s6 = id196
s5 = id195
s4 = this
s3 = id192
if0 s10 goto elseid208
s8 = s7
if0 s7 goto null_err
s11 = [s7 + 0]
s11 = [s11 + 20]
id198 = s9
id199 = s2
id194 = s8
id193 = s7
id196 = s6
id195 = s5
this = s4
id192 = s3
a2 = s7
s10 = call s11()
s9 = id198
s2 = id199
s8 = id194
s7 = id193
s6 = id196
s5 = id195
s4 = this
s3 = id192
s7 = s10
goto endid208
elseid208:
s10 = 0
s5 = s10
endid208:
goto endid206
elseid206:
s10 = s2 < s3
if0 s10 goto elseid212
if0 s7 goto null_err
s10 = [s7 + 0]
s10 = [s10 + 52]
id198 = s9
id194 = s8
id193 = s7
id196 = s6
id195 = s5
this = s4
id192 = s3
a2 = s7
s11 = call s10()
s9 = id198
s8 = id194
s7 = id193
s6 = id196
s5 = id195
s4 = this
s3 = id192
if0 s11 goto elseid214
s8 = s7
if0 s7 goto null_err
s11 = [s7 + 0]
s11 = [s11 + 24]
id198 = s9
id194 = s8
id193 = s7
id196 = s6
id195 = s5
this = s4
id192 = s3
a2 = s7
s10 = call s11()
s9 = id198
s8 = id194
s7 = id193
s6 = id196
s5 = id195
s4 = this
s3 = id192
s7 = s10
goto endid214
elseid214:
s10 = 0
s5 = s10
endid214:
goto endid212
elseid212:
if0 s9 goto elseid198
t4 = 0
if0 s7 goto null_err
s10 = [s7 + 0]
s10 = [s10 + 52]
id198 = s9
id194 = s8
id193 = s7
id196 = s6
id195 = s5
this = s4
id218 = t4
id192 = s3
a2 = s7
s11 = call s10()
s9 = id198
s8 = id194
s7 = id193
s6 = id196
s5 = id195
s4 = this
t4 = id218
s3 = id192
s10 = 1
s2 = s10 - s11
if0 s2 goto endid219
if0 s7 goto null_err
s10 = [s7 + 0]
s10 = [s10 + 56]
id198 = s9
id194 = s8
id193 = s7
id196 = s6
id195 = s5
this = s4
id218 = t4
id192 = s3
a2 = s7
s2 = call s10()
s9 = id198
s8 = id194
s7 = id193
s6 = id196
s5 = id195
s4 = this
t4 = id218
s3 = id192
s11 = 1
s10 = s11 - s2
t4 = s10
endid219:
if0 t4 goto elseid218
s10 = 1
s2 = s10
goto endid218
elseid218:
if0 s4 goto null_err
s10 = [s4 + 0]
s10 = [s10 + 28]
id198 = s9
id197 = s2
id194 = s8
id193 = s7
id196 = s6
id195 = s5
this = s4
id192 = s3
a2 = s4
a3 = s8
a4 = s7
s11 = call s10()
s9 = id198
s2 = id197
s8 = id194
s7 = id193
s6 = id196
s5 = id195
s4 = this
s3 = id192
s2 = s11
endid218:
goto endid198
elseid198:
if0 s4 goto null_err
s11 = [s4 + 0]
s11 = [s11 + 28]
id198 = s9
id197 = s2
id194 = s8
id193 = s7
id196 = s6
id195 = s5
this = s4
id192 = s3
a2 = s4
a3 = s8
a4 = s7
s10 = call s11()
s9 = id198
s2 = id197
s8 = id194
s7 = id193
s6 = id196
s5 = id195
s4 = this
s3 = id192
s2 = s10
endid198:
s10 = 1
s6 = s10
s10 = 0
s5 = s10
endid212:
endid206:
s10 = 0
s9 = s10
goto loopid203
endid203:
goto TreeDelete_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeDelete_end:
id196 = s6
      return id196

func TreeRemove()
s9 = a2
t2 = a3
s5 = a4
if0 s5 goto null_err
s8 = [s5 + 0]
s8 = [s8 + 56]
this = s9
id235 = t2
id236 = s5
a2 = s5
t5 = call s8()
s9 = this
t2 = id235
s5 = id236
if0 t5 goto elseid241
if0 s9 goto null_err
t5 = [s9 + 0]
t5 = [t5 + 8]
this = s9
id235 = t2
id236 = s5
a2 = s9
a3 = t2
a4 = s5
s8 = call t5()
s9 = this
t2 = id235
s5 = id236
t5 = s8
goto endid241
elseid241:
if0 s5 goto null_err
s8 = [s5 + 0]
s8 = [s8 + 52]
this = s9
id235 = t2
id237 = t5
id236 = s5
a2 = s5
s7 = call s8()
s9 = this
t2 = id235
t5 = id237
s5 = id236
if0 s7 goto elseid245
if0 s9 goto null_err
s8 = [s9 + 0]
s8 = [s8 + 60]
this = s9
id235 = t2
id237 = t5
id236 = s5
a2 = s9
a3 = t2
a4 = s5
s7 = call s8()
s9 = this
t2 = id235
t5 = id237
s5 = id236
t5 = s7
goto endid245
elseid245:
if0 s5 goto null_err
s7 = [s5 + 0]
s7 = [s7 + 12]
this = s9
id235 = t2
id237 = t5
a2 = s5
s8 = call s7()
s9 = this
t2 = id235
t5 = id237
s4 = s8
if0 t2 goto null_err
s8 = [t2 + 0]
s8 = [s8 + 20]
this = s9
id238 = s4
id235 = t2
id237 = t5
a2 = t2
s5 = call s8()
s9 = this
s4 = id238
t2 = id235
t5 = id237
if0 s5 goto null_err
s7 = [s5 + 0]
s7 = [s7 + 12]
this = s9
id238 = s4
id235 = t2
id237 = t5
a2 = s5
s8 = call s7()
s9 = this
s4 = id238
t2 = id235
t5 = id237
s5 = s8
if0 s9 goto null_err
s7 = [s9 + 0]
s7 = [s7 + 72]
this = s9
id235 = t2
id237 = t5
a2 = s9
a3 = s4
a4 = s5
s8 = call s7()
s9 = this
t2 = id235
t5 = id237
if0 s8 goto elseid255
if0 t2 goto null_err
s7 = [t2 + 0]
s7 = [s7 + 32]
s5 = [s9 + 12]
this = s9
id235 = t2
id237 = t5
a2 = t2
a3 = s5
s8 = call s7()
s9 = this
t2 = id235
t5 = id237
t5 = s8
if0 t2 goto null_err
s5 = [t2 + 0]
s5 = [s5 + 4]
s7 = 0
this = s9
id235 = t2
id237 = t5
a2 = t2
a3 = s7
s8 = call s5()
s9 = this
t2 = id235
t5 = id237
t5 = s8
goto endid255
elseid255:
if0 t2 goto null_err
s7 = [t2 + 0]
s7 = [s7 + 16]
s8 = [s9 + 12]
id235 = t2
id237 = t5
a2 = t2
a3 = s8
s9 = call s7()
t2 = id235
t5 = id237
t5 = s9
if0 t2 goto null_err
s8 = [t2 + 0]
s8 = [s8 + 76]
s7 = 0
id237 = t5
a2 = t2
a3 = s7
s9 = call s8()
t5 = id237
t5 = s9
endid255:
endid245:
endid241:
t5 = 1
goto TreeRemove_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeRemove_end:
id268 = t5
      return id268

func TreeRemoveRight()
s11 = a2
s5 = a3
s2 = a4
loopid272:
if0 s2 goto null_err
s6 = [s2 + 0]
s6 = [s6 + 52]
id270 = s2
this = s11
id269 = s5
a2 = s2
t2 = call s6()
s2 = id270
s11 = this
s5 = id269
if0 t2 goto endid272
if0 s2 goto null_err
t2 = [s2 + 0]
t2 = [t2 + 68]
if0 s2 goto null_err
s6 = [s2 + 0]
s6 = [s6 + 24]
id275 = t2
id270 = s2
this = s11
id269 = s5
a2 = s2
s10 = call s6()
t2 = id275
s2 = id270
s11 = this
s5 = id269
if0 s10 goto null_err
s6 = [s10 + 0]
s6 = [s6 + 12]
id275 = t2
id270 = s2
this = s11
id269 = s5
a2 = s10
t3 = call s6()
t2 = id275
s2 = id270
s11 = this
s5 = id269
id270 = s2
this = s11
id269 = s5
a2 = s2
a3 = t3
s6 = call t2()
s2 = id270
s11 = this
s5 = id269
s10 = s6
s5 = s2
if0 s2 goto null_err
s6 = [s2 + 0]
s6 = [s6 + 24]
id271 = s10
id270 = s2
this = s11
id269 = s5
a2 = s2
t2 = call s6()
s10 = id271
s2 = id270
s11 = this
s5 = id269
s2 = t2
goto loopid272
endid272:
if0 s5 goto null_err
s2 = [s5 + 0]
s2 = [s2 + 16]
t2 = [s11 + 12]
id271 = s10
id269 = s5
a2 = s5
a3 = t2
s6 = call s2()
s10 = id271
s5 = id269
s10 = s6
if0 s5 goto null_err
t2 = [s5 + 0]
t2 = [t2 + 76]
s6 = 0
id271 = s10
a2 = s5
a3 = s6
s2 = call t2()
s10 = id271
s10 = s2
s5 = 1
goto TreeRemoveRight_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeRemoveRight_end:
id289 = s5
      return id289

func TreeRemoveLeft()
t1 = a2
s10 = a3
s1 = a4
loopid293:
if0 s1 goto null_err
t3 = [s1 + 0]
t3 = [t3 + 56]
this = t1
id291 = s1
id290 = s10
a2 = s1
s5 = call t3()
t1 = this
s1 = id291
s10 = id290
if0 s5 goto endid293
if0 s1 goto null_err
t3 = [s1 + 0]
t3 = [t3 + 68]
if0 s1 goto null_err
s3 = [s1 + 0]
s3 = [s3 + 20]
id296 = t3
this = t1
id291 = s1
id290 = s10
a2 = s1
s5 = call s3()
t3 = id296
t1 = this
s1 = id291
s10 = id290
if0 s5 goto null_err
s9 = [s5 + 0]
s9 = [s9 + 12]
id296 = t3
this = t1
id291 = s1
id290 = s10
a2 = s5
s3 = call s9()
t3 = id296
t1 = this
s1 = id291
s10 = id290
this = t1
id291 = s1
id290 = s10
a2 = s1
a3 = s3
s5 = call t3()
t1 = this
s1 = id291
s10 = id290
t3 = s5
s10 = s1
if0 s1 goto null_err
s3 = [s1 + 0]
s3 = [s3 + 20]
id292 = t3
this = t1
id291 = s1
id290 = s10
a2 = s1
s5 = call s3()
t3 = id292
t1 = this
s1 = id291
s10 = id290
s1 = s5
goto loopid293
endid293:
if0 s10 goto null_err
s3 = [s10 + 0]
s3 = [s3 + 32]
s9 = [t1 + 12]
id292 = t3
id290 = s10
a2 = s10
a3 = s9
s5 = call s3()
t3 = id292
s10 = id290
t3 = s5
if0 s10 goto null_err
s5 = [s10 + 0]
s5 = [s5 + 4]
s3 = 0
id292 = t3
a2 = s10
a3 = s3
t1 = call s5()
t3 = id292
t3 = t1
s10 = 1
goto TreeRemoveLeft_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeRemoveLeft_end:
id310 = s10
      return id310

func TreeSearch()
t2 = a2
s7 = a3
t5 = t2
t2 = 1
s8 = t2
s5 = 0
t2 = s5
loopid318:
if0 s8 goto endid318
if0 t5 goto null_err
s2 = [t5 + 0]
s2 = [s2 + 12]
id312 = t5
id311 = s7
id314 = s8
id313 = t2
a2 = t5
s5 = call s2()
t5 = id312
s7 = id311
s8 = id314
t2 = id313
s2 = s5
s5 = s7 < s2
if0 s5 goto elseid321
if0 t5 goto null_err
s6 = [t5 + 0]
s6 = [s6 + 56]
id315 = s2
id312 = t5
id311 = s7
id314 = s8
id313 = t2
a2 = t5
s5 = call s6()
s2 = id315
t5 = id312
s7 = id311
s8 = id314
t2 = id313
if0 s5 goto elseid323
if0 t5 goto null_err
s6 = [t5 + 0]
s6 = [s6 + 20]
id315 = s2
id312 = t5
id311 = s7
id314 = s8
id313 = t2
a2 = t5
s5 = call s6()
s2 = id315
t5 = id312
s7 = id311
s8 = id314
t2 = id313
t5 = s5
goto endid323
elseid323:
s5 = 0
s8 = s5
endid323:
goto endid321
elseid321:
s5 = s2 < s7
if0 s5 goto elseid327
if0 t5 goto null_err
s2 = [t5 + 0]
s2 = [s2 + 52]
id312 = t5
id311 = s7
id314 = s8
id313 = t2
a2 = t5
s5 = call s2()
t5 = id312
s7 = id311
s8 = id314
t2 = id313
if0 s5 goto elseid329
if0 t5 goto null_err
s5 = [t5 + 0]
s5 = [s5 + 24]
id312 = t5
id311 = s7
id314 = s8
id313 = t2
a2 = t5
s2 = call s5()
t5 = id312
s7 = id311
s8 = id314
t2 = id313
t5 = s2
goto endid329
elseid329:
s5 = 0
s8 = s5
endid329:
goto endid327
elseid327:
s5 = 1
t2 = s5
s5 = 0
s8 = s5
endid327:
endid321:
goto loopid318
endid318:
goto TreeSearch_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeSearch_end:
id313 = t2
      return id313

func TreePrint()
s6 = a2
s2 = s6
if0 s6 goto null_err
s4 = [s6 + 0]
s4 = [s4 + 80]
a2 = s6
a3 = s2
s5 = call s4()
s6 = s5
s6 = 1
goto TreePrint_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreePrint_end:
id339 = s6
      return id339

func TreeRecPrint()
t2 = a2
t3 = a3
if0 t3 goto null_err
t5 = [t3 + 0]
t5 = [t5 + 56]
id340 = t3
this = t2
a2 = t3
t1 = call t5()
t3 = id340
t2 = this
if0 t1 goto elseid343
if0 t2 goto null_err
t1 = [t2 + 0]
t1 = [t1 + 80]
if0 t3 goto null_err
t5 = [t3 + 0]
t5 = [t5 + 20]
id340 = t3
this = t2
id344 = t1
a2 = t3
s1 = call t5()
t3 = id340
t2 = this
t1 = id344
id340 = t3
this = t2
a2 = t2
a3 = s1
t5 = call t1()
t3 = id340
t2 = this
t1 = t5
goto endid343
elseid343:
t5 = 1
t1 = t5
endid343:
if0 t3 goto null_err
s1 = [t3 + 0]
s1 = [s1 + 12]
id341 = t1
id340 = t3
this = t2
a2 = t3
t5 = call s1()
t1 = id341
t3 = id340
t2 = this
print(t5)
if0 t3 goto null_err
s1 = [t3 + 0]
s1 = [s1 + 52]
id341 = t1
id340 = t3
this = t2
a2 = t3
t5 = call s1()
t1 = id341
t3 = id340
t2 = this
if0 t5 goto elseid352
if0 t2 goto null_err
s1 = [t2 + 0]
s1 = [s1 + 80]
if0 t3 goto null_err
t5 = [t3 + 0]
t5 = [t5 + 24]
id341 = t1
id353 = s1
this = t2
a2 = t3
t0 = call t5()
t1 = id341
s1 = id353
t2 = this
id341 = t1
a2 = t2
a3 = t0
t5 = call s1()
t1 = id341
t1 = t5
goto endid352
elseid352:
t2 = 1
t1 = t2
endid352:
t2 = 1
goto TreeRecPrint_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeRecPrint_end:
id358 = t2
      return id358

func Treeaccept()
s4 = a2
t0 = a3
s3 = 333
print(s3)
if0 t0 goto null_err
s9 = [t0 + 0]
s9 = [s9 + 0]
a2 = t0
a3 = s4
s3 = call s9()
s9 = s3
s3 = 0
goto Treeaccept_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
Treeaccept_end:
id364 = s3
      return id364

func Visitorvisit()
t1 = a2
s3 = a3
if0 s3 goto null_err
s1 = [s3 + 0]
s1 = [s1 + 52]
id365 = s3
this = t1
a2 = s3
t4 = call s1()
s3 = id365
t1 = this
if0 t4 goto elseid368
s1 = [t1 + 4]
if0 s3 goto null_err
s1 = [s3 + 0]
s1 = [s1 + 24]
id365 = s3
this = t1
a2 = s3
t4 = call s1()
s3 = id365
t1 = this
[t1 + 4] = t4
s9 = [t1 + 4]
if0 s9 goto null_err
t4 = [s9 + 0]
t4 = [t4 + 40]
id365 = s3
this = t1
a2 = s9
a3 = t1
s1 = call t4()
s3 = id365
t1 = this
s9 = s1
goto endid368
elseid368:
s1 = 0
s9 = s1
endid368:
if0 s3 goto null_err
s1 = [s3 + 0]
s1 = [s1 + 56]
id365 = s3
this = t1
id366 = s9
a2 = s3
t4 = call s1()
s3 = id365
t1 = this
s9 = id366
if0 t4 goto elseid377
s1 = [t1 + 8]
if0 s3 goto null_err
t4 = [s3 + 0]
t4 = [t4 + 20]
this = t1
id366 = s9
a2 = s3
s1 = call t4()
t1 = this
s9 = id366
[t1 + 8] = s1
s1 = [t1 + 8]
if0 s1 goto null_err
s3 = [s1 + 0]
s3 = [s3 + 40]
id366 = s9
a2 = s1
a3 = t1
t4 = call s3()
s9 = id366
s9 = t4
goto endid377
elseid377:
t1 = 0
s9 = t1
endid377:
t1 = 0
goto Visitorvisit_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
Visitorvisit_end:
id385 = t1
      return id385

func MyVisitorvisit()
s3 = a2
t3 = a3
if0 t3 goto null_err
t1 = [t3 + 0]
t1 = [t1 + 52]
id386 = t3
this = s3
a2 = t3
t0 = call t1()
t3 = id386
s3 = this
if0 t0 goto elseid389
t0 = [s3 + 4]
if0 t3 goto null_err
t1 = [t3 + 0]
t1 = [t1 + 24]
id386 = t3
this = s3
a2 = t3
t0 = call t1()
t3 = id386
s3 = this
[s3 + 4] = t0
s1 = [s3 + 4]
if0 s1 goto null_err
t1 = [s1 + 0]
t1 = [t1 + 40]
id386 = t3
this = s3
a2 = s1
a3 = s3
t0 = call t1()
t3 = id386
s3 = this
t1 = t0
goto endid389
elseid389:
t0 = 0
t1 = t0
endid389:
if0 t3 goto null_err
s1 = [t3 + 0]
s1 = [s1 + 12]
id387 = t1
id386 = t3
this = s3
a2 = t3
t0 = call s1()
t1 = id387
t3 = id386
s3 = this
print(t0)
if0 t3 goto null_err
s1 = [t3 + 0]
s1 = [s1 + 56]
id387 = t1
id386 = t3
this = s3
a2 = t3
t0 = call s1()
t1 = id387
t3 = id386
s3 = this
if0 t0 goto elseid400
t0 = [s3 + 8]
if0 t3 goto null_err
s1 = [t3 + 0]
s1 = [s1 + 20]
id387 = t1
this = s3
a2 = t3
t0 = call s1()
t1 = id387
s3 = this
[s3 + 8] = t0
t3 = [s3 + 8]
if0 t3 goto null_err
s1 = [t3 + 0]
s1 = [s1 + 40]
id387 = t1
a2 = t3
a3 = s3
t0 = call s1()
t1 = id387
t1 = t0
goto endid400
elseid400:
s3 = 0
t1 = s3
endid400:
s3 = 0
goto MyVisitorvisit_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
MyVisitorvisit_end:
id408 = s3
      return id408


