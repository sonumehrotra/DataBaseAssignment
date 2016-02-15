package com.knoldus.datbasestudent
import java.sql.{PreparedStatement, ResultSet}

import org.slf4j.{LoggerFactory, Logger}

/**
  * Created by knoldus on 13/2/16.
  */
class Student {

  val logger:Logger = (LoggerFactory.getLogger(this.getClass))
  def search(name: String): List[Int] = {

    val conn = DbConnection.connectDb()
    val statement = conn.createStatement()
    val result = statement.executeQuery("SELECT id FROM student WHERE name='" + name + "';")
    def store(result: ResultSet, list: List[Int]): List[Int] = {

      logger.info("The method to store result started")
      result match {
        case a if !a.next => list
        case b => {
          val id = result.getInt(1)
          store(result, list ::: List(id))
        }
      }
    }
    store(result, List())
  }

  def insert(id:Int,name:String,email:String,mobile:Int):Boolean={

    logger.info("The method to perform insertion")
    val conn = DbConnection.connectDb()
    val query = "insert into student (id,name,email,mobile) values (?,?,?,?);"
    val statement:PreparedStatement= conn.prepareStatement(query)
    statement.setInt(1,id)
    statement.setString(2,name)
    statement.setString(3,email)
    statement.setInt(4,mobile)
    statement.execute()
  }

  def updateName(id:Int,newname:String):Int={

    logger.info("The method to perform updation")
    val conn = DbConnection.connectDb()
    val statement = conn.createStatement()
    val query= "update student set name = '" + newname + "' where id = '" + id + "' ;"
    statement.executeUpdate(query)
  }

  def deleteStudent(id:Int):Int={

    logger.info("The method to perform deletion")
    val conn = DbConnection.connectDb()
    val statement = conn.createStatement()
    val query= "delete from student where id = '" + id + "' ;"
    statement.executeUpdate(query)
  }
}

