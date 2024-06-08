class CF {
	public static void main(String[] a){
        System.out.println(new A().run());	
	}
}

class A {
	public int run() {
        int j;
        int i;
        boolean b;
        i = 6;
		j = 1 ;
        b = true;
	    while (b){ // i = 7
            System.out.println(j);
            j = j + 1;
            if(1 < (j - i)) {
                b = false;
            }
            else {
                b = true;
            }


        }

        return 0;
    }
}