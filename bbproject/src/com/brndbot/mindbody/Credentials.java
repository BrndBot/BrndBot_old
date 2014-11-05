package com.brndbot.mindbody;

/* Instantiate this for credential resolution and pass to each
 * Mindbody client class during its instantiation
 */
public class Credentials 
{
	private int _user_id;
	private String _mb_name;
	private String _mb_password;
	private int _mb_id;
	
	public int getUserID() {
		return _user_id;
	}

	public void setUserID(int user_id) {
		_user_id = user_id;
	}
	
	public String getMBName() { return _mb_name; }
	public String getMBPassword() { return _mb_password; }
	public int getMBID() { return _mb_id; }
	
	public Credentials(int user_id)
	{
		_mb_name = "GreatPathsInc";
		_mb_password = "DIT8ow/xuG7INUkbAo15eKZRl5A=";
		_mb_id = 7335;
	}
}
