func Main()
s8 = @f
s9 = call s8()
print(s9)
v1 = s9
      return v1

func f()
t3 = 10
s5 = 20
s3 = t3 + s5
t2 = 30
t0 = t3 < t2
if0 t0 goto L8
s2 = t3 + s3
t5 = t3 + s5
goto L10
L8:
s1 = s5 + s3
goto L14
L10:
t0 = 50
s5 = s2 + t0
s4 = s3 + s2
t0 = s4 + t5
print(t0)
s1 = s3 + t5
t0 = 40
s5 = s5 + t0
goto L8
L14:
s6 = 10
t5 = t3 + s6
L22:
s5 = 55
t0 = 20
s3 = t3 - t0
t1 = s5 < s3
if0 t1 goto L20
s2 = s3 + s5
s1 = s2 - s6
t1 = s2 + s1
print(t1)
s5 = s3 + s1
t1 = 2
t5 = t1 - s5
goto L22
L20:
t3 = 7
s5 = t5 + t3
s3 = s3 + s5
t1 = 2
s1 = t1 * s3
t4 = t5 + s6
t3 = 55
s6 = t3
s7 = @func_g
t2 = t4 < s6
if0 t2 goto L27
t3 = t4 - t0
two = t1
twenty = t0
c = s3
d = s2
g = t4
i = t3
s = s4
a2 = s5
a3 = s3
a4 = s2
a5 = s1
a6 = t5
a7 = t4
h = s6
i = t3
s6 = call s7(h i)
t1 = two
t0 = twenty
s3 = c
s2 = d
t4 = g
t3 = i
s4 = s
s1 = t3 + s6
goto L29
L27:
t5 = 1
t2 = t4 + t5
s1 = t1 * t2
L29:
t1 = 5
t5 = s1 - t1
t2 = t4 + t5
t1 = s1 - t2
s5 = t1 + t5
print(s5)
t3 = s1 + t2
s3 = s3 + s2
t1 = s2 + t0
t5 = t3 + t1
s4 = s2 < t5
L3:
if0 s4 goto L12
s1 = s2 + t1
s5 = 30
t0 = s1 + s5
t0 = t3 - t0
t5 = s1 - t0
t0 = 0
s4 = t5 * t0
t1 = s3 + t4
t5 = s1 + t5
t0 = t1 - t5
print(t0)
goto L3
L12:
t4 = t2 - t5
t4 = s1 + t4
g = t4
      return g

func func_g(x7 x8)
t3 = a2
t5 = a3
s2 = a4
t4 = a5
s4 = a6
s6 = a7
t0 = x7
s10 = x8
s11 = t3 + t5
s3 = s2 + t4
s2 = s4 + s6
t4 = t0 + s10
s1 = 10
s6 = s1
t2 = s10 < t3
if0 t2 goto L42
s10 = @func_g
b = s3
c = s2
d = t4
e = s6
a2 = t2
a3 = t0
a4 = t2
a5 = s4
a6 = s1
a7 = t2
x2 = t5
ten = s1
s11 = call s10(x2 ten)
s3 = b
s2 = c
t4 = d
s6 = e
L42:
s6 = t4 - s11
s10 = s3 * s6
s11 = s2 - s10
s3 = t4 + s6
s2 = s6 + s10
s10 = s11 + s3
j = s10
      return j


