package res.rs

import res.RS_PARSE_TEST_DIR
import esmeta.util.SystemUtils.*

class ParseSmallTest extends RSTest {
  val name: String = "rsParseTest"

  lazy val rsFilter = extFilter("res")
  // registration
  def init: Unit = {
    // check parser and stringifier from files
    for (file <- walkTree(RS_PARSE_TEST_DIR)) {
      val filename = file.getName
      val parentname = file.getParentFile.getName
      val testname = s"$parentname/$filename"
      if (rsFilter(filename)) check(testname) {
        val name = file.toString
        RSTest.parseFileTest(name)
      }
    }
  }

  init
}
