package pinglogger.ping;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import pinglogger.io.IO;

public class Pinger {
	private IO io;
	
	public Pinger() {
		this.io = new IO();
		this.start();
	}
	
	public void start() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				while(true) {
					if(!someInternet()) {
						io.escribirLinea("Conexin con Internet perdida...");
						try {
							while(!someInternet()) {
								Thread.sleep(1000);
							}
							io.escribirLinea("Conexin restablecida con éxito!");
						}catch(InterruptedException e) {
							e.printStackTrace();
						}
					}else{
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}	
					}
				}
			}
		});
		t.start();
	}
	
	private boolean someInternet() {
		try {
			URL url = new URL("http://www.google.com");
			HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();
			
			@SuppressWarnings("unused")
			Object objData = urlConnect.getContent();
		} catch(UnknownHostException e) {
			return false;
		}catch (MalformedURLException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
