import java.io.*;
import java.sql.Time;
import java.util.*;

public class CityDistances {
	private ArrayList<String> cityList = new ArrayList<String>();
	private HashSet<String> closeCities = new HashSet<String>();
	private String currentLine;
	private static final int MAX_HOURS = 6;

	
	private void loadDataFromFile(){
		try(FileReader fr = new FileReader(new File("../Problem0000/cities1000.txt")); BufferedReader br = new BufferedReader(fr)){
			String line;
			while( (line = br.readLine()) != null && line!=""){
				cityList.add(line);
			}
		} catch (Exception c){
			c.printStackTrace();
		}
		
		System.out.println("List size is: " + cityList.size());
	}
	
	private void analizeCityDistances(int startingPoint){
		currentLine = cityList.get(startingPoint);		
		int timeDifference;
		String time;
		String c0, c1, i0, i1;
		
		for (int i = startingPoint + 1; i < cityList.size(); i++){
			c0 = currentLine.split(" ")[0];
			c1 = currentLine.split(" ")[1];
			i0 = cityList.get(i).split(" ")[0];
			i1 = cityList.get(i).split(" ")[1];
			
			timeDifference = calculateTimeDifferenceInMin(c0, i0);
			time = convertMinToTime( timeDifference);
			
			if( timeDifference < MAX_HOURS * 60){
				if(!closeCities.contains(time + " " + c1 + " " + i1)){
					if(!closeCities.contains(time + " " + i1 + " " + c1)){
						closeCities.add(time + " " + c1 + " " + i1);
						System.out.println(time + " " + c1 + " " + i1);
					}
				}
			}
		}
		
		if (startingPoint + 1 < cityList.size()) {
			analizeCityDistances(startingPoint + 1);
		}
		 
	}
		
	private int calculateTimeDifferenceInMin(String first, String second){
		String[] firstDecons = first.split(":");
		String[] secondDecons = second.split(":");
		
		return Math.abs( Integer.parseInt(firstDecons[0]) * 60  + Integer.parseInt(firstDecons[1]) - 
				       Integer.parseInt(secondDecons[0]) * 60 - Integer.parseInt(secondDecons[1]) ); 
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
