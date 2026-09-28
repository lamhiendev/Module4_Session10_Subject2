package demo.pharmacyservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class PharmacyServiceApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(PharmacyServiceApplication.class);
    @Value("${app.branch-name:Chua co ten}")
    private String branchName;
    @Value("${app.hotline:Chua co hotline}")
    private String hotline;
    @Value("${spring.datasource.url}")
    private String dbUrl;
    public static void main(String[] args) {
        SpringApplication.run(PharmacyServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("=================================================");
        log.info("THONG TIN CHI NHANH HIEU THUOC TAP TRUNG:");
        log.info("Ten Chi Nhanh : {}", branchName);
        log.info("Hotline       : {}", hotline);
        log.info("Database URL  : {}", dbUrl);
        log.info("=================================================");
    }
}
