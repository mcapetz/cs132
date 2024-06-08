module clk_divider(
    input clk_in,
    input [32:0] speed,
    input rst,
    output reg clk_out
)

parameter BASE_SPEED = 1000000;
wire [32:0] threshold = BASE_SPEED / speed;
reg [32:0] counter = 0;
reg [32:0] counter_d;
reg clk_out_d;

initial begin
    clk_out = 0;
end

always @(*) begin // combinational
    // calculate counter_d
    if(rst || counter == threshold / 2) begin
        counter_d = 0;
    end
    else begin
        counter_d = counter + 1;
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

always @(posedge clk_in) begin
    counter <= counter_d;
    clk_out <= clk_out_d;
end

endmodule


// testbench

module tb_lab3(output reg clk100, output reg clk200);

reg clock = 0;
integer i;
clockDivider100hz clk1(clock, clk100);
clockDivider200Hz clk2(clock, clk200);

always begin
    #20 clock = ~clock;
end

initial begin   
    #10000 $stop;
end

endmodule 


module tb_lab3(output reg clk100, output reg clk200);

reg clock = 0;
integer i;
clockDivider100hz clk1(clock, clk100);
clockDivider200Hz clk2(clock, clk200);

always begin
    #20 clock = ~clock;
end
initial begin
    #1000000 $stop;
end

endmodule