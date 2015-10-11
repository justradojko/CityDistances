import java.io.*;
import java.sql.Time;
import java.util.*;

public class CityDistances {
	private ArrayList<CityRecord> cityList = new ArrayList<CityRecord>();
	private HashSet<CloseCities> closeCities = new HashSet<CloseCities>();  
	private static final int MAX_HOURS = 6;

	private void loadDataFromFile(){
		try(FileReader fr = new FileReader(new File("../Problem0000/cities1000.txt")); BufferedReader br = new BufferedReader(fr)){
			String line;
			while( (line = br.readLine()) != null && line!=""){
				CityRecord city = new CityRecord("\"" + line.split("\"")[1] + "\"", getTimeInMinutes(line.split(" ")[0]));
				cityList.add(city);
			}
			Collections.sort(cityList);
		} catch (Exception c){
			c.printStackTrace();
		}
		
		System.out.println("cityList size is: " + cityList.size());
	}
	
	private void printCityList() {
		for (CityRecord cityRecord : cityList) {
			System.out.println(cityRecord.getTimeInMinutes() + " " + cityRecord.getCityName());
		}
		System.out.println("cityList size is: " + cityList.size());
	}
	
	
	
	private void analizeCityDistances(){
		int t;
		for (int i = 0; i < cityList.size() - 1; i++) {
			int j = i + 1;
			
			while ( (t = Math.abs( cityList.get(i).getTimeInMinutes() - cityList.get(j).getTimeInMinutes() )) < (MAX_HOURS * 60)) {
				closeCities.add(new CloseCities(convertMinToTime(t), cityList.get(i).getCityName(), cityList.get(j).getCityName()));
				if (j < cityList.size() - 1)
					j++;
				else
					break;
			}
		}
	}
	
	private int getTimeInMinutes(String s){
		String[] el = s.split(":");
		return (Integer.parseInt(el[0]) * 60  + Integer.parseInt(el[1]));
	}
	
	private String convertMinToTime(int mins){
		Time time = new Time(mins * 60000 - 3600000);
		return time.toString().substring(0, 5);
	}
	
	
	
	
	public void go(){
		this.loadDataFromFile();
		this.analizeCityDistances();
		System.out.println("HashSet size: " + closeCities.size());
	}
	
	public static void main(String[] args) {
		CityDistances app = new CityDistances();
		app.go();
	}
}
