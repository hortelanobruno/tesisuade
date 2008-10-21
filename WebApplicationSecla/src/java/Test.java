import java.util.ArrayList;
import java.util.List;

import db.DBManager;



public class Test {

	public Test() {
		//Connection conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=sistemarecibos", "sa", "123456");
		//insert into usuarios values('admin','admin','administracion','saraza')
		//delete from usuarios where usuario = 'admin'
		DBManager manager = new DBManager();
		//roberto1000199902 / 09 / 2008
		//String result = manager.completarBoleta("1000", "30 / 09 / 2008", "roberto", "1999");
		List<Integer> boletas = new ArrayList<Integer>();
		boletas.add(1000);
		manager.confirmarRecibos(boletas);
		System.out.println("Termino");
	}
	
	
	public static void main(String args[]){
		new Test();
	}
}
