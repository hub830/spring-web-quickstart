
package demo.fabric;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import com.mysql.fabric.jdbc.FabricMySQLConnection;

/**
 * Demonstrate working with employee data in MySQL Fabric with Connector/J and
 * the JDBC APIs.
 */
public class EmployeesJdbc3 {
	public static void main(String args[]) throws Exception {

		String hostname = "192.168.16.51";
		String port = "32274";
		String database = "test";
		String user = "root";
		String password = "";

		String baseUrl = "jdbc:mysql:fabric://" + hostname + ":" + Integer.valueOf(port) + "/";

		// Load the driver if running under Java 5
		if (!com.mysql.jdbc.Util.isJdbc4()) {
			Class.forName("com.mysql.fabric.jdbc.FabricMySQLDriver");
		}

		// 1. Create database and table for our demo
		Connection rawConnection = DriverManager.getConnection(baseUrl + "test?fabricServerGroup=group_id-global&fabricUsername=admin&fabricPassword=admin", user, password);

		Statement statement = rawConnection.createStatement();
		statement.executeUpdate("create table employees (emp_no int not null," + "first_name varchar(50), last_name varchar(50)," + "primary key (emp_no))");
		
		FabricMySQLConnection connection = (FabricMySQLConnection) rawConnection;

		Integer ids[] = new Integer[] { 3, 4, 10003, 10004 };
		String firstNames[] = new String[] { "John", "Jane", "Andy", "Alice" };
		String lastNames[] = new String[] { "Doe", "Doe", "Wiley", "Wein" };


		PreparedStatement ps = connection.prepareStatement("INSERT INTO employees VALUES (?,?,?)");
		for (int i = 0; i < 4; ++i) {
			ps.setInt(1, ids[i]);
			ps.setString(2, firstNames[i]);
			ps.setString(3, lastNames[i]);
			ps.executeUpdate();
		}

		connection.close();
		
		rawConnection.close();


	}
}