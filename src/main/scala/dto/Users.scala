package dto

import java.util.UUID

import com.outworkers.phantom.dsl._
import scala.concurrent.Future

  case class User(
                 id: UUID,
                 email: String,
                 name: String,
                 registrationDate: DateTime
                 )

  abstract class Users extends Table[Users, User] {
    object id extends UUIDColumn with PartitionKey
    object email extends StringColumn
    object name extends StringColumn
    object registrationDate extends DateTimeColumn

    def store(user: User): Future[ResultSet] = {
      insert.value(_.id, user.id)
        .value(_.email, user.email)
        .value(_.name, user.name)
        .value(_.registrationDate, user.registrationDate)
        .future()
    }

    def getById(id: UUID): Future[Option[User]] = {
      select.where(_.id eqs id).one()
    }

    def getLimit(limit: Int): Future[ResultSet] = {
      select.limit(limit).future()
    }

    def getAll(): Future[List[User]] = {
      select.fetch()
    }

  }