package utils

import com.datastax.driver.core.SocketOptions
import com.outworkers.phantom.dsl._
import dto.Users
import utils.CassandraConnector.default


object CassandraConnector {
  import config.Config._

  val default: CassandraConnection =
    ContactPoints(cassandraConfig.hosts)
        .withClusterBuilder(_.withSocketOptions(
          new SocketOptions()
            .setConnectTimeoutMillis(20000)
            .setReadTimeoutMillis(20000)
        ).withPort(cassandraConfig.port)
        ).noHeartbeat().keySpace(KeySpace(cassandraConfig.keySpace).ifNotExists().`with`(
      replication eqs SimpleStrategy.replication_factor(1)
    )
    )
}


class LocalDataBase(keyspace: CassandraConnection) extends Database[LocalDataBase](keyspace) {
  object users extends Users with Connector
}

object LocalDataBase extends LocalDataBase(default)