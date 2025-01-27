package noidea.Dystopia.controllers;


import lombok.AllArgsConstructor;
import noidea.Dystopia.db_reader.DbReader;
import noidea.Dystopia.models.Dystopia;
import noidea.Dystopia.services.EmailService;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Controller
@AllArgsConstructor

@CrossOrigin(origins = "http://localhost:8081")
public class DystopiaController {


    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345";


    private final DbReader dbReader = new DbReader();


    @GetMapping("/home")
    public String getDistopiaOne() {
        return "home";
    }

    @GetMapping("/animation_3D")
    public String getAnim() {
        return "animation_3D";
    }

    @GetMapping("/page_three")
    public String getPageThree(Model model) {
        List<String> humans = new ArrayList<>();
        humans.add("Johnson");
        humans.add("Eric Foxy");
        humans.add("Lee Si Cin");
        model.addAttribute("test", dbReader.getMSG());
//        model.addAttribute("image", dbReader.getIMG());

        model.addAttribute("xyz", humans);
        model.addAttribute("title", "imperium");
        return "page_three";

    }

    @GetMapping("/page_four")
    public String getPageFour(Model model) throws SQLException, IOException {
        List<String> fruits = new ArrayList<>();
        List<Dystopia> dystopias = new ArrayList<Dystopia>();
        model.addAttribute("dystopias", dystopias);
        model.addAttribute("one", fruits);


        String strTime = DateTimeFormatter.ofPattern("HH : mm : ss").format(LocalDateTime.now());
//        model.addAttribute("serverTimeH", dateHours.getHours());
//        model.addAttribute("serverTimeM", dateMinutes.getMinutes());
        model.addAttribute("serverTime", strTime);

        return "page_four";
    }

    @GetMapping("/page_five")
    public String getPageFive() {
        return "page_five";
    }

    @GetMapping("/page_sixth")
    public String getPageSixth(Model model) {
        model.addAttribute("test2", dbReader.getMSG());
        return "page_sixth";
    }
}


//    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
//    public String showImage(@PathVariable("id") int id, Model model) {
//
//        try {
//
//            String URL = "jdbc:postgresql://localhost:5432/postgres";
//            String USER = "postgres";
//            String PASSWORD = "12345";
//
//
//            String query = "SELECT image_data FROM images_t WHERE id = ?";
//
//            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//                 PreparedStatement pstmt = conn.prepareStatement(query)) {
//                pstmt.setInt(1, id);
//
//                try (ResultSet rs = pstmt.executeQuery()) {
//                    if (rs.next()) {
//                        byte[] imgData = rs.getBytes("image_data");
//
//                        if (imgData == null || imgData.length == 0) {
//                            return "error_page";
//                        }
//                        String imageData = Base64.getEncoder().encodeToString(imgData);
//                        if (imageData != null) {
//                            model.addAttribute("imageData", imageData);
//                            return "page_four";
//
//
//                        }
//                    }
//                }
//                return "error_page";
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return "error_page";
//        }
//
//    }
//}

