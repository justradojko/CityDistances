import java.io.*;
import java.sql.Time;
import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;

public class CityDistances {
	private ArrayList<CityRecord> cityList = new ArrayList<CityRecord>();
	private HashSet<CloseCities> closeCities = new HashSet<CloseCities>();  
	private static final int MAX_HOURS = 6;

	private static SessionFactory factory; 

	private void loadDataFromFileAndSort(){
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
	
	private void saveDistancesToDB(){
		Session session = factory.openSession();
		Transaction tx = null;		
		try {
			System.out.println("Writing to DB...");			
			int i = 0;
			for (CloseCities element : closeCities) {
				tx = session.beginTransaction();
				session.save(element);				
				i++;
				if (i>10) break;
				tx.commit();
			}						
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			System.out.println("Closing session...");
			session.close();
		}
	}
	
	private void loadDataFromDB() {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "from CloseCities";
			Query query = session.createQuery(hql);
			List<CloseCities> results = query.list();
			System.out.println("Number of loaded lines from DB: " + results.size());
			
			Iterator<CloseCities> iterator = results.iterator();
			if (iterator.hasNext()) {
				CloseCities temp = (CloseCities) iterator.next();
				System.out.println("First first city: " + temp.getFirstCity());
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			session.close();
		}
	}
	
	private boolean deleteEntryByID(int id) {
		boolean returnB = false;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			CloseCities city = (CloseCities) session.get(CloseCities.class, id);
			session.delete(city);
			tx.commit();
			returnB = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			session.close();
		}
		return returnB;
	}
	
	
	
	
	public void go(){
		this.loadDataFromFileAndSort();
		this.analizeCityDistances();
		System.out.println("CloseCities sie: " + closeCities.size());
		
		try {
			CityDistances.factory = new Configuration().configure().buildSessionFactory();
			System.out.println("SessionFactory created");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		CODE FOR INTERACTION WITH DB GOES HERE
		*/
//		this.saveDistancesToDB();
//		this.loadDataFromDB();
		this.deleteEntryByID(2);
		
		CityDistances.factory.close();
	}
	
	public static void main(String[] args) {
		CityDistances app = new CityDistances();
		app.go();
	}
}
