package config

object Config {
  val cassandraConfig: CassandraConfig = CassandraConfig.loadConfig("cassandra")
}
