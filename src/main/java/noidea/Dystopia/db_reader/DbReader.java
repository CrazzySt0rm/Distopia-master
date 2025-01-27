package noidea.Dystopia.db_reader;

import jakarta.servlet.http.HttpServletResponse;
import noidea.Dystopia.models.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;




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

//        String SQL = "SELECT id, message FROM dystopia_message ORDER BY id DESC LIMIT 1";
        String SQL = "SELECT message FROM dystopia_message ORDER BY id DESC LIMIT 1";

        String x = "";


        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            // display actor information
//            displayMSG(rs);


            x = displayMSG(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return x;

    }

    private String displayMSG(ResultSet rs) throws SQLException {
        StringBuilder sb = new StringBuilder();
        while (rs.next()) {
//            sb.append(rs.getString("id")).append("\t").append(rs.getString("message")).append("\t");
            sb.append(rs.getString("message")).append("\t");



        }
        return sb.toString();
    }

//    private String displayImage() throws SQLException, IOException{
//
//        // Укажите параметры подключения к вашей базе данных


    // Подготовка SQL-запроса для выборки изображения
//                String query = "SELECT bytes FROM images_t WHERE id = ?"; // замените your_table и id на ваши значения
//
//                try (PreparedStatement pstmt = connect().prepareStatement(query)) {
//                    // Предположим, что вы ищете изображение по идентификатору
//                    int id = 2102; // укажите нужный ID
//                    pstmt.setInt(1, id);
//
//                    try (ResultSet rs = pstmt.executeQuery()) {
//                        if (rs.next()) {
//                            byte[] imgData = rs.getBytes("bytes");
//
//                            // Преобразуем байты в изображение
//                            BufferedImage bufferedImage = convertByteArrayToBufferedImage(imgData);
//
//                            // Сохраняем изображение в файл (например, в формате PNG)
//                            File outputFile = new File("output.png");
//                            ImageIO.write(bufferedImage, "png", outputFile);
//
//                            System.out.println("Изображение успешно сохранено в файл: " + outputFile.getAbsolutePath());
//                        } else {
//                            System.out.println("Запись с указанным ID не найдена.");
//                        }
//                    }
//                }
//
//        return query;
//    }
//
//        /**
//         * Метод для преобразования массива байт в объект BufferedImage
//         */
//        private static BufferedImage convertByteArrayToBufferedImage(byte[] imgData) throws IOException {
//            ByteArrayInputStream bis = new ByteArrayInputStream(imgData);
//            return ImageIO.read(bis);
//        }
//    }


//    private void displayImage(int id, HttpServletResponse response) throws SQLException, IOException {
//        String query = "SELECT bytes FROM images_t WHERE id = ?";
//
//        try (Connection conn = connect(); // Предполагая, что у тебя есть метод connect()
//             PreparedStatement pstmt = conn.prepareStatement(query)) {
//            pstmt.setInt(1, id);
//
//            try (ResultSet rs = pstmt.executeQuery()) {
//                if (rs.next()) {
//                    byte[] imgData = rs.getBytes("bytes");
//
//                    // Отправляем изображение браузеру
//                    response.setContentType("image/png");
//                    response.getOutputStream().write(imgData);
//                } else {
//                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found.");
//                }
//            }
//        }
//    }
}










