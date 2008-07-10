package exampleblog;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.db.IDBConnection;
import com.hp.hpl.jena.db.ModelRDB;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelMaker;

public class Test {

	private OntModel ontology;
	private IDBConnection connection;
	private ModelMaker maker;
	private ModelRDB RDBModel;

	public Test() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String dbname = "";
			String dbuser = "";
			String dbpass = "";
			String dbengine = "";
			String dbmodelname = "";

			connection = new DBConnection("jdbc:mysql://localhost/" + dbname,
					dbuser, dbpass, dbengine);

			maker = ModelFactory.createModelRDBMaker(connection);

			RDBModel = (ModelRDB) maker.openModel(dbmodelname);

			ontology = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,
					RDBModel);

			// aca hay que poner el codigo nuestro

			try {
				ontology.close();
				RDBModel.close();
				connection.close();
			} catch (Exception e) {
				System.out
						.println("Se ha producido una excepcion controlada en 'Main'");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean createDatabaseScheema(String dbname, String dbuser,
			String dbpass, String dbengine, String dbmodelname) {

		boolean created = false;

		if (existsDatabase(dbname, dbuser, dbpass) == false) {
			createDatabase(dbname, dbuser, dbpass);
		}

		IDBConnection con = null;
		con = new DBConnection("jdbc:mysql://localhost/" + dbname, dbuser,
				dbpass, dbengine);

		try {
			con.cleanDB();

			ModelRDB RDBModel = null;

			RDBModel = ModelRDB.createModel(con, dbmodelname);
			System.out.println("Base de datos '" + dbname + "' preparada.");

			RDBModel.close();
			con.close();

			created = true;

		} catch (Exception e) {
			System.out.println("Se ha producido una excepcion controlada.");
			e.printStackTrace();
		}
		return created;
	}

	public static boolean existsDatabase(String dbname, String dbuser,
			String dbpass) {
		Connection con = null;
		boolean exists = false;

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/",
					dbuser, dbpass);

			if (con.isClosed())
				exists = false;
			else {
				ResultSet res = con.createStatement().executeQuery(
						"SHOW DATABASES;");
				while (res.next()) {
					if (res.getString(1).equals(dbname)) {
						exists = true;
						break;
					}
				}
				res.close();
				con.close();
			}
		} catch (Exception e) {
			System.out.println("Se ha producido una excepcion controada.");
			e.printStackTrace();
		}

		return exists;
	}

	public static boolean createDatabase(String dbname, String dbuser,
			String dbpass) {
		boolean created = false;
		Connection con = null;
		int ret = 0;

		try {

			con = DriverManager.getConnection("jdbc:mysql://localhost/",
					dbuser, dbpass);
			ret = con.createStatement().executeUpdate(
					"CREATE DATABASE " + dbname + ";");
			if (ret > 0) {
				created = true;
				System.out.println("Se ha creado la base de datos '" + dbname
						+ "'.");
			}
			con.close();
		} catch (Exception e) {
			System.out.println("Se ha producido una excepcion controlada.");
			e.printStackTrace();
		}

		return created;
	}

	private boolean saveOntModelIntoDatabase(String dbname, String dbuser,
			String dbpass, String dbengine, String dbmodelname,
			OntModel ontmodel) {

		boolean saved = false;
		IDBConnection con = null;
		con = new DBConnection("jdbc:mysql://localhost/" + dbname, dbuser,
				dbpass, dbengine);

		ModelMaker maker = ModelFactory.createModelRDBMaker(con);

		if (maker.hasModel(dbmodelname) == false) {
			System.out.println("El modelo '" + dbmodelname
					+ "' no existe en la BBDD.");
			return false;
		}

		ModelRDB RDBModel = (ModelRDB) maker.openModel(dbmodelname);

		OntModel ontmodel2 = null;
		ontmodel2 = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,
				RDBModel);

		ontmodel2.add(ontmodel);

		System.out
				.println("Se ha guardado una instancia de OntModel en la BBDD.");
		saved = true;
		try {
			ontmodel2.close();
			ontmodel.close();
			RDBModel.close();
			con.close();
		} catch (Exception e) {
			System.out.println("Se ha producido una excepcion controlada.");
			e.printStackTrace();
		}

		return saved;
	}

	private OntModel loadOntModelFromOwlFile(String owlfile) {

		OntModel ontmodel = null;
		InputStream is = null;

		// OWL_MEM es la especificacion para modelos OWL almacenados en memoria
		ontmodel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

		try {
			is = new FileInputStream(new File(owlfile));
			ontmodel.read(is, "");
			System.out.println("Se ha cargado una instancia OntModel.");
		} catch (Exception e) {
			System.out.println("Se ha producido una excepcion controlada.");
			e.printStackTrace();
		}
		return ontmodel;
	}

	public static void main(String args[]) {

		new Test();

	}

}
