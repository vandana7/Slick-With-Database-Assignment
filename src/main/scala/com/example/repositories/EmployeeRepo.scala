package com.example.repositories

import com.example.connection.{DBComponent, MySqlComponent, PostgresComponent}
import com.example.mapping.{Employee, EmployeeAsTable}

import scala.concurrent.Future


class EmployeeRepo extends EmployeeAsTable with MySqlComponent {

  this: DBComponent =>

  import driver.api._


  /**
    * creatting schema for our table
    **/


  def create(): Future[Unit] = db.run(employeeTableQuery.schema.create)

  /**
    * insering a row in our table
    **/

  def insert(newrow: Employee): Future[Int] = db.run {
    employeeTableQuery += newrow
  }

  /**
    *delete a record from our table
  **/

  def deleteRecordWithId(id: Int): Future[Int] = db.run {
    employeeTableQuery.filter(_.id === id).delete
  }

  /**
    *update a record in our table
    **/

  /**
     * Update name of  record for the given id in or table
 * */

  def updateName(id: Int, name: String): Future[Int] = {
    val query = employeeTableQuery.filter(_.id === id).map(_.name).update(name)
    db.run(query)
  }


  /**
    *  Insertion(if record is not present) or updation(if existing record)
  * */

  def upsert(newemployee: Employee): Future[Int] = {
    val query = employeeTableQuery.insertOrUpdate(newemployee)
    employeeTableQuery+=newemployee
    db.run(query)
  }

  /**
    * fetch all the records in the Table
  * */

  def fetchAll: Future[List[Employee]] = db.run{
    employeeTableQuery.to[List].result
  }
}
