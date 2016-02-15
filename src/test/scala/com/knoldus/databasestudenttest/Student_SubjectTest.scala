package com.knoldus.databasestudenttest
import com.knoldus.datbasestudent.Student_Subject

import org.scalatest.FunSuite

/**
  * Created by knoldus on 13/2/16.
  */
class Student_SubjectTest extends FunSuite{

  val obj= new Student_Subject

  test("This is to test assigning of subjects"){
    val result=obj.assignSubjects("Sonu Mehrotra",1,List("Java Programming","C++ Programming"))
    assert(result===true)
  }

  test("This is to search a subject") {
    val result= obj.searchSubjectForStudent(1)
    val finalResult=List("C Programming","Java Programming","C++ Programming")
    assert(result===finalResult)
  }

test("This is to test deletion"){
  val result= obj.deleteAssignedSubject("Sonu Mehrotra",1,"Java Programming")
  assert(result===true)
}
}
