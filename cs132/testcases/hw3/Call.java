class Main {
	public static void main(String[] a){
        int x;
        int y;
        x = 0;
        y = 0;
		System.out.println(new A().run(x, y));
	}
}

class A {


	public int run2() {
		System.out.println(42);
		return 100;
	}

	public int run(int x, int y) {
		System.out.println(42);
        System.out.println(this.run2());
		return 99;
	}
}
