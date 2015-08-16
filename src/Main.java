import pinglogger.ping.Pinger;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		System.out.println("Iniciado!");
		new Pinger();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Ejecución finalizada");
		}
	}

}
