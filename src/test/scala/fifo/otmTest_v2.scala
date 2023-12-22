package fifo

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec


class otmTest_v2 extends AnyFlatSpec with ChiselScalatestTester {
    "otm" should "pass basic tests" in {
        test(new onetomany_v2(4)){c =>
        c.io.sels(0).poke(true.B)
        c.io.sels(1).poke(false.B)
        c.io.sels(2).poke(false.B)
        c.io.sels(3).poke(false.B)

        c.io.Masterin.valid.poke(true.B)
        c.io.Slaveout.foreach(_.ready.poke(false.B))
    

        println("test0:")
        for(i <- 0 until 3){
            val data = i * i + 1
            c.io.Masterin.bits.poke(data.U)
            c.clock.step(1)
            println(s"$i step")
            println(s"\tMasterwidx=${c.io.Masterwidx.peek().litValue}, Masterridx=${c.io.Masterridx.peek().litValue}")
            println(s"\tMasterfull=${c.io.Masterfull.peek().litValue},Masterempty=${c.io.Masterempty.peek().litValue}")
            println(s"\tSwidx0=${c.io.Slavewidx(0).peek().litValue}, Swidx1=${c.io.Slavewidx(1).peek().litValue}. Swidx2=${c.io.Slavewidx(2).peek().litValue}, Swidx3=${c.io.Slavewidx(3).peek().litValue}")
            println(s"\tSridx0=${c.io.Slaveridx(0).peek().litValue}, Sridx1=${c.io.Slaveridx(1).peek().litValue}. Sridx2=${c.io.Slaveridx(2).peek().litValue}, Sridx3=${c.io.Slaveridx(3).peek().litValue}")
            println(s"\tSfull0=${c.io.Slavefull(0).peek().litValue}, Sfull1=${c.io.Slavefull(1).peek().litValue}. Sfull2=${c.io.Slavefull(2).peek().litValue}, Sfull3=${c.io.Slavefull(3).peek().litValue}")
            println(s"\tSempty0=${c.io.Slaveempty(0).peek().litValue}, Sempty1=${c.io.Slaveempty(1).peek().litValue}. Sempty2=${c.io.Slaveempty(2).peek().litValue}, Sempty3=${c.io.Slaveempty(3).peek().litValue}")
            println(s"\tout0=${c.io.Slaveout(0).bits.peek().litValue}, out1=${c.io.Slaveout(1).bits.peek().litValue}. out2=${c.io.Slaveout(2).bits.peek().litValue}, out3=${c.io.Slaveout(3).bits.peek().litValue}")
        }

        c.io.Masterin.valid.poke(false.B)
        c.io.Slaveout.foreach(_.ready.poke(true.B))
        c.io.sels(0).poke(true.B)
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
            println(s"\tMasterfull=${c.io.Masterfull.peek().litValue},Masterempty=${c.io.Masterempty.peek().litValue}")
            println(s"\tSwidx0=${c.io.Slavewidx(0).peek().litValue}, Swidx1=${c.io.Slavewidx(1).peek().litValue}. Swidx2=${c.io.Slavewidx(2).peek().litValue}, Swidx3=${c.io.Slavewidx(3).peek().litValue}")
            println(s"\tSridx0=${c.io.Slaveridx(0).peek().litValue}, Sridx1=${c.io.Slaveridx(1).peek().litValue}. Sridx2=${c.io.Slaveridx(2).peek().litValue}, Sridx3=${c.io.Slaveridx(3).peek().litValue}")
            println(s"\tSfull0=${c.io.Slavefull(0).peek().litValue}, Sfull1=${c.io.Slavefull(1).peek().litValue}. Sfull2=${c.io.Slavefull(2).peek().litValue}, Sfull3=${c.io.Slavefull(3).peek().litValue}")
            println(s"\tSempty0=${c.io.Slaveempty(0).peek().litValue}, Sempty1=${c.io.Slaveempty(1).peek().litValue}. Sempty2=${c.io.Slaveempty(2).peek().litValue}, Sempty3=${c.io.Slaveempty(3).peek().litValue}")
            println(s"\tout0=${c.io.Slaveout(0).bits.peek().litValue}, out1=${c.io.Slaveout(1).bits.peek().litValue}. out2=${c.io.Slaveout(2).bits.peek().litValue}, out3=${c.io.Slaveout(3).bits.peek().litValue}")
        }

        c.io.Masterin.valid.poke(true.B)
        c.io.Slaveout.foreach(_.ready.poke(false.B))
        c.io.sels(0).poke(false.B)
        c.io.sels(1).poke(false.B)
        c.io.sels(2).poke(true.B)
        c.io.sels(3).poke(false.B)
        println("test2:")
        for(i <- 0 until 4){
            val data = i * i + 1
            c.io.Masterin.bits.poke(data.U)
            c.clock.step(1)
            println(s"$i step")
            println(s"\tMasterwidx=${c.io.Masterwidx.peek().litValue}, Masterridx=${c.io.Masterridx.peek().litValue}")
            println(s"\tMasterfull=${c.io.Masterfull.peek().litValue},Masterempty=${c.io.Masterempty.peek().litValue}")
            println(s"\tSwidx0=${c.io.Slavewidx(0).peek().litValue}, Swidx1=${c.io.Slavewidx(1).peek().litValue}. Swidx2=${c.io.Slavewidx(2).peek().litValue}, Swidx3=${c.io.Slavewidx(3).peek().litValue}")
            println(s"\tSridx0=${c.io.Slaveridx(0).peek().litValue}, Sridx1=${c.io.Slaveridx(1).peek().litValue}. Sridx2=${c.io.Slaveridx(2).peek().litValue}, Sridx3=${c.io.Slaveridx(3).peek().litValue}")
            println(s"\tSfull0=${c.io.Slavefull(0).peek().litValue}, Sfull1=${c.io.Slavefull(1).peek().litValue}. Sfull2=${c.io.Slavefull(2).peek().litValue}, Sfull3=${c.io.Slavefull(3).peek().litValue}")
            println(s"\tSempty0=${c.io.Slaveempty(0).peek().litValue}, Sempty1=${c.io.Slaveempty(1).peek().litValue}. Sempty2=${c.io.Slaveempty(2).peek().litValue}, Sempty3=${c.io.Slaveempty(3).peek().litValue}")
            println(s"\tout0=${c.io.Slaveout(0).bits.peek().litValue}, out1=${c.io.Slaveout(1).bits.peek().litValue}. out2=${c.io.Slaveout(2).bits.peek().litValue}, out3=${c.io.Slaveout(3).bits.peek().litValue}")
        }

        c.io.sels(0).poke(false.B)
        c.io.sels(1).poke(false.B)
        c.io.sels(2).poke(false.B)
        c.io.sels(3).poke(true.B)
        println("test3:")
        for(i <- 0 until 4){
            val data = i * i + 1
            c.io.Masterin.bits.poke(data.U)
            c.clock.step(1)
            println(s"$i step")
            println(s"\tMasterwidx=${c.io.Masterwidx.peek().litValue}, Masterridx=${c.io.Masterridx.peek().litValue}")
            println(s"\tMasterfull=${c.io.Masterfull.peek().litValue},Masterempty=${c.io.Masterempty.peek().litValue}")
            println(s"\tSwidx0=${c.io.Slavewidx(0).peek().litValue}, Swidx1=${c.io.Slavewidx(1).peek().litValue}. Swidx2=${c.io.Slavewidx(2).peek().litValue}, Swidx3=${c.io.Slavewidx(3).peek().litValue}")
            println(s"\tSridx0=${c.io.Slaveridx(0).peek().litValue}, Sridx1=${c.io.Slaveridx(1).peek().litValue}. Sridx2=${c.io.Slaveridx(2).peek().litValue}, Sridx3=${c.io.Slaveridx(3).peek().litValue}")
            println(s"\tSfull0=${c.io.Slavefull(0).peek().litValue}, Sfull1=${c.io.Slavefull(1).peek().litValue}. Sfull2=${c.io.Slavefull(2).peek().litValue}, Sfull3=${c.io.Slavefull(3).peek().litValue}")
            println(s"\tSempty0=${c.io.Slaveempty(0).peek().litValue}, Sempty1=${c.io.Slaveempty(1).peek().litValue}. Sempty2=${c.io.Slaveempty(2).peek().litValue}, Sempty3=${c.io.Slaveempty(3).peek().litValue}")
            println(s"\tout0=${c.io.Slaveout(0).bits.peek().litValue}, out1=${c.io.Slaveout(1).bits.peek().litValue}. out2=${c.io.Slaveout(2).bits.peek().litValue}, out3=${c.io.Slaveout(3).bits.peek().litValue}")
        }
        }
    }
}


