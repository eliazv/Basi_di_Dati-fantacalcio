package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import db.DBConnection;
import model.Calciatore;
import model.Conto_Crediti;
import model.Match;
import model.Partecipante;
import model.Prestazione;
import model.Squadra;


public class DBController {
	 private DBConnection dataSource;
	    private String tableName;
	    
	    public DBController() {
	        dataSource = new DBConnection();
	        tableName="Partecipante";
		}      
	    
	    public void dropAndCreateTable() {
		    Connection connection = this.dataSource.getMySQLConnection();
		    Statement statement = null;
		    try {
		        statement = connection.createStatement ();
		        try{
		            statement.executeUpdate ("DROP TABLE " + tableName);
		        }
		        catch (SQLException e) {
		            // the table does not exist
		        }
		        statement.executeUpdate (
		            "CREATE TABLE "+ tableName +" ("
		                + "Nickname INT NOT NULL PRIMARY KEY,"
		                + "Nome VARCHAR(40), " 
		                + "Cognome VARCHAR(40), "
		                + "Mail VARCHAR(40) UNIQUE " 
		            + ") "
		        );
		        statement.close ();
		    }
		    catch (SQLException e) {
		    	 new Exception(e.getMessage());
		         System.out.println("Errore"+ e.getMessage());
		    }
		    finally {
		        try {
		            if (statement != null) 
		                statement.close();
		            if (connection!= null)
		                connection.close();
		        }
		        catch (SQLException e) {
		        	 new Exception(e.getMessage());
		             System.out.println("Errore"+ e.getMessage());
		        }
		    }
		}
	    

	    
	    public void persist(Partecipante Partecipante) {
	        Connection connection = this.dataSource.getMySQLConnection();

	        if (findByPrimaryKeyPartecipante(Partecipante.getNickname())!=null){
	        	 new Exception("Partecipante exists");
	             System.out.println("Errore"+ "Partecipante exists");
	        } 
	           
	                
	        PreparedStatement statement = null; 
	        String insert = "insert into "+ tableName +" (Nickname, Nome, Cognome, Mail) values (?,?,?,?)";
	        try {
	            statement = connection.prepareStatement(insert);
	            statement.setString(1, Partecipante.getNickname());
	            statement.setString(2, Partecipante.getNome());
	            statement.setString(3, Partecipante.getCognome());
	            statement.setString(4, Partecipante.getMail());
	            statement.executeUpdate();
	        }
	        catch (SQLException e) {
	           	new Exception(e.getMessage());
	            System.out.println("Errore"+ e.getMessage());
	        }
	        finally {
	            try {
	                if (statement != null) 
	                    statement.close();
	                if (connection!= null)
	                    connection.close();
	            }
	            catch (SQLException e) {
	            	new Exception(e.getMessage());
		            System.out.println("Errore"+ e.getMessage());
	            }
	        }
	    }
	    
	    
	  //questo è l'esercizio
	    public void update(Partecipante Partecipante) {
	     
	   
	    }
	//fine esercizio
	    
	    public void deletePartecipante(Partecipante Partecipante) {
	        Connection connection = this.dataSource.getMySQLConnection();

	        PreparedStatement statement = null;
	        String insert = "delete from "+ tableName +" where Nickname = ?";
	        try {
	            statement = connection.prepareStatement(insert);
	            statement.setString(1, Partecipante.getNickname());
	            statement.executeUpdate();
	        }
	        catch (SQLException e) {
	        	new Exception(e.getMessage());
	            System.out.println("Errore"+ e.getMessage());
	        }
	        finally {
	            try {
	                if (statement != null) 
	                    statement.close();
	                if (connection!= null)
	                    connection.close();
	            }
	            catch (SQLException e) {
	            	new Exception(e.getMessage());
		            System.out.println("Errore"+ e.getMessage());
	            }
	        }
	    }

	    public Partecipante findByPrimaryKeyPartecipante(String string)  {
	        Partecipante Partecipante = null;
	        
	        Connection connection = this.dataSource.getMySQLConnection();
	        PreparedStatement statement = null;
	        String query = "select * from "+ tableName +" where Nickname=?";
	        try {
	            statement = connection.prepareStatement(query);
	            statement.setString(1, string);
	            ResultSet result = statement.executeQuery();
	            if (result.next()) {
	                Partecipante = new Partecipante();
	                Partecipante.setNickname(result.getString("Nickname"));
	                Partecipante.setNome(result.getString("Nome"));
	                Partecipante.setCognome(result.getString("Cognome"));
	                Partecipante.setMail(result.getString("Mail"));
	                }
	        }
	        catch (SQLException e) {
	        	new Exception(e.getMessage());
	            System.out.println("Errore"+ e.getMessage());
	        }
	        finally {
	            try {
	                if (statement != null) 
	                    statement.close();
	                if (connection!= null)
	                    connection.close();
	            } catch (SQLException e) {
	            	new Exception(e.getMessage());
		             System.out.println("Errore"+ e.getMessage());
	            }
	        }
	        return Partecipante;
	    }   
	    
	    
	    
	    
	    
	    public List<Partecipante> findAllPartecipante()  {
	        List<Partecipante> partecipante = null;
	        Partecipante Partecipante = null;
	        Connection connection = this.dataSource.getMySQLConnection();
	        PreparedStatement statement = null;
	        String query = "select * from "+ tableName ;
	        try {
	            statement = connection.prepareStatement(query);
	            ResultSet result = statement.executeQuery();
	            if(result.next()) {
	                partecipante = new LinkedList<Partecipante>();
	                Partecipante = new Partecipante();
	                Partecipante.setNickname(result.getString("Nickname"));
	                Partecipante.setNome(result.getString("Nome"));
	                Partecipante.setCognome(result.getString("Cognome"));
	                Partecipante.setMail(result.getString("Mail"));
	                partecipante.add(Partecipante);
	            }
	            while(result.next()) {
	                Partecipante = new Partecipante();
	                Partecipante.setNickname(result.getString("Nickname"));
	                Partecipante.setNome(result.getString("Nome"));
	                Partecipante.setCognome(result.getString("Cognome"));
	                Partecipante.setMail(result.getString("Mail"));
	                partecipante.add(Partecipante);
	            }
	        }
	        catch (SQLException e) {
	        	new Exception(e.getMessage());
	            System.out.println("Errore view partec "+ e.getMessage());
	        }
	        finally {
	            try {
	                if (statement != null) 
	                    statement.close();
	                if (connection!= null)
	                    connection.close();
	            } catch (SQLException e) {
	            	new Exception(e.getMessage());
		             System.out.println("Errore view partec2 "+ e.getMessage());
	            }
	        }
	        return partecipante;
	    }   
	    
	    
	    public void insertPartecipante(Partecipante partecipante, Conto_Crediti conto) {
	        Connection connection = this.dataSource.getMySQLConnection();
	      
	         PreparedStatement statement = null; 
	         PreparedStatement statement2 = null;
	         PreparedStatement statement3 = null;
	         String insert = "insert into partecipante (Nickname, Nome, Cognome, Mail) values (?,?,?,?)";
	         String insert2 = "insert into conto_crediti (Nickname, Saldo, creditiIniziali) values (?,?,?)";
	         String insert3 = "insert into squadra(nickname, punticlassifica, partitegiocate, vittorie, pareggi, sconfitte, puntiPrestazionetot) "
	         		+ "values(?, 0,0,0,0,0,0)";
	         try {
	               	
	             statement = connection.prepareStatement(insert);
	             
	             statement.setString(1, partecipante.getNickname());
	             statement.setString(2, partecipante.getNome());
	             statement.setString(3, partecipante.getCognome());
	             statement.setString(4, partecipante.getMail());

	             statement.executeUpdate();
	             
	             statement2 = connection.prepareStatement(insert2);
	             
	             statement2.setString(1, conto.getNickname());
	             statement2.setInt(2, conto.getSaldo());
	             statement2.setInt(3, conto.getCreditiIniziali());

	             statement2.executeUpdate();
	             
	             
	             statement3 = connection.prepareStatement(insert3);
	             
	             statement3.setString(1, partecipante.getNickname());

	             statement3.executeUpdate();
	             
	         }
	         catch (SQLException e) {
	            	new Exception(e.getMessage());
	             System.out.println("Errore ins partec"+ e.getMessage());
	         }
	         finally {
	             try {
	                 if (statement != null) 
	                     statement.close();
	                 if (connection!= null)
	                     connection.close();
	             }
	             catch (SQLException e) {
	             	new Exception(e.getMessage());
	 	            System.out.println("Errore ins partec2"+ e.getMessage());
	             }
	         }
	     }

	    //rifare con la label forse
	    public Conto_Crediti findByPrimaryKeyConto(String string)  {
	        Conto_Crediti conto = null;
	        
	        Connection connection = this.dataSource.getMySQLConnection();
	        PreparedStatement statement = null;
	        String query = "select * from Conto_Crediti where Nickname=?";
	        try {
	            statement = connection.prepareStatement(query);
	            statement.setString(1, string);
	            ResultSet result = statement.executeQuery();
	            if (result.next()) {
	                conto = new Conto_Crediti();
	                conto.setNickname(result.getString("Nickname"));
	                conto.setSaldo(result.getInt("Saldo"));
	                conto.setCreditiIniziali(result.getInt("CreditiIniziali"));
	                }
	        }
	        catch (SQLException e) {
	        	new Exception(e.getMessage());
	            System.out.println("Errore crediti"+ e.getMessage());
	        }
	        finally {
	            try {
	                if (statement != null) 
	                    statement.close();
	                if (connection!= null)
	                    connection.close();
	            } catch (SQLException e) {
	            	new Exception(e.getMessage());
		             System.out.println("Errore crediti2"+ e.getMessage());
	            }
	        }
	        return conto;
	    }   
	    
	    
	    public List<Calciatore> findAllCalciatore()  {
	        List<Calciatore> listcal = null;
	        Calciatore cal = null;
	        Connection connection = this.dataSource.getMySQLConnection();
	        PreparedStatement statement = null;
	        String query = "select * from Calciatore" ;
	        try {
	            statement = connection.prepareStatement(query);
	            ResultSet result = statement.executeQuery();
	            if(result.next()) {
	                listcal = new LinkedList<Calciatore>();
	                cal = new Calciatore();
	                cal.setIdcalciatore(result.getString("IDCalciatore"));
	                cal.setNome(result.getString("Nome"));
	                cal.setCognome(result.getString("Cognome"));
	                cal.setRuolo(result.getString("Ruolo"));
	                cal.setNazionalita(result.getString("Nazionalita"));
	                cal.setEta(result.getInt("Eta"));
	                cal.setQuotazioneAcquisto(result.getInt("QuotazioneAcquisto"));
	                cal.setNickname(result.getString("Nickname"));
	                listcal.add(cal);
	            }
	            while(result.next()) {
	                cal = new Calciatore();
	                cal.setIdcalciatore(result.getString("IDCalciatore"));
	                cal.setNome(result.getString("Nome"));
	                cal.setCognome(result.getString("Cognome"));
	                cal.setRuolo(result.getString("Ruolo"));
	                cal.setNazionalita(result.getString("Nazionalita"));
	                cal.setEta(result.getInt("Eta"));
	                cal.setQuotazioneAcquisto(result.getInt("QuotazioneAcquisto"));
	                cal.setNickname(result.getString("Nickname"));
	                listcal.add(cal);
	            }
	        }
	        catch (SQLException e) {
	        	new Exception(e.getMessage());
	            System.out.println("Error view calc"+ e.getMessage());
	        }
	        finally {
	            try {
	                if (statement != null) 
	                    statement.close();
	                if (connection!= null)
	                    connection.close();
	            } catch (SQLException e) {
	            	new Exception(e.getMessage());
		             System.out.println("Errore view calc 2"+ e.getMessage());
	            }
	        }
	        return listcal;
	    }   
	    
	    
	    
	    public void insertCalciatore(Calciatore calciatore) {
	        Connection connection = this.dataSource.getMySQLConnection();
	      
	         PreparedStatement statement = null; 
	         String insert = "insert into calciatore (IDCalciatore, Nome, Cognome, Ruolo, Nazionalita, Eta, QuotazioneAcquisto, Nickname) values (?,?,?,?,?,?,?,?)";
	         try {
	               	
	             statement = connection.prepareStatement(insert);
	             
	             statement.setString(1, calciatore.getIdcalciatore());
	             statement.setString(2, calciatore.getNome());
	             statement.setString(3, calciatore.getCognome());
	             statement.setString(4, calciatore.getRuolo());
	             statement.setString(5, calciatore.getNazionalita());
	             statement.setInt(6, calciatore.getEta());
	             statement.setInt(7, calciatore.getQuotazioneAcquisto());
	             statement.setString(8,  calciatore.getNickname());
	             
	             statement.executeUpdate();

	         }
	         catch (SQLException e) {
	            	new Exception(e.getMessage());
	             System.out.println("Errore ins calc:"+ e.getMessage());
	         }
	         finally {
	             try {
	                 if (statement != null) 
	                     statement.close();
	                 if (connection!= null)
	                     connection.close();
	             }
	             catch (SQLException e) {
	             	new Exception(e.getMessage());
	 	            System.out.println("Error ins calc2"+ e.getMessage());
	             }
	         }
	     }
	    
	    public void acquistaCalciatore(Calciatore calciatore) {
	        Connection connection = this.dataSource.getMySQLConnection();
	       
	         PreparedStatement statement = null; 
	         PreparedStatement statement2 = null;
	         String update = "UPDATE Calciatore SET quotazioneAcquisto = ?, nickname = ? where IDCalciatore = ?";
	         String update2 = "UPDATE Conto_Crediti SET saldo = saldo - ? Where nickname = ?"; 
	         try {
	        	
	             statement = connection.prepareStatement(update);
	             
	             statement.setInt(1, calciatore.getQuotazioneAcquisto());
	             statement.setString(2, calciatore.getNickname());
	             statement.setString(3, calciatore.getIdcalciatore());
	             
	             statement.executeUpdate();
	             
	             
	             statement2 = connection.prepareStatement(update2);
	             
	             statement2.setInt(1, calciatore.getQuotazioneAcquisto());
	             statement2.setString(2, calciatore.getNickname());
	             
	             statement2.executeUpdate();	             

	         }
	         catch (SQLException e) {
	            	new Exception(e.getMessage());
	             System.out.println("Error acq1: "+ e.getMessage());
	         }
	         finally {
	             try {
	                 if (statement != null) 
	                     statement.close();
	                 if (connection!= null)
	                     connection.close();
	             }
	             catch (SQLException e) {
	             	new Exception(e.getMessage());
	 	            System.out.println("Error acq2:"+ e.getMessage());
	             }
	         }
	     }
	    
	    public void insertPrestazione(Prestazione prestazione) {
	        Connection connection = this.dataSource.getMySQLConnection();
	      
	         PreparedStatement statement = null; 
	         String insert = "INSERT INTO Prestazione(IDCalciatore, IDMatch, puntivalutazione) values (?,?,?)";
	         try {
	               	
	             statement = connection.prepareStatement(insert);
	             
	             statement.setString(1, prestazione.getIdcalciatore());
	             statement.setString(2, prestazione.getIdmatch());
	             statement.setInt(3, prestazione.getPuntiValutazione());
	           
	             statement.executeUpdate();

	         }
	         catch (SQLException e) {
	            	new Exception(e.getMessage());
	             System.out.println("Errore ins calc:"+ e.getMessage());
	         }
	         finally {
	             try {
	                 if (statement != null) 
	                     statement.close();
	                 if (connection!= null)
	                     connection.close();
	             }
	             catch (SQLException e) {
	             	new Exception(e.getMessage());
	 	            System.out.println("Error ins calc2"+ e.getMessage());
	             }
	         }
	     }
	    public Prestazione findByPrimaryKeyPrestazione(String string)  {
	    	Prestazione Prestazione = null;
	        
	        Connection connection = this.dataSource.getMySQLConnection();
	        PreparedStatement statement = null;
	        String query = "select AVG(puntiValutazione) AS media from prestazione where IDCalciatore = ?";
	        try {
	            statement = connection.prepareStatement(query);
	            statement.setString(1, string);
	            ResultSet result = statement.executeQuery();
	            if (result.next()) {
	            	Prestazione = new Prestazione();
	            	//Prestazione.setIdcalciatore(result.getString("IDCalciatore"));
	            	//Prestazione.setIdmatch(result.getString("IDMatch"));
	            	Prestazione.setPuntiValutazione(result.getInt("media"));
	                }
	        }
	        catch (SQLException e) {
	        	new Exception(e.getMessage());
	            System.out.println("Errore"+ e.getMessage());
	        }
	        finally {
	            try {
	                if (statement != null) 
	                    statement.close();
	                if (connection!= null)
	                    connection.close();
	            } catch (SQLException e) {
	            	new Exception(e.getMessage());
		             System.out.println("Errore"+ e.getMessage());
	            }
	        }
	        return Prestazione;
	    }   
	    
	    
	    public List<Calciatore> findByPrimaryKeyCalciatore(String string)  {
	    	Calciatore Calciatore= null;
	    	List<Calciatore> calciatori = null;
	        Connection connection = this.dataSource.getMySQLConnection();
	        PreparedStatement statement = null;
	        String query = "select * from Calciatore where Nickname=?";
	        try {
	            statement = connection.prepareStatement(query);
	            statement.setString(1, string);
	            ResultSet result = statement.executeQuery();
	            if (result.next()) {
	            	calciatori = new LinkedList<Calciatore>();
	            	Calciatore = new Calciatore();
	            	Calciatore.setIdcalciatore(result.getString("IDCalciatore"));
	            	Calciatore.setCognome(result.getString("Cognome"));
	            	Calciatore.setNome(result.getString("Nome"));
	            	Calciatore.setEta(result.getInt("eta"));
	            	Calciatore.setRuolo(result.getString("Ruolo"));
	            	Calciatore.setNazionalita(result.getString("Nazionalita"));
	            	Calciatore.setQuotazioneAcquisto(result.getInt("quotazioneAcquisto"));
	            	Calciatore.setNickname(result.getString("Nickname"));
	            	calciatori.add(Calciatore);
	            }
	            while(result.next()) {
	            	Calciatore = new Calciatore();
	            	Calciatore.setIdcalciatore(result.getString("IDCalciatore"));
	            	Calciatore.setCognome(result.getString("Cognome"));
	            	Calciatore.setNome(result.getString("Nome"));
	            	Calciatore.setEta(result.getInt("eta"));
	            	Calciatore.setRuolo(result.getString("Ruolo"));
	            	Calciatore.setNazionalita(result.getString("Nazionalita"));
	            	Calciatore.setQuotazioneAcquisto(result.getInt("quotazioneAcquisto"));
	            	Calciatore.setNickname(result.getString("Nickname"));
	            	calciatori.add(Calciatore);
	            }
	        }
	        catch (SQLException e) {
	        	new Exception(e.getMessage());
	            System.out.println("Errore"+ e.getMessage());
	        }
	        finally {
	            try {
	                if (statement != null) 
	                    statement.close();
	                if (connection!= null)
	                    connection.close();
	            } catch (SQLException e) {
	            	new Exception(e.getMessage());
		             System.out.println("Errore"+ e.getMessage());
	            }
	        }
	        return calciatori;
	    }   
	    
	    
	    public void giocaMatch(Match incontro)  {

	        Connection connection = this.dataSource.getMySQLConnection();
	        PreparedStatement statement = null;
	        PreparedStatement statement2 = null;
	        PreparedStatement statement3 = null;
	        PreparedStatement statement4 = null;
	        String query = "insert into maatch(IDMatch, squadraCasa, squadraTrasferta, puntiValutazioneCasa, puntiValutazioneTrasferta, annoCorrente) values(?, ?, ?, "
	        		+ "(select SUM(puntiValutazione) AS sommma from Calciatore c, prestazione p where p.IDCalciatore = c.IDCalciatore and Nickname=? and IDMatch=?), "
	        		+ "(select SUM(puntiValutazione) AS sommma2 from Calciatore c, prestazione p where p.IDCalciatore = c.IDCalciatore and Nickname=? and IDMatch=?), ?)"; 
	        
	        String queryFor2="select * from maatch where IDMatch=?";
	        
	        String query2= "UPDATE squadra SET partitegiocate= partitegiocate+1, vittorie=vittorie+?, pareggi=pareggi+?, sconfitte=sconfitte+?, "
	        		+ "puntiPrestazionetot=puntiprestazionetot+?, puntiClassifica = (vittorie*3) + pareggi  Where nickname = ?";	        		
	        try {
               	
	             statement = connection.prepareStatement(query);
	             
	             statement.setString(1, incontro.getIDMatch());
	             statement.setString(2, incontro.getSquadraCasa());
	             statement.setString(3, incontro.getSquadraTrasferta());
	             statement.setString(4, incontro.getSquadraCasa());
	             statement.setString(5, incontro.getIDMatch());
	             statement.setString(6, incontro.getSquadraTrasferta());
	             statement.setString(7, incontro.getIDMatch());
	             statement.setInt(8, incontro.getAnnoCorrente());
	             	             	           
	             statement.executeUpdate();
	             
	             
	             
	             statement4 = connection.prepareStatement(queryFor2);
	             statement4.setString(1, incontro.getIDMatch());
	             ResultSet result = statement4.executeQuery();
	             if (result.next()) {
	            	 incontro.setPuntiValutazioneCasa(result.getInt("puntiValutazioneCasa"));
	            	 incontro.setPuntiValutazioneTrasferta(result.getInt("puntiValutazioneTrasferta"));
	             }
	             
	                         
	             
	             
	             int v=0, p=0, s=0;
	             if(incontro.getPuntiValutazioneCasa() > incontro.getPuntiValutazioneTrasferta()) {
	            	 v=1;
	             }
	             else if(incontro.getPuntiValutazioneCasa() == incontro.getPuntiValutazioneTrasferta()) {
	            	 p=1;
	             }
	             else if(incontro.getPuntiValutazioneCasa() < incontro.getPuntiValutazioneTrasferta()) {
	            	 s=1;
	             }
	             statement2 = connection.prepareStatement(query2);
	             
	             statement2.setInt(1, v);
	             statement2.setInt(2, p);
	             statement2.setInt(3, s);
	             statement2.setInt(4, incontro.getPuntiValutazioneCasa());
	             statement2.setString(5, incontro.getSquadraCasa());

	             statement2.executeUpdate();
	             
	             
	             
	             statement3 = connection.prepareStatement(query2);
	             
	             int v2=0, p2=0, s2=0;
	             if(incontro.getPuntiValutazioneCasa() < incontro.getPuntiValutazioneTrasferta()) {
	            	 v2=1;
	             }
	             else if(incontro.getPuntiValutazioneCasa() == incontro.getPuntiValutazioneTrasferta()) {
	            	 p2=1;
	             }
	             else if(incontro.getPuntiValutazioneCasa() > incontro.getPuntiValutazioneTrasferta()) {
	            	 s2=1;
	             }
	             
	             statement3.setInt(1, v2);
	             statement3.setInt(2, p2);
	             statement3.setInt(3, s2);
	             statement3.setInt(4, incontro.getPuntiValutazioneTrasferta());
	             statement3.setString(5, incontro.getSquadraTrasferta());

	             statement3.executeUpdate();
	             
	             
	             

	         }
	         catch (SQLException e) {
	            	new Exception(e.getMessage());
	             System.out.println("Errore ins calc:"+ e.getMessage());
	         }
	         finally {
	             try {
	                 if (statement != null) 
	                     statement.close();
	                 if (connection!= null)
	                     connection.close();
	             }
	             catch (SQLException e) {
	             	new Exception(e.getMessage());
	 	            System.out.println("Error ins calc2"+ e.getMessage());
	             }
	         }
	     }
	    
	    public Match findByPrimaryKeyMatch(String string)  {
	    	Match match = null;
	        
	        Connection connection = this.dataSource.getMySQLConnection();
	        PreparedStatement statement = null;
	        String query = "select squadraCasa, puntiValutazioneCasa, puntivalutazionetrasferta, squadraTrasferta from maatch where IDMatch = ?";
	        try {
	            statement = connection.prepareStatement(query);
	            statement.setString(1, string);
	            ResultSet result = statement.executeQuery();
	            if (result.next()) {
	            	match = new Match();
	            	match.setPuntiValutazioneCasa(result.getInt("puntiValutazioneCasa"));
	            	match.setPuntiValutazioneTrasferta(result.getInt("puntivalutazionetrasferta"));
	            	match.setSquadraCasa(result.getString("squadraCasa"));
	            	match.setSquadraTrasferta(result.getString("squadraTrasferta"));
	                }
	        }
	        catch (SQLException e) {
	        	new Exception(e.getMessage());
	            System.out.println("Errore"+ e.getMessage());
	        }
	        finally {
	            try {
	                if (statement != null) 
	                    statement.close();
	                if (connection!= null)
	                    connection.close();
	            } catch (SQLException e) {
	            	new Exception(e.getMessage());
		             System.out.println("Errore"+ e.getMessage());
	            }
	        }
	        return match;
	    } 
	    

	    public List<Squadra> showClassifica()  {
	        List<Squadra> listcal = null;
	        Squadra cal = null;
	        Connection connection = this.dataSource.getMySQLConnection();
	        PreparedStatement statement = null;
	        String query = "select * from Squadra order by puntiClassifica DESC" ;
	        try {
	            statement = connection.prepareStatement(query);
	            ResultSet result = statement.executeQuery();
	            if(result.next()) {
	                listcal = new LinkedList<Squadra>();
	                cal = new Squadra();
	                cal.setNicknameString(result.getString("Nickname"));
	                cal.setPuntiClassifica(result.getInt("puntiClassifica"));
	                cal.setPartiteGiocate(result.getInt("partiteGiocate"));
	                cal.setVittorie(result.getInt("vittorie"));
	                cal.setPareggi(result.getInt("pareggi"));
	                cal.setSconfitte(result.getInt("sconfitte"));
	                cal.setPuntiPrestazioneTot(result.getInt("puntiPrestazioneTot"));
	                listcal.add(cal);
	            }
	            while(result.next()) {
	                cal = new Squadra();
	                cal.setNicknameString(result.getString("Nickname"));
	                cal.setPuntiClassifica(result.getInt("puntiClassifica"));
	                cal.setPartiteGiocate(result.getInt("partiteGiocate"));
	                cal.setVittorie(result.getInt("vittorie"));
	                cal.setPareggi(result.getInt("pareggi"));
	                cal.setSconfitte(result.getInt("sconfitte"));
	                cal.setPuntiPrestazioneTot(result.getInt("puntiPrestazioneTot"));
	                listcal.add(cal);
	            }
	        }
	        catch (SQLException e) {
	        	new Exception(e.getMessage());
	            System.out.println("Error view calc"+ e.getMessage());
	        }
	        finally {
	            try {
	                if (statement != null) 
	                    statement.close();
	                if (connection!= null)
	                    connection.close();
	            } catch (SQLException e) {
	            	new Exception(e.getMessage());
		             System.out.println("Errore view calc 2"+ e.getMessage());
	            }
	        }
	        return listcal;
	    }   
	    
	    //TODO
	    public List<Prestazione> prestazInMatch(String nick)  {
	        List<Prestazione> listcal = null;
	        Prestazione cal = null;
	        Connection connection = this.dataSource.getMySQLConnection();
	        PreparedStatement statement = null;
	        String query = "select p.IDCalciatore, p.puntiValutazione, p.IDMatch from  prestazione p, calciatore c where p.IDcalciatore = c.IDCalciatore and c.nickname=? " ;// and p.IDmatch=?" ;
	        try {
	        	statement = connection.prepareStatement(query);
		        statement.setString(1, nick);
		        //statement.setString(2, match);
	            ResultSet result = statement.executeQuery();
	            if(result.next()) {
	                listcal = new LinkedList<Prestazione>();
	                cal = new Prestazione();
	                cal.setIdcalciatore(result.getString("IDCalciatore"));
	                cal.setIdmatch(result.getString("IDMatch"));
	                cal.setPuntiValutazione(result.getInt("puntiValutazione"));
	                listcal.add(cal);
	            }
	            while(result.next()) {
	                cal = new Prestazione();
	                cal.setIdcalciatore(result.getString("IDCalciatore"));
	                cal.setIdmatch(result.getString("IDMatch"));
	                cal.setPuntiValutazione(result.getInt("puntiValutazione"));
	                listcal.add(cal);
	            }
	        }
	        catch (SQLException e) {
	        	new Exception(e.getMessage());
	            System.out.println("Error view calc"+ e.getMessage());
	        }
	        finally {
	            try {
	                if (statement != null) 
	                    statement.close();
	                if (connection!= null)
	                    connection.close();
	            } catch (SQLException e) {
	            	new Exception(e.getMessage());
		             System.out.println("Errore view calc 2"+ e.getMessage());
	            }
	        }
	        return listcal;
	    }   
	    
	    public List<Match> findByPrimaryKeyCampionato(String string)  {
	    	Match Calciatore= null;
	    	List<Match> calciatori = null;
	        Connection connection = this.dataSource.getMySQLConnection();
	        PreparedStatement statement = null;
	        String query = "select * from maatch where AnnoCorrente=?";
	        try {
	            statement = connection.prepareStatement(query);
	            statement.setString(1, string);
	            ResultSet result = statement.executeQuery();
	            if (result.next()) {
	            	calciatori = new LinkedList<Match>();
	            	Calciatore = new Match();
	            	Calciatore.setIDMatch(result.getString("IDMatch"));
	            	Calciatore.setAnnoCorrente(result.getInt("AnnoCorrente"));
	            	calciatori.add(Calciatore);
	            }
	            while(result.next()) {
	            	Calciatore = new Match();
	            	Calciatore.setIDMatch(result.getString("IDMatch"));
	            	Calciatore.setAnnoCorrente(result.getInt("AnnoCorrente"));
	            	calciatori.add(Calciatore);
	            }
	        }
	        catch (SQLException e) {
	        	new Exception(e.getMessage());
	            System.out.println("Errore"+ e.getMessage());
	        }
	        finally {
	            try {
	                if (statement != null) 
	                    statement.close();
	                if (connection!= null)
	                    connection.close();
	            } catch (SQLException e) {
	            	new Exception(e.getMessage());
		             System.out.println("Errore"+ e.getMessage());
	            }
	        }
	        return calciatori;
	    }   
	    

	    
	}


