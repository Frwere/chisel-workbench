package fifo

import chisel3._
import chisel3.util._

class doubleFIFO[T <: Data](ioType: T, depth: Int) extends Module{
    val FIFO1 = Module(new FIFO(ioType, depth/2))
    val FIFO2 = Module(new FIFO(ioType, depth/2))
    //val io1 = IO(FIFO1.io)
    //val io2 = IO(FIFO2.io)
    val io = IO(new Bundle{
        val in = Flipped(Decoupled(ioType))
        val out = Decoupled(ioType)
        val dataout = Output(ioType)
        val full1 = Output(Bool())
        val full2 = Output(Bool())
        val empty1 = Output(Bool())
        val empty2 = Output(Bool())
        val widx1 = Output(UInt((log2Ceil(depth)).W))
        val widx2 = Output(UInt((log2Ceil(depth)).W))
        val ridx1 = Output(UInt((log2Ceil(depth)).W))
        val ridx2 = Output(UInt((log2Ceil(depth)).W))
        
    })

    require(depth > 1 && isPow2(depth))
    io.dataout := RegNext(io.out.bits)
    
    io.full1 := FIFO1.io.full
    io.full2 := FIFO2.io.full
    io.empty1 := FIFO1.io.empty
    io.empty2 := FIFO2.io.empty

    
    io.widx1 := FIFO1.io.widx
    io.widx2 := FIFO2.io.widx
    io.ridx1 := FIFO1.io.ridx
    io.ridx2 := FIFO2.io.ridx
    

    io.in <> FIFO1.io.in
    FIFO1.io.out <> FIFO2.io.in
    FIFO2.io.out <> io.out 

}
