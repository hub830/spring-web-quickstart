
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
public class EmployeesJdbc2 {
	public static void main(String args[]) throws Exception {

		String hostname = "192.168.16.25";
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
		Connection rawConnection = DriverManager.getConnection(baseUrl + "test?fabricServerGroup=my_group&fabricUsername=admin&fabricPassword=admin", user, password);

		Statement statement = rawConnection.createStatement();
		statement.executeUpdate("drop table if exists employees");
		statement.executeUpdate("create table employees (emp_no int not null," + "first_name varchar(50), last_name varchar(50)," + "primary key (emp_no))");
		statement.close();
		
		
		rawConnection.close();


	}
}