# RESTFul-Java-Client-AutoDealer-API
RESTFul Java Client API for a AutoDealer Application

***Goal : Focus on optimizing for low total elapsed time between retrieving the datasetid and posting the server. 
          A successful submission will complete in significantly less than 30 seconds.
          
** A Auto-Dealer application to retrieves a datasetID, retrieves all vehicles and dealers for that dataset, and successfully posts to the Server endpoint. 
** Each vehicle and dealer should be requested only once. 
** You will receive a response structure when you post to the Server endpoint that describes status and total ellapsed time; 
** output this response is total time taken and status.
** The server has a built in delay that varies between 1 and 4 seconds for each request for a vehicle or for a dealer. 
