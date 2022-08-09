package res.rs

import res.ResTest
import esmeta.spec.Spec
import esmeta.interp.*
import esmeta.ir.NormalInst
import esmeta.js.util.*
import esmeta.js.Ast
import esmeta.util.SystemUtils.*
import org.scalatest.Assertions.*

trait RSTest extends ResTest {
  def category: String = "rs"
}
object RSTest {
  // lazy val spec = ResTest.spec
  lazy val grammar = ResTest.grammar
  // lazy val cfg = spec.toCFG

  // file extension converter from .js to .ir
  // lazy val js2ir = changeExt("js", "ir")

  // parse RS codes
  lazy val parser = Parser(grammar)("Implementations")
  def parse(str: String): Ast = parser.from(str)
  def parseFile(filename: String): Ast = parser.fromFile(filename)

  // eval JS codes
  // def eval(
  //  str: String,
  //  checkAfter: List[NormalInst] = Nil,
  //  cachedAst: Option[Ast] = None,
  // ): State =
  //  Interp(Initialize(cfg, str, cachedAst), checkAfter)
  // def evalFile(
  //  filename: String,
  //  checkAfter: List[NormalInst] = Nil,
  //  cachedAst: Option[Ast] = None,
  // ): State = eval(readFile(filename), checkAfter, cachedAst)

  // tests for RS parser
  def parseTest(ast: Ast): Ast =
    val newAst = parser.from(ast.toString(grammar = Some(grammar)))
    assert(ast == newAst)
    ast
  def parseTest(str: String): Ast = parseTest(parse(str))
  def parseFileTest(filename: String): Ast = parseTest(parseFile(filename))

  // tests for JS interpreter
  // def checkExit(st: State): st.type = st(GLOBAL_RESULT) match
  //  case Undef => st
  //  case v     => fail(s"return not undefined: $v")
  // def evalTest(
  //  str: String,
  //  checkAfter: List[NormalInst] = Nil,
  //  cachedAst: Option[Ast] = None,
  // ): State = checkExit(eval(str, checkAfter, cachedAst))
  // def evalTestFile(
  //  filename: String,
  //  checkAfter: List[NormalInst] = Nil,
  //  cachedAst: Option[Ast] = None,
  // ): State = checkExit(evalFile(filename, checkAfter, cachedAst))
}
