class Main {
    public static void main(String[] a){
        // System.out.println(new B().hi());
        System.out.println(new C().run());
        // C c; // < this is another problem to solve
        // c = new C();
        // System.out.println(c.hi());
        System.out.println(100);
    }
}

class A {
    int x;
    public int run() {
        return 10;
    }
}

class B extends A {
    public B hi() {
        x=5;
        return new B();
    }
}

class C extends B {
    public int run() {
        int d;
        d = (this.hi()).hi();
        System.out.println(200);
        System.out.println(x);
        return x;
    }
}