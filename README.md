# toStrig macro


```scala
@toString
case class Hoge(i: Int, s: String)

assert(Hoge(1, "a").toString === "Hoge(i=1, s=a)")
```
