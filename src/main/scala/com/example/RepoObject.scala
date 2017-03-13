package com.example

import com.example.mapping.{Dependents, Employee, Project}
import com.example.repositories.{DependentsRepo, EmployeeRepo, ProjectRepo}

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration

object RepoObject extends App{
  val obj = new EmployeeRepo
/*  obj.create()
  val res = obj.insert(Employee(1,"vandana"))
  val res1 = res.map { res => s"$res row inserted" }.recover {
    case ex: Throwable => ex.getMessage
  }

  res1.map(println(_))
  Thread.sleep(10000)
  val res2 = obj.insert(Employee(3,"isha"))
  val res3 = res.map { res => s"$res row inserted" }.recover {
    case ex: Throwable => ex.getMessage
  }

  res3.map(println(_))
  Thread.sleep(10000)

  val res4 = obj.deleteRecordWithId(2)
  val res3 = res4.map { res => s"$res row deleted" }.recover {
        case ex: Throwable => ex.getMessage
      }
  res3.map(println(_))
   Thread.sleep(10000)*/
  /*val res5 = obj.updateName(1,"kavita")
  val res3 = res5.map { res => s"$res row updated" }.recover {
            case ex: Throwable => ex.getMessage
       }
  res3.map(println(_))
  */

  val res13 = obj.fetchAll
  val res3 = res13.map { employeeList => s"$employeeList"}.recover{
    case ex: Throwable => s"Listing Failed ${ex.getMessage}"
  }
  println(Await.result(res13, Duration.Inf))
  Thread.sleep(10000)


      val obj1 = new ProjectRepo
     // obj1.create()
/*  val res6 = obj1.insert(Project(1,"prject1",8,"isha",3))
  val res3 = res6.map { res => s"$res row inserted" }.recover {
    case ex: Throwable => ex.getMessage
  }
  res3.map(println(_))
       Thread.sleep(10000)*/
 /* val res7 = obj1.insert(Project(2,"prject2",8,"kavita",1))
  val res3 = res7.map { res => s"$res row updated" }.recover {
    case ex: Throwable => ex.getMessage
  }
  res3.map(println(_))
  Thread.sleep(10000)*/

  /*val res8 = obj1.updateName(1,"welcomeProject")
  val res3 = res8.map { res => s"$res row inserted" }.recover {
    case ex: Throwable => ex.getMessage
  }
  res3.map(println(_))
  Thread.sleep(10000)*/

  /*val res9 = obj1.fetchAll
  val res3 = res9.map { projectList => s"$projectList"}.recover{
    case ex: Throwable => s"Listing Failed ${ex.getMessage}"
  }
  println(Await.result(res9, Duration.Inf))
  Thread.sleep(10000)*/

  val obj2 = new DependentsRepo
  /* obj2.create()
   val res10 = obj2.insert(Dependents(1,"richa",3,"sister",Some(20)))
   val res3 = res10.map { res => s"$res row inserted" }.recover {
     case ex: Throwable => ex.getMessage
   }
   res3.map(println(_))
   Thread.sleep(10000)*/
  /*val res11 = obj2.insert(Dependents(2,"vandana",1,"sister",Some(24)))
  val res3 = res11.map { res => s"$res row inserted" }.recover {
    case ex: Throwable => ex.getMessage
  }
  res3.map(println(_))
  Thread.sleep(10000)*/

  /*val res12 = obj2.fetchAll
 val res3 = res12.map { dependentList => s"$dependentList"}.recover{
   case ex: Throwable => s"Listing Failed ${ex.getMessage}"
 }
 println(Await.result(res12, Duration.Inf))
 Thread.sleep(10000)*/


}
