func main()
s5 = 4
t0 = alloc(s5)
if0 t0 goto null_err
s5 = 4
s6 = alloc(s5)
if0 s6 goto null_err
s5 = @BTStart
[s6 + 0] = s5
[t0 + 0] = s6
if0 t0 goto null_err
s5 = [t0 + 0]
s5 = [s5 + 0]
a2 = t0
s6 = call s5()
print(s6)
goto main_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
main_end:
s6 = 0
id5 = s6
      return id5

func BTStart()
s8 = a2
s9 = 28
s8 = alloc(s9)
if0 s8 goto null_err
s9 = 80
s3 = alloc(s9)
if0 s3 goto null_err
s9 = @TreeDelete
[s3 + 0] = s9
s9 = @TreeSetHas_Left
[s3 + 4] = s9
s9 = @TreeRemoveLeft
[s3 + 8] = s9
s9 = @TreeGetKey
[s3 + 12] = s9
s9 = @TreeSetRight
[s3 + 16] = s9
s9 = @TreeGetLeft
[s3 + 20] = s9
s9 = @TreeGetRight
[s3 + 24] = s9
s9 = @TreeRemove
[s3 + 28] = s9
s9 = @TreeSetLeft
[s3 + 32] = s9
s9 = @TreeInsert
[s3 + 36] = s9
s9 = @TreePrint
[s3 + 40] = s9
s9 = @TreeInit
[s3 + 44] = s9
s9 = @TreeGetHas_Right
[s3 + 48] = s9
s9 = @TreeGetHas_Left
[s3 + 52] = s9
s9 = @TreeRemoveRight
[s3 + 56] = s9
s9 = @TreeSearch
[s3 + 60] = s9
s9 = @TreeSetKey
[s3 + 64] = s9
s9 = @TreeCompare
[s3 + 68] = s9
s9 = @TreeSetHas_Right
[s3 + 72] = s9
s9 = @TreeRecPrint
[s3 + 76] = s9
[s8 + 0] = s3
s3 = s8
if0 s3 goto null_err
s8 = [s3 + 0]
s8 = [s8 + 44]
s9 = 16
id6 = s3
a2 = s3
a3 = s9
s1 = call s8()
s3 = id6
s8 = s1
if0 s3 goto null_err
s1 = [s3 + 0]
s1 = [s1 + 40]
id6 = s3
id7 = s8
a2 = s3
s9 = call s1()
s3 = id6
s8 = id7
s8 = s9
s9 = 100000000
print(s9)
if0 s3 goto null_err
s9 = [s3 + 0]
s9 = [s9 + 36]
s1 = 8
id6 = s3
id7 = s8
a2 = s3
a3 = s1
t4 = call s9()
s3 = id6
s8 = id7
s8 = t4
if0 s3 goto null_err
s9 = [s3 + 0]
s9 = [s9 + 40]
id6 = s3
id7 = s8
a2 = s3
s1 = call s9()
s3 = id6
s8 = id7
s8 = s1
if0 s3 goto null_err
s9 = [s3 + 0]
s9 = [s9 + 36]
s1 = 24
id6 = s3
id7 = s8
a2 = s3
a3 = s1
t4 = call s9()
s3 = id6
s8 = id7
s8 = t4
if0 s3 goto null_err
s1 = [s3 + 0]
s1 = [s1 + 36]
t4 = 4
id6 = s3
id7 = s8
a2 = s3
a3 = t4
s9 = call s1()
s3 = id6
s8 = id7
s8 = s9
if0 s3 goto null_err
t4 = [s3 + 0]
t4 = [t4 + 36]
s9 = 12
id6 = s3
id7 = s8
a2 = s3
a3 = s9
s1 = call t4()
s3 = id6
s8 = id7
s8 = s1
if0 s3 goto null_err
s9 = [s3 + 0]
s9 = [s9 + 36]
s1 = 20
id6 = s3
id7 = s8
a2 = s3
a3 = s1
t4 = call s9()
s3 = id6
s8 = id7
s8 = t4
if0 s3 goto null_err
s1 = [s3 + 0]
s1 = [s1 + 36]
t4 = 28
id6 = s3
id7 = s8
a2 = s3
a3 = t4
s9 = call s1()
s3 = id6
s8 = id7
s8 = s9
if0 s3 goto null_err
s9 = [s3 + 0]
s9 = [s9 + 36]
s1 = 14
id6 = s3
id7 = s8
a2 = s3
a3 = s1
t4 = call s9()
s3 = id6
s8 = id7
s8 = t4
if0 s3 goto null_err
s9 = [s3 + 0]
s9 = [s9 + 40]
id6 = s3
id7 = s8
a2 = s3
s1 = call s9()
s3 = id6
s8 = id7
s8 = s1
if0 s3 goto null_err
s9 = [s3 + 0]
s9 = [s9 + 60]
s1 = 24
id6 = s3
id7 = s8
a2 = s3
a3 = s1
t4 = call s9()
s3 = id6
s8 = id7
print(t4)
if0 s3 goto null_err
s1 = [s3 + 0]
s1 = [s1 + 60]
t4 = 12
id6 = s3
id7 = s8
a2 = s3
a3 = t4
s9 = call s1()
s3 = id6
s8 = id7
print(s9)
if0 s3 goto null_err
s1 = [s3 + 0]
s1 = [s1 + 60]
s9 = 16
id6 = s3
id7 = s8
a2 = s3
a3 = s9
t4 = call s1()
s3 = id6
s8 = id7
print(t4)
if0 s3 goto null_err
s9 = [s3 + 0]
s9 = [s9 + 60]
s1 = 50
id6 = s3
id7 = s8
a2 = s3
a3 = s1
t4 = call s9()
s3 = id6
s8 = id7
print(t4)
if0 s3 goto null_err
s1 = [s3 + 0]
s1 = [s1 + 60]
t4 = 12
id6 = s3
id7 = s8
a2 = s3
a3 = t4
s9 = call s1()
s3 = id6
s8 = id7
print(s9)
if0 s3 goto null_err
s9 = [s3 + 0]
s9 = [s9 + 0]
s1 = 12
id6 = s3
id7 = s8
a2 = s3
a3 = s1
t4 = call s9()
s3 = id6
s8 = id7
s8 = t4
if0 s3 goto null_err
s9 = [s3 + 0]
s9 = [s9 + 40]
id6 = s3
id7 = s8
a2 = s3
s1 = call s9()
s3 = id6
s8 = id7
s8 = s1
if0 s3 goto null_err
s8 = [s3 + 0]
s8 = [s8 + 60]
s9 = 12
a2 = s3
a3 = s9
s1 = call s8()
print(s1)
s8 = 0
goto BTStart_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
BTStart_end:
id85 = s8
      return id85

func TreeInit()
s9 = a2
s6 = a3
t4 = [s9 + 24]
[s9 + 24] = s6
s6 = [s9 + 20]
s6 = 0
[s9 + 20] = s6
s6 = [s9 + 8]
s6 = 0
[s9 + 8] = s6
s6 = 1
goto TreeInit_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeInit_end:
id92 = s6
      return id92

func TreeSetRight()
t2 = a2
t0 = a3
t1 = [t2 + 16]
[t2 + 16] = t0
t0 = 1
goto TreeSetRight_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeSetRight_end:
id95 = t0
      return id95

func TreeSetLeft()
s1 = a2
s3 = a3
t4 = [s1 + 4]
[s1 + 4] = s3
s1 = 1
goto TreeSetLeft_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeSetLeft_end:
id98 = s1
      return id98

func TreeGetRight()
t0 = a2
s5 = [t0 + 16]
goto TreeGetRight_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeGetRight_end:
id99 = s5
      return id99

func TreeGetLeft()
s9 = a2
s6 = [s9 + 4]
goto TreeGetLeft_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeGetLeft_end:
id100 = s6
      return id100

func TreeGetKey()
s10 = a2
t5 = [s10 + 24]
goto TreeGetKey_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeGetKey_end:
id101 = t5
      return id101

func TreeSetKey()
t5 = a2
t0 = a3
s2 = [t5 + 24]
[t5 + 24] = t0
t5 = 1
goto TreeSetKey_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeSetKey_end:
id104 = t5
      return id104

func TreeGetHas_Right()
s5 = a2
s8 = [s5 + 8]
goto TreeGetHas_Right_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeGetHas_Right_end:
id105 = s8
      return id105

func TreeGetHas_Left()
s6 = a2
t0 = [s6 + 20]
goto TreeGetHas_Left_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeGetHas_Left_end:
id106 = t0
      return id106

func TreeSetHas_Left()
t2 = a2
t1 = a3
t3 = [t2 + 20]
[t2 + 20] = t1
t2 = 1
goto TreeSetHas_Left_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeSetHas_Left_end:
id109 = t2
      return id109

func TreeSetHas_Right()
s5 = a2
s11 = a3
s7 = [s5 + 8]
[s5 + 8] = s11
s11 = 1
goto TreeSetHas_Right_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeSetHas_Right_end:
id112 = s11
      return id112

func TreeCompare()
t1 = a2
t0 = a3
s11 = a4
t1 = 0
s5 = t1
s7 = 1
t1 = s11 + s7
s7 = t1
t1 = t0 < s11
if0 t1 goto elseid120
t1 = 0
s5 = t1
goto endid120
elseid120:
t1 = t0 < s7
s11 = 1
s7 = s11 - t1
if0 s7 goto elseid122
t1 = 0
s5 = t1
goto endid122
elseid122:
t1 = 1
s5 = t1
endid122:
endid120:
goto TreeCompare_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeCompare_end:
id115 = s5
      return id115

func TreeInsert()
s7 = a2
s5 = a3
s9 = 28
s8 = alloc(s9)
if0 s8 goto null_err
s9 = 80
s6 = alloc(s9)
if0 s6 goto null_err
s9 = @TreeDelete
[s6 + 0] = s9
s9 = @TreeSetHas_Left
[s6 + 4] = s9
s9 = @TreeRemoveLeft
[s6 + 8] = s9
s9 = @TreeGetKey
[s6 + 12] = s9
s9 = @TreeSetRight
[s6 + 16] = s9
s9 = @TreeGetLeft
[s6 + 20] = s9
s9 = @TreeGetRight
[s6 + 24] = s9
s9 = @TreeRemove
[s6 + 28] = s9
s9 = @TreeSetLeft
[s6 + 32] = s9
s9 = @TreeInsert
[s6 + 36] = s9
s9 = @TreePrint
[s6 + 40] = s9
s9 = @TreeInit
[s6 + 44] = s9
s9 = @TreeGetHas_Right
[s6 + 48] = s9
s9 = @TreeGetHas_Left
[s6 + 52] = s9
s9 = @TreeRemoveRight
[s6 + 56] = s9
s9 = @TreeSearch
[s6 + 60] = s9
s9 = @TreeSetKey
[s6 + 64] = s9
s9 = @TreeCompare
[s6 + 68] = s9
s9 = @TreeSetHas_Right
[s6 + 72] = s9
s9 = @TreeRecPrint
[s6 + 76] = s9
[s8 + 0] = s6
s6 = s8
if0 s6 goto null_err
s8 = [s6 + 0]
s8 = [s8 + 44]
this = s7
id128 = s6
id127 = s5
a2 = s6
a3 = s5
s9 = call s8()
s7 = this
s6 = id128
s5 = id127
s8 = s9
s9 = s7
s10 = 1
s7 = s10
loopid158:
if0 s7 goto endid158
if0 s9 goto null_err
s10 = [s9 + 0]
s10 = [s10 + 12]
id132 = s9
id130 = s7
id129 = s8
id128 = s6
id127 = s5
a2 = s9
s11 = call s10()
s9 = id132
s7 = id130
s8 = id129
s6 = id128
s5 = id127
s10 = s11
s11 = s5 < s10
if0 s11 goto elseid161
if0 s9 goto null_err
s11 = [s9 + 0]
s11 = [s11 + 52]
id132 = s9
id130 = s7
id129 = s8
id128 = s6
id127 = s5
a2 = s9
s10 = call s11()
s9 = id132
s7 = id130
s8 = id129
s6 = id128
s5 = id127
if0 s10 goto elseid163
if0 s9 goto null_err
s11 = [s9 + 0]
s11 = [s11 + 20]
id132 = s9
id130 = s7
id129 = s8
id128 = s6
id127 = s5
a2 = s9
s10 = call s11()
s9 = id132
s7 = id130
s8 = id129
s6 = id128
s5 = id127
s9 = s10
goto endid163
elseid163:
s10 = 0
s7 = s10
if0 s9 goto null_err
s10 = [s9 + 0]
s10 = [s10 + 4]
s11 = 1
id132 = s9
id130 = s7
id129 = s8
id128 = s6
id127 = s5
a2 = s9
a3 = s11
t0 = call s10()
s9 = id132
s7 = id130
s8 = id129
s6 = id128
s5 = id127
s8 = t0
if0 s9 goto null_err
s11 = [s9 + 0]
s11 = [s11 + 32]
id132 = s9
id130 = s7
id129 = s8
id128 = s6
id127 = s5
a2 = s9
a3 = s6
s10 = call s11()
s9 = id132
s7 = id130
s8 = id129
s6 = id128
s5 = id127
s8 = s10
endid163:
goto endid161
elseid161:
if0 s9 goto null_err
s10 = [s9 + 0]
s10 = [s10 + 48]
id132 = s9
id130 = s7
id129 = s8
id128 = s6
id127 = s5
a2 = s9
s11 = call s10()
s9 = id132
s7 = id130
s8 = id129
s6 = id128
s5 = id127
if0 s11 goto elseid173
if0 s9 goto null_err
s11 = [s9 + 0]
s11 = [s11 + 24]
id132 = s9
id130 = s7
id129 = s8
id128 = s6
id127 = s5
a2 = s9
s10 = call s11()
s9 = id132
s7 = id130
s8 = id129
s6 = id128
s5 = id127
s9 = s10
goto endid173
elseid173:
s10 = 0
s7 = s10
if0 s9 goto null_err
s11 = [s9 + 0]
s11 = [s11 + 72]
t0 = 1
id132 = s9
id130 = s7
id129 = s8
id128 = s6
id127 = s5
a2 = s9
a3 = t0
s10 = call s11()
s9 = id132
s7 = id130
s8 = id129
s6 = id128
s5 = id127
s8 = s10
if0 s9 goto null_err
s11 = [s9 + 0]
s11 = [s11 + 16]
id132 = s9
id130 = s7
id129 = s8
id128 = s6
id127 = s5
a2 = s9
a3 = s6
s10 = call s11()
s9 = id132
s7 = id130
s8 = id129
s6 = id128
s5 = id127
s8 = s10
endid173:
endid161:
goto loopid158
endid158:
s5 = 1
goto TreeInsert_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeInsert_end:
id182 = s5
      return id182

func TreeDelete()
t1 = a2
s3 = a3
t2 = t1
t4 = t1
s4 = 1
t5 = s4
s4 = 0
s1 = s4
s2 = 1
s4 = s2
loopid194:
if0 t5 goto endid194
if0 t2 goto null_err
s2 = [t2 + 0]
s2 = [s2 + 12]
id187 = s1
id186 = t5
id188 = s4
id183 = s3
id185 = t4
id184 = t2
this = t1
a2 = t2
t3 = call s2()
s1 = id187
t5 = id186
s4 = id188
s3 = id183
t4 = id185
t2 = id184
t1 = this
s2 = t3
t3 = s3 < s2
if0 t3 goto elseid197
if0 t2 goto null_err
t3 = [t2 + 0]
t3 = [t3 + 52]
id187 = s1
id186 = t5
id189 = s2
id188 = s4
id183 = s3
id185 = t4
id184 = t2
this = t1
a2 = t2
s7 = call t3()
s1 = id187
t5 = id186
s2 = id189
s4 = id188
s3 = id183
t4 = id185
t2 = id184
t1 = this
if0 s7 goto elseid199
t4 = t2
if0 t2 goto null_err
t3 = [t2 + 0]
t3 = [t3 + 20]
id187 = s1
id186 = t5
id189 = s2
id188 = s4
id183 = s3
id185 = t4
id184 = t2
this = t1
a2 = t2
s7 = call t3()
s1 = id187
t5 = id186
s2 = id189
s4 = id188
s3 = id183
t4 = id185
t2 = id184
t1 = this
t2 = s7
goto endid199
elseid199:
t3 = 0
t5 = t3
endid199:
goto endid197
elseid197:
t3 = s2 < s3
if0 t3 goto elseid203
if0 t2 goto null_err
t3 = [t2 + 0]
t3 = [t3 + 48]
id187 = s1
id186 = t5
id188 = s4
id183 = s3
id185 = t4
id184 = t2
this = t1
a2 = t2
s2 = call t3()
s1 = id187
t5 = id186
s4 = id188
s3 = id183
t4 = id185
t2 = id184
t1 = this
if0 s2 goto elseid205
t4 = t2
if0 t2 goto null_err
s2 = [t2 + 0]
s2 = [s2 + 24]
id187 = s1
id186 = t5
id188 = s4
id183 = s3
id185 = t4
id184 = t2
this = t1
a2 = t2
t3 = call s2()
s1 = id187
t5 = id186
s4 = id188
s3 = id183
t4 = id185
t2 = id184
t1 = this
t2 = t3
goto endid205
elseid205:
s2 = 0
t5 = s2
endid205:
goto endid203
elseid203:
if0 s4 goto elseid188
s11 = 0
if0 t2 goto null_err
s2 = [t2 + 0]
s2 = [s2 + 48]
id187 = s1
id186 = t5
id188 = s4
id183 = s3
id185 = t4
id184 = t2
this = t1
id209 = s11
a2 = t2
s7 = call s2()
s1 = id187
t5 = id186
s4 = id188
s3 = id183
t4 = id185
t2 = id184
t1 = this
s11 = id209
t3 = 1
s2 = t3 - s7
if0 s2 goto endid210
if0 t2 goto null_err
s2 = [t2 + 0]
s2 = [s2 + 52]
id187 = s1
id186 = t5
id188 = s4
id183 = s3
id185 = t4
id184 = t2
this = t1
id209 = s11
a2 = t2
t3 = call s2()
s1 = id187
t5 = id186
s4 = id188
s3 = id183
t4 = id185
t2 = id184
t1 = this
s11 = id209
s2 = 1
s7 = s2 - t3
s11 = s7
endid210:
if0 s11 goto elseid209
s2 = 1
s7 = s2
goto endid209
elseid209:
if0 t1 goto null_err
t3 = [t1 + 0]
t3 = [t3 + 28]
id187 = s1
id186 = t5
id188 = s4
id183 = s3
id185 = t4
id184 = t2
this = t1
id190 = s7
a2 = t1
a3 = t4
a4 = t2
s2 = call t3()
s1 = id187
t5 = id186
s4 = id188
s3 = id183
t4 = id185
t2 = id184
t1 = this
s7 = id190
s7 = s2
endid209:
goto endid188
elseid188:
if0 t1 goto null_err
t3 = [t1 + 0]
t3 = [t3 + 28]
id187 = s1
id186 = t5
id188 = s4
id183 = s3
id185 = t4
id184 = t2
this = t1
id190 = s7
a2 = t1
a3 = t4
a4 = t2
s2 = call t3()
s1 = id187
t5 = id186
s4 = id188
s3 = id183
t4 = id185
t2 = id184
t1 = this
s7 = id190
s7 = s2
endid188:
s2 = 1
s1 = s2
s2 = 0
t5 = s2
endid203:
endid197:
s2 = 0
s4 = s2
goto loopid194
endid194:
goto TreeDelete_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeDelete_end:
id187 = s1
      return id187

func TreeRemove()
t3 = a2
t1 = a3
s11 = a4
if0 s11 goto null_err
t2 = [s11 + 0]
t2 = [t2 + 52]
id227 = s11
id226 = t1
this = t3
a2 = s11
s2 = call t2()
s11 = id227
t1 = id226
t3 = this
if0 s2 goto elseid232
if0 t3 goto null_err
t2 = [t3 + 0]
t2 = [t2 + 8]
id227 = s11
id226 = t1
this = t3
a2 = t3
a3 = t1
a4 = s11
s2 = call t2()
s11 = id227
t1 = id226
t3 = this
t2 = s2
goto endid232
elseid232:
if0 s11 goto null_err
s2 = [s11 + 0]
s2 = [s2 + 48]
id228 = t2
id227 = s11
id226 = t1
this = t3
a2 = s11
s7 = call s2()
t2 = id228
s11 = id227
t1 = id226
t3 = this
if0 s7 goto elseid236
if0 t3 goto null_err
s7 = [t3 + 0]
s7 = [s7 + 56]
id228 = t2
id227 = s11
id226 = t1
this = t3
a2 = t3
a3 = t1
a4 = s11
s2 = call s7()
t2 = id228
s11 = id227
t1 = id226
t3 = this
t2 = s2
goto endid236
elseid236:
if0 s11 goto null_err
s7 = [s11 + 0]
s7 = [s7 + 12]
id228 = t2
id226 = t1
this = t3
a2 = s11
s2 = call s7()
t2 = id228
t1 = id226
t3 = this
s4 = s2
if0 t1 goto null_err
s11 = [t1 + 0]
s11 = [s11 + 20]
id228 = t2
id229 = s4
id226 = t1
this = t3
a2 = t1
s2 = call s11()
t2 = id228
s4 = id229
t1 = id226
t3 = this
if0 s2 goto null_err
s7 = [s2 + 0]
s7 = [s7 + 12]
id228 = t2
id229 = s4
id226 = t1
this = t3
a2 = s2
s11 = call s7()
t2 = id228
s4 = id229
t1 = id226
t3 = this
s7 = s11
if0 t3 goto null_err
s11 = [t3 + 0]
s11 = [s11 + 68]
id228 = t2
id226 = t1
this = t3
a2 = t3
a3 = s4
a4 = s7
s2 = call s11()
t2 = id228
t1 = id226
t3 = this
if0 s2 goto elseid246
if0 t1 goto null_err
s7 = [t1 + 0]
s7 = [s7 + 32]
s2 = [t3 + 12]
id228 = t2
id226 = t1
this = t3
a2 = t1
a3 = s2
s11 = call s7()
t2 = id228
t1 = id226
t3 = this
t2 = s11
if0 t1 goto null_err
s7 = [t1 + 0]
s7 = [s7 + 4]
s2 = 0
id228 = t2
id226 = t1
this = t3
a2 = t1
a3 = s2
s11 = call s7()
t2 = id228
t1 = id226
t3 = this
t2 = s11
goto endid246
elseid246:
if0 t1 goto null_err
s2 = [t1 + 0]
s2 = [s2 + 16]
s11 = [t3 + 12]
id228 = t2
id226 = t1
a2 = t1
a3 = s11
t3 = call s2()
t2 = id228
t1 = id226
t2 = t3
if0 t1 goto null_err
s2 = [t1 + 0]
s2 = [s2 + 72]
s11 = 0
id228 = t2
a2 = t1
a3 = s11
t3 = call s2()
t2 = id228
t2 = t3
endid246:
endid236:
endid232:
t1 = 1
goto TreeRemove_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeRemove_end:
id259 = t1
      return id259

func TreeRemoveRight()
s4 = a2
t3 = a3
s3 = a4
loopid263:
if0 s3 goto null_err
t4 = [s3 + 0]
t4 = [t4 + 48]
id260 = t3
id261 = s3
this = s4
a2 = s3
t5 = call t4()
t3 = id260
s3 = id261
s4 = this
if0 t5 goto endid263
if0 s3 goto null_err
t4 = [s3 + 0]
t4 = [t4 + 64]
if0 s3 goto null_err
t5 = [s3 + 0]
t5 = [t5 + 24]
id266 = t4
id260 = t3
id261 = s3
this = s4
a2 = s3
s2 = call t5()
t4 = id266
t3 = id260
s3 = id261
s4 = this
if0 s2 goto null_err
s1 = [s2 + 0]
s1 = [s1 + 12]
id266 = t4
id260 = t3
id261 = s3
this = s4
a2 = s2
t5 = call s1()
t4 = id266
t3 = id260
s3 = id261
s4 = this
id260 = t3
id261 = s3
this = s4
a2 = s3
a3 = t5
s1 = call t4()
t3 = id260
s3 = id261
s4 = this
s2 = s1
t3 = s3
if0 s3 goto null_err
t5 = [s3 + 0]
t5 = [t5 + 24]
id260 = t3
id262 = s2
id261 = s3
this = s4
a2 = s3
t4 = call t5()
t3 = id260
s2 = id262
s3 = id261
s4 = this
s3 = t4
goto loopid263
endid263:
if0 t3 goto null_err
t5 = [t3 + 0]
t5 = [t5 + 16]
s1 = [s4 + 12]
id260 = t3
id262 = s2
a2 = t3
a3 = s1
t4 = call t5()
t3 = id260
s2 = id262
s2 = t4
if0 t3 goto null_err
t4 = [t3 + 0]
t4 = [t4 + 72]
t5 = 0
id262 = s2
a2 = t3
a3 = t5
s1 = call t4()
s2 = id262
s2 = s1
t3 = 1
goto TreeRemoveRight_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeRemoveRight_end:
id280 = t3
      return id280

func TreeRemoveLeft()
t3 = a2
s8 = a3
s4 = a4
loopid284:
if0 s4 goto null_err
s10 = [s4 + 0]
s10 = [s10 + 52]
id282 = s4
id281 = s8
this = t3
a2 = s4
t5 = call s10()
s4 = id282
s8 = id281
t3 = this
if0 t5 goto endid284
if0 s4 goto null_err
s10 = [s4 + 0]
s10 = [s10 + 64]
if0 s4 goto null_err
t5 = [s4 + 0]
t5 = [t5 + 20]
id287 = s10
id282 = s4
id281 = s8
this = t3
a2 = s4
s2 = call t5()
s10 = id287
s4 = id282
s8 = id281
t3 = this
if0 s2 goto null_err
t5 = [s2 + 0]
t5 = [t5 + 12]
id287 = s10
id282 = s4
id281 = s8
this = t3
a2 = s2
t2 = call t5()
s10 = id287
s4 = id282
s8 = id281
t3 = this
id282 = s4
id281 = s8
this = t3
a2 = s4
a3 = t2
t5 = call s10()
s4 = id282
s8 = id281
t3 = this
t2 = t5
s8 = s4
if0 s4 goto null_err
t5 = [s4 + 0]
t5 = [t5 + 20]
id282 = s4
id281 = s8
id283 = t2
this = t3
a2 = s4
s10 = call t5()
s4 = id282
s8 = id281
t2 = id283
t3 = this
s4 = s10
goto loopid284
endid284:
if0 s8 goto null_err
s4 = [s8 + 0]
s4 = [s4 + 32]
t5 = [t3 + 12]
id281 = s8
id283 = t2
a2 = s8
a3 = t5
s10 = call s4()
s8 = id281
t2 = id283
t2 = s10
if0 s8 goto null_err
s10 = [s8 + 0]
s10 = [s10 + 4]
s4 = 0
id283 = t2
a2 = s8
a3 = s4
t5 = call s10()
t2 = id283
t2 = t5
s8 = 1
goto TreeRemoveLeft_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeRemoveLeft_end:
id301 = s8
      return id301

func TreeSearch()
s1 = a2
s3 = a3
s10 = s1
t2 = 1
s1 = t2
t3 = 0
t2 = t3
loopid309:
if0 s1 goto endid309
if0 s10 goto null_err
t3 = [s10 + 0]
t3 = [t3 + 12]
id305 = s10
id304 = t2
id303 = s1
id302 = s3
a2 = s10
s8 = call t3()
s10 = id305
t2 = id304
s1 = id303
s3 = id302
s4 = s8
t3 = s3 < s4
if0 t3 goto elseid312
if0 s10 goto null_err
s8 = [s10 + 0]
s8 = [s8 + 52]
id305 = s10
id304 = t2
id306 = s4
id303 = s1
id302 = s3
a2 = s10
t3 = call s8()
s10 = id305
t2 = id304
s4 = id306
s1 = id303
s3 = id302
if0 t3 goto elseid314
if0 s10 goto null_err
s8 = [s10 + 0]
s8 = [s8 + 20]
id305 = s10
id304 = t2
id306 = s4
id303 = s1
id302 = s3
a2 = s10
t3 = call s8()
s10 = id305
t2 = id304
s4 = id306
s1 = id303
s3 = id302
s10 = t3
goto endid314
elseid314:
t3 = 0
s1 = t3
endid314:
goto endid312
elseid312:
t3 = s4 < s3
if0 t3 goto elseid318
if0 s10 goto null_err
s8 = [s10 + 0]
s8 = [s8 + 48]
id305 = s10
id304 = t2
id303 = s1
id302 = s3
a2 = s10
t3 = call s8()
s10 = id305
t2 = id304
s1 = id303
s3 = id302
if0 t3 goto elseid320
if0 s10 goto null_err
t3 = [s10 + 0]
t3 = [t3 + 24]
id305 = s10
id304 = t2
id303 = s1
id302 = s3
a2 = s10
s8 = call t3()
s10 = id305
t2 = id304
s1 = id303
s3 = id302
s10 = s8
goto endid320
elseid320:
t3 = 0
s1 = t3
endid320:
goto endid318
elseid318:
t3 = 1
t2 = t3
t3 = 0
s1 = t3
endid318:
endid312:
goto loopid309
endid309:
goto TreeSearch_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeSearch_end:
id304 = t2
      return id304

func TreePrint()
s2 = a2
s11 = s2
if0 s2 goto null_err
s4 = [s2 + 0]
s4 = [s4 + 76]
a2 = s2
a3 = s11
s7 = call s4()
s2 = s7
s2 = 1
goto TreePrint_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreePrint_end:
id330 = s2
      return id330

func TreeRecPrint()
s9 = a2
s3 = a3
if0 s3 goto null_err
s1 = [s3 + 0]
s1 = [s1 + 52]
id331 = s3
this = s9
a2 = s3
s10 = call s1()
s3 = id331
s9 = this
if0 s10 goto elseid334
if0 s9 goto null_err
t5 = [s9 + 0]
t5 = [t5 + 76]
if0 s3 goto null_err
s1 = [s3 + 0]
s1 = [s1 + 20]
id331 = s3
this = s9
id335 = t5
a2 = s3
s10 = call s1()
s3 = id331
s9 = this
t5 = id335
id331 = s3
this = s9
a2 = s9
a3 = s10
s1 = call t5()
s3 = id331
s9 = this
t5 = s1
goto endid334
elseid334:
s10 = 1
t5 = s10
endid334:
if0 s3 goto null_err
s1 = [s3 + 0]
s1 = [s1 + 12]
id332 = t5
id331 = s3
this = s9
a2 = s3
s10 = call s1()
t5 = id332
s3 = id331
s9 = this
print(s10)
if0 s3 goto null_err
s1 = [s3 + 0]
s1 = [s1 + 48]
id332 = t5
id331 = s3
this = s9
a2 = s3
s10 = call s1()
t5 = id332
s3 = id331
s9 = this
if0 s10 goto elseid343
if0 s9 goto null_err
s1 = [s9 + 0]
s1 = [s1 + 76]
if0 s3 goto null_err
s10 = [s3 + 0]
s10 = [s10 + 24]
id332 = t5
this = s9
id344 = s1
a2 = s3
t4 = call s10()
t5 = id332
s9 = this
s1 = id344
id332 = t5
a2 = s9
a3 = t4
s10 = call s1()
t5 = id332
t5 = s10
goto endid343
elseid343:
s9 = 1
t5 = s9
endid343:
s9 = 1
goto TreeRecPrint_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
TreeRecPrint_end:
id349 = s9
      return id349


