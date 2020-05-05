import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection dbConn;
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			String user = "konkuk";
			String pw = "oracle";
			String url = "jdbc:oracle:thin:@192.168.210.64:1521:xe";
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);
			
		} catch(ClassNotFoundException cnfe) {
			System.out.println("DB  : "+cnfe.toString());
		} catch(SQLException sqle) {
			System.out.println("DB  : "+sqle.toString());
		} catch(Exception e) {
			System.out.println("UnKnown Error");
			e.printStackTrace();
		}
		return conn;
	}
}