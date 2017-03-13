package com.example.mapping

import com.example.connection.DBComponent

case class Dependents( id:Int,name:String,empReference:Int,relation:String,age:Option[Int])

trait DependentAsTable extends EmployeeAsTable {

  this: DBComponent =>

  import driver.api._

  class DependentTable(tag: Tag) extends Table[Dependents](tag, "employee_dependents") {

    val id = column[Int]("depen_id", O.PrimaryKey, O.AutoInc)
    val name = column[String]("name")
    val empReference = column[Int]("emp_id")
    val relation = column[String]("relation")
    val age = column[Option[Int]]("age", O.Default(None))

    def employeeDependentFK = foreignKey("employee_dependents_fk", empReference, employeeTableQuery)(_.id)

    def * = (id,name,empReference, relation, age) <> (Dependents.tupled, Dependents.unapply)

  }

  protected val dependentTableQuery = TableQuery[DependentTable]

  protected def dependentTableAutoInc = dependentTableQuery returning dependentTableQuery.map(_.id)

}


