module clk_divider(
        input clk_in,
        input [32:0] speed,
        input rst,
        output reg clk_out
//        output reg [32:0] counter_d
    );
    
    parameter BASE_SPEED = 100000000; // can reduce this
    wire [32:0] threshold = BASE_SPEED / speed; // making the fast clock slower, now we know how many pulses to accumulate
    reg [32:0] counter = 0;
    reg [32:0] counter_d;
    reg clk_out_d;
    
    // want combinational components to do the heavy lifting since sequential is too slow
    
    // threshold to make clock = 0 half the time, = 1 half the time (50% cycle clock)
    
    initial begin
        clk_out = 0;
    end
    
    always @(*) begin // combinational
        // calculate counter_d
        // count up until you hit half the threshold then make it zero
        if(rst || counter == threshold / 2) begin
            counter_d = 0;
        end
        else begin
            counter_d = counter + 1;  // _d is the next val that is updated once a cycle
        end
        // calculate clk_out_d
        if(rst) begin
            clk_out_d = 0;
        end
        else if(counter == threshold / 2 || counter == threshold) begin
            clk_out_d = !clk_out;
        end
        else begin
            clk_out_d = clk_out;
        end
    end
    
    always @(posedge clk_in) begin // sequential
        counter <= counter_d;
        clk_out <= clk_out_d;
    end
     
endmodule


// testbench practice


module tb_lab3(output reg clk100, output reg clk200);
	reg clock = 0;
	integer i;
	clockDivider100hz clk1(clock, clk100);
	clockDivider200hz clk2(clock, clk200);

	always begin
		#20 clock = ~clock;
	end
	
	initial begin
		#10000 $stop;
	end
endmodule