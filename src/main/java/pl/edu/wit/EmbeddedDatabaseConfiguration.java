package pl.edu.wit;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

//@Configuration
//@EnableAutoConfiguration
//public class EmbeddedDatabaseConfiguration {
//
//    @Bean(name = "dataSource")
//    public DriverManagerDataSource getDataSource(){
//        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//        driverManagerDataSource.setDriverClassName("org.h2.Driver");
//        driverManagerDataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_ON_EXIT=FALSE;IGNORECASE=TRUE;DB_CLOSE_DELAY=-1");
//        driverManagerDataSource.setUsername("sa");
//        driverManagerDataSource.setPassword("");
//        return driverManagerDataSource;
//    }
//}
