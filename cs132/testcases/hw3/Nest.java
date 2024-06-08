class Main {
	public static void main(String[] a){
		System.out.println(new A().run());
	}
}

 class B {
    public int run() {
        C c;
        c = new C();
        System.out.println(c.helper());
        return 100;
    }
}

class C {

    public int helper() {
        System.out.println(0);
        return 99;
    }

}

class A {

    public int helper() {
        C c;
        c = new C();
        return c.helper();
    }
    
	public int run() {
        B b;
        b = new B();
        System.out.println(this.helper());
        return b.run();
	}
}
