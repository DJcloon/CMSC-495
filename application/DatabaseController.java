package application;
import java.sql.Connection;  
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseController {  

	static String url = "jdbc:sqlite:src/application/CLMSv2.db";  

	public static void createNewDatabase() {  
		try {  
			Connection conn = DriverManager.getConnection(url);  
			if (conn != null) {  
				DatabaseMetaData meta = conn.getMetaData();  
				System.out.println("The driver name is " + meta.getDriverName());  
				System.out.println("A new database has been created.");
				//createNewTableShips(conn, url);
				createNewTablePassengers(conn, url);
				//loadShip(conn);
				loadPassenger(conn);
			}  
		} catch (SQLException e) {System.out.println(e.getMessage());}  
	}  
//UPDATE ships SET Maintenance = 2023 WHERE ID = 1; 
	public static void createNewTableShips(Connection conn, String url) {  

		// SQL statement for creating a new table  
		String sql = "CREATE TABLE IF NOT EXISTS ships (\n"  
				+ " id integer PRIMARY KEY,\n"  
				+ " ShipName text NOT NULL,\n"  
				+ " ShipCompany text NOT NULL,\n"
				+ " Location text NOT NULL,\n"
				+ " TripLength real,\n"
				+ " Cabins real,\n"
				+ " YearOfBuild real,\n"
				+ " Maintenance real,\n"
				+ " Capacity real,\n"
				+ " Origin text NOT NULL,\n"
				+ " FinalDestination text NOT NULL,\n"
				+ " Destination1 text NOT NULL,\n"
				+ " Destination2 text NOT NULL,\n"
				+ " Destination3 text NOT NULL,\n"
				+ " Destination4 text NOT NULL,\n"
				+ " Destination5 text NOT NULL\n"
				+ ");";  
		try{  
			Statement stmt = conn.createStatement();  
			stmt.execute(sql);  
		} catch (SQLException e) {  
			System.out.println(e.getMessage());  
		}  
	}  
	
	public static void createNewTablePassengers(Connection conn, String url) {  

		// SQL statement for creating a new table  
		String sql = "CREATE TABLE IF NOT EXISTS passengers (\n"  
				+ " id integer PRIMARY KEY,\n"  
				+ " ShipName text NOT NULL,\n" 
				+ " Name_First text NOT NULL,\n" 
				+ " Name_Last text NOT NULL,\n"
				+ " Email text NOT NULL,\n"
				+ " CabinType integer real\n"
				+ ");";  
		try{  
			Statement stmt = conn.createStatement();  
			stmt.execute(sql);  
		} catch (SQLException e) {  
			System.out.println(e.getMessage());  
		}  
	} 
	//Method to add ship to database	
	public static void insertPassenger(Connection conn, String shipName, String firstName, 
			String lastName, String Email, int cabinType) {  
		String sql = "INSERT INTO passengers(ShipName, " 
					+ "Name_First, " 
					+ "Name_Last, "
					+ "Email, "
					+ "CabinType) "
					+ "VALUES(?,?,?,?,?)";  

		try{  
			PreparedStatement pstmt = conn.prepareStatement(sql);  

			pstmt.setString(1, shipName);  
			pstmt.setString(2, firstName);
			pstmt.setString(3, lastName);
			pstmt.setString(4, Email);
			pstmt.setInt(5, cabinType);
			pstmt.executeUpdate();  
		} catch (SQLException e) {  
			System.out.println(e.getMessage());  
		}  
	}  
	
	public static void selectAllPassengers(Connection conn){  
		String sql = "SELECT * FROM passengers";  

		try {  
			Statement stmt  = conn.createStatement();  
			ResultSet rs    = stmt.executeQuery(sql);  

			// loop through the result set  
			while (rs.next()) {  
				System.out.println(rs.getInt("id")+  "\t" +
						rs.getString("ShipName")+  "\t" +
						rs.getString("Name_First")+  "\t" +
						rs.getString("Name_Last")+  "\t" +
						rs.getString("Email")+  "\t" +
						rs.getInt("CabinType"));  
			}  
		} catch (SQLException e) {  
			System.out.println(e.getMessage());  
		}  
	} 
	
	public static void loadPassenger(Connection conn) {
		insertPassenger(conn, "Carnival Paradise", "Steve", 
				 "Jobs", "Steve.jobs@msn.com", 1);
	}
	
	
	//Method to add ship to database	
	public static void insertShip(Connection conn, String name, String company, String location, int tripLength, int numCabins, 
			int yearOfBuild, int maintenance, int maxCapacity,String origin, String finalDestination, 
			String destination1,String destination2, String destination3, String destination4, String destination5) {  
		String sql = "INSERT INTO ships(ShipName, "
				+ "ShipCompany, "
				+ "Location, "
				+ "TripLength, "
				+ "Cabins, "
				+ "YearOfBuild, "
				+ "Maintenance, "
				+ "Capacity, "
				+ "Origin, "
				+ "FinalDestination, "
				+ "Destination1, "
				+ "Destination2, "
				+ "Destination3, "
				+ "Destination4, "
				+ "Destination5) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  

		try{  
			PreparedStatement pstmt = conn.prepareStatement(sql);  

			pstmt.setString(1, name);  
			pstmt.setString(2, company);
			pstmt.setString(3, location);
			pstmt.setInt(4, tripLength);
			pstmt.setInt(5, numCabins);
			pstmt.setInt(6, yearOfBuild);
			pstmt.setInt(7, maintenance);
			pstmt.setInt(8, maxCapacity);
			pstmt.setString(9, origin);
			pstmt.setString(10, finalDestination);
			pstmt.setString(11, destination1);
			pstmt.setString(12, destination2);
			pstmt.setString(13, destination3);
			pstmt.setString(14, destination4);
			pstmt.setString(15, destination5);
			pstmt.executeUpdate();  
		} catch (SQLException e) {  
			System.out.println(e.getMessage());  
		}  
	}  
	
	//method to preload ship data to SQLite Database
	public static void loadShip(Connection conn) {
			
		insertShip(conn, "Carnival Paradise", "Carnival Cruise Line",
				"https://www.cruisemapper.com/?imo=9120877",
				4, 61, 1998, 2021, 183,
				"Tampa, Florida", "Tampa, Florida",
				"Cozumel, Mexico", "", "", "", "");
		insertShip(conn, "Carnival Legend", "Carnival Cruise Line",
				"https://www.cruisemapper.com/?imo=9224726",
				6,62,2002,2021,186,
				"Baltimore, Maryland", "Baltimore, Maryland",
				"Kings Wharf, Bermuda", "", "", "", "");
		insertShip(conn, "Carnival Panorama","Carnival Cruise Line",
				"https://www.cruisemapper.com/?imo=9802384",
				7,965,2019,2024,2895,
				"Los Angeles, California","Los Angeles, California"
				,"Puerto Vallarta, Mexico","Mazatlan, Mexico","Cabo San Lucas, Mexico","","");
		insertShip(conn, "Carnival Vista","Carnival Cruise Line",
				"https://www.cruisemapper.com/?imo=9692569"
				,7,965,2016,0,2895,
				"Galveston, Texas","Galveston, Texas",
				"Roatan Island, Honduras","Belize City, Belize","Cozumel, Mexico","","");
		insertShip(conn, "Disney Wish","Disney Cruise Line",
				"https://www.cruisemapper.com/?imo=9834739",
				4,238,2022,0,714,
				"Orlando, Florida","Orlando, Florida",
				"Nassau, Bahamas","Disney Castaway Island, Bahamas","","","");
		insertShip(conn, "Disney Magic","Disney Cruise Line",
				"https://www.cruisemapper.com/?imo=9126807",
				4,877,1998,2023,2631,
				"Miami, Florida","Miami, Florida",
				"Nassau, Bahamas","Disney Castaway Island, Bahamas","","","");
		insertShip(conn, "Disney Wonder","Disney Cruise Line",
				"https://www.cruisemapper.com/?imo=9126819",
				7,877,1999,2023,2631,
				"Vancouver, Canada","Vancouver, Canada",
				"Skagway, Alaska","Juneau, Alaska","Ketchikan, Alaska","","");
		insertShip(conn, "Marella Discovery","Marella Cruises",
				"https://www.cruisemapper.com/?imo=9070632",
				7,915,1996,2022,2745,
				"Orlando, Florida","Orlando, Florida",
				"Puerto Plata, Dominican Republic","Grand Turk Island, Turks and Caicos Islands","Nassau, Bahamas","Miami, Florida","");
		insertShip(conn, "Marella Voyager","Marella Cruises",
				"https://www.cruisemapper.com/?imo=9106302",
				7,956,1997,2023,2868,
				"Palma de Mallorca, Spain","Palma de Mallorca, Spain",
				"Olbia, Italy","Naples, Italy","Piombino, Italy","Nice, France","Palamos, Spain");
		insertShip(conn, "Marella Explorer","Marella Cruises",
				"https://www.cruisemapper.com/?imo=9106297",
				7,962,1996,2021,2886,
				"Corfu Island, Greece","Corfu Island, Greece",
				"Koper, Slovenia","Ravenna, Italy","Split, Croatia","Dubrovnik, Croatia","Kotor, Montenegro");
		insertShip(conn, "Oceania Insignia","Oceania Cruises",
				"https://www.cruisemapper.com/?imo=9156462",
				7,349,1998,2022,1047,
				"New York City, New York","New York City, New York"
				,"St George, Bermuda","Hamilton, Bermuda","","","");
		insertShip(conn, "Oceania Allura","Oceania Cruises",
				"x",
				0,613,2025,0,1839,
				"x","x"
				,"x","","","","");
		
		
		/*insert(conn, name, company, 
		 * location, 
		 * tripLength, numCabins, yearOfBuild, maintenance, maxCapacity, 
		 * origin, finalDestination, 
		 * destination1, destination2, destination3, destination4, destination5);*/
	}

	public static void selectAll(Connection conn){  
		String sql = "SELECT * FROM ships";  

		try {  
			Statement stmt  = conn.createStatement();  
			ResultSet rs    = stmt.executeQuery(sql);  

			// loop through the result set  
			while (rs.next()) {  
				System.out.println(rs.getInt("id")+  "\t" +
						rs.getString("ShipName")+  "\t" +
						rs.getString("ShipCompany")+  "\t" +
						rs.getString("Location")+  "\t" +
						rs.getInt("TripLength")+  "\t" + 
						rs.getInt("Cabins")+  "\t" +
						rs.getInt("YearOfBuild")+  "\t" +
						rs.getInt("Maintenance")+  "\t" +
						rs.getInt("Capacity")+  "\t" +
						rs.getString("Origin")+  "\t" +
						rs.getString("FinalDestination")+  "\t" +
						rs.getString("Destination1")+  "\t" +
						rs.getString("Destination2")+  "\t" +
						rs.getString("Destination3")+  "\t" +
						rs.getString("Destination4")+  "\t" +
						rs.getString("Destination5"));  
			}  
		} catch (SQLException e) {  
			System.out.println(e.getMessage());  
		}  
	}  

	public static Connection connect() {  
		Connection conn = null;  

		try {  
            // db parameters  
            // create a connection to the database  
            conn = DriverManager.getConnection(url);  
              
            System.out.println("Connection to SQLite has been established.");  
              
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }   
        
        return conn;
    }  
	
	public static void main(String[] args) {  
		createNewDatabase(); 
		//selectAll(connect());
		selectAllPassengers(connect());
	}
}  