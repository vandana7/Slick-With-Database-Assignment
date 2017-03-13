package com.example.mapping
import com.example.connection.DBComponent





case class Employee(id: Int, name: String)

trait EmployeeAsTable {

  this: DBComponent =>

  import driver.api._

   class EmployeeAsTable(tag: Tag) extends Table[Employee](tag, "employees") {

    val id = column[Int]("emp_id", O.PrimaryKey,O.AutoInc)
    val name = column[String]("emp_name")


    def * = (id, name) <> (Employee.tupled, Employee.unapply)
  }

  protected val employeeTableQuery = TableQuery[EmployeeAsTable]

  protected def employeeAsTableAutoInc = employeeTableQuery returning employeeTableQuery.map(_.id)
}