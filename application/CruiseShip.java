package application;

public class CruiseShip extends CruiseCompany {

	//variables
	 int shipID;
	String name;
	String company;
	String location;
	int tripLength; 
	int numCabins;
	int yearOfBuild;
	int maintenance;
	int maxCapacity;
	String origin;
	String finalDestination;
	String destination1;
	String destination2;
	String destination3;
	String destination4;
	String destination5;
	
	//array of cabins
	Cabin[] cabins;
	
	public CruiseShip(int shipID, String name, String company, String location, int tripLength, int numCabins, 
    		int yearOfBuild, int maintenance, int maxCapacity,String origin, String finalDestination, 
    		String destination1,String destination2, String destination3, String destination4, String destination5) {
		super(company);
		this.shipID = shipID;
		this.name = name;
		this.location = location;
		this.origin = origin;
		this.destination1 = destination1;
		this.destination2 = destination2;
		this.destination3 = destination3;
		this.destination4 = destination4;
		this.destination5 = destination5;
		this.tripLength = tripLength; 
		this.numCabins = numCabins;
		this.yearOfBuild = yearOfBuild;
		this.maintenance = maintenance;
		this.maxCapacity = maxCapacity;
	}
	
	//getter methods
	public int getShipID(){
		return shipID;
		}
	public String getCompany(){
		return company;
		}
	public String getName(){
		return name;
		}
	public String getLocation(){
		return location;
		}
	public String getorigin(){
		return origin;
		}
	public int getTripLength(){
		return tripLength;
		}
	public int getYearOfBuild(){
		return yearOfBuild;
		}
	public int getNumCabins(){
		return numCabins;
		}
	public int getMaintenance() {
		return maintenance;
		}
	public void setMaintenance(int maintenance) {
		this.maintenance = maintenance;
		DatabaseController.updateShip(shipID+1, maintenance);
	}
	public int getMaxCapacity(){
		return maxCapacity;
		}
	public int getAvailableCabins() {
		return getNumCabins() - cabins.length;	
		}
	public void printShipDetails() {
		System.out.println(shipID + name + company + location + tripLength + numCabins + 
	    		yearOfBuild + maintenance + maxCapacity + origin + finalDestination + 
	    		 destination1 + destination2 + destination3 + destination4 + destination5);
	}
}
