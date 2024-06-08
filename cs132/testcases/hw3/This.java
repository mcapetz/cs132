

class Main {
	public static void main(String[] a){
        TestClass obj;
        obj = new TestClass();
        System.out.println(obj.run());
	}
}
class TestClass {
    int val;
    TestClass test_class;

    public int change() {
        val = 10; 
        return 0;
    }

    public int change2() {
        val = 11; 
        return 0;
    }

    public int getVal() {
        return val;
    }

    public int run() {
        TestClass x;
        TestClass y;
        x = this;
        System.out.println(x.change());
        System.out.println(x.getVal());
        System.out.println(x.change2());
        System.out.println(x.getVal());

        y = this;
        System.out.println(x.getVal());


        return 0;
    }
}