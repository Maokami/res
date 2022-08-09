package res

/** line seperator */
val LINE_SEP = System.getProperty("line.separator")

/** base project directory root */
val BASE_DIR = System.getenv("RES_HOME")

/** tests directory root */
val TEST_DIR = s"$BASE_DIR/tests"

/** source code directory */
val SRC_DIR = s"$BASE_DIR/src/main/scala/res"

/** resource directory */
val RESOURCE_DIR = s"$BASE_DIR/src/main/resources"

/** package name */
val PACKAGE_NAME = "res"

/** tests directory */
val RS_PARSE_TEST_DIR = s"$BASE_DIR/rescript-syntax/tests/parsing/grammar"

////////////////////////////////////////////////////////////////////////////////
// Mutable Global Options
////////////////////////////////////////////////////////////////////////////////
/** debugging mode */
var DEBUG: Boolean = false

/** silent mode */
var SILENT: Boolean = false

/** show duration time */
var TIME: Boolean = false

/** logging mode */
var LOG: Boolean = false

/** test mode (turn it on only when executing tests) */
var TEST_MODE: Boolean = false

/** timeout */
val TIMEOUT = 10
