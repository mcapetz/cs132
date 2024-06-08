class MemVar {
 public static void main(String[] a){
  System.out.println(new A().run());
 }
}

class A {
 int b;
 public int run() {
    int c;
  c = this.helper();
  System.out.println(c);
  c = this.helper2();
  System.out.println(c);
  return b;
 }
 public int helper() {
  b = 3;
  return b;
 }
 public int helper2() {
  b = 5;
  return b;
 }
}