class Inherit { 
    public static void main (String [] args) { 
        A a; 
        A a2;
        a = new A();
        System.out.println(a.run()); 
        System.out.println(a.run2()); 
        
    } 
} 

class A {
    int f1;
    int f2;
    public int run() {
        int c;
        int d;
        f1 = 0;
        f2 = 3;
        return f1 + f2;
    }

    public int run2() {
        return f2 * 2;
    }

    
}


