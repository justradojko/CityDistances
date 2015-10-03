import javax.xml.transform.Source;


public class CloseCities {
	private String time;
	private String firstCity;
	private String secondCity;
	
	@Override
	public int hashCode() {
		final int prime = 1;
		int result = 1;
		result = prime * result
				+ ((firstCity == null) ? 0 : firstCity.hashCode());
		result = prime * result
				+ ((secondCity == null) ? 0 : secondCity.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;		
		CloseCities other = (CloseCities) obj;				
		if (firstCity == null) {
			if (other.firstCity != null)
				return false;
		} else if (! (firstCity.equals(other.firstCity) || firstCity.equals(other.secondCity) ))
			return false;
		if (secondCity == null) {
			if (other.secondCity != null)
				return false;
		} else if (! (secondCity.equals(other.secondCity) || secondCity.equals(other.firstCity) ))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	public CloseCities(String t, String s1, String s2){
		time = t;
		firstCity = s1;
		secondCity = s2;
	}

	public String getTime() {
		return time; 
	}

	public String getFirstCity() {
		return firstCity;
	}

	public String getSecondCity() {
		return secondCity;
	}
}
