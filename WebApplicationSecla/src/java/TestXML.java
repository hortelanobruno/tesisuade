import db.ConexionVO;


public class TestXML {
	
	
	public TestXML() {
		ConexionVO con = new ConexionVO();
		con.setDatabase("sistemaderecibos");
		con.setIp("10.0.0.1");
		con.setPassword("123456");
		con.setPort("1433");
		con.setUsuario("sa");
		
		
		
		
		//GRABOOOOOOOOOOOOOOOOOOOO
		
		
        
	}
	
	public static void main(String args[]){
		new TestXML();
	}
}
