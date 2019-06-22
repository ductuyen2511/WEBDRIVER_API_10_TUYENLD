package TestNG;

import org.testng.annotations.Test;

public class Topic_02_Group {
  
  @Test(groups = "payment")
  public void TC_01() {
	  System.out.println("Testcase 01");
  }
  
  @Test(groups = "payment")
  public void TC_02() {
	  System.out.println("Testcase 02");
  }
  
  @Test(groups = "product")
  public void TC_03() {
	  System.out.println("Testcase 03");
  }
  
  @Test(groups = "customer")
  public void TC_04() {
	  System.out.println("Testcase 04");
  }
  
  @Test(groups = "payment")
  public void TC_05() {
	  System.out.println("Testcase 05");
  }
 
  @Test(groups = "product")
  public void TC_06() {
	  System.out.println("Testcase 06");
  }

}
