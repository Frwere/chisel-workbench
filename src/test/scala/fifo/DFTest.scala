package fifo

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class DFTest extends AnyFlatSpec with ChiselScalatestTester {
    "FIFO" should "pass basic tests" in {
        test(new doubleFIFO(UInt(8.W), 8)){c =>
        c.io.out.ready.poke(false.B)
        c.io.in.valid.poke(true.B)

        println("testing write:")
        for(i <- 0 until 8){
            val datain = i * i + 1
            c.io.in.bits.poke((datain.U))
            c.clock.step(1)
            println(s"$i step")
            //println(s"\tfull1=${c.io.full1.peek().litValue}, full2=${c.io.full2.peek().litValue}")
            //println(s"\twidx1=${c.io.widx1.peek().litValue}, widx2=${c.io.widx2.peek().litValue}")
            //println(s"\tridx1=${c.io.ridx1.peek().litValue}, ridx2=${c.io.ridx2.peek().litValue}")
        }
        println("write test success")

        println("testing read:")
        println(s"\tbits=${c.io.out.bits.peek().litValue}")
        c.io.out.ready.poke(true.B)
        c.io.in.valid.poke(false.B)
        for(i <- 0 until 8){
            val datain = i * i + 1
            c.clock.step(1)
            c.io.dataout.expect(datain.U)
            println(s"$i read:")
            println(s"\tdataout=${c.io.dataout.peek().litValue}")
            //println(s"\tempty1=${c.io.empty1.peek().litValue}, empty2=${c.io.empty2.peek().litValue}")
            //println(s"\twidx1=${c.io.widx1.peek().litValue}, widx2=${c.io.widx2.peek().litValue}")
            //println(s"\tridx1=${c.io.ridx1.peek().litValue}, ridx2=${c.io.ridx2.peek().litValue}")
        }
        println("read test success")
    


        }

    }
}

