package IntroductionJDBC;

import java.sql.*;

public class ProgrammePrincipal {
    public static void main(String[] args) {

        // 1ere Etape : Trouver driver jdbc sur internet (api)

        String nomDuDriverJDBCduSGBD = "com.mysql.cj.jdbc.Driver";


        // 2eme Etape : Trouver l'URL de notre base de donnee

        String urlBD = "jdbc:mysql://localhost:3306/mabase";

        // 3eme Etape : charger en mémoire le Driver

        try{
            Class.forName(nomDuDriverJDBCduSGBD);
            System.out.println("Chargement du Driver réussi");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
            System.exit(-1);
        }

        // 4eme Etape : Connecter entre programme et BD

        Connection maConnection = null;

        try {
            maConnection = DriverManager.getConnection(urlBD, "root", "");
            System.out.println("Connection à la base réussi");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        // 5eme Etape : création du statement

        Statement monStatement = null;

        try {
            monStatement = maConnection.createStatement();
            System.out.println("Statement créé");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        String requeteSansSelect = "CREATE TABLE IF NOT EXISTS Livres (" +
                "IdLivre INTEGER, " +
                "Titre VARCHAR(100), " +
                "DatePublication DATE" +
                ");";

        try {
            monStatement.executeUpdate(requeteSansSelect);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }


        requeteSansSelect = "INSERT INTO Livres VALUES (" +
                "1, " +
                "'Ruination', " +
                "'2022-09-06'" +
                ");";

        try {
            monStatement.executeUpdate(requeteSansSelect);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        //Select
        requeteSansSelect = "Select IdLivre from Livres";
        try {
            ResultSet result = monStatement.executeQuery(requeteSansSelect);
            while (result.next()){
                System.out.println(result.getInt("IdLivre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        // Derniere Etape : Close la BD

        try {
            monStatement.close();
            maConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
