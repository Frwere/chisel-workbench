package fifo

import chisel3._
import chisel3.util._
import MUX._

class onetomany_v2(num: Int) extends Module{
    val MasterFIFO = Module(new FIFO(UInt(32.W), 4))
    val SlaveFIFO = VecInit(Seq.tabulate(num)(_ => Module(new FIFO(UInt(32.W), 4)).io))
    val mux = Module(new otmMux(num))
    val io = IO(new Bundle{
        val sels = Input(Vec(num, Bool()))
        val Masterin = Flipped(Decoupled(UInt(32.W)))
        val Masterfull = Output(Bool())
        val Masterempty = Output(Bool())
        val Slaveout = Vec(num, Decoupled(UInt(32.W)))
        val Slavefull = Output(Vec(num, Bool()))
        val Slaveempty = Output(Vec(num, Bool()))
        val Masterwidx = Output(UInt(2.W))
        val Masterridx = Output(UInt(2.W))
        val Slavewidx = Output(Vec(num, UInt(2.W)))
        val Slaveridx = Output(Vec(num, UInt(2.W)))
    })

    io.Masterwidx := MasterFIFO.io.widx
    io.Masterridx := MasterFIFO.io.ridx
    io.Masterfull := MasterFIFO.io.full
    io.Masterempty := MasterFIFO.io.empty
    for(i <- 0 until num){
        io.Slavewidx(i) := SlaveFIFO(i).widx
        io.Slaveridx(i) := SlaveFIFO(i).ridx
        io.Slaveempty(i) := SlaveFIFO(i).empty
        io.Slavefull(i) := SlaveFIFO(i).full
    }


    io.Masterin <> MasterFIFO.io.in
    for(i <- 0 until num){
        SlaveFIFO(i).out <> io.Slaveout(i)
    }

    MasterFIFO.io.out <> mux.io.in
    for(i <- 0 until num){
        mux.io.out(i) <> SlaveFIFO(i).in
        mux.io.sels(i) := io.sels(i)
    }
    
 
   
}
