class Main {
	public static void main(String[] a){
        A a;
        int y;

		System.out.println(new A().run(5, 6, false));
        System.out.println((1));

        a = new A();
        y = a.x();

	}
}

class A {

    public int x() {
        return 1;
    }

	public int run(int size, int z, boolean b) {
        int[] arr;
        arr = new int[size];
        arr[0] = 1;
        b = true && false;

        if(true) {
            System.out.println(5);
            System.out.println(5);
            System.out.println(5);
            System.out.println(5);
        }
        else {
            System.out.println(6);
        }

		return (5 + 6) - 4;
	}
}
