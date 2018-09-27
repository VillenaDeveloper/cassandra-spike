package config

import pureconfig.loadConfigOrThrow

final case class CassandraConfig (
                                 port: Int,
                                 hosts: Seq[String],
                                 keySpace: String
                                 )

object CassandraConfig {
  def loadConfig(namespace: String): CassandraConfig = loadConfigOrThrow[CassandraConfig](namespace)
}
