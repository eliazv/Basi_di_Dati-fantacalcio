package model;

public class Squadra {
	private String nicknameString;
	private int puntiClassifica ;
	private int partiteGiocate ;
	private int vittorie ;
	private int pareggi ;	
	private int sconfitte ;
	private int puntiPrestazioneTot ;
	
	public String getNicknameString() {
		return nicknameString;
	}
	public void setNicknameString(String nicknameString) {
		this.nicknameString = nicknameString;
	}
	public int getPuntiClassifica() {
		return puntiClassifica;
	}
	public void setPuntiClassifica(int puntiClassifica) {
		this.puntiClassifica = puntiClassifica;
	}
	public int getPartiteGiocate() {
		return partiteGiocate;
	}
	public void setPartiteGiocate(int partiteGiocate) {
		this.partiteGiocate = partiteGiocate;
	}
	public int getVittorie() {
		return vittorie;
	}
	public void setVittorie(int vittorie) {
		this.vittorie = vittorie;
	}
	public int getPareggi() {
		return pareggi;
	}
	public void setPareggi(int pareggi) {
		this.pareggi = pareggi;
	}
	public int getSconfitte() {
		return sconfitte;
	}
	public void setSconfitte(int sconfitte) {
		this.sconfitte = sconfitte;
	}
	public int getPuntiPrestazioneTot() {
		return puntiPrestazioneTot;
	}
	public void setPuntiPrestazioneTot(int puntiPrestazioneTot) {
		this.puntiPrestazioneTot = puntiPrestazioneTot;
	}
}
