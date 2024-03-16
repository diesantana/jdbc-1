package db;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
	
	private static Connection connection = null;
	
	// Responsável pela conexão com o banco de dados
	public static Connection connect() {
		try {
			if (connection == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				connection = DriverManager.getConnection(url, props);
			}
		} catch (SQLException | ClassNotFoundException e) {
			throw new DbException(e.getMessage());
		}
		return connection;
	}
	
	// fechar a conexão 
	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	
	// carrega as propriedades
	private static Properties loadProperties() {
		
		try (FileInputStream fs = new FileInputStream("db.properties")){
			Properties properties = new Properties();
			properties.load(fs);
			return properties;
			
		} catch (IOException e) {
			throw new DbException(e.getMessage());

		}
	}
	
	
}
