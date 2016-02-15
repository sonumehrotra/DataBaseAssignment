package com.knoldus.datbasestudent
import java.sql._
import java.sql.DriverManager
import java.sql.Connection
import com.knoldus.datbasestudent._
import org.slf4j.{LoggerFactory, Logger}

/**
  * Created by knoldus on 13/2/16.
  */
class Student_Subject {

  val logger:Logger = (LoggerFactory.getLogger(this.getClass))

  def assignSubjects(name: String, studentId: Int, subjectList: List[String]): Boolean = {

    logger.info("The method to assign subjects started")
    val obj = new Student
    val idList = obj.search(name)
    if (idList contains studentId) {

      def pullSubjectId(subjectList: List[String], subjectIdList: List[Int]): List[Int] = {

        val obj = new Subject
        subjectList match {
          case head :: tail => pullSubjectId(subjectList.tail, subjectIdList ::: List(obj.searchSubject(subjectList.head)))
          case Nil => subjectIdList
        }
      }
      val subjectIdList = pullSubjectId(subjectList, List())
      if (!subjectIdList.isEmpty) {
        def insertSubject(subjectIdList: List[Int], conn: Connection, statement: Statement): Boolean = {
          subjectIdList match {
            case head :: tail => {
              val query = "insert into student_subject values ('" + studentId + "','" + subjectIdList.head + "' );"
              statement.executeUpdate(query)
              insertSubject(subjectIdList.tail, conn, statement)
            }
            case Nil => true
          }
        }
        val conn = DbConnection.connectDb()
        val statement = conn.createStatement()
        insertSubject(subjectIdList, conn, statement)
      }
      else {false}
    }
    else {false}
  }

  def searchSubjectForStudent(stud_id: Int): List[String] = {
    val conn = DbConnection.connectDb()
    val statement = conn.createStatement()
    val query = "select subject.subject, subject.id from subject inner join student_subject on subject.id=student_subject.sub_id where stud_id='" + stud_id + "' ;"
    val result = statement.executeQuery(query)
    def store(result: ResultSet, list: List[String]): List[String] = {
      result match {
        case a if !a.next => list
        case b => {
          val sub_name = result.getString(1)
          store(result, list ::: List(sub_name))
        }
      }
    }
    store(result, List())
  }

  def deleteAssignedSubject(studentName: String, studentId: Int, subjectName: String): Boolean = {
    val subjectId = (new Subject).searchSubject(subjectName)
    if (subjectId > 0) {
      val conn = DbConnection.connectDb()
      val statement = conn.createStatement()
      val query = "delete from student_subject where stud_id = '" + studentId + "' and sub_id ='" + subjectId + "' ;"
      val exec = statement.executeUpdate(query)
      if (exec == 1){ true }
      else  {false}
    }
    else {false}
  }
}


