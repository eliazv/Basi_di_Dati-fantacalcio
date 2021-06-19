package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.DBController;
import model.Calciatore;
import model.Conto_Crediti;
import model.Match;
import model.Partecipante;
import model.Prestazione;
import model.Squadra;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;




public class MainView{

	private final int SIZE = 800;
	private final JFrame frame;
	private JTextField txtNickname;
	private JTextField txtCognomeP;
	private JTextField txtMailP;
	private JTextField txtNomeP;
	private JButton btnInsertPartecipante;
	private JPanel panel_Calciatore;
	private JTextField txtIDCalciatoreAcquisto;
	private JLabel lblNewLabel_5;
	private JButton btnViewPartecipante;
	private JTextField txtIDCalciatore;
	private JTextField txtNomeC;
	private JTextField txtCognomeC;
	private JTextField txtRuolo;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JTable tableCalciatore;
	private JTable tablePartecipante;
	private JTextField txtNicknameC;
	private JTextField txtQuotazAcquisto;
	private JTextField txtEta;
	private JTextField txtNazionalita;
	private JTextField txtNicknameConto;
	private JLabel lblConto;
	private JTextField txtCreditiIniziali;
	private JLabel lblNewLabel_11;
	private JTextField txtCasa;
	private JTextField txtTrasferta;
	private JTextField txtIDMatch;
	private JTextField txtNicknameSpec;
	private JTable table_prestazioni;
	private JTextField txtIdmatchPrest;
	private JTextField txtIdcalciatorePrest;
	private JTextField txtPuntiPrestazione;
	private JTextField txtIdcalciatoreAvg;
	private JButton btnAvgPrest;
	private JButton btnVisualizzaSquadra;
	private JTextField txtSquadra;
	private JTextField txtAnno;
	private JTable table_Statistiche;
	private JTable tableAnno;
	private JTextField txtAnno_1;
	private JButton btnAnnoSelect;
	private JTextField txtIDMatchRis;
	private JButton btnVediRisult;
	private JLabel lblRisult;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public MainView() {
		
		DBController controller = new DBController();
		
		new panelGame();
		
		this.frame = new JFrame();
		
		this.frame.setTitle("Fantacalcio");

		this.frame.setSize(this.SIZE, this.SIZE);

		this.frame.setLocationRelativeTo(null);

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.frame.setResizable(false);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_Partecipante = new JPanel();
		tabbedPane.addTab("Partecipante", null, panel_Partecipante, null);
		
		txtNickname = new JTextField();
		txtNickname.setText("Nickname");
		txtNickname.setColumns(10);
		
		txtNomeP = new JTextField();
		txtNomeP.setText("Nome");
		txtNomeP.setToolTipText("");
		txtNomeP.setColumns(10);
		
		txtCognomeP = new JTextField();
		txtCognomeP.setText("Cognome");
		txtCognomeP.setColumns(10);
		
		txtMailP = new JTextField();
		txtMailP.setText("Mail");
		txtMailP.setColumns(10);
		
		btnInsertPartecipante = new JButton("Inserisci");
		btnInsertPartecipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Partecipante par = new Partecipante();
				par.setNickname(txtNickname.getText());
				par.setNome(txtNomeP.getText());
				par.setCognome(txtCognomeP.getText());
				par.setMail(txtMailP.getText());
				
				Conto_Crediti con = new Conto_Crediti();
				con.setCreditiIniziali(Integer.parseInt(txtCreditiIniziali.getText()));
				con.setNickname(txtNickname.getText());
				con.setSaldo(con.getCreditiIniziali());
				controller.insertPartecipante(par, con);
			}
		});
		
		btnViewPartecipante = new JButton("Vedi partecipanti");
		btnViewPartecipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Partecipante> bar = controller.findAllPartecipante();
				DefaultTableModel model = (DefaultTableModel) tablePartecipante.getModel();
				model.setRowCount(0);
				bar.forEach(d ->{
					model.addRow(new Object[] {
							d.getNickname(), d.getNome(), d.getCognome(), d.getMail()
					});
				});
			}
		});
		
		JScrollPane scrollPanePartecipante = new JScrollPane();
		
		txtNicknameConto = new JTextField();
		txtNicknameConto.setText("Nickname");
		txtNicknameConto.setColumns(10);
		
		lblConto = new JLabel("Saldo: 0");
		
		JButton btnVisualizzaConto = new JButton("Visualizza Conto");
		btnVisualizzaConto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int saldo = controller.findByPrimaryKeyConto(txtNicknameConto.getText()).getSaldo();
				lblConto.setText("Saldo: "+saldo);
			}
		});
		
		txtCreditiIniziali = new JTextField();
		txtCreditiIniziali.setText("Crediti Iniziali");
		txtCreditiIniziali.setColumns(10);
		
		lblNewLabel_11 = new JLabel("");
		
		JLabel lblNewLabel_7 = new JLabel("Inserisci Partecipante");
		
		JLabel lblNewLabel_8 = new JLabel("Mostra Conto Crediti Partecipante");
		GroupLayout gl_panel_Partecipante = new GroupLayout(panel_Partecipante);
		gl_panel_Partecipante.setHorizontalGroup(
			gl_panel_Partecipante.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Partecipante.createSequentialGroup()
					.addGroup(gl_panel_Partecipante.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Partecipante.createSequentialGroup()
							.addGroup(gl_panel_Partecipante.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_Partecipante.createSequentialGroup()
									.addGap(10)
									.addGroup(gl_panel_Partecipante.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnInsertPartecipante, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(txtCognomeP, Alignment.LEADING)
										.addComponent(txtCreditiIniziali, Alignment.LEADING)
										.addComponent(txtMailP, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
										.addComponent(txtNomeP, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
										.addComponent(txtNickname, Alignment.LEADING)))
								.addGroup(gl_panel_Partecipante.createSequentialGroup()
									.addContainerGap()
									.addComponent(btnViewPartecipante, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPanePartecipante, GroupLayout.PREFERRED_SIZE, 629, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_Partecipante.createSequentialGroup()
							.addContainerGap()
							.addComponent(txtNicknameConto, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnVisualizzaConto)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblConto, GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE))
						.addGroup(gl_panel_Partecipante.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_7, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
						.addGroup(gl_panel_Partecipante.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_Partecipante.setVerticalGroup(
			gl_panel_Partecipante.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Partecipante.createSequentialGroup()
					.addGroup(gl_panel_Partecipante.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Partecipante.createSequentialGroup()
							.addGap(14)
							.addGroup(gl_panel_Partecipante.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_Partecipante.createSequentialGroup()
									.addGap(23)
									.addComponent(txtNickname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_Partecipante.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_11)
										.addComponent(txtNomeP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtCognomeP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(txtMailP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtCreditiIniziali, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnInsertPartecipante)
									.addGap(40)
									.addComponent(btnViewPartecipante))
								.addGroup(gl_panel_Partecipante.createSequentialGroup()
									.addGap(29)
									.addComponent(scrollPanePartecipante, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_Partecipante.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_7)))
					.addGap(20)
					.addComponent(lblNewLabel_8)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_Partecipante.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConto)
						.addComponent(btnVisualizzaConto)
						.addComponent(txtNicknameConto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(348, Short.MAX_VALUE))
		);
		
		tablePartecipante = new JTable();
		tablePartecipante.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nickname", "Nome", "Cognome", "Mail"
			}
		));
		scrollPanePartecipante.setViewportView(tablePartecipante);
		panel_Partecipante.setLayout(gl_panel_Partecipante);
		
		panel_Calciatore = new JPanel();
		tabbedPane.addTab("Calciatore", null, panel_Calciatore, null);
		
		txtIDCalciatoreAcquisto = new JTextField();
		txtIDCalciatoreAcquisto.setText("IDCalciatore");
		txtIDCalciatoreAcquisto.setColumns(10);
		
		JButton btnAcquistaC = new JButton("Acquista");
		btnAcquistaC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Calciatore par = new Calciatore();
				par.setQuotazioneAcquisto(Integer.parseInt(txtQuotazAcquisto.getText()));
				par.setNickname(txtNicknameC.getText());
				par.setIdcalciatore(txtIDCalciatoreAcquisto.getText());
				
				controller.acquistaCalciatore(par);
			}
		});
		
		JLabel lblInserisciCalciatore = new JLabel("Inserisci Calciatore");
		
		txtIDCalciatore = new JTextField();
		txtIDCalciatore.setText("IDCalciatore");
		txtIDCalciatore.setColumns(10);
		
		txtNomeC = new JTextField();
		txtNomeC.setText("Nome");
		txtNomeC.setColumns(10);
		
		txtCognomeC = new JTextField();
		txtCognomeC.setText("Cognome");
		txtCognomeC.setColumns(10);
		
		txtRuolo = new JTextField();
		txtRuolo.setText("Ruolo");
		txtRuolo.setColumns(10);
		
		JButton btnInserisciCalciatore = new JButton("Inserisci");
		btnInserisciCalciatore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Calciatore par = new Calciatore();
					par.setIdcalciatore(txtIDCalciatore.getText());
					par.setNome(txtNomeC.getText());
					par.setCognome(txtCognomeC.getText());
					par.setRuolo(txtRuolo.getText());
					par.setNazionalita(txtNazionalita.getText());
					par.setEta(Integer.parseInt(txtEta.getText()));
					par.setQuotazioneAcquisto(0);
					par.setNickname("");
					
					controller.insertCalciatore(par);
			}
		});
		
		scrollPane_2 = new JScrollPane();
		
		scrollPane_3 = new JScrollPane();
		
		txtNicknameC = new JTextField();
		txtNicknameC.setText("Nickname");
		txtNicknameC.setColumns(10);
		
		txtQuotazAcquisto = new JTextField();
		txtQuotazAcquisto.setText("Quotazione Acquisto");
		txtQuotazAcquisto.setColumns(10);
		
		JLabel lblInserisciCalciatore_1 = new JLabel("Acquista Calciatore");
		
		txtEta = new JTextField();
		txtEta.setText("Et\u00E0");
		txtEta.setColumns(10);
		
		txtNazionalita = new JTextField();
		txtNazionalita.setText("Nazionalit\u00E0");
		txtNazionalita.setColumns(10);
		
		JButton btnVisualizzaCalciatori = new JButton("Visualizza tutti Calciatori");
		btnVisualizzaCalciatori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Calciatore> cal = controller.findAllCalciatore();
				DefaultTableModel model = (DefaultTableModel) tableCalciatore.getModel();
				model.setRowCount(0);
				cal.forEach(d ->{
					model.addRow(new Object[] {
							d.getIdcalciatore(), d.getNome(), d.getCognome(), d.getRuolo(), d.getNazionalita(), d.getEta(),  d.getQuotazioneAcquisto(), d.getNickname(),
					});
				});
			}
		});
		
		btnVisualizzaSquadra = new JButton("Visualizza squadra");
		btnVisualizzaSquadra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Calciatore> cal = controller.findByPrimaryKeyCalciatore(txtSquadra.getText());
				DefaultTableModel model = (DefaultTableModel) tableCalciatore.getModel();
				model.setRowCount(0);
				cal.forEach(d ->{
					model.addRow(new Object[] {
							d.getIdcalciatore(), d.getNome(), d.getCognome(), d.getRuolo(), d.getNazionalita(), d.getEta(),  d.getQuotazioneAcquisto(), d.getNickname(),
					});
				});
			}
		});
		
		txtSquadra = new JTextField();
		txtSquadra.setText("Nickname");
		txtSquadra.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Vedi Squadra Partecipante");
		GroupLayout gl_panel_Calciatore = new GroupLayout(panel_Calciatore);
		gl_panel_Calciatore.setHorizontalGroup(
			gl_panel_Calciatore.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Calciatore.createSequentialGroup()
					.addGroup(gl_panel_Calciatore.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Calciatore.createSequentialGroup()
							.addGap(203)
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_Calciatore.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_Calciatore.createParallelGroup(Alignment.LEADING)
								.addComponent(lblInserisciCalciatore, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_Calciatore.createParallelGroup(Alignment.LEADING, false)
									.addComponent(txtRuolo)
									.addComponent(txtCognomeC)
									.addComponent(txtNomeC)
									.addComponent(txtIDCalciatore, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
									.addComponent(txtEta)
									.addComponent(txtNazionalita)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_Calciatore.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_Calciatore.createSequentialGroup()
									.addGap(35)
									.addGroup(gl_panel_Calciatore.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnAcquistaC, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(txtQuotazAcquisto)
										.addComponent(txtIDCalciatoreAcquisto)
										.addComponent(txtNicknameC, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
										.addComponent(txtSquadra)
										.addComponent(btnVisualizzaSquadra, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addGroup(gl_panel_Calciatore.createSequentialGroup()
									.addGap(53)
									.addComponent(lblInserisciCalciatore_1, GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)))))
					.addGap(24))
				.addGroup(gl_panel_Calciatore.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnInserisciCalciatore, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
					.addGap(295)
					.addComponent(btnVisualizzaCalciatori, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(233))
				.addGroup(gl_panel_Calciatore.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_Calciatore.setVerticalGroup(
			gl_panel_Calciatore.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Calciatore.createSequentialGroup()
					.addGap(14)
					.addGroup(gl_panel_Calciatore.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInserisciCalciatore)
						.addComponent(lblInserisciCalciatore_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_Calciatore.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtIDCalciatore, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtNicknameC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_Calciatore.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNomeC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtIDCalciatoreAcquisto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_Calciatore.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCognomeC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtQuotazAcquisto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_Calciatore.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtRuolo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAcquistaC))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_Calciatore.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_Calciatore.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNazionalita, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtSquadra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_Calciatore.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnInserisciCalciatore)
						.addComponent(btnVisualizzaCalciatori)
						.addComponent(btnVisualizzaSquadra))
					.addGap(18)
					.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE)
					.addGap(65)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		tableCalciatore = new JTable();
		tableCalciatore.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"IDCalciatore",  "Nome", "Cognome", "Ruolo", "Nazionalita", "Eta", "QuotazioneAcquisto", "Nickname"
			}
		));
		scrollPane_3.setViewportView(tableCalciatore);
		panel_Calciatore.setLayout(gl_panel_Calciatore);
		
		JPanel panel_Match = new JPanel();
		tabbedPane.addTab("Match", null, panel_Match, null);
		
		JButton btnGiocaMatch = new JButton("gioca match");
		btnGiocaMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Match m = new Match();
				m.setIDMatch(txtIDMatch.getText());
				m.setAnnoCorrente(Integer.parseInt(txtAnno.getText()));
				m.setSquadraCasa(txtCasa.getText());
				m.setSquadraTrasferta(txtTrasferta.getText());
				controller.giocaMatch(m);
				
			}
		});
		
		JButton btnPrestInMatch = new JButton("Mostra Prestazioni");
		btnPrestInMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Prestazione> cal = controller.prestazInMatch(txtNicknameSpec.getText());
				DefaultTableModel model = (DefaultTableModel) table_prestazioni.getModel();
				model.setRowCount(0);
				cal.forEach(d ->{
					model.addRow(new Object[] {
							d.getIdcalciatore(), d.getPuntiValutazione(), d.getIdmatch()
					});
				});
			}
		});
		
		txtCasa = new JTextField();
		txtCasa.setText("Nick in Casa");
		txtCasa.setColumns(10);
		
		txtTrasferta = new JTextField();
		txtTrasferta.setText("Nick in Trasferta");
		txtTrasferta.setColumns(10);
		
		txtIDMatch = new JTextField();
		txtIDMatch.setText("IDMatch");
		txtIDMatch.setColumns(10);
		
		JLabel lblNewLabel_15 = new JLabel("Gioca Match");
		
		txtNicknameSpec = new JTextField();
		txtNicknameSpec.setText("Nickname");
		txtNicknameSpec.setColumns(10);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		
		txtIdmatchPrest = new JTextField();
		txtIdmatchPrest.setText("IDMatch");
		txtIdmatchPrest.setColumns(10);
		
		txtIdcalciatorePrest = new JTextField();
		txtIdcalciatorePrest.setText("IDCalciatore");
		txtIdcalciatorePrest.setColumns(10);
		
		txtPuntiPrestazione = new JTextField();
		txtPuntiPrestazione.setText("Punti Prestazione");
		txtPuntiPrestazione.setColumns(10);
		
		JButton btnAddPrestazione = new JButton("Aggiungi prestazione");
		btnAddPrestazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Prestazione par = new Prestazione();
				par.setIdcalciatore(txtIdcalciatorePrest.getText());
				par.setIdmatch(txtIdmatchPrest.getText());
				par.setPuntiValutazione(Integer.parseInt(txtPuntiPrestazione.getText()));
				
				controller.insertPrestazione(par);
			}
		});
		JLabel lblAvgPrest = new JLabel("Media: 0");
		txtIdcalciatoreAvg = new JTextField();
		txtIdcalciatoreAvg.setText("IDCalciatore");
		txtIdcalciatoreAvg.setColumns(10);
		btnAvgPrest = new JButton("Vedi media");
		btnAvgPrest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int prest = controller.findByPrimaryKeyPrestazione(txtIdcalciatoreAvg.getText()).getPuntiValutazione();
				lblAvgPrest.setText("Media: "+prest);				
			}
		});
		
		txtAnno = new JTextField();
		txtAnno.setText("Anno");
		txtAnno.setColumns(10);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		
		txtAnno_1 = new JTextField();
		txtAnno_1.setText("Anno");
		txtAnno_1.setColumns(10);
		
		btnAnnoSelect = new JButton("Vedi Match anno");
		btnAnnoSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Match> cal = controller.findByPrimaryKeyCampionato(txtAnno_1.getText());
				DefaultTableModel model = (DefaultTableModel) tableAnno.getModel();
				model.setRowCount(0);
				cal.forEach(d ->{
					model.addRow(new Object[] {
							d.getAnnoCorrente(), d.getIDMatch()
					});
				});
			}
		});
		
		
		
		txtIDMatchRis = new JTextField();
		txtIDMatchRis.setText("IDMatch");
		txtIDMatchRis.setColumns(10);
		
		lblRisult = new JLabel("risultato");
		btnVediRisult = new JButton("mostra risultato");
		btnVediRisult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Match prest = controller.findByPrimaryKeyMatch(txtIDMatchRis.getText());
				lblRisult.setText(prest.getSquadraCasa()+ " " + prest.getPuntiValutazioneCasa() + " - "+ prest.getPuntiValutazioneTrasferta()+ " " + prest.getSquadraTrasferta());	
			}
		});
		
		JLabel lblNewLabel = new JLabel("Aggiongi prestazione calciatore");
		
		JLabel lblNewLabel_1 = new JLabel("Mostra Media Calciatore");
		
		JLabel lblNewLabel_2 = new JLabel("Mostra Risultato Match");
		
		JLabel lblNewLabel_3 = new JLabel("Mostra prestazioni dei calciatori di un partecipante");
		
		JLabel lblNewLabel_4 = new JLabel("Mostra Match di uno specifico anno");
		
		
		
		GroupLayout gl_panel_Match = new GroupLayout(panel_Match);
		gl_panel_Match.setHorizontalGroup(
			gl_panel_Match.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Match.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_Match.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_Match.createSequentialGroup()
							.addGroup(gl_panel_Match.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_Match.createSequentialGroup()
									.addGroup(gl_panel_Match.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_Match.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(btnGiocaMatch, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(txtTrasferta, Alignment.LEADING)
											.addComponent(txtIDMatch, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
											.addComponent(txtAnno)
											.addComponent(txtCasa, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_Match.createSequentialGroup()
											.addGroup(gl_panel_Match.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel_Match.createSequentialGroup()
													.addGap(141)
													.addGroup(gl_panel_Match.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(btnAddPrestazione, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(txtPuntiPrestazione, Alignment.LEADING)
														.addComponent(txtIdcalciatorePrest, Alignment.LEADING)
														.addComponent(txtIdmatchPrest, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
														.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
												.addComponent(lblNewLabel_15, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_panel_Match.createParallelGroup(Alignment.LEADING, false)
												.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblAvgPrest, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnAvgPrest, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
												.addComponent(txtIdcalciatoreAvg))))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_Match.createParallelGroup(Alignment.LEADING)
										.addComponent(lblRisult, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnVediRisult, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel_Match.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(txtIDMatchRis, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))))
								.addGroup(gl_panel_Match.createSequentialGroup()
									.addGroup(gl_panel_Match.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel_Match.createSequentialGroup()
											.addGap(276)
											.addComponent(btnPrestInMatch, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
									.addGroup(gl_panel_Match.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_Match.createSequentialGroup()
											.addGap(139)
											.addComponent(btnAnnoSelect))
										.addGroup(gl_panel_Match.createSequentialGroup()
											.addGap(35)
											.addGroup(gl_panel_Match.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
												.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE))))))
							.addGap(36))
						.addGroup(Alignment.TRAILING, gl_panel_Match.createSequentialGroup()
							.addGap(139)
							.addComponent(txtNicknameSpec, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
							.addGap(200)
							.addComponent(txtAnno_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(227))
						.addGroup(gl_panel_Match.createSequentialGroup()
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_panel_Match.setVerticalGroup(
			gl_panel_Match.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Match.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel_Match.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_15)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2))
					.addGap(12)
					.addGroup(gl_panel_Match.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtIDMatch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtIdmatchPrest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtIdcalciatoreAvg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtIDMatchRis, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_Match.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCasa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtIdcalciatorePrest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAvgPrest)
						.addComponent(lblRisult))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_Match.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtTrasferta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPuntiPrestazione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_Match.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtAnno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAvgPrest)
						.addComponent(btnVediRisult))
					.addGap(13)
					.addGroup(gl_panel_Match.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGiocaMatch)
						.addComponent(btnAddPrestazione))
					.addGap(26)
					.addGroup(gl_panel_Match.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_4))
					.addGap(3)
					.addGroup(gl_panel_Match.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNicknameSpec, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAnno_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPrestInMatch)
						.addComponent(btnAnnoSelect))
					.addGap(7)
					.addGroup(gl_panel_Match.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(233, Short.MAX_VALUE))
		);
		
		tableAnno = new JTable();
		tableAnno.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"AnnoCorrente", "IDMatch"
			}
		));
		scrollPane_4.setViewportView(tableAnno);
		
		table_prestazioni = new JTable();
		table_prestazioni.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"IDCalciatore", "PuntiValutazione", "IDMatch"
			}
		));
		scrollPane.setViewportView(table_prestazioni);
		panel_Match.setLayout(gl_panel_Match);
		
		JPanel panel_Statistiche = new JPanel();
		tabbedPane.addTab("Statistiche", null, panel_Statistiche, null);
		
		JButton btnClassifica = new JButton("Mostra classifica");
		btnClassifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Squadra> cal = controller.showClassifica();
				DefaultTableModel model = (DefaultTableModel) table_Statistiche.getModel();
				model.setRowCount(0);
				cal.forEach(d ->{
					model.addRow(new Object[] {
							d.getNicknameString(), d.getPuntiClassifica(), d.getPuntiClassifica(), d.getVittorie(), d.getPareggi(), d.getSconfitte(),  d.getPuntiPrestazioneTot()
					});
				});
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_Statistiche = new GroupLayout(panel_Statistiche);
		gl_panel_Statistiche.setHorizontalGroup(
			gl_panel_Statistiche.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Statistiche.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_Statistiche.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 769, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnClassifica))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_Statistiche.setVerticalGroup(
			gl_panel_Statistiche.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Statistiche.createSequentialGroup()
					.addGap(25)
					.addComponent(btnClassifica)
					.addGap(55)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(221, Short.MAX_VALUE))
		);
		
		table_Statistiche = new JTable();
		table_Statistiche.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nickname", "PuntiClassifica", "PartiteGiocate", "Vittorie", "Pareggi", "Sconfitte", "PuntiPrestazioneTot"
			}
		));
		scrollPane_1.setViewportView(table_Statistiche);
		panel_Statistiche.setLayout(gl_panel_Statistiche);
		
		lblNewLabel_5 = new JLabel("Fantacalcio");
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 34));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel_5, BorderLayout.NORTH);

		this.frame.setVisible(true);
		
	}

	
	
	public class panelGame extends JPanel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;


	}
}
