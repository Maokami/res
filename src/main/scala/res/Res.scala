package res

import esmeta.ESMeta
import esmeta.js.util.{Parser => JSParser}
import esmeta.spec.Grammar
import esmeta.spec.util.Parser
import esmeta.util.SystemUtils.readFile

object Res {

  /** the main entry point of Res. */
  def main(tokens: Array[String]): Unit = ESMeta.main(tokens)

  def grammar =
    val content = readFile(s"$RESOURCE_DIR/grammar").stripLineEnd
    val prods = Parser.parseProductions(content)
    Grammar(prods, List())

  def parser = JSParser(grammar)

  def implsProd =
    grammar.nameMap("Implementations").rhsList

  lazy val scriptP = parser("Script")
  def specsP = parser("Specifications")
  def specP = parser("Specification")
  def implsP = parser("Implementations")
  def implP = parser("Implementation")
  def moduleimP = parser("ModuleTypeImpl")
  def letP = parser("SignLetDescription")
  def polyP = parser("PolyTypeExpression")
  def typeP = parser("TypeExpression", List(true, true))
  def typevarP = parser("TypeVar")
  def typedefP = parser("TypeDefinition")
  def identP = parser("Ident")
  def atomicTypeP = parser("AtomicTypeExpression")
  def atomicP = parser("AtomicExpr")
  def modulespecP = parser("ModuleSpecification")
  def fielddeclP = parser("AttributedFieldDeclarations")
  def ifP = parser("IfOrIfLetExpr")
  def ifcondP = parser("IfCondition")
  def exprP = parser("Expr", List(false, false))
  def thenP = parser("ThenBranch")

  def testResP(filename: String) =
    implsP.fromFile(s"$RS_PARSE_TEST_DIR/$filename")
  def testResiP(filename: String) =
    specsP.fromFile(s"$RS_PARSE_TEST_DIR/$filename")
}
