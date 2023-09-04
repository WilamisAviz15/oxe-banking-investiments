package database
import com.typesafe.config.{Config, ConfigFactory}

object DBConfig {
  private val config: Config = ConfigFactory.load()

  val dbUrl: String = config.getString("database.url")
  val dbUser: String = config.getString("database.user")
  val dbPassword: String = config.getString("database.password")
}