package res

import res.RESOURCE_DIR
import esmeta.ESMeta
import esmeta.js.util.{Parser => JSParser}
import esmeta.spec.Grammar
import esmeta.spec.util.Parser
import esmeta.util.SystemUtils.readFile

object Res {

  /** the main entry point of Res. */
  def main(tokens: Array[String]): Unit = ESMeta.main(tokens)

  val grammar =
    val content = readFile(s"$RESOURCE_DIR/grammar").stripLineEnd
    val prods = Parser.parseProductions(content)
    Grammar(prods, List())

  val parser = JSParser(grammar)

  val specsP = parser("Specifications")
  val specP = parser("Specification")
  val implsP = parser("Implementations")
  val moduleimP = parser("ModuleTypeImpl")
  val letP = parser("SignLetDescription")
  val polyP = parser("PolyTypeExpression")
  val typeP = parser("TypeExpression")
  val typevarP = parser("TypeVar")
  val typedefP = parser("TypeDefinition")
  val identP = parser("Ident")
  val AtomicP = parser("AtomicModuleType")
}
