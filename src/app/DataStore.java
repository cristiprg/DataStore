package app;

import org.eclipse.californium.core.CoapServer;

public class DataStore {

	private CoapServer coapServer = new CoapServer();
	
	public DataStore() {
		coapServer.add(new UpdateInfoResource("UpdateSpotInfo"));
		coapServer.start();
		// infinite loop
		while(true){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args){
		new DataStore();
	}
}
