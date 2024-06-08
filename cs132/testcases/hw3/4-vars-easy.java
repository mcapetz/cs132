class Main {
	public static void main(String[] a){
		System.out.println(new A().run());
	}
}

class A {
	public int run() {
		int a;
		a = this.helper(12);
		return a + 13;
	}

	public int helper(int param) {
		int x;
		x = param;
		param = param + 1;
		System.out.println(x);
		return x;
	}
}