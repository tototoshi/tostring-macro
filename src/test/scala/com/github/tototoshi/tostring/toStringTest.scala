package com.github.tototoshi.tostring

import org.scalatest.FunSuite

class toStringTest extends FunSuite {

  test("@toString") {
    @toString
    case class Hoge(i: Int, s: String)

    assert(Hoge(1, "a").toString === "Hoge(i=1, s=a)")
  }

}
