package autodealer.api;

import org.json.JSONObject;
import autodealer.service.AutoDealerDataRetrival;

/**@author VINAY
 * DataSet API: This API return the dataSet Id
 * Return: String of dataSetId (JSON object) */

public class DataSetApi {
	
	private static AutoDealerDataRetrival auto = new AutoDealerDataRetrival();
	private static JSONObject jsonObj = null;
	
	/*
	 * public DataSetApi() { this.auto = new AutoDealerDataRetrival(); }
	 */
	
	/** This method retrieves dataSet Id 
	 **/
	public static String getDataSetId() {
		String datasetId = "";
		String dataSetIdUrl = "http://vautointerview.azurewebsites.net/api/datasetId";
		
		//Retrieve dataSetId using service -->retriveDataSetId()
		String dataSetIdJson = auto.getInformationAPI(dataSetIdUrl);

		jsonObj = new JSONObject(dataSetIdJson);

		datasetId = jsonObj.getString("datasetId").trim();

		return datasetId;
	}
}
