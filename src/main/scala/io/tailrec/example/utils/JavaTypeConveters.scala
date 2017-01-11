package io.tailrec.example.utils

import java.util.Optional

/**
  * @author Hussachai Puripunpinyo
  */
object JavaTypeConveters {

  implicit class JavaOptionalConverter[T](optional: Optional[T]) {
    def asScala: Option[T] = if(optional.isPresent) Some(optional.get()) else None
  }

  implicit class ScalaOptionConverter[T](option: Option[T]) {
    def asJava: Optional[T] = if(option.isDefined) Optional.of[T](option.get) else Optional.empty[T]()
  }

}
