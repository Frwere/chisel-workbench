package fifo

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class otmTest extends AnyFlatSpec with ChiselScalatestTester {
    "otm" should "pass basic tests" in {
        test(new onetomany(4)){c =>
        c.io.sels(0).poke(true.B)
        c.io.sels(1).poke(false.B)
        c.io.sels(2).poke(false.B)
        c.io.sels(3).poke(false.B)

        c.io.Masterin.valid.poke(true.B)
        c.io.Slaveout.foreach(_.ready.poke(true.B))
    

        println("test0:")
        for(i <- 0 until 4){
            val data = i * i + 1
            c.io.Masterin.bits.poke(data.U)
            c.clock.step(1)
            println(s"$i step")
            println(s"\tMasterwidx=${c.io.Masterwidx.peek().litValue}, Masterridx=${c.io.Masterridx.peek().litValue}")
            println(s"\tSwidx0=${c.io.Slavewidx(0).peek().litValue}, Swidx1=${c.io.Slavewidx(1).peek().litValue}. Swidx2=${c.io.Slavewidx(2).peek().litValue}, widx3=${c.io.Slavewidx(3).peek().litValue}")
            println(s"\tSridx0=${c.io.Slaveridx(0).peek().litValue}, Sridx1=${c.io.Slaveridx(1).peek().litValue}. Sridx2=${c.io.Slaveridx(2).peek().litValue}, ridx3=${c.io.Slaveridx(3).peek().litValue}")
            println(s"\tout0=${c.io.Slaveout(0).bits.peek().litValue}, out1=${c.io.Slaveout(1).bits.peek().litValue}. out2=${c.io.Slaveout(2).bits.peek().litValue}, out3=${c.io.Slaveout(3).bits.peek().litValue}")
        }

        c.io.sels(0).poke(false.B)
        c.io.sels(1).poke(true.B)
        c.io.sels(2).poke(false.B)
        c.io.sels(3).poke(false.B)
        println("test1:")
        for(i <- 0 until 4){
            val data = i * i + 1
            c.io.Masterin.bits.poke(data.U)
            c.clock.step(1)
            println(s"$i step")
            println(s"\tMasterwidx=${c.io.Masterwidx.peek().litValue}, Masterridx=${c.io.Masterridx.peek().litValue}")
            println(s"\tSwidx0=${c.io.Slavewidx(0).peek().litValue}, Swidx1=${c.io.Slavewidx(1).peek().litValue}. Swidx2=${c.io.Slavewidx(2).peek().litValue}, widx3=${c.io.Slavewidx(3).peek().litValue}")
            println(s"\tSridx0=${c.io.Slaveridx(0).peek().litValue}, Sridx1=${c.io.Slaveridx(1).peek().litValue}. Sridx2=${c.io.Slaveridx(2).peek().litValue}, ridx3=${c.io.Slaveridx(3).peek().litValue}")
            println(s"\tout0=${c.io.Slaveout(0).bits.peek().litValue}, out1=${c.io.Slaveout(1).bits.peek().litValue}. out2=${c.io.Slaveout(2).bits.peek().litValue}, out3=${c.io.Slaveout(3).bits.peek().litValue}")
        }

        c.io.sels(0).poke(false.B)
        c.io.sels(1).poke(false.B)
        c.io.sels(2).poke(true.B)
        c.io.sels(3).poke(false.B)
        println("test1:")
        for(i <- 0 until 4){
            val data = i * i + 1
            c.io.Masterin.bits.poke(data.U)
            c.clock.step(1)
            println(s"$i step")
            println(s"\tMasterwidx=${c.io.Masterwidx.peek().litValue}, Masterridx=${c.io.Masterridx.peek().litValue}")
            println(s"\tSwidx0=${c.io.Slavewidx(0).peek().litValue}, Swidx1=${c.io.Slavewidx(1).peek().litValue}. Swidx2=${c.io.Slavewidx(2).peek().litValue}, widx3=${c.io.Slavewidx(3).peek().litValue}")
            println(s"\tSridx0=${c.io.Slaveridx(0).peek().litValue}, Sridx1=${c.io.Slaveridx(1).peek().litValue}. Sridx2=${c.io.Slaveridx(2).peek().litValue}, ridx3=${c.io.Slaveridx(3).peek().litValue}")
            println(s"\tout0=${c.io.Slaveout(0).bits.peek().litValue}, out1=${c.io.Slaveout(1).bits.peek().litValue}. out2=${c.io.Slaveout(2).bits.peek().litValue}, out3=${c.io.Slaveout(3).bits.peek().litValue}")
        }

        c.io.sels(0).poke(false.B)
        c.io.sels(1).poke(false.B)
        c.io.sels(2).poke(false.B)
        c.io.sels(3).poke(true.B)
        println("test1:")
        for(i <- 0 until 4){
            val data = i * i + 1
            c.io.Masterin.bits.poke(data.U)
            c.clock.step(1)
            println(s"$i step")
            println(s"\tMasterwidx=${c.io.Masterwidx.peek().litValue}, Masterridx=${c.io.Masterridx.peek().litValue}")
            println(s"\tSwidx0=${c.io.Slavewidx(0).peek().litValue}, Swidx1=${c.io.Slavewidx(1).peek().litValue}. Swidx2=${c.io.Slavewidx(2).peek().litValue}, widx3=${c.io.Slavewidx(3).peek().litValue}")
            println(s"\tSridx0=${c.io.Slaveridx(0).peek().litValue}, Sridx1=${c.io.Slaveridx(1).peek().litValue}. Sridx2=${c.io.Slaveridx(2).peek().litValue}, ridx3=${c.io.Slaveridx(3).peek().litValue}")
            println(s"\tout0=${c.io.Slaveout(0).bits.peek().litValue}, out1=${c.io.Slaveout(1).bits.peek().litValue}. out2=${c.io.Slaveout(2).bits.peek().litValue}, out3=${c.io.Slaveout(3).bits.peek().litValue}")
        }
        }

    }
}
