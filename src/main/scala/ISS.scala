package freechips.rocketchip.util

import chisel3._
import chisel3.util._


class ISS extends Module {
   val io = IO(new Bundle{
      val copy_valid    =    Input(Bool())
      val sent_valid    =    Input(Bool())
      val intreg_input  =    Input(Vec(32, UInt(64.W)))
      val fpreg_input   =    Input(Vec(32, UInt(65.W)))
      val intpc_input   =    Input(UInt(64.W))
      val fpcsr_input   =    Input(UInt(65.W))
      val copy_done     =    Output(Bool())
      val sent_done     =    Output(Bool())
      val intreg_output =    Output(Vec(32, UInt(64.W)))
      val fpreg_output  =    Output(Vec(32, UInt(65.W)))
      val intpc_output  =    Output(UInt(64.W))
      val fpcsr_output  =    Output(UInt(65.W))
})
   val intreg           =    Reg(Vec(32,UInt(64.W)))
   val fpreg            =    Reg(Vec(32,UInt(65.W)))
   val intpcreg         =    RegInit(0.U(64.W))
   val fpcsrreg         =    RegInit(0.U(65.W))
   
//copy request
when(io.copy_valid){
   for(i <-0 until 32){
      intreg(31-i) := io.intreg_input(i)
      fpreg(i) := io.fpreg_input(i)
   }  
   intpcreg := io.intpc_input
   fpcsrreg := io.fpcsr_input
   io.copy_done := true.B
} 
.otherwise{
      for(i <-0 until 32){
      intreg(31-i) := intreg(31-i)
      fpreg(i) := fpreg(i)   
   }
   intpcreg := intpcreg
   fpcsrreg := fpcsrreg
   io.copy_done := false.B
}

//sent request
when(io.sent_valid){
   for(i <-0 until 32){ 
      io.intreg_output(i) := intreg(31-i)
      io.fpreg_output(i) := fpreg(i)
   }     
   io.intpc_output := intpcreg
   io.fpcsr_output := fpcsrreg
   io.sent_done := true.B
}
.otherwise{
   for(i <-0 until 32){ 
      io.intreg_output(i) := 0.U  
      io.fpreg_output(i) := 0.U
   }     
   io.intpc_output := 0.U
   io.fpcsr_output := 0.U
   io.sent_done := false.B
}
}