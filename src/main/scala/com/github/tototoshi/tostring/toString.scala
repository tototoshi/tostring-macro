package com.github.tototoshi.tostring

import scala.collection.immutable.Seq
import scala.meta._

class toString extends scala.annotation.StaticAnnotation {

  inline def apply(defn: Any): Any = meta {

    def addStat(classDefn: Defn.Class, stat: Stat): Defn.Class = {
      val templ = classDefn.templ
      val stats = templ.stats.getOrElse(Seq.empty[Stat])
      val newStats = stats :+ stat
      val newTempl = templ.copy(stats = Some(newStats))
      classDefn.copy(templ = newTempl)
    }

    def addToStringMethod(classDefn: Defn.Class): Stat = {
      val className = classDefn.name.toString
      val paramNames = classDefn.ctor.paramss.flatten.map { param =>
        val fieldName = param.name.toString
        q"""${Lit.String(fieldName)} + "=" + ${Term.Name(fieldName)}"""
      }
      val body = paramNames.reduce { (a, b) => q"""$a + ", " + $b""" }
      val method = q"""override def toString(): String = ${Lit.String(className)} + "(" + $body + ")""""
      addStat(classDefn, method)
    }

    defn match {
      case Term.Block(Seq(classDefn: Defn.Class, objDefn: Defn.Object)) =>
        Term.Block(Seq(addToStringMethod(classDefn), objDefn))
      case classDefn: Defn.Class =>
        val objName = Term.Name(classDefn.name.toString)
        val objDefn = q"object $objName"
        Term.Block(Seq(addToStringMethod(classDefn), objDefn))
      case _ => sys.error("error")
    }

  }

}
