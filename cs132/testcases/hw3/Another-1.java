class Main {
	public static void main(String[] a){
		System.out.println(new A().run());
	}
}

class B {
	public int helper1(){
		return 1;
	}
}

class A {
	public B joke() {
        A a;
        a = this;
        System.out.println(111);
        System.out.println((a.joke()).helper1());
		return new B();
	}
	public int run() {
        System.out.println(222);
		return (this.joke()).helper1();
	}
}