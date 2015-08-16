package pinglogger.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class IO {
	private static final String logDirectory = "C:/pinglogger/";
	private static final String dateDisplay = "dd-MM-yyyy";
	private static final String timeDisplay = "HH:mm:ss";
	
	private Calendar calendar;
	private BufferedWriter bwriter;
	private FileWriter writer;
	private DateFormat dateFormat;
	private DateFormat timeFormat;
	
	public IO() {
		this.dateFormat = new SimpleDateFormat(dateDisplay);
		this.timeFormat = new SimpleDateFormat(timeDisplay);
		this.calendar = Calendar.getInstance();
		
		File archivo = new File(logDirectory + this.dateFormat.format(this.calendar.getTime()) + ".txt");
		if(!archivo.exists())
			try {
				archivo.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void escribirLinea(String mensaje) {
		try {
			this.calendar.setTime(new Date());
			this.writer = new FileWriter(logDirectory + this.dateFormat.format(this.calendar.getTime()) + ".txt", true);
			this.bwriter = new BufferedWriter(this.writer);
			this.bwriter.write("[" + this.timeFormat.format(this.calendar.getTime()) + "] " + mensaje);
			this.bwriter.newLine();
			this.bwriter.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				this.bwriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
