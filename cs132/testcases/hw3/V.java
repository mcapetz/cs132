class Main {
    public static void main(String[] a){
        System.out.println(new C().run());
        // System.out.println(200);
    }
}

class A {
    int x;
    public int run() {
        return 10;
    }
    public int hi() {
        x=5;
        return x;
    }
}

class B extends A {
    
}

class C extends B {
    public int run() {
        int d;
        d = this.hi();
        return x;
    }
}