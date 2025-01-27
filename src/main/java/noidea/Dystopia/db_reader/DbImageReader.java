//package noidea.Dystopia.db_reader;
//
//import noidea.Dystopia.models.Image;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.sql.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class DbImageReader {
//
//    private void go() {
//
//        String dbUrl = "${postgres_url}";
//        String query = "SELECT text, FROM dystopia_message ORDER by DATA";
//    }
//
//    public Connection connect() throws SQLException {
//        return DriverManager.getConnection(url, user, password);
//    }
//
//    public final String url = "jdbc:postgresql://localhost:5432/postgres";
//    private final String user = "postgres";
//    private final String password = "12345";
//
//    public String getIMG() {
//        String SQL = "SELECT id, bytes FROM images_t ORDER BY id DESC LIMIT 1";
//
//        try (Connection conn = DriverManager.getConnection(url, user, password);
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(SQL)) {
//
//            // Move to the first result in the ResultSet
//            if (rs.next()) {
//
//                // File to save the retrieved image
//                File myFile = new File("87.jpg");
//
//                try (FileOutputStream fos = new FileOutputStream(myFile)) {
//
//                    // Get the length and binary data of the image
//                    int len = rs.getInt(2);
//                    byte[] buf = rs.getBytes("bytes");
//
//                    // Write the binary data to the file
//                    fos.write(buf, 0, len);
//                    System.out.println("Image read successfully and saved as 87.jpg");
//                } catch (FileNotFoundException e) {
//                    throw new RuntimeException(e);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//        } catch (SQLException ex) {
//            Logger lgr = Logger.getLogger(Image.class.getName());
//            lgr.log(Level.SEVERE, ex.getMessage(), ex);
//        }
//        return SQL;
//    }
//}
