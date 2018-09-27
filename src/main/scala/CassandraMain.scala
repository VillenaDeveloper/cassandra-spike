import com.outworkers.phantom.connectors.KeySpace
import utils.{CassandraConnector, LocalDataBase}
import com.outworkers.util.testing._
import dto.User

import scala.concurrent.ExecutionContext



object CassandraMain extends App {


  implicit val ec = ExecutionContext.Implicits.global

  implicit val session = CassandraConnector.default.session

  implicit val keySpace = KeySpace(LocalDataBase.space.name)

  val user = gen[User]

  val testStore = LocalDataBase.users.store(user)

  testStore.map{
    case result => println(s"EMAIL: ${result.one().getString("email")}")
  }

  val returnedUser = LocalDataBase.users.getLimit(1)

  returnedUser.map{
    case result => println(s"Size is ${result.all().size()}")
  }

  val returnedAll = LocalDataBase.users.getAll()

  returnedAll.map{ resultSet =>
    //case result => println(s"Size of ALL is ${result.all().size()}")
      resultSet.foreach(println(_))
    session.close()
  }

}
