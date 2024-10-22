expect fun getPlatform(): Platform

interface Platform {
  val name: String
  val data: String
//  fun getNumber(): String

  fun getPayment():String
}
