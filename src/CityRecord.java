
public class CityRecord {
	private String cityName;
	private int timeInMinutes;


	public CityRecord(String s, int t){
		cityName = s;
		timeInMinutes = t;
	}
	
	public String getCityName() {
		return cityName;
	}

	public int getTimeInMinutes() {
		return timeInMinutes;
	}	
}
