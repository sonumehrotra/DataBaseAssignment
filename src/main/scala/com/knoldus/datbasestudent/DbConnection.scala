package com.knoldus.datbasestudent
import java.sql.DriverManager
import java.sql.Connection

/**
  * Created by knoldus on 13/2/16.
  */
object DbConnection {

  def connectDb():Connection={

    val driver="com.mysql.jdbc.Driver"
    val url= "jdbc:mysql://localhost/sonu"
    val user="root"
    val password="root"
    try{
      Class.forName(driver)
      val connection:Connection= DriverManager.getConnection(url,user,password)
      connection
    }
    finally {

    }

  }

}
