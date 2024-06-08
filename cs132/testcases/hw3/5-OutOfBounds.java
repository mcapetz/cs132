class Main {
	public static void main(String[] a){

		int[] x;
		x = new int[20];
		x[2] = 99;
		x[19] = 39;

		System.out.println(x[19]);

		System.out.println(new A().run());
		
	}
}

class A {
	public int run() {
		int[] a;
		int x;
		a = new int[20];
		a[2] = 99;
		a[1] = 39;
		x = 2-3;
		a[4] = 5;

		return a[2];
	}
}
