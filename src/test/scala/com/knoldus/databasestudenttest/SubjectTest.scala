import org.scalatest.FunSuite
import com.knoldus.datbasestudent.Subject

/**
  * Created by knoldus on 12/2/16.
  */
class SubjectTest extends FunSuite{
  val obj= new Subject
  test("This is to test entering of a new subject") {
    val result=obj.enterNewSubject(4,"C# programming")
    val finalResult= false
    assert(result===finalResult)
  }
  test("This is to test search of a subject"){
    val result=obj.searchSubject("Java Programming")
    val finalResult=2
    assert(finalResult===result)

  }
  test("This is to test the deletion of a subject basis id"){

    val result=obj.deleteSubject(2)
    assert(1===result)
  }

}