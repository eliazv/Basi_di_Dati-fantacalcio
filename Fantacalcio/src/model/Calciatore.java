package model;

public class Calciatore {
	private String idcalciatore;
    private String nome;
    private String cognome;
    private String ruolo;
    private int eta;
    private String nazionalita;
    private int quotazioneAcquisto;
    private String nickname;
    
	public String getIdcalciatore() {
		return idcalciatore;
	}
	public void setIdcalciatore(String idcalciatore) {
		this.idcalciatore = idcalciatore;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public int getEta() {
		return eta;
	}
	public void setEta(int eta) {
		this.eta = eta;
	}
	public String getNazionalita() {
		return nazionalita;
	}
	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}
	public int getQuotazioneAcquisto() {
		return quotazioneAcquisto;
	}
	public void setQuotazioneAcquisto(int quotazioneAcquisto) {
		this.quotazioneAcquisto = quotazioneAcquisto;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
