package org.app.dispatcher;

import java.io.IOException;
import javax.servlet.ServletException;


import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;
import org.dsrg.soenea.application.servlet.service.DispatcherFactory;

public class ManageDeck extends Dispatcher {
	
	@Override
	public void execute() throws ServletException, IOException {
		
		String httpMethod = myRequest.getMethod();
		String dispatcherName = null;
		
		if(httpMethod.equals("GET")) {
			dispatcherName = "org.app.dispatcher.ListDeck";
		} else if(httpMethod.equals("POST")) {
			dispatcherName = "org.app.dispatcher.UploadDeck";
		}
		
		try {
			Dispatcher dispatcher = DispatcherFactory.getInstance(dispatcherName);
			this.redirectToDispatcher(dispatcher);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
