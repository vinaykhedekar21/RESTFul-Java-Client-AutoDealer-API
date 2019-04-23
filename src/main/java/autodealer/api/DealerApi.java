package autodealer.api;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import autodealer.model.Dealer;
import autodealer.model.Dealers;
import autodealer.model.Vehicle;
import autodealer.service.AutoDealerDataRetrival;

/**@author VINAY
*This API retrieves the dealer information and create a dealer object. 
* It uses map which has information of dealer : List<Vehicles>
* @param dealers 
* @param vehicleDealerMap 
* */

public class DealerApi {
	
	private static AutoDealerDataRetrival auto = new AutoDealerDataRetrival();
	private static JSONObject jsonObj = null;
		
	public static void processDealerInformation(String dataSetId, Dealers dealers, Map<Integer, List<Vehicle>> dealerVehicleMap) {
		String dealerUrl = "";
		String dealerName = "";
		String dealerData = "";
		
		if(dealerVehicleMap != null) {
					
			for(Map.Entry<Integer, List<Vehicle>> entry : dealerVehicleMap.entrySet()) {
				
				int dealerId = entry.getKey();
				List<Vehicle> vehicleList = entry.getValue();
				
				//Get Dealer information
				dealerUrl = "http://vautointerview.azurewebsites.net/api/"+dataSetId+"/dealers/"+dealerId;
				dealerData = auto.getInformationAPI(dealerUrl);
				
				jsonObj = new JSONObject(dealerData);
				
				//Dealer Id
				dealerName = jsonObj.getString("name");
				
				//Create a dealer Object
				Dealer dealer = new Dealer();
				dealer.setDealerId(dealerId);
				dealer.setName(dealerName);
				dealer.setVehicles(vehicleList);
				
				if (dealer != null) {
					//Add dealer Object to the Dealers List
					dealers.getDealers().add(dealer);
				}
			}
		}
	}
	
	public static String convertIntoJson(Dealers dealers) {
		
		Gson gsonBuilder = new GsonBuilder().create();
		String jsonFromPojo = gsonBuilder.toJson(dealers);

		return jsonFromPojo;
	}
}
