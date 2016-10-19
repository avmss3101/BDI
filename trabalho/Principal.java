import java.sql.*;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/movies?autoReconnect=true&useSSL=false";//add para evitar erro vermelho ?autoReconnect=true&useSSL=false 
		String login = "avmss";
		String senha = "123456";
		
		String sql = "";
		int id1 = 1;
		int year1 = 1;
		String title1 = "";
		Scanner entrada = new Scanner(System.in);
		
		/*Consulta*/
		System.out.println("Consultas");
		System.out.println("1 - ID:");
		System.out.println("2 - Title:");
		System.out.println("3 - Year:");
		System.out.println("4 - Score:");
		System.out.println("5 - Votes:");
		System.out.println("6 - Os 10 com mais score: ");
		System.out.println("7 - Os 10 mais votados: ");
		System.out.println("8 - Os 10 mais antigos: ");
		int escolha = entrada.nextInt();
		
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
			sql = "";
			break;
		case 5:
			sql = "";
			break;
		case 6:
			sql = "SELECT title, year, score, votes FROM movie ORDER BY score DESC LIMIT 10";
			break;
		case 7:
			sql = "SELECT title, year, score, votes FROM movie ORDER BY votes DESC LIMIT 10";
			break;
		case 8:
			sql = "SELECT title, year, score, votes FROM movie ORDER BY year ASC LIMIT 10";
			break;

		default:
			break;
		}
		
		entrada.close();
		
		try
        {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
            System.out.println("\nDriver carregado com sucesso!\n");
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
        catch (Exception ex)
        {
            System.out.println("\nDriver nao pode ser carregado!");
        }
	}

}
