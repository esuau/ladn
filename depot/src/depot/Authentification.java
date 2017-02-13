public class Authentification {
	private String login, password;
	
	public Authentification(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public setLogin(String login) {
		this.login = login;
	}
	
	public setPassword(String password) {
		this.password = password;
	}
	
	public getLogin() {
		return this.login;
	}
	
	public getPassword() {
		return this.password;
	}
}