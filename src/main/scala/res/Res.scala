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

  lazy val scriptP = parser("Script")
  def specsP = parser("Specifications")
  val specP = parser("Specification")
  def implsP = parser("Implementations")
  def testResP(filename: String) =
    implsP.fromFile(s"$RS_PARSE_TEST_DIR/$filename")
  def testResiP(filename: String) =
    specsP.fromFile(s"$RS_PARSE_TEST_DIR/$filename")
  val moduleimP = parser("ModuleTypeImpl")
  val letP = parser("SignLetDescription")
  val polyP = parser("PolyTypeExpression")
  val typeP = parser("TypeExpression")
  val typevarP = parser("TypeVar")
  val typedefP = parser("TypeDefinition")
  val identP = parser("Ident")
  val atomicP = parser("AtomicModuleType")
  val modulespecP = parser("ModuleSpecification")
  val fielddeclP = parser("AttributedFieldDeclarations")
}
