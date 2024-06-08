class Main {
	public static void main(String[] a){
		A a;
        a = new A();

        if ((a.func1()) && (a.func2())) {
			System.out.println(100);
		}
		else {
			System.out.println(200);
		}
		System.out.println(5);
	}
}

class A {
 
	public bool func1() {
		System.out.println(35);
		return false;
	}
	public bool func2() {
		System.out.println(34);
		return true;
	}
	public int run() {
		if ((this.func1()) && (this.func2())) {
			System.out.println(100);
		}
		else {
			System.out.println(200);
		}
		return 5;
	}

}