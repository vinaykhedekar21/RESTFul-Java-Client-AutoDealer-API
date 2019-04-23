package autodealer.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import autodealer.model.Dealers;

public class AutoDealerDataRetrival {

	String output = "";
	URL url = null;
	HttpURLConnection conn = null;

	/**
	 * A GET service API to retrieve a dataset ID, VehicleId List and dealer
	 * information
	 */
	public String getInformationAPI(String requestUrl) {

		try {

			url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			while ((output = br.readLine()) != null) {
				return output;
			}

			// Disconnect the connection
			conn.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

	/**
	 * A POST Answer API - This function post the answer using HTTPUrlConnection
	 * objectOutputStream
	 * 
	 * @input Dealar, DataSetId, dealerJSON
	 */
	public void postAnswerApi(Dealers dealers, String dataSetId, String dealersJosn) {

		try {

			url = new URL("http://vautointerview.azurewebsites.net/api/" + dataSetId + "/answer");

			conn = (HttpURLConnection) url.openConnection();

			// Setting basic post request
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			conn.setRequestProperty("Content-Type", "application/json");

			conn.setDoOutput(true); // this is to enable writing
			conn.setDoInput(true); // this is to enable reading

			OutputStream os = conn.getOutputStream();
			os.write(dealersJosn.toString().getBytes("UTF-8"));
			os.close();

			int responseCode = conn.getResponseCode();
			
			System.out.println("Response Code: " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			  System.out.println("***Response***");
			  System.out.println(response.toString());
			 

		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
