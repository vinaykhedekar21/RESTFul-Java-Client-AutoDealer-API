package autodealer.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import autodealer.model.Vehicle;
import autodealer.service.AutoDealerDataRetrival;

/**@author: VINAY
 * This API process the Vehicle information*/

public class VehicleApi {
	
	private static AutoDealerDataRetrival auto = new AutoDealerDataRetrival();
	private static JSONObject jsonObj = null;
	private static Map<Integer, List<Vehicle>> dealerVehicleMap = new HashMap<Integer, List<Vehicle>>();
	
	
	/** This method will process each vehicle Id and maintain the map of dealer : list of vehicle*/
	public static Map<Integer, List<Vehicle>> getVehicleDetails(String dataSetId) {
		
		String vehicleIdUrl = null;
		String vehicleData = null;
		List<Integer> vehicleIdList = new ArrayList<Integer>();
		
		/**Invoke processVehicle() and get List of vehicle Id's*/
		//Get VehicleId List
		vehicleIdList = getVehicleIds(dataSetId);
		
		//Process each vehicle Id 
		if(vehicleIdList != null) {
					
			for(int vehicleId : vehicleIdList) {
				vehicleIdUrl = "http://vautointerview.azurewebsites.net/api/"+dataSetId+"/vehicles/"+vehicleId;
				
				//Retrieve vehicle information
				vehicleData = auto.getInformationAPI(vehicleIdUrl);
						
				if (vehicleData != null) {
					//Invoke method process Vehicle Information
					dealerVehicleMap = processVehicleInformation(vehicleData);
				}
			}
		}
		return dealerVehicleMap;
	}
	
	/** This method will process the vehicle information and prepare a Map*/
	private static Map<Integer, List<Vehicle>> processVehicleInformation(String vehicleData) {
			
		//get the result JSON object
		jsonObj = new JSONObject(vehicleData);
		
		//Dealer Id
		int dealerId = jsonObj.getInt("dealerId");
		
		//Create the vehicle object
		Vehicle vehicle = new Vehicle();
		
		vehicle.setVehicleId(jsonObj.getInt("vehicleId"));
		vehicle.setYear(jsonObj.getInt("year"));
		vehicle.setMake(jsonObj.getString("make").trim());
		vehicle.setModel(jsonObj.getString("model").trim());
		
		//Put information into the Map Dealer Id : List of vehicles 
		if(!dealerVehicleMap.containsKey(dealerId)) {
			List<Vehicle> vehicleList = new ArrayList<Vehicle>();
			vehicleList.add(vehicle);
			dealerVehicleMap.put(dealerId, vehicleList);
		}
		else {
			dealerVehicleMap.get(dealerId).add(vehicle);
		}
		return dealerVehicleMap;
	}
	
	/** This method retrieves Vehicle Id List for the dataset Id */
	public static List<Integer> getVehicleIds(String dataSetId) {
		
		List<Integer> vehicleIdList = new ArrayList<Integer>();
		String vehicleIds = null;
		JSONArray vehicleIdObj = null;
				
		String vehicleUrl = "http://vautointerview.azurewebsites.net/api/"+dataSetId+"/vehicles";
		
		/*Retrieve vehicle Id's using a service */
		vehicleIds = auto.getInformationAPI(vehicleUrl);
		 
		  //Result : vehicle Id JSON Object
		  jsonObj = new JSONObject(vehicleIds);
		  
		  vehicleIdObj = jsonObj.getJSONArray("vehicleIds"); 
		  
		  if (vehicleIdObj != null && vehicleIdObj.length() > 0) {
				for (int i = 0; i < vehicleIdObj.length(); i++) {
	
					int vehicleId = vehicleIdObj.getInt(i);
	
					vehicleIdList.add(vehicleId);
				} 
		  }
		  return vehicleIdList;
	}
}
