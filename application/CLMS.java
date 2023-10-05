package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CLMS {
	public static void main(String[] args) {  
		databaseQuery("*", "ships", "TripLength", 4);

	
}
	
	
	public static void databaseQuery(String column, String tableName, String searchTerm, String value){  
		String sql = "SELECT "+column+" FROM "+tableName+ 
					 " WHERE "+searchTerm+" = "+value;
		queryCore(CreateShipDatabase.connect(), sql);
	}  
	public static void databaseQuery(String column, String tableName, String searchTerm, int value){  
		String sql = "SELECT "+column+" FROM " +tableName+ " WHERE " +searchTerm+ " = " +value;;  
		queryCore(CreateShipDatabase.connect(), sql);
		}  
	
	public static void queryCore(Connection conn, String sql) {
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
	}
