package com.example.repositories

import com.example.connection.{DBComponent, MySqlComponent, PostgresComponent}
import com.example.mapping.{Project, ProjectAsTable}

import scala.concurrent.Future

class ProjectRepo extends ProjectAsTable with MySqlComponent {

  this: DBComponent =>

  import driver.api._


  /**
    * for creatting schema for our table
    **/


  def create(): Future[Unit] = db.run(projectTableQuery.schema.create)


  /**
    * for inserting a row in our table
    */

  /*
* inserting a record
* */

  def insert(project: Project): Future[Int] = db.run {
    projectTableQuery += project
  }

  /**
    *for deleting a record on the basis of id
    */

  def deleteById(id: Int): Future[Int] = {
    val query = projectTableQuery.filter(_.proId === id).delete
    db.run(query)
  }

  /**
  * for updating project name for the given id
  * */

  def updateName(id: Int, name: String): Future[Int] = {
    val query = projectTableQuery.filter(_.proId === id).map(_.proName).update(name)
    db.run(query)
  }

  /**
  *  for Insertion(if record is not present) or updation(if existing record)
  * */

  def upsert(project: Project): Future[Int] = {
    val query = projectTableQuery.insertOrUpdate(project)
    projectTableQuery += project
    db.run(query)
  }

  /**
  *  for Retrieving all the records from Table
  * */

  def fetchAll : Future[List[Project]] = db.run{
    projectTableQuery.to[List].result
  }
}