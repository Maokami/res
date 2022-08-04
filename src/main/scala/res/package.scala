package res

/** line seperator */
val LINE_SEP = System.getProperty("line.separator")

/** base project directory root */
val BASE_DIR = System.getenv("RES_HOME")

/** source code directory */
val SRC_DIR = s"$BASE_DIR/src/main/scala/res"

/** package name */
val PACKAGE_NAME = "res"

/** resource directory */
val RESOURCE_DIR = s"$BASE_DIR/src/main/resources"
