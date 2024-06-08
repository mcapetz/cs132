class BubbleSort{
    public static void main(String[] a){
	System.out.println(new BBS().Start(10));
    }
}


// This class contains the array of integers and
// methods to initialize, print and sort the array
// using Bublesort
class BBS{
    
    int[] number ;
    int size ;

    // Invoke the Initialization, Sort and Printing
    // Methods
    public int Start(int sz){
	int aux01 ;
	aux01 = this.Init(sz);
	aux01 = this.Print();
	System.out.println(99999); // so i got to this
	aux01 = this.Sort();
	aux01 = this.Print();
	return 0 ;
    }

 
    // Sort array of integers using Bublesort method
    public int Sort(){
	int nt ;
	int i ;
	int aux02 ;
	int aux04 ;
	int aux05 ;
	int aux06 ;
	int aux07 ;
	int j ;
	int t ;
	int my_var;

	my_var = number.length;
	// // System.out.println(2222);
	// System.out.println(my_var);
	// System.out.println(2222);
	i = size - 1 ;
	aux02 = 0 - 1 ;
	// System.out.println(size); // seems like the size is 10
	// System.out.println(i); // seems like i is 9
	while (aux02 < i) {
	    j = 1 ;
	    //aux03 = i+1 ;
	    while (j < (i+1)){ // j < 10
		aux07 = j - 1 ;
		aux04 = number[aux07] ;
		// System.out.println(8888);
		// System.out.println(j); // get to j = 7
		aux05 = number[j] ; // now there is a problem
		// System.out.println(7777);
		if (aux05 < aux04) {
		    aux06 = j - 1 ;
		    t = number[aux06] ;
			// System.out.println(6666);
		    number[aux06] = number[j] ;
			// System.out.println(5555);
		    number[j] = t;
			// System.out.println(4444);
		}
		else nt = 0 ;
		j = j + 1 ;
	    }
	    i = i - 1 ;
	}
	return 0 ;
    }

    // Printing method
    public int Print(){
	int j ;
	j = 0 ;
	while (j < (size)) {
	    System.out.println(number[j]);
	    j = j + 1 ;
	}
	return 0 ;
    }
    
    // Initialize array of integers
    public int Init(int sz){
	size = sz ;
	number = new int[sz] ;
	
	number[0] = 20 ;
	number[1] = 7  ; 
	number[2] = 12 ;
	number[3] = 18 ;
	number[4] = 2  ; 
	number[5] = 11 ;
	number[6] = 6  ; 
	number[7] = 9  ; 
	number[8] = 19 ; 
	number[9] = 5  ;
	
	return 0 ;	
    }

}
