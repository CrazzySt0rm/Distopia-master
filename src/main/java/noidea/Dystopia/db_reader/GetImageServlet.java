//package noidea.Dystopia.db_reader;
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//@WebServlet("/MyImage")
//public class GetImageServlet extends HttpServlet {
//
//    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
//    private static final String USER = "postgres";
//    private static final String PASSWORD = "12345";
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int id = Integer.parseInt(req.getParameter("id"));
//
//        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
//            displayImage(conn, id, resp);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void displayImage(Connection conn, int id, HttpServletResponse response) throws SQLException, IOException {
//        String query = "SELECT bytes FROM images_t WHERE id = 1";
//
//        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
//            pstmt.setInt(1, id);
//
//            try (ResultSet rs = pstmt.executeQuery()) {
//                if (rs.next()) {
//                    byte[] imgData = rs.getBytes("bytes");
//
//                    // Отправляем изображение браузеру
//                    response.setContentType("image/jpg");
//                    response.getOutputStream().write(imgData);
//                } else {
//                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found.");
//                }
//            }
//        }
//    }
//}
