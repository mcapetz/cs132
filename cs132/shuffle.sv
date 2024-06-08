func main()
stack_1 = a4
stack_2 = a7
stack_3 = a3
stack_4 = a5
stack_5 = a2
stack_6 = s11
stack_7 = a6
t0 = id1
t0 = 1
a2 = 2
a3 = 3
a4 = 4
a5 = 5
a6 = 6
a7 = @f
id1 = t0
id2 = a2
id3 = a3
id4 = a4
id5 = a5
id6 = a6
s11 = call a7(id1 id2 id3 id4 id5 id6)
id1 = t0
a4 = stack_1
a7 = stack_2
a3 = stack_3
a5 = stack_4
a2 = stack_5
t0 = stack_6
s11 = stack_7
a6 = stack_8
      return id1

func f(x1 x2 x3 x4 x5 x6)
stack_1 = t3
stack_2 = s1
stack_3 = s4
stack_4 = s3
stack_5 = t5
stack_6 = t4
stack_7 = s2
stack_8 = t2
t2 = x1
t3 = x2
t4 = x3
t5 = x4
s1 = x5
s2 = x6
print(t2)
print(t3)
print(t4)
print(t5)
print(s1)
print(s2)
s3 = @g
x6 = s2
x5 = s1
x4 = t5
x3 = t4
x2 = t3
x1 = t2
s4 = call s3(x6 x5 x4 x3 x2 x1)
x1 = t2
t3 = stack_1
s1 = stack_2
s4 = stack_3
s3 = stack_4
t5 = stack_5
t4 = stack_6
s2 = stack_7
t2 = stack_8
      return x1

func g(v1 v2 v3 v4 v5 v6)
stack_1 = s6
stack_2 = s8
stack_3 = s9
stack_4 = s10
stack_5 = s7
stack_6 = s5
s6 = v1
s7 = v2
s8 = v3
s9 = v4
s10 = v5
s5 = v6
print(s6)
print(s7)
print(s8)
print(s9)
print(s10)
print(s5)
v1 = s6
s6 = stack_1
s8 = stack_2
s9 = stack_3
s10 = stack_4
s7 = stack_5
s5 = stack_6
      return v1


