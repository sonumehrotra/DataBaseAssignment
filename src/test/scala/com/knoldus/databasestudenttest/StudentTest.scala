package com.knoldus.databasestudenttest
import org.scalatest.FunSuite
import com.knoldus.datbasestudent.Student
/**
  * Created by knoldus on 13/2/16.
  */

 class StudentTest extends FunSuite {

  val obj = new Student

  test("This is to test search") {

    val result = obj.search("Sonu Mehrotra")
    val resultList = List(1)
    assert(result === resultList)
  }

  test("This is to test insert") {

    val result = obj.insert(3, "Kunal Kapoor", "kunal@knoldus.com", 991123698)
    val boolResult = false
    assert(result === boolResult)
  }

  test("This is to test the updation of name") {

    val result = obj.updateName(3, "Kunal Kapoor")
    val finalResult = 1
    assert(finalResult === result)
  }

  test("This is to test the deletion of student basis id") {

    val result = obj.deleteStudent(2)
    val finalResult = 1
    assert(finalResult === result)
  }
}

