package model;

public class Conto_Crediti {
	private String nickname;
    private int saldo;
    private int creditiIniziali;
    
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSaldo() {
		return saldo;
	}
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	public int getCreditiIniziali() {
		return creditiIniziali;
	}
	public void setCreditiIniziali(int creditiIniziali) {
		this.creditiIniziali = creditiIniziali;
	}
}
