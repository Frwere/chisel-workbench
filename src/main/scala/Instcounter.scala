package freechips.rocketchip.util

import chisel3._
import chisel3.util._


class Instcounter extends Module{
   val io = IO(new Bundle{
      val wb_valid    =    Input(Bool())
      val copy_valid  =    Output(Bool())
})
   val instnumber     =    RegInit(0.U(64.W))
   val fpcsrreg       =    RegInit(0.U(65.W))
   
//copy request
when(io.wb_valid){
   instnumber := instnumber + 1.U
}
.otherwise{
   instnumber := instnumber
}
when(instnumber === 5000.U){
   io.copy_valid := true.B
}
.otherwise
{
   io.copy_valid := false.B
}
}
