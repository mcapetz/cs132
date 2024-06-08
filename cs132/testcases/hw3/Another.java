class Main {
	public static void main(String[] a){
		System.out.println(new A().run());
	}
}

class A {
	int b;
	public int run() {
        int c;
		c = this.helper();
		return b;
	}
	public int helper() {
		b = 3;
		return b;
	}
}
