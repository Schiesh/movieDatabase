package movieProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class MovieProject {

	static void castHighestRatedMovie() {
		//input a cast name and return their highest rated movie
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/project?" + "user=root&password=ADD_YOUR_PASSWORD");  //CHANGE PASSWORD
		}
		catch (SQLException ex) {
			
			System.out.println("SQLException:" + ex.getMessage());
			System.out.println("SQLState:" + ex.getSQLState());
			System.out.println("VendorError:" + ex.getErrorCode());
		}
		
		Statement stmt = null;
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		
		System.out.print("\nPlease enter a cast member's name: ");
		
		String userInput = scan.nextLine();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT mname, score FROM movies NATURAL JOIN movieCast WHERE cname LIKE " + "\"%"+ userInput + "%\""  + "ORDER BY score DESC;");
			
			System.out.println("\nMovie Title" + "\t\t\t\t\t\t\t\t" + " Score");
			System.out.println("----------------------------------------------------------------------------------------------");
			
				while (rs.next()) {
					String mname = rs.getString("mname");
					int score = rs.getInt("score");
					
					System.out.printf("%-72s %-5d %n", mname, score);
			}
		}
		catch (SQLException ex) {
			
			System.out.println("SQLException:" + ex.getMessage());
			System.out.println("SQLState:" + ex.getSQLState());
			System.out.println("VendorError:" + ex.getErrorCode());
		}
		
	}
	
	static void searchMovie() {
		//search for a movie using key words
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/project?" + "user=root&password=ADD_YOUR_PASSWORD");   //CHANGE PASSWORD
		}
		catch (SQLException ex) {
			
			System.out.println("SQLException:" + ex.getMessage());
			System.out.println("SQLState:" + ex.getSQLState());
			System.out.println("VendorError:" + ex.getErrorCode());
		}
		
		Statement stmt = null;
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		
		System.out.print("\nPlease enter a key word to find your movie title: ");
		
		String userInput = scan.nextLine();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT mid, mname, score FROM movies WHERE mname LIKE " + "\"%" + userInput + "%\"" + ";");
			
			System.out.println("\n" + "Movie ID     Movie Title" + "\t\t\t\t\t\t\t\t" + " Score");
			System.out.println("----------------------------------------------------------------------------------------------");
			
				while (rs.next()) {
					int mid = rs.getInt("mid");
					String mname = rs.getString("mname");
					int score = rs.getInt("score");
					
					System.out.printf("%-12d %-72s %5d %n", mid, mname, score);
			}
		}
		catch (SQLException ex) {
			
			System.out.println("SQLException:" + ex.getMessage());
			System.out.println("SQLState:" + ex.getSQLState());
			System.out.println("VendorError:" + ex.getErrorCode());
		}
		
		
	}
	
	static void topTenCast() {
		//find the top ten cast members with the highest average movie scores
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/project?" + "user=root&password=ADD_YOUR_PASSWORD");  //CHANGE PASSWORD
		}
		catch (SQLException ex) {
			
			System.out.println("SQLException:" + ex.getMessage());
			System.out.println("SQLState:" + ex.getSQLState());
			System.out.println("VendorError:" + ex.getErrorCode());
		}
		
		Statement stmt = null;
		ResultSet rs = null;
		
		System.out.println("\nBelow is the Top ten cast member with the highest average movie scores: ");
		
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT cname, AVG(score) average FROM movies NATURAL JOIN movieCast GROUP BY cname ORDER BY average DESC LIMIT 10;");
			
			System.out.println("\n" + "Cast Member" + "\t\t" + "  Average Score");
			System.out.println("----------------------------------------------------------------------------------------------");
			
				while (rs.next()) {
					String cname = rs.getString("cname");
					double average = rs.getDouble("average");
					
					System.out.printf("%-25s %-10f %n", cname, average);
			}
		}
		catch (SQLException ex) {
			
			System.out.println("SQLException:" + ex.getMessage());
			System.out.println("SQLState:" + ex.getSQLState());
			System.out.println("VendorError:" + ex.getErrorCode());
		}
	}
	
	static void mostMoviesTogether() {
		//find cast members that have played in the most movies together
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/project?" + "user=root&password=ADD_YOUR_PASSWORD");  //CHANGE PASSWORD
		}
		catch (SQLException ex) {
			
			System.out.println("SQLException:" + ex.getMessage());
			System.out.println("SQLState:" + ex.getSQLState());
			System.out.println("VendorError:" + ex.getErrorCode());
		}
		
		Statement stmt = null;
		ResultSet rs = null;
		
		System.out.println("\nBelow are the list of cast members who have played in the most movies together: ");
		
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT e.cname, c.cname, COUNT(e.mid) casted_together FROM movieCast e JOIN movieCast c "
					+ "ON (e.mid = c.mid) WHERE e.cname <> c.cname GROUP BY e.cname, c.cname ORDER BY casted_together DESC LIMIT 2");
			
			System.out.println("\n" + "Cast Member 1" + "\t\t" + "Cast Member 2" + "\t\t" + "Number of Movies Together");
			System.out.println("----------------------------------------------------------------------------------------------");
			
				while (rs.next()) {
					String cname1 = rs.getString("e.cname");
					String cname2 = rs.getString("c.cname");
					int casted_together = rs.getInt("casted_together");
					
					System.out.printf("%-23s %-23s %-2d %n", cname1, cname2, casted_together);
			}
		}
		catch (SQLException ex) {
			
			System.out.println("SQLException:" + ex.getMessage());
			System.out.println("SQLState:" + ex.getSQLState());
			System.out.println("VendorError:" + ex.getErrorCode());
		}
	}
	
	static void castList() {
		//input a movie name and return the cast list
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/project?" + "user=root&password=ADD_YOUR_PASSWORD");  //CHANGE PASSWORD
		}
		catch (SQLException ex) {
			
			System.out.println("SQLException:" + ex.getMessage());
			System.out.println("SQLState:" + ex.getSQLState());
			System.out.println("VendorError:" + ex.getErrorCode());
		}
		
		Statement stmt = null;
		ResultSet rs = null;
		Scanner scan = new Scanner(System.in);
		
		System.out.print("\nPlease enter a movie title to find the cast list and please be specific: ");
		
		String userInput = scan.nextLine();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT cid, cname, mname FROM movies NATURAL JOIN movieCast WHERE mname LIKE " + "\"%" + userInput + "%\"" + "ORDER BY cname ASC;");
			
			System.out.println("\n" + "Cast ID" + "\t\t" + "Cast Name" + "\t\t" + "       Movie Title");
			System.out.println("--------------------------------------------------------------------------------------------------------------");
			
				while (rs.next()) {
					int cid = rs.getInt("cid");
					String cname = rs.getString("cname");
					String mname = rs.getString("mname");
					
					System.out.printf("%-15d %-30s %-72s %n", cid, cname, mname);
			}
		}
		catch (SQLException ex) {
			
			System.out.println("SQLException:" + ex.getMessage());
			System.out.println("SQLState:" + ex.getSQLState());
			System.out.println("VendorError:" + ex.getErrorCode());
		}
		
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		}
		catch (Exception ex) {
			
		}
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/project?" + "user=root&password=ADD_YOUR_PASSWORD");  //CHANGE PASSWORD
		}
		catch (SQLException ex) {
			
			System.out.println("SQLException:" + ex.getMessage());
			System.out.println("SQLState:" + ex.getSQLState());
			System.out.println("VendorError:" + ex.getErrorCode());
		}
		Statement stmt = null;
		ResultSet rs = null;
		
		System.out.println("Loading Movie Table...");
		
		try {
			
			stmt = conn.createStatement();
			
			stmt.executeUpdate("CREATE TABLE movies (mid integer, mname CHAR (100), score integer, PRIMARY KEY (mid));");
					
			
		}
		catch (SQLException ex) {
			
			System.out.println("SQLException:" + ex.getMessage());
			System.out.println("SQLState:" + ex.getSQLState());
			System.out.println("VendorError:" + ex.getErrorCode());
		}
		
		
		stmt = null;
		rs = null;
		
		System.out.println("Loading Cast Table...");
		
		try {
			
			stmt = conn.createStatement();
			
			
			stmt.executeUpdate("CREATE TABLE movieCast (mid integer, cid integer, cname CHAR (50), "
					+ "PRIMARY KEY (mid, cid), FOREIGN KEY (mid) REFERENCES movies(mid));");
					
			
		}
		catch (SQLException ex) {
			
			System.out.println("SQLException:" + ex.getMessage());
			System.out.println("SQLState:" + ex.getSQLState());
			System.out.println("VendorError:" + ex.getErrorCode());
		}
		
		stmt = null;
		rs = null;
		
		System.out.println("Loading Movies...");
		
		try {
			File file = new File("C:/Users/Russell/Desktop/Project/movie-name-score.txt");  // CHANGE movie-name-score.txt LOCATION
			Scanner scan = new Scanner(file);
			stmt = conn.createStatement();
			
			
				while (scan.hasNextLine()) {
					stmt.executeUpdate("INSERT INTO movies values(" + scan.nextLine() + ");");
					
			
			}
		}
		catch (SQLException ex) {
			
			System.out.println("SQLException:" + ex.getMessage());
			System.out.println("SQLState:" + ex.getSQLState());
			System.out.println("VendorError:" + ex.getErrorCode());
		}
		
		stmt = null;
		rs = null;
		
		System.out.println("Loading Cast...");
		
		try {
			File file = new File("C:/Users/Russell/Desktop/Project/movie-cast.txt");   // CHANGE movie-cast.txt LOCATION
			Scanner scan = new Scanner(file);
			stmt = conn.createStatement();
			
			
				while (scan.hasNextLine()) {
					stmt.executeUpdate("INSERT INTO movieCast values(" + scan.nextLine() + ");");
					
			
			}
		}
		catch (SQLException ex) {
			
			System.out.println("SQLException:" + ex.getMessage());
			System.out.println("SQLState:" + ex.getSQLState());
			System.out.println("VendorError:" + ex.getErrorCode());
		}
		
		int input = 0;
		Scanner scan = new Scanner(System.in);
		while (input != 6) {
			
			System.out.println("\n-----------------------------------------------------------------------------------------------");
			System.out.println("1: Find the Highest rated movie of a cast member.");
			System.out.println("2: Search for a specific movie.");
			System.out.println("3: The top ten cast members with the highest average of movie ratings");
			System.out.println("4: Find the list of cast members that have worked together the most.");
			System.out.println("5: Find the List of cast members of a specific movie.");
			System.out.println("6: To end the program.");
			System.out.print("\nPlease enter which option you would like:");
			input = scan.nextInt();
			switch(input) {
				case 1:
					castHighestRatedMovie();
					break;
				
				case 2:
					searchMovie();
					break;
					
				case 3:
					topTenCast();
					break;
					
				case 4:
					mostMoviesTogether();
					break;
					
				case 5:
					castList();
					break;
					
				case 6:
					System.out.println("\nThank you for using our database!");
					break;
					
				default:
					System.out.println("\nPlease try again!");
				
			}
		}
	}
}
