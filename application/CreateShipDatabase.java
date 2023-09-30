package application;

import java.sql.Connection;  
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
*/
public class CreateShipDatabase {  

	static String fileName = "CLMS";
	static String excelFileName = "C:/Users/djclo/OneDrive/Desktop/School/CMSC_495/Group3/CLMS_database.xlsx";


	public static void createNewDatabase(String fileName) {  
		String url = "jdbc:sqlite:C:/Users/djclo/sqlite/" + fileName;  
		try {  
			Connection conn = DriverManager.getConnection(url);  
			if (conn != null) {  
				DatabaseMetaData meta = conn.getMetaData();  
				System.out.println("The driver name is " + meta.getDriverName());  
				System.out.println("A new database has been created.");
				createNewTable(conn, url);   
				/*
				 // requires Apache POI
				 for(int i = 0; i < 40; i++) 
				{
					try {
						loadShip(conn, excelFileName, i);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}*/
				selectAll(conn);
			}  
		} catch (SQLException e) {System.out.println(e.getMessage());}  

	}  

	public static void createNewTable(Connection conn, String url) {  

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

	public static void insert(Connection conn, String name, String company, String location, int tripLength, int numCabins, 
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
	/*
	 * // Method to pull Ship records from Obsolete Excel database to insert into SQlite
	 
	public static void loadShip(Connection conn, String filename, int i) throws IOException {
		String name = null;
		String company = null;
		String location = null;
		int tripLength = 0; 
		int numCabins = 0;
		int yearOfBuild = 0;
		int maintenance = 0;
		int maxCapacity = 0;
		String origin = null;
		String finalDestination = null;
		String destination1 = null;
		String destination2 = null;
		String destination3 = null;
		String destination4 = null;
		String destination5 = null;

		// Creating a xls file object with specific file path to read
		File xlsFile = new File(filename);
		// Creating input stream
		FileInputStream inputStream = new FileInputStream(xlsFile);
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		// Reading the first sheet of the excel file
		XSSFSheet sheet = workbook.getSheetAt(0);
		Row row = sheet.getRow(i+1);  
		Iterator<Cell> cellIterator = row.iterator();
		// Iterating all the columns in a row
		int colNum = 0;    
		while (cellIterator.hasNext()) {
			//int z = 0;
			Cell cell = cellIterator.next();
			switch (colNum) {
			case 0:
				name = cell.getRichStringCellValue().getString();
				//System.out.println(colNum+ " " + name);
				break;
			case 1:
				company = cell.getRichStringCellValue().getString();
				//System.out.println(colNum+ " " + company);
				break;
			case 2:
				location = cell.getRichStringCellValue().getString();
				//System.out.println(colNum+ " " + location);
				break;
			case 3:
				tripLength = (int) cell.getNumericCellValue();
				//System.out.println(colNum+ " " + tripLength);
				break;
			case 4:
				numCabins = (int) cell.getNumericCellValue();
				//System.out.println(colNum+ " " + numCabins);
				break;
			case 5:
				yearOfBuild = (int) cell.getNumericCellValue();
				//System.out.println(colNum+ " " + yearOfBuild);
				break;
			case 6:
				maintenance = (int) cell.getNumericCellValue();
				//System.out.println(colNum+ " " + maintenance);
				break;
			case 7:
				maxCapacity = (int) cell.getNumericCellValue();
				//System.out.println(colNum+ " " + maxCapacity);
				break;
			case 8:
				origin = cell.getRichStringCellValue().getString();
				//System.out.println(colNum+ " " + origin);
				break;
			case 9:
				finalDestination = cell.getRichStringCellValue().getString();
				//System.out.println(colNum+ " " + destination);
				break;
			case 10:
				destination1 = cell.getRichStringCellValue().getString();
				//System.out.println(colNum+ " " + destination);
				break;
			case 11:
				destination2 = cell.getRichStringCellValue().getString();
				//System.out.println(colNum+ " " + destination);
				break;
			case 12:
				destination3 = cell.getRichStringCellValue().getString();
				//System.out.println(colNum+ " " + destination);
				break;
			case 13:
				destination4 = cell.getRichStringCellValue().getString();
				//System.out.println(colNum+ " " + destination);
				break;
			case 14:
				destination5 = cell.getRichStringCellValue().getString();
				//System.out.println(colNum+ " " + destination);
				break;
			default:
				break;
			}
			colNum++;
		}

		// Closing the workbook and input stream

		workbook.close();
		inputStream.close();
		//System.out.println(i+"loaded");
		insert(conn, name, company, location, tripLength, numCabins, yearOfBuild, maintenance, maxCapacity, origin, finalDestination, destination1, 
				destination2, destination3, destination4, destination5);
	}*/

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

	public static void main(String[] args) {  
		createNewDatabase(fileName); 
	}
}  