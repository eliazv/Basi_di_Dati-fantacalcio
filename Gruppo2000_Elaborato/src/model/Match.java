package model;

public class Match {
	private String IDMatch;
	private String squadraCasa;
	private String squadraTrasferta;
	private int AnnoCorrente;
	private int puntiValutazioneCasa;
	private int puntiValutazioneTrasferta;
	
	public String getIDMatch() {
		return IDMatch;
	}
	public void setIDMatch(String iDMatch) {
		IDMatch = iDMatch;
	}
	public String getSquadraCasa() {
		return squadraCasa;
	}
	public void setSquadraCasa(String squadraCasa) {
		this.squadraCasa = squadraCasa;
	}
	public String getSquadraTrasferta() {
		return squadraTrasferta;
	}
	public void setSquadraTrasferta(String squadraTrasferta) {
		this.squadraTrasferta = squadraTrasferta;
	}
	public int getAnnoCorrente() {
		return AnnoCorrente;
	}
	public void setAnnoCorrente(int annoCorrente) {
		AnnoCorrente = annoCorrente;
	}
	public int getPuntiValutazioneCasa() {
		return puntiValutazioneCasa;
	}
	public void setPuntiValutazioneCasa(int puntiValutazioneCasa) {
		this.puntiValutazioneCasa = puntiValutazioneCasa;
	}
	public int getPuntiValutazioneTrasferta() {
		return puntiValutazioneTrasferta;
	}
	public void setPuntiValutazioneTrasferta(int puntiValutazioneTrasferta) {
		this.puntiValutazioneTrasferta = puntiValutazioneTrasferta;
	}
}
