package app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.OptionSet;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class UpdateInfoResource extends CoapResource {

	private final String fileName = "logFile.txt";
	
	public UpdateInfoResource(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void handlePOST(CoapExchange exchange){
		exchange.accept();
		
		OptionSet optionSet = exchange.getRequestOptions();
		String queryString = optionSet.getURIQueryString();
		System.out.println("queyString = " + queryString);
		if (queryString.startsWith("Message=")){
			String message = queryString.split("=")[1];
			
			// log the message in the file
			try {
				Files.write(Paths.get(fileName), (message + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
			} catch (IOException e) {
				// the file doesn't exist ... create one
				PrintWriter writer;
				try {
					writer = new PrintWriter(fileName, "UTF-8");
					writer.println(message);
					writer.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					exchange.respond(ResponseCode.BAD_OPTION);
					return;
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					exchange.respond(ResponseCode.BAD_OPTION);
					return;
				}
			}
		}
		
		exchange.respond(ResponseCode.CREATED);
	}

}
