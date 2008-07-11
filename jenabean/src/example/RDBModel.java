package example;

import static example.AssemblerVocabulary.NS;
import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;

@Namespace(NS)
public class RDBModel {
	private Connection connection;

	@RdfProperty(NS + "Connection")
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	

}
