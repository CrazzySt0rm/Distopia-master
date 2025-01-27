package noidea.Dystopia;

import noidea.Dystopia.db_reader.DbReader;

import noidea.Dystopia.models.Dystopia;
import noidea.Dystopia.models.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class DystopiaApplication {

	public static void main(String[] args) throws SQLException {

		SpringApplication.run(DystopiaApplication.class, args);

		DbReader dbReader = new DbReader();
		dbReader.getMSG();




	}
}
