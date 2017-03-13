package com.example.mapping

import com.example.connection.DBComponent

case class Project(proId:Int,proName:String,teamCount:Int,leadName:String,leadId:Int)

trait ProjectAsTable extends EmployeeAsTable {

  this: DBComponent =>

  import driver.api._

   class ProjectAsTable(tag: Tag) extends Table[Project](tag, "project") {

    val proId = column[Int]("pro_id", O.PrimaryKey)
    val proName = column[String]("pro_name")
    val teamCount = column[Int]("team_count")
    val leadName = column[String]("lead_name")
     val leadId = column[Int]("lead_id")

    def employeeProjectFK = foreignKey("employee_project_fk", leadId, employeeTableQuery)(_.id)

    def * = (proId, proName, teamCount, leadName, leadId) <> (Project.tupled, Project.unapply)
  }

  protected val projectTableQuery = TableQuery[ProjectAsTable]

  protected def projectAsTableAutoInc = projectTableQuery returning projectTableQuery.map(_.proId)

}