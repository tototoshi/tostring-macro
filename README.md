# toStrig macro

[![Build Status](https://travis-ci.org/tototoshi/tostring-macro.png)](https://travis-ci.org/tototoshi/tostring-macro)


```scala
@toString
case class Hoge(i: Int, s: String)

assert(Hoge(1, "a").toString === "Hoge(i=1, s=a)")
```
