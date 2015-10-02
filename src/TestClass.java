import java.util.HashSet;

/*
 * CLASS THAT CHECKS IF THERE ARE DUPLICATES IN CREATED CLOSE_CITIES HASHMAP
 * */

public class TestClass {
	private HashSet<CloseCities> dataToTest;
	private CityDistances app = new CityDistances();
	boolean duplicateExists = false;
	
	public void test(){
		app.go();
		dataToTest = app.getCloseCities();
		
		for (CloseCities element : dataToTest) {
			if(dataToTest.contains( new CloseCities(element.getTime(), element.getSecondCity(), element.getFirstCity())) ){
				duplicateExists = true;
				break;
			}
		}
		System.out.println("Duplicate exists: " + duplicateExists);
	}
	
	public static void main (String[] args){
		TestClass testClass = new TestClass();
		testClass.test();
	}
}
