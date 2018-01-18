package bdd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.ArrayList;

import fgopkg.Servant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import fgopkg.SaveState;

public class BDDActions {
	
	private int playerID;
	private String servant;
	private int level;
	private int NP;
	private int S1;
	private int S2;
	private int S3;
	
	public BDDActions() {}

	private Connection connect() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = DriverManager.getConnection("jdbc:mysql://mysql-oyashiro.alwaysdata.net/oyashiro_inscription", "oyashiro", "thepassword"); //mysql-oyashiro.alwaysdata.net
		return con;		
	}
	
	private void disconnect(Connection con) throws SQLException {
		con.close();
	}
	
	private void displayJPRows (Connection con) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("SELECT * FROM JPServant");
		ResultSet result = ps.executeQuery();
					
		while(result.next()) { 
			System.out.println(result.getInt("ID") + " : " + result.getString("SERVANT") + " Level " + result.getInt("LEVEL") + " NP" + result.getInt("NP") +
					"\n Skill 1 : " + result.getInt("S1") + " Skill 2 : " + result.getInt("S2") + "  Skill 3 : " + result.getInt("S3"));
		}
	}
	
	private Boolean checkData (Connection con) throws SQLException {
		Boolean isIn = false;
		
		PreparedStatement ps = con.prepareStatement("SELECT SERVANT FROM JPServant WHERE ID = ?");
		ps.setInt(1, playerID);
		ResultSet result = ps.executeQuery();
		
		while(result.next()) { 
			if (this.servant.equals(result.getString("SERVANT"))) {
				isIn = true;
				break;
			}
		}
		
		return isIn;
	}
	
	private void updateDataJP (Connection con) throws SQLException {
		PreparedStatement ps = con.prepareStatement("UPDATE JPServant SET LEVEL = ?, NP = ?, S1 = ?, S2 = ?, S3 = ? WHERE ID = ? AND SERVANT = ?");
		ps.setInt(6, playerID);
		ps.setString(7, servant);
		ps.setInt(1, level);
		ps.setInt(2, NP);
		ps.setInt(3, S1);
		ps.setInt(4, S2);
		ps.setInt(5, S3);
		ps.executeUpdate();
		
	}
	
	private void addDataJP (Connection con) throws SQLException {
		
		if (this.checkData(con)) { 
			//System.out.println("Already in DB - Updating data");
			updateDataJP(con);
			return;
		}
		
		PreparedStatement ps = con.prepareStatement("INSERT INTO JPServant(ID, SERVANT, LEVEL, NP, S1, S2, S3) VALUES (?,?,?,?,?,?,?)"); 
		ps.setInt(1, playerID);
		ps.setString(2, servant);
		ps.setInt(3, level);
		ps.setInt(4, NP);
		ps.setInt(5, S1);
		ps.setInt(6, S2);
		ps.setInt(7, S3);
		ps.executeUpdate();
		
	}
	
	private void sendAllJP (Connection con, int ID, File servantList) throws SQLException, FileNotFoundException, IOException, ClassNotFoundException {
		this.playerID = ID;
		
		ObjectInputStream ois = new ObjectInputStream (new FileInputStream (servantList));
	    SaveState s = (SaveState) ois.readObject();
	    ArrayList<Servant> servList = new ArrayList<Servant>();
	    servList.addAll(s.getData());
		
		for (int i = 0; i < servList.size(); ++i) {
			servant = servList.get(i).getName();
			level = servList.get(i).getLvl();
			NP = servList.get(i).getNp();
			S1 = servList.get(i).getS1lvl();
			S2 = servList.get(i).getS2lvl();
			S3 = servList.get(i).getS3lvl();
			addDataJP(con);
		}
	}
	
	private boolean addAccount (Connection con, int ID, String pass) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("INSERT INTO JPAccount(ID, PASS) VALUES (?,?)");
		ps.setInt(1, ID);
		ps.setString(2, pass);
		ps.executeUpdate();
		
		return true;
	}
	
private boolean checkPass (Connection con, int ID, String pass) throws SQLException {
        
        PreparedStatement ps = con.prepareStatement("SELECT PASS FROM JPAccount WHERE ID = ?");
        ps.setInt(1, ID);        
        ResultSet result = ps.executeQuery();
        
        
        if (!result.next()) return addAccount(con, ID, pass);
        if (pass.equals(result.getString("PASS"))) return true;
        return false;
    }
	
	public boolean SendBD (int ID, File servList, String pass) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, FileNotFoundException, IOException {
		//System.out.println("Connexion en cours...");
		Connection con = this.connect();
		//System.out.println("Connecté !");
		
		if (!checkPass(con, ID, pass)) return false;
		
		sendAllJP(con, ID, servList);
		
		//System.out.println("Déconnexion en cours...");
		this.disconnect(con);
		//System.out.println("Déonnecté !");
		
		return true;
	}
	
	private ObservableList<Servant> receiveJP (Connection con, int ID) throws SQLException {
		ObservableList<Servant> servList =  FXCollections.observableArrayList();
		
		PreparedStatement ps = con.prepareStatement("SELECT * FROM JPServant WHERE ID = ?");
		ps.setInt(1, ID);
		ResultSet result = ps.executeQuery();
		
		while (result.next()) {
			Servant line = new Servant ();
			line.setName(result.getString("SERVANT"));
			line.setLvl(result.getInt("LEVEL"));
			line.setNp(result.getInt("NP"));
			line.setS1lvl((result.getInt("S1")));
			line.setS2lvl((result.getInt("S2")));
			line.setS3lvl((result.getInt("S3")));
			
			servList.add(line);
		}
		return servList;
		
	}
	
	public ObservableList<Servant> GetDB (int ID) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Connection con = this.connect();
		ObservableList<Servant> servList =  receiveJP(con, ID);		
		this.disconnect(con);
		return servList;
	}
	
	public int getPlayerID() {
		return playerID;
	}
	
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	
	public String getServant() {
		return servant;
	}
	
	public void setServant(String servant) {
		this.servant = servant;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getNP() {
		return NP;
	}
	
	public void setNP(int nP) {
		NP = nP;
	}
	
	public int getS1() {
		return S1;
	}

	public void setS1(int s1) {
		S1 = s1;
	}

	public int getS2() {
		return S2;
	}

	public void setS2(int s2) {
		S2 = s2;
	}

	public int getS3() {
		return S3;
	}

	public void setS3(int s3) {
		S3 = s3;
	}
	
}
