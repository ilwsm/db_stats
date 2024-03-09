package org.kinocat.db_stats;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class DbStatsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DbStatsApplication.class, args);
    }

    @Override
    public void run(String... args) {
    }
}
