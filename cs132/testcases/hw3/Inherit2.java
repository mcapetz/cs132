class Inherit { 
    public static void main (String [] args) { 
        System.out.println(new A().run()); 
        System.out.println(new B().run());   
        System.out.println(new B().run2());         
    } 
} 

class A {
    int f1;
    int f2;
    public int run() {
        f1 = 6;
        return 99;
    }

}

class B extends A {
    public int run() {
        C c;
        c = new C();
        System.out.println(c.run()); 
        return f1;
    }

    public int run2() {
        return 88;
    }


}

class C extends A {
    

}



