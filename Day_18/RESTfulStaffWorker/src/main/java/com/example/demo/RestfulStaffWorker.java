package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication(scanBasePackages = {"com.example.demo"})
public class RestfulStaffWorker implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(RestfulStaffWorker.class, args);
  }

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public void run(String... args) throws Exception {
    createTable();
  }

  public void createTable() {
    this.jdbcTemplate.execute(
            "CREATE TABLE IF NOT EXISTS `staff` (\n" +
                    "  `IDKaryawan` int NOT NULL AUTO_INCREMENT,\n" +
                    "  `Nama` varchar(45) DEFAULT NULL,\n" +
                    "  `TunjanganPulsa` decimal(15,3) DEFAULT NULL,\n" +
                    "  `GajiPokok` decimal(15,3) DEFAULT NULL,\n" +
                    "  `AbsensiHari` int DEFAULT NULL,\n" +
                    "  `TunjanganMakan` decimal(15,3) DEFAULT NULL,\n" +
                    "  `Email` json DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`IDKaryawan`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci"
    );
  }


}

