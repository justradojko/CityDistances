import java.io.*;
import java.sql.Time;
import java.util.*;

public class CityDistances {
	private ArrayList<CityRecord> cityList = new ArrayList<CityRecord>();
	private HashSet<CloseCities> closeCities = new HashSet<CloseCities>();
	private CityRecord currentCity;
	private static final int MAX_HOURS = 6;
	
	public HashSet<CloseCities> getCloseCities(){
		return closeCities;
	}

	private void loadDataFromFile(){
		try(FileReader fr = new FileReader(new File("../Problem0000/cities1000.txt")); BufferedReader br = new BufferedReader(fr)){
			String line;
			while( (line = br.readLine()) != null && line!=""){
				CityRecord city = new CityRecord("\"" + line.split("\"")[1] + "\"", getTimeInMinutes(line.split(" ")[0]));
				cityList.add(city);
			}
		} catch (Exception c){
			c.printStackTrace();
		}
		
		System.out.println("List size is: " + cityList.size());
	}
	
	private void analizeCityDistances(int startingPoint){
		currentCity = cityList.get(startingPoint);		
		String time;
		
		for (int i = startingPoint + 1; i < cityList.size(); i++){
			
			time = convertMinToTime( Math.abs(currentCity.getTimeInMinutes() - cityList.get(i).getTimeInMinutes() ) );
						
			if( ( Math.abs(currentCity.getTimeInMinutes() - cityList.get(i).getTimeInMinutes()) ) < MAX_HOURS * 60){
				if( !(closeCities.contains( new CloseCities(time, currentCity.getCityName(), cityList.get(i).getCityName())))){
					closeCities.add(  new CloseCities(time, cityList.get(i).getCityName(), currentCity.getCityName())  );
					System.out.println(time + " " + cityList.get(i).getCityName() + " " + currentCity.getCityName());
				}
			}
		}
		
		if (startingPoint + 1 < cityList.size()) {
			analizeCityDistances(startingPoint + 1);
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
		this.analizeCityDistances(0);
		System.out.println("HashSet size: " + closeCities.size());
	}
	
	public static void main(String[] args) {
		CityDistances app = new CityDistances();
		app.go();
	}

}
