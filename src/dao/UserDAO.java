package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import utility.ConnectionManager;

public class UserDAO implements UserDaoInterface {

	@Override
	public int signUp(User user) throws Exception {

		String signUp_sql = "INSERT INTO USERS (email, password) VALUES(?,?)";
		
		int signUp = 0;
		try {
			Connection con = ConnectionManager.getConnection();
			
			PreparedStatement st = con.prepareStatement(signUp_sql);
			st.setString(1, user.getEmail());
			st.setString(2, user.getPassword());
			
			signUp = st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return signUp;
	}

	@Override
	public boolean loginUser(User user) {
		String email = user.getEmail();
		String password = user.getPassword();
		String login_sql = "SELECT email, password FROM USERS";
		
		Connection con;
		Boolean loginStatus = false;
		try {
			con = ConnectionManager.getConnection();
			PreparedStatement st = con.prepareStatement(login_sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				if(email.equals(rs.getString("email"))&& password.equals(rs.getString("password"))) {
					con.close();
					loginStatus = true;
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return loginStatus;
	}
	
}