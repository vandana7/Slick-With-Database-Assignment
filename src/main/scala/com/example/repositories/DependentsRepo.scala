package com.example.repositories

import com.example.connection.{DBComponent, MySqlComponent, PostgresComponent}
import com.example.mapping.{DependentAsTable, Dependents}

import scala.concurrent.Future

class DependentsRepo extends DependentAsTable with MySqlComponent{

  this: DBComponent =>

  import driver.api._

  /**
    *for creating schema for our table
    */

  def create(): Future[Unit] = db.run(dependentTableQuery.schema.create)

  /**
    * for inserting a new row in our database
    */

  def insert(dependent: Dependents): Future[Int] = db.run {
    dependentTableQuery += dependent
  }

  /**
    *for deleting a record from table
    */

  def delete(empid: Int): Future[Int] = {
    val query = dependentTableQuery.filter(d => d.empReference === empid)
    val action = query.delete
    db.run(action)
  }

  /**
    * for updating a record in our table
    * */

  def updateName(id: Int, name: String): Future[Int] = {
    val query = dependentTableQuery.filter(_.id === id).map(_.name).update(name)
    db.run(query)
  }

  /**
    *  for Insertion(if record is not present) or updation(if existing record)
    * */

  def upsert(dependent: Dependents): Future[Int] = {
    val query = dependentTableQuery.insertOrUpdate(dependent)
    dependentTableQuery += dependent
    db.run(query)
  }

  /**
    * for Retrieving all the records in the Table
    * */

  def fetchAll: Future[List[Dependents]] = db.run{
    dependentTableQuery.to[List].result
  }


}