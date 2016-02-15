package com.knoldus.datbasestudent
import java.sql.{PreparedStatement, ResultSet}

/**
  * Created by knoldus on 13/2/16.
  */
class Subject {

  def enterNewSubject(id:Int, name:String):Boolean={

    val conn = DbConnection.connectDb()
    val query = "insert into subject (id,subject) values (?,?);"
    val statement:PreparedStatement= conn.prepareStatement(query)
    statement.setInt(1,id)
    statement.setString(2,name)
    statement.execute()

  }

  def searchSubject(name:String):Int={

    val conn = DbConnection.connectDb()
    val statement = conn.createStatement()
    val result:ResultSet = statement.executeQuery("SELECT id FROM subject WHERE subject='" + name + "';")
    result.next()
    result.getInt(1)

  }

  def deleteSubject(id:Int):Int={

    val conn = DbConnection.connectDb()
    val statement = conn.createStatement()
    val query= "delete from subject where id = '" + id + "' ;"
    statement.executeUpdate(query)

  }
}
