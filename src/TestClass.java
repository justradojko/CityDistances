import java.util.HashMap;
import java.util.Set;

public class TestClass {
	CityDistances distances = new CityDistances();
	private HashMap<String, String> map;
	
	public void test(){
		distances.go();
		map = distances.getCloseCities();
		Set<String> set = map.keySet();
		boolean duplicateExists = false;
		for (String key : set) {
			if (map.get(  map.get(key).split(" ")[0] + " " + map.get(key)) != null) {
				duplicateExists = true;
			}
		}
		System.out.println("Duplicate exists: " + duplicateExists);
	}
	public static void main(String[] args){
		TestClass test = new TestClass();		
		test.test();
	}
}
