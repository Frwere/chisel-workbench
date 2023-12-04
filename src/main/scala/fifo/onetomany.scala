package fifo

import chisel3._
import chisel3.util._

class onetomany(num: Int) extends Module{
    val MasterFIFO = Module(new FIFO(UInt(8.W), 8))
    val SlaveFIFO = VecInit(Seq.tabulate(num)(_ => Module(new FIFO(UInt(8.W), 8)).io))
    
    val io = IO(new Bundle{
        val sels = Input(Vec(num, Bool()))
        val Masterin = Flipped(Decoupled(UInt(8.W)))
        val Masterout = Decoupled(UInt(8.W))
        val Slavein = Flipped(Vec(num, Decoupled(UInt(8.W))))
        val Slaveout = Vec(num, Decoupled(UInt(8.W)))
        val Masterwidx = Output(UInt(3.W))
        val Masterridx = Output(UInt(3.W))
        val Slavewidx = Output(Vec(num, UInt(3.W)))
        val Slaveridx = Output(Vec(num, UInt(3.W)))
    })

    io.Masterwidx := MasterFIFO.io.widx
    io.Masterridx := MasterFIFO.io.ridx
    for(i <- 0 until num){
        io.Slavewidx(i) := SlaveFIFO(i).widx
        io.Slaveridx(i) := SlaveFIFO(i).ridx
    }

    io.Masterin <> MasterFIFO.io.in
    MasterFIFO.io.out <> io.Masterout
    for(i <- 0 until num){
        io.Slavein(i) <> SlaveFIFO(i).in
        SlaveFIFO(i).out <> io.Slaveout(i)
    }

    for(i <- 0 until num){
        when(io.sels(i)){
            MasterFIFO.io.out <> SlaveFIFO(i).in
        }
    }
}
