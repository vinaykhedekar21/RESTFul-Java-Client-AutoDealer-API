package autodealer.model;

import java.util.ArrayList;
import java.util.List;

/**@author VINAY
 *Dealers Pojo holds the list of dealer objects as complete list of Dealers 
 **/
public class Dealers {
	
	List<Dealer> dealers = new ArrayList<Dealer>();

	/**
	 * @return the dealers
	 */
	public List<Dealer> getDealers() {
		return dealers;
	}

	/**
	 * @param dealers the dealers to set
	 */
	public void setDealers(List<Dealer> dealers) {
		this.dealers = dealers;
	}
	
}
