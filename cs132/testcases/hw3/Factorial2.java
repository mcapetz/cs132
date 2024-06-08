class Factorial{
    public static void main(String[] a){
        System.out.println(new Fac().ComputeFac(10));
    }
}

class A {
    int b;
    int c;

    public int one() {
        int[] x;
        int c;
        int d;
		x = new int[20];

        x[3] = 9;
        x[19] = 8;

        c = x[3];
        d = x[19];

        b = c - d;
        return b;
    }
}

class Fac {
    public int ComputeFac(int num){
        int num_aux ;
        boolean b;
        A a;
        // a = new A();

        if (num < 1) {
             a = new A();
             num_aux = a.one() ;
            // num_aux = 1;
        }
        else
            num_aux = num * (this.ComputeFac(num-1)) ;
        return num_aux ;
    }
}
