func Main()
a5 = 0
t0 = v1
t0 = 1
stack_0 = t1
t1 = v2
null = 2
a6 = 3
stack_1 = t1
t1 = v4
null = 4
stack_2 = t1
t1 = v5
null = 5
stack_3 = t1
t1 = v6
null = 6
stack_4 = t1
t1 = v7
null = 7
stack_5 = t1
t1 = v8
null = 8
stack_6 = t1
t1 = v9
null = 9
stack_7 = t1
t1 = v10
null = 10
stack_8 = t1
t1 = v11
null = 11
stack_9 = t1
t1 = v12
null = 12
stack_10 = t1
t1 = v13
null = 13
stack_11 = t1
t1 = v14
null = 14
stack_12 = t1
t1 = v15
null = 15
stack_13 = t1
t1 = v16
null = 16
stack_14 = t1
t1 = v17
null = 17
stack_15 = t1
t1 = v18
null = 18
stack_16 = t1
t1 = v19
null = 19
a5 = 4
t0 = w0
t0 = alloc(a5)
a7 = alloc(a5)
a5 = @f
[a7 + 0] = a5
a5 = a7
[t0 + 0] = a5
if0 t0 goto null1
t1 = w1
t1 = [t0 + 0]
t1 = [t1 + 0]
w0 = t0
v1 = t0
v2 = stack_0
v3 = a6
v4 = stack_1
v5 = stack_2
v6 = stack_3
v7 = stack_4
a7 = call t1(w0 v1 v2 v3 v4 v5 v6 v7)
print(a7)
stack_16 = stack_16 + stack_15
stack_14 = stack_14 + stack_13
stack_12 = stack_12 + stack_11
stack_10 = stack_10 + stack_9
stack_8 = stack_8 + stack_7
stack_6 = stack_6 + stack_5
stack_4 = stack_4 + stack_3
stack_2 = stack_2 + stack_1
a6 = stack_0 + t0
goto main_end
null1:
error("null pointer")
main_end:
v0 = a5
      return v0

func f(this n1 n2 n3 n4 n5 n6 n7)
stack_1 = s1
stack_2 = s3
stack_3 = s5
stack_4 = t2
stack_5 = t3
stack_6 = s6
stack_7 = s4
stack_8 = t5
stack_9 = t4
stack_10 = s2
s2 = this
t2 = n1
t3 = n2
t4 = n3
t5 = n4
s1 = n5
s3 = n6
s4 = n7
s2 = 4
s5 = alloc(s2)
s6 = alloc(s2)
s2 = @g
[s6 + 0] = s2
s2 = s6
[s5 + 0] = s2
if0 s5 goto null1
s2 = [s5 + 0]
s2 = [s2 + 0]
w0 = s5
n7 = s4
n6 = s3
n5 = s1
n4 = t5
n3 = t4
n2 = t3
n1 = t2
s6 = call s2(w0 n7 n6 n5 n4 n3 n2 n1)
print(t2)
print(t3)
print(t4)
print(t5)
print(s1)
print(s3)
print(s4)
goto f_end
null1:
error("null pointer")
f_end:
n1 = t2
s1 = stack_1
s3 = stack_2
s5 = stack_3
t2 = stack_4
t3 = stack_5
s6 = stack_6
s4 = stack_7
t5 = stack_8
t4 = stack_9
s2 = stack_10
      return n1

func g(this n1 n2 n3 n4 n5 n6 n7)
stack_1 = s9
stack_2 = a2
stack_3 = s10
stack_4 = s7
stack_5 = s11
stack_6 = a3
stack_7 = s8
stack_8 = a4
a2 = this
s7 = n1
s8 = n2
s9 = n3
s10 = n4
s11 = n5
a3 = n6
a4 = n7
print(s7)
print(s8)
print(s9)
print(s10)
print(s11)
print(a3)
print(a4)
n1 = s7
s9 = stack_1
a2 = stack_2
s10 = stack_3
s7 = stack_4
s11 = stack_5
a3 = stack_6
s8 = stack_7
a4 = stack_8
      return n1


