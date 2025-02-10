package noidea.Dystopia.db_reader;

import java.sql.*;





public class DbReader {

    private void go() {

        String dbUrl = "${postgres_url}";
        String query = "SELECT text, FROM dystopia_message ORDER by DATA";
    }

    public Connection connect() throws SQLException {

//        String password = "${postgres_password}";
//        String user = "${postgres_username}";
        return DriverManager.getConnection(url, user, password);
    }

    public final String url = "jdbc:postgresql://localhost:5432/postgres";
    public final String user = "postgres";
    public final String password = "johnsonxn3";



    public String getMSG() {

        String SQL = "SELECT message FROM dystopia_message ORDER BY id DESC LIMIT 1";

        String x = "";


        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {


            x = displayMSG(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return x;

    }

    private String displayMSG(ResultSet rs) throws SQLException {
        StringBuilder sb = new StringBuilder();
        while (rs.next()) {
            sb.append(rs.getString("message")).append("\t");

        }
        return sb.toString();
    }
}










