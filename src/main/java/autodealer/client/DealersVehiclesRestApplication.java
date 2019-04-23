package autodealer.client;

import java.util.List;
import java.util.Map;

import autodealer.api.DataSetApi;
import autodealer.api.DealerApi;
import autodealer.api.VehicleApi;
import autodealer.model.Dealers;
import autodealer.model.Vehicle;
import autodealer.service.AutoDealerDataRetrival;

/**@author VINAY
 * This program contains main business logic and API calls to execute business logic*/

public class DealersVehiclesRestApplication {

	Dealers dealers = null;
	String dataSetId = "";
	String dealersJosn = "";
	private static AutoDealerDataRetrival auto = null;
	Map<Integer, List<Vehicle>> vehicleDealerMap = null;
	
	public DealersVehiclesRestApplication() {
		this.auto = new AutoDealerDataRetrival();
	}
		
	/** Dealers post API main function
	 * @throws Exception */
	public void dealersVehiclesPostAPI() throws Exception {
		//Get new Dealer object
		dealers = new Dealers();
		
		//Get dataSetId
		dataSetId = DataSetApi.getDataSetId();
			
		//Invoke getVehicleDetails()
		vehicleDealerMap = VehicleApi.getVehicleDetails(dataSetId);
							
		//Process dealers information and create a dealer Object
		DealerApi.processDealerInformation(dataSetId, dealers, vehicleDealerMap);
		
		//Convert Dealers object into the JOSN
		dealersJosn = DealerApi.convertIntoJson(dealers);
		
		//post answer API
		auto.postAnswerApi(dealers, dataSetId, dealersJosn);
	}
	
	/**Main driver function*/
	public static void main(String[] args) throws Exception {
		
		DealersVehiclesRestApplication app = new DealersVehiclesRestApplication();
		app.dealersVehiclesPostAPI();
	}
}
