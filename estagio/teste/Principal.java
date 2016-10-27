import java.sql.*;
import java.util.Scanner;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Principal {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/movies?autoReconnect=true&useSSL=false";//add para evitar erro vermelho ?autoReconnect=true&useSSL=false 
		String login = "avmss";
		String senha = "123456";
		boolean loop = true;
		
		while(loop) {
			JLabel label1 = new JLabel("Login:");
			JTextField login1 = new JTextField();
			JOptionPane.showConfirmDialog(null,	new Object[]{label1, login1}, "Login", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			login = login1.getText();
			//System.out.print("Login: ");
			//login = entrada.next();
			//System.out.println(login);
			JLabel label2 = new JLabel("Senha:");
			JPasswordField pass = new JPasswordField();
			JOptionPane.showConfirmDialog(null,	new Object[]{label2, pass}, "Senha", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
			senha = new String(pass.getPassword());
			//System.out.print("Senha: ");
			//senha = entrada.next();
			//System.out.println(senha);
			if (login.equals("avmss") && senha.equals("123456")) {
				loop = false;
				break;
			}
			
		}
		
		//entrada.close();
		
		carregar(url, login, senha);
		comeca(url, login, senha);
		
	}
	
	public static void carregar(String url, String login, String senha) {
		try
        {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("\nDriver carregado com sucesso!\n"); 
        }
        catch (Exception ex)
        {
            System.out.println("\nDriver nao pode ser carregado!");
        }
	}
	
	public static void comeca(String url, String login, String senha) {
		String sql = "";
		int id1 = 1;
		int year1 = 1;
		String title1 = "";
		Scanner entrada = new Scanner(System.in);
		//boolean loop1 = true;
		//JLabel label3 = new JLabel("Consulta:");
		//JOptionPane.showConfirmDialog(null,	new Object[]{label2, pass}, "Senha", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
		//JOptionPane.showConfirmDialog(null, new Object[]{label3}, "Consulta", JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_OPTION);
		loop: while (true) {
		/*Consulta*/
		System.out.println("Consulta");
		System.out.println("1 - ID");
		System.out.println("2 - Titulo");
		System.out.println("3 - Ano");
		System.out.println("4 - Os 10 mais bem avaliados");
		System.out.println("5 - Os 10 mais votados");
		System.out.println("6 - Os 10 mais antigos");
		System.out.println("7 - Os 10 mais recentes");
		System.out.println("10 - Sair");
		int escolha = entrada.nextInt();
		
		/*
		 * select c.performerID, p.name from casting c inner join performer p on p.ID = c.performerID where c.movieID = 100;
		 * */
		/*
		 * select m.title, m.year, p.name from performer p inner join casting c on c.performerID = p.ID join movie m on m.ID where p.name like '%arnold sc%' and m.ID = c.movieID;
		 * */
		switch (escolha) {
		case 1:
			System.out.println("ID: ");
			id1 = entrada.nextInt();
			sql = "SELECT title, year, score, votes FROM movie WHERE ID = " + id1;
			break;
		case 2:
			System.out.println("Titulo: ");
			title1 = entrada.next();
			sql = "SELECT ID, title, year, score, votes FROM movie WHERE title LIKE '%" + title1 + "%'";
			break;
		case 3:
			System.out.println("Ano: ");
			year1 = entrada.nextInt();
			sql = "SELECT ID, title, year, score, votes FROM movie WHERE year = " + year1;
			break;
		case 4:
			sql = "SELECT title, year, score, votes FROM movie ORDER BY score DESC LIMIT 10";
			break;
		case 5:
			sql = "SELECT title, year, score, votes FROM movie ORDER BY votes DESC LIMIT 10";
			break;
		case 6:
			sql = "SELECT title, year, score, votes FROM movie ORDER BY year ASC LIMIT 10";
			break;
		case 7:
			sql = "SELECT title, year, score, votes FROM movie ORDER BY year DESC LIMIT 10";
			break;
		case 10:
			break loop;
		default:
			break;
		}
		
		
         try
         {
                 Connection conn = DriverManager.getConnection(url, login, senha);
                 try
                 {
                 /*String*/ //sql = "SELECT ID, title FROM movie WHERE title LIKE '%life%'";
                    Statement stm = conn.createStatement();
                    try
                    {
                     ResultSet rs = stm.executeQuery(sql);
                  while (rs.next())
                  {
                	  if (escolha == 3 || escolha == 2) {
                     	 String id = "";
                     	 id = rs.getString("ID");
                     	 System.out.println("ID: " + id);
                      }
                	  
                     String title = rs.getString("title");
                     String ano = rs.getString("year");
                     String score = rs.getString("score");
                     String votos = rs.getString("votes");
                     
                     //String id = rs.getString("ID");
                     //System.out.println("ID: " + id + "\nTitulo: " + title);
                     System.out.println("Titulo: " + title + "\nAno: " + ano + "\nScore: " + score + "\nVotos: " + votos);
                     System.out.println("---------------------------------------");
                  }
                  System.out.println("\nConsulta realizada com sucesso!!!\n");                    
                    }
               catch (Exception ex)
               {
                  System.out.println("\nErro no resultset!");
               }
                 }
                  catch (Exception ex)
            {
               System.out.println("\nErro no statement!");
            }
         }
         catch (Exception ex)
         {
            System.out.println("\nErro no connection!");
         }  
	}
	entrada.close();
	}

}
