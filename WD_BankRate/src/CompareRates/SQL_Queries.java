package CompareRates;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL_Queries {

	private Connection conn;
	
	public SQL_Queries() throws ClassNotFoundException, SQLException {
		  String myDriver = "org.gjt.mm.mysql.Driver";
		  String myUrl = "jdbc:mysql://localhost/WebDriverProjects";
		  Class.forName(myDriver);
		  conn = DriverManager.getConnection(myUrl, "root", "");
	}
	
	public void insertInto_BankRate(String LOB, float rate) throws SQLException {
		  String sqlStatement = "INSERT INTO `BankRate` (`LineOfBusiness`, `RatePercent`) " + 
				  				"VALUES ('" + LOB + "', '" + rate + "')";
		  Statement stmt = this.conn.createStatement();
		  stmt.execute(sqlStatement);
	}
	public void sql_closeConn() throws SQLException {
		  this.conn.close();
		
	}
}