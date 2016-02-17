
public class start {

	public static void main(String[] args) {
		DB_write dbw = new DB_write();
		dbw.write();
		
		DB_read dbr = new DB_read();
		dbr.read();

	}

}
