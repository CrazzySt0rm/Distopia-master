//package noidea.Dystopia.db_reader;
//
//
//import org.springframework.stereotype.Service;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Base64;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//@WebServlet("/MyImage")
//
//public class GetImageServletTwo extends HttpServlet {
//
//    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
//    private static final String USER = "postgres";
//    private static final String PASSWORD = "12345";
//
//    public String getImageAsString(int id) throws SQLException, IOException {
//        String query = "SELECT bytes FROM images_t WHERE id = ?";
//
//        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement pstmt = conn.prepareStatement(query)) {
//            pstmt.setInt(1, id);
//
//            try (ResultSet rs = pstmt.executeQuery()) {
//                if (rs.next()) {
//                    byte[] imgData = rs.getBytes("bytes");
//                    return Base64.getEncoder().encodeToString(imgData);
//                }
//            }
//        }
//
//        return null; // Если изображение не найдено
//    }
//}
