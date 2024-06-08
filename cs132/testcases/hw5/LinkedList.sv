func main()
t1 = 4
s11 = alloc(t1)
if0 s11 goto null_err
t1 = 4
t3 = alloc(t1)
if0 t3 goto null_err
t1 = @LLStart
[t3 + 0] = t1
[s11 + 0] = t3
if0 s11 goto null_err
t3 = [s11 + 0]
t3 = [t3 + 0]
a2 = s11
t1 = call t3()
print(t1)
goto main_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
main_end:
s11 = 0
id5 = s11
      return id5

func ElementInit()
t0 = a2
t1 = a3
t3 = a4
t2 = a5
t4 = [t0 + 12]
[t0 + 12] = t1
t1 = [t0 + 4]
[t0 + 4] = t3
t1 = [t0 + 8]
[t0 + 8] = t2
t0 = 1
goto ElementInit_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
ElementInit_end:
id12 = t0
      return id12

func ElementGetAge()
s11 = a2
t1 = [s11 + 12]
goto ElementGetAge_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
ElementGetAge_end:
id13 = t1
      return id13

func ElementGetSalary()
s4 = a2
t1 = [s4 + 4]
goto ElementGetSalary_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
ElementGetSalary_end:
id14 = t1
      return id14

func ElementGetMarried()
s7 = a2
s9 = [s7 + 8]
goto ElementGetMarried_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
ElementGetMarried_end:
id15 = s9
      return id15

func ElementEqual()
s4 = a2
s6 = a3
s7 = 1
s5 = s7
if0 s6 goto null_err
s7 = [s6 + 0]
s7 = [s7 + 8]
this = s4
id17 = s5
id16 = s6
a2 = s6
s8 = call s7()
s4 = this
s5 = id17
s6 = id16
s10 = s8
if0 s4 goto null_err
s8 = [s4 + 0]
s8 = [s8 + 20]
s7 = [s4 + 12]
this = s4
id17 = s5
id16 = s6
a2 = s4
a3 = s10
a4 = s7
s9 = call s8()
s4 = this
s5 = id17
s6 = id16
s7 = 1
s8 = s7 - s9
if0 s8 goto elseid24
s7 = 0
s5 = s7
goto endid24
elseid24:
if0 s6 goto null_err
s8 = [s6 + 0]
s8 = [s8 + 0]
this = s4
id17 = s5
id16 = s6
a2 = s6
s7 = call s8()
s4 = this
s5 = id17
s6 = id16
s10 = s7
if0 s4 goto null_err
s7 = [s4 + 0]
s7 = [s7 + 20]
s8 = [s4 + 4]
this = s4
id17 = s5
id16 = s6
a2 = s4
a3 = s10
a4 = s8
s9 = call s7()
s4 = this
s5 = id17
s6 = id16
s8 = 1
s7 = s8 - s9
if0 s7 goto elseid32
s7 = 0
s5 = s7
goto endid32
elseid32:
s7 = [s4 + 8]
if0 s7 goto elseid38
if0 s6 goto null_err
s4 = [s6 + 0]
s4 = [s4 + 16]
id17 = s5
id16 = s6
a2 = s6
s7 = call s4()
s5 = id17
s6 = id16
s4 = 1
s8 = s4 - s7
if0 s8 goto elseid39
s4 = 0
s5 = s4
goto endid39
elseid39:
s7 = 0
s4 = s7
endid39:
goto endid38
elseid38:
if0 s6 goto null_err
s8 = [s6 + 0]
s8 = [s8 + 16]
id20 = s4
id17 = s5
a2 = s6
s7 = call s8()
s4 = id20
s5 = id17
if0 s7 goto elseid46
s6 = 0
s5 = s6
goto endid46
elseid46:
s6 = 0
s4 = s6
endid46:
endid38:
endid32:
endid24:
goto ElementEqual_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
ElementEqual_end:
id17 = s5
      return id17

func ElementCompare()
s1 = a2
s4 = a3
t5 = a4
s2 = 0
s1 = s2
s2 = 1
s3 = t5 + s2
s2 = s3
s3 = s4 < t5
if0 s3 goto elseid56
t5 = 0
s1 = t5
goto endid56
elseid56:
s3 = s4 < s2
t5 = 1
s2 = t5 - s3
if0 s2 goto elseid58
t5 = 0
s1 = t5
goto endid58
elseid58:
t5 = 1
s1 = t5
endid58:
endid56:
goto ElementCompare_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
ElementCompare_end:
id51 = s1
      return id51

func ListInit()
s5 = a2
s6 = [s5 + 12]
s6 = 1
[s5 + 12] = s6
s5 = 1
goto ListInit_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
ListInit_end:
id65 = s5
      return id65

func ListInitNew()
t2 = a2
s5 = a3
s8 = a4
s3 = a5
s6 = [t2 + 12]
[t2 + 12] = s3
s3 = [t2 + 4]
[t2 + 4] = s5
s3 = [t2 + 8]
[t2 + 8] = s8
s3 = 1
goto ListInitNew_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
ListInitNew_end:
id72 = s3
      return id72

func ListInsert()
s11 = a2
t4 = a3
t3 = s11
s11 = 16
t5 = alloc(s11)
if0 t5 goto null_err
s11 = 40
t0 = alloc(s11)
if0 t0 goto null_err
s11 = @ListDelete
[t0 + 0] = s11
s11 = @ListPrint
[t0 + 4] = s11
s11 = @ListInit
[t0 + 8] = s11
s11 = @ListGetElem
[t0 + 12] = s11
s11 = @ListGetNext
[t0 + 16] = s11
s11 = @ListSetNext
[t0 + 20] = s11
s11 = @ListSearch
[t0 + 24] = s11
s11 = @ListGetEnd
[t0 + 28] = s11
s11 = @ListInitNew
[t0 + 32] = s11
s11 = @ListInsert
[t0 + 36] = s11
[t5 + 0] = t0
t0 = t5
if0 t0 goto null_err
s1 = [t0 + 0]
s1 = [s1 + 32]
s11 = 0
id76 = t0
a2 = t0
a3 = t4
a4 = t3
a5 = s11
t5 = call s1()
t0 = id76
t3 = t5
goto ListInsert_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
ListInsert_end:
id76 = t0
      return id76

func ListSetNext()
t0 = a2
t3 = a3
t2 = [t0 + 8]
[t0 + 8] = t3
t2 = 1
goto ListSetNext_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
ListSetNext_end:
id94 = t2
      return id94

func ListDelete()
s5 = a2
s1 = a3
s2 = s5
t2 = 0
t4 = t2
t2 = 0
t5 = 1
t0 = t2 - t5
t5 = t0
s3 = s5
s4 = s5
t2 = [s5 + 12]
s9 = t2
t2 = [s5 + 4]
t0 = t2
loopid111:
t2 = 0
s5 = 1
s8 = s5 - s9
if0 s8 goto endid113
s5 = 1
s8 = s5 - t4
t2 = s8
endid113:
if0 t2 goto endid111
if0 s1 goto null_err
s5 = [s1 + 0]
s5 = [s5 + 12]
id101 = s9
id100 = s4
id103 = t5
id102 = t0
id95 = s1
id97 = t4
id96 = s2
id99 = s3
a2 = s1
a3 = t0
t2 = call s5()
s9 = id101
s4 = id100
t5 = id103
t0 = id102
s1 = id95
t4 = id97
s2 = id96
s3 = id99
if0 t2 goto elseid118
t2 = 1
t4 = t2
t2 = 0
s5 = t5 < t2
if0 s5 goto elseid120
if0 s3 goto null_err
s5 = [s3 + 0]
s5 = [s5 + 16]
id101 = s9
id100 = s4
id103 = t5
id102 = t0
id95 = s1
id97 = t4
id96 = s2
id99 = s3
a2 = s3
t2 = call s5()
s9 = id101
s4 = id100
t5 = id103
t0 = id102
s1 = id95
t4 = id97
s2 = id96
s3 = id99
s2 = t2
goto endid120
elseid120:
t2 = 0
s8 = 555
s5 = t2 - s8
print(s5)
if0 s4 goto null_err
s8 = [s4 + 0]
s8 = [s8 + 20]
if0 s3 goto null_err
s5 = [s3 + 0]
s5 = [s5 + 16]
id101 = s9
id100 = s4
id103 = t5
id102 = t0
id95 = s1
id97 = t4
id96 = s2
id99 = s3
id127 = s8
a2 = s3
t2 = call s5()
s9 = id101
s4 = id100
t5 = id103
t0 = id102
s1 = id95
t4 = id97
s2 = id96
s3 = id99
s8 = id127
id101 = s9
id100 = s4
id103 = t5
id102 = t0
id95 = s1
id97 = t4
id96 = s2
id99 = s3
a2 = s4
a3 = t2
s5 = call s8()
s9 = id101
s4 = id100
t5 = id103
t0 = id102
s1 = id95
t4 = id97
s2 = id96
s3 = id99
t2 = s5
t2 = 0
s8 = 555
s5 = t2 - s8
print(s5)
endid120:
goto endid118
elseid118:
s5 = 0
t2 = s5
endid118:
s5 = 1
s8 = s5 - t4
if0 s8 goto elseid135
s4 = s3
if0 s3 goto null_err
s8 = [s3 + 0]
s8 = [s8 + 16]
id101 = s9
id100 = s4
id103 = t5
id102 = t0
id104 = t2
id95 = s1
id97 = t4
id96 = s2
id99 = s3
a2 = s3
s5 = call s8()
s9 = id101
s4 = id100
t5 = id103
t0 = id102
t2 = id104
s1 = id95
t4 = id97
s2 = id96
s3 = id99
s3 = s5
if0 s3 goto null_err
s8 = [s3 + 0]
s8 = [s8 + 28]
id101 = s9
id100 = s4
id103 = t5
id102 = t0
id104 = t2
id95 = s1
id97 = t4
id96 = s2
id99 = s3
a2 = s3
s5 = call s8()
s9 = id101
s4 = id100
t5 = id103
t0 = id102
t2 = id104
s1 = id95
t4 = id97
s2 = id96
s3 = id99
s9 = s5
if0 s3 goto null_err
s8 = [s3 + 0]
s8 = [s8 + 12]
id101 = s9
id100 = s4
id103 = t5
id102 = t0
id104 = t2
id95 = s1
id97 = t4
id96 = s2
id99 = s3
a2 = s3
s5 = call s8()
s9 = id101
s4 = id100
t5 = id103
t0 = id102
t2 = id104
s1 = id95
t4 = id97
s2 = id96
s3 = id99
t0 = s5
s5 = 1
t5 = s5
goto endid135
elseid135:
s5 = 0
t2 = s5
endid135:
goto loopid111
endid111:
goto ListDelete_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
ListDelete_end:
id96 = s2
      return id96

func ListSearch()
s6 = a2
s3 = a3
s2 = 0
t2 = s2
s5 = s6
s2 = [s6 + 12]
s10 = s2
s8 = [s6 + 4]
s2 = s8
loopid154:
s8 = 1
s6 = s8 - s10
if0 s6 goto endid154
if0 s3 goto null_err
s6 = [s3 + 0]
s6 = [s6 + 12]
id145 = s3
id147 = s5
id146 = t2
id149 = s10
id148 = s2
a2 = s3
a3 = s2
s8 = call s6()
s3 = id145
s5 = id147
t2 = id146
s10 = id149
s2 = id148
if0 s8 goto elseid158
s8 = 1
t2 = s8
goto endid158
elseid158:
s6 = 0
s8 = s6
endid158:
if0 s5 goto null_err
s8 = [s5 + 0]
s8 = [s8 + 16]
id145 = s3
id147 = s5
id146 = t2
id149 = s10
id148 = s2
a2 = s5
s6 = call s8()
s3 = id145
s5 = id147
t2 = id146
s10 = id149
s2 = id148
s5 = s6
if0 s5 goto null_err
s6 = [s5 + 0]
s6 = [s6 + 28]
id145 = s3
id147 = s5
id146 = t2
id149 = s10
id148 = s2
a2 = s5
s8 = call s6()
s3 = id145
s5 = id147
t2 = id146
s10 = id149
s2 = id148
s10 = s8
if0 s5 goto null_err
s8 = [s5 + 0]
s8 = [s8 + 12]
id145 = s3
id147 = s5
id146 = t2
id149 = s10
id148 = s2
a2 = s5
s6 = call s8()
s3 = id145
s5 = id147
t2 = id146
s10 = id149
s2 = id148
s2 = s6
goto loopid154
endid154:
goto ListSearch_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
ListSearch_end:
id146 = t2
      return id146

func ListGetEnd()
s8 = a2
s7 = [s8 + 12]
goto ListGetEnd_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
ListGetEnd_end:
id167 = s7
      return id167

func ListGetElem()
s9 = a2
s10 = [s9 + 4]
goto ListGetElem_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
ListGetElem_end:
id168 = s10
      return id168

func ListGetNext()
s6 = a2
s7 = [s6 + 8]
goto ListGetNext_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
ListGetNext_end:
id169 = s7
      return id169

func ListPrint()
s1 = a2
s2 = s1
t4 = [s1 + 12]
s3 = t4
t4 = [s1 + 4]
t5 = t4
loopid175:
s1 = 1
t4 = s1 - s3
if0 t4 goto endid175
if0 t5 goto null_err
t4 = [t5 + 0]
t4 = [t4 + 8]
id172 = t5
id171 = s3
id170 = s2
a2 = t5
s1 = call t4()
t5 = id172
s3 = id171
s2 = id170
print(s1)
if0 s2 goto null_err
s1 = [s2 + 0]
s1 = [s1 + 16]
id172 = t5
id171 = s3
id170 = s2
a2 = s2
t4 = call s1()
t5 = id172
s3 = id171
s2 = id170
s2 = t4
if0 s2 goto null_err
s1 = [s2 + 0]
s1 = [s1 + 28]
id172 = t5
id171 = s3
id170 = s2
a2 = s2
t4 = call s1()
t5 = id172
s3 = id171
s2 = id170
s3 = t4
if0 s2 goto null_err
s1 = [s2 + 0]
s1 = [s1 + 12]
id172 = t5
id171 = s3
id170 = s2
a2 = s2
t4 = call s1()
t5 = id172
s3 = id171
s2 = id170
t5 = t4
goto loopid175
endid175:
t4 = 1
goto ListPrint_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
ListPrint_end:
id186 = t4
      return id186

func LLStart()
s10 = a2
t1 = 16
s11 = alloc(t1)
if0 s11 goto null_err
t1 = 40
s10 = alloc(t1)
if0 s10 goto null_err
t1 = @ListDelete
[s10 + 0] = t1
t1 = @ListPrint
[s10 + 4] = t1
t1 = @ListInit
[s10 + 8] = t1
t1 = @ListGetElem
[s10 + 12] = t1
t1 = @ListGetNext
[s10 + 16] = t1
t1 = @ListSetNext
[s10 + 20] = t1
t1 = @ListSearch
[s10 + 24] = t1
t1 = @ListGetEnd
[s10 + 28] = t1
t1 = @ListInitNew
[s10 + 32] = t1
t1 = @ListInsert
[s10 + 36] = t1
[s11 + 0] = s10
s10 = s11
if0 s10 goto null_err
s11 = [s10 + 0]
s11 = [s11 + 8]
id188 = s10
a2 = s10
t1 = call s11()
s10 = id188
t3 = t1
s9 = s10
if0 s9 goto null_err
t1 = [s9 + 0]
t1 = [t1 + 8]
id187 = s9
id189 = t3
a2 = s9
s10 = call t1()
s9 = id187
t3 = id189
t3 = s10
if0 s9 goto null_err
t1 = [s9 + 0]
t1 = [t1 + 4]
id187 = s9
id189 = t3
a2 = s9
s10 = call t1()
s9 = id187
t3 = id189
t3 = s10
t1 = 16
s10 = alloc(t1)
if0 s10 goto null_err
t1 = 24
s11 = alloc(t1)
if0 s11 goto null_err
t1 = @ElementGetSalary
[s11 + 0] = t1
t1 = @ElementInit
[s11 + 4] = t1
t1 = @ElementGetAge
[s11 + 8] = t1
t1 = @ElementEqual
[s11 + 12] = t1
t1 = @ElementGetMarried
[s11 + 16] = t1
t1 = @ElementCompare
[s11 + 20] = t1
[s10 + 0] = s11
t1 = s10
if0 t1 goto null_err
s1 = [t1 + 0]
s1 = [s1 + 4]
t5 = 25
s4 = 37000
t0 = 0
id187 = s9
id189 = t3
Elementvmt = s11
id190 = t1
a2 = t1
a3 = t5
a4 = s4
a5 = t0
s10 = call s1()
s9 = id187
t3 = id189
s11 = Elementvmt
t1 = id190
t3 = s10
if0 s9 goto null_err
s10 = [s9 + 0]
s10 = [s10 + 36]
id187 = s9
id189 = t3
Elementvmt = s11
id190 = t1
a2 = s9
a3 = t1
s4 = call s10()
s9 = id187
t3 = id189
s11 = Elementvmt
t1 = id190
s9 = s4
if0 s9 goto null_err
s4 = [s9 + 0]
s4 = [s4 + 4]
id187 = s9
id189 = t3
Elementvmt = s11
id190 = t1
a2 = s9
s10 = call s4()
s9 = id187
t3 = id189
s11 = Elementvmt
t1 = id190
t3 = s10
s10 = 10000000
print(s10)
s4 = 16
s10 = alloc(s4)
if0 s10 goto null_err
s4 = 24
s11 = alloc(s4)
if0 s11 goto null_err
s4 = @ElementGetSalary
[s11 + 0] = s4
s4 = @ElementInit
[s11 + 4] = s4
s4 = @ElementGetAge
[s11 + 8] = s4
s4 = @ElementEqual
[s11 + 12] = s4
s4 = @ElementGetMarried
[s11 + 16] = s4
s4 = @ElementCompare
[s11 + 20] = s4
[s10 + 0] = s11
t1 = s10
if0 t1 goto null_err
s1 = [t1 + 0]
s1 = [s1 + 4]
t5 = 39
s4 = 42000
s10 = 1
id187 = s9
id189 = t3
Elementvmt = s11
id190 = t1
a2 = t1
a3 = t5
a4 = s4
a5 = s10
t0 = call s1()
s9 = id187
t3 = id189
s11 = Elementvmt
t1 = id190
t3 = t0
s10 = t1
if0 s9 goto null_err
s4 = [s9 + 0]
s4 = [s4 + 36]
id187 = s9
id189 = t3
Elementvmt = s11
id190 = t1
id191 = s10
a2 = s9
a3 = t1
t5 = call s4()
s9 = id187
t3 = id189
s11 = Elementvmt
t1 = id190
s10 = id191
s9 = t5
if0 s9 goto null_err
s4 = [s9 + 0]
s4 = [s4 + 4]
id187 = s9
id189 = t3
Elementvmt = s11
id190 = t1
id191 = s10
a2 = s9
t5 = call s4()
s9 = id187
t3 = id189
s11 = Elementvmt
t1 = id190
s10 = id191
t3 = t5
s4 = 10000000
print(s4)
s4 = 16
t5 = alloc(s4)
if0 t5 goto null_err
s4 = 24
s11 = alloc(s4)
if0 s11 goto null_err
s4 = @ElementGetSalary
[s11 + 0] = s4
s4 = @ElementInit
[s11 + 4] = s4
s4 = @ElementGetAge
[s11 + 8] = s4
s4 = @ElementEqual
[s11 + 12] = s4
s4 = @ElementGetMarried
[s11 + 16] = s4
s4 = @ElementCompare
[s11 + 20] = s4
[t5 + 0] = s11
t1 = t5
if0 t1 goto null_err
s4 = [t1 + 0]
s4 = [s4 + 4]
t5 = 22
t4 = 34000
s1 = 0
id187 = s9
id189 = t3
Elementvmt = s11
id190 = t1
id191 = s10
a2 = t1
a3 = t5
a4 = t4
a5 = s1
t0 = call s4()
s9 = id187
t3 = id189
s11 = Elementvmt
t1 = id190
s10 = id191
t3 = t0
if0 s9 goto null_err
s4 = [s9 + 0]
s4 = [s4 + 36]
id187 = s9
id189 = t3
Elementvmt = s11
id190 = t1
id191 = s10
a2 = s9
a3 = t1
t5 = call s4()
s9 = id187
t3 = id189
s11 = Elementvmt
t1 = id190
s10 = id191
s9 = t5
if0 s9 goto null_err
t5 = [s9 + 0]
t5 = [t5 + 4]
id187 = s9
id189 = t3
Elementvmt = s11
id190 = t1
id191 = s10
a2 = s9
s4 = call t5()
s9 = id187
t3 = id189
s11 = Elementvmt
t1 = id190
s10 = id191
t3 = s4
s4 = 16
t5 = alloc(s4)
if0 t5 goto null_err
s4 = 24
s11 = alloc(s4)
if0 s11 goto null_err
s4 = @ElementGetSalary
[s11 + 0] = s4
s4 = @ElementInit
[s11 + 4] = s4
s4 = @ElementGetAge
[s11 + 8] = s4
s4 = @ElementEqual
[s11 + 12] = s4
s4 = @ElementGetMarried
[s11 + 16] = s4
s4 = @ElementCompare
[s11 + 20] = s4
[t5 + 0] = s11
s2 = t5
if0 s2 goto null_err
t4 = [s2 + 0]
t4 = [t4 + 4]
t5 = 27
s4 = 34000
t0 = 0
id187 = s9
id189 = t3
Elementvmt = s11
id190 = t1
id192 = s2
id191 = s10
a2 = s2
a3 = t5
a4 = s4
a5 = t0
s1 = call t4()
s9 = id187
t3 = id189
s11 = Elementvmt
t1 = id190
s2 = id192
s10 = id191
t3 = s1
if0 s9 goto null_err
s4 = [s9 + 0]
s4 = [s4 + 24]
id187 = s9
id189 = t3
Elementvmt = s11
id190 = t1
id192 = s2
id191 = s10
a2 = s9
a3 = s10
t5 = call s4()
s9 = id187
t3 = id189
s11 = Elementvmt
t1 = id190
s2 = id192
s10 = id191
print(t5)
if0 s9 goto null_err
t5 = [s9 + 0]
t5 = [t5 + 24]
id187 = s9
id189 = t3
Elementvmt = s11
id190 = t1
id191 = s10
a2 = s9
a3 = s2
s4 = call t5()
s9 = id187
t3 = id189
s11 = Elementvmt
t1 = id190
s10 = id191
print(s4)
s4 = 10000000
print(s4)
s4 = 16
t5 = alloc(s4)
if0 t5 goto null_err
s4 = 24
s11 = alloc(s4)
if0 s11 goto null_err
s4 = @ElementGetSalary
[s11 + 0] = s4
s4 = @ElementInit
[s11 + 4] = s4
s4 = @ElementGetAge
[s11 + 8] = s4
s4 = @ElementEqual
[s11 + 12] = s4
s4 = @ElementGetMarried
[s11 + 16] = s4
s4 = @ElementCompare
[s11 + 20] = s4
[t5 + 0] = s11
t1 = t5
if0 t1 goto null_err
s1 = [t1 + 0]
s1 = [s1 + 4]
s4 = 28
s11 = 35000
t5 = 0
id187 = s9
id189 = t3
id190 = t1
id191 = s10
a2 = t1
a3 = s4
a4 = s11
a5 = t5
t0 = call s1()
s9 = id187
t3 = id189
t1 = id190
s10 = id191
t3 = t0
if0 s9 goto null_err
s4 = [s9 + 0]
s4 = [s4 + 36]
id187 = s9
id189 = t3
id190 = t1
id191 = s10
a2 = s9
a3 = t1
s11 = call s4()
s9 = id187
t3 = id189
t1 = id190
s10 = id191
s9 = s11
if0 s9 goto null_err
s11 = [s9 + 0]
s11 = [s11 + 4]
id187 = s9
id189 = t3
id190 = t1
id191 = s10
a2 = s9
s4 = call s11()
s9 = id187
t3 = id189
t1 = id190
s10 = id191
t3 = s4
s11 = 2220000
print(s11)
if0 s9 goto null_err
s4 = [s9 + 0]
s4 = [s4 + 0]
id187 = s9
id189 = t3
id190 = t1
a2 = s9
a3 = s10
s11 = call s4()
s9 = id187
t3 = id189
t1 = id190
s9 = s11
if0 s9 goto null_err
s11 = [s9 + 0]
s11 = [s11 + 4]
id187 = s9
id189 = t3
id190 = t1
a2 = s9
s10 = call s11()
s9 = id187
t3 = id189
t1 = id190
t3 = s10
s10 = 33300000
print(s10)
if0 s9 goto null_err
s10 = [s9 + 0]
s10 = [s10 + 0]
id187 = s9
id189 = t3
a2 = s9
a3 = t1
s11 = call s10()
s9 = id187
t3 = id189
s9 = s11
if0 s9 goto null_err
t1 = [s9 + 0]
t1 = [t1 + 4]
id189 = t3
a2 = s9
s10 = call t1()
t3 = id189
t3 = s10
s10 = 44440000
print(s10)
s10 = 0
goto LLStart_end
null_err:
error("null pointer")
array_err:
error("array index out of bounds")
LLStart_end:
id310 = s10
      return id310


