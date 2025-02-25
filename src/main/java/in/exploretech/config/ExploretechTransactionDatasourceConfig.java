//package in.exploretech.config;
//
//import org.mymath.dbs.FetchDatabaseName;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import javax.xml.crypto.Data;
//import java.util.Properties;
//
//@Configuration
//@EnableJpaRepositories(
//        basePackages = {"in.exploretech.collection.repository"},
//        entityManagerFactoryRef = "transactionExploretechEntityManagerFactory",
//        transactionManagerRef = "transactionExploretechTransactionManager"
//)
//public class ExploretechTransactionDatasourceConfig {
//
//    @Autowired
//    private ExternalConfig externalConfig;
//
//    @Autowired
//    @Qualifier("companyExploreTechDataSource")
//    private DataSource companyExploretechSource;
//
//    @Bean(name = "exploretechTransactionDataSource")
//    DataSource exploretechTransactionDataSource(){
//
//        FetchDatabaseName fetcher = new FetchDatabaseName(
//                externalConfig.getSqlHostName(), 	// Hostname
//                externalConfig.getSqlPort(), 	  	// Port
//                externalConfig.getCompanyDb(), 	    // Database Name
//                externalConfig.getSqlUser(), 		// Username
//                externalConfig.getSqlPassword() 	// Password
//        );
//
//        System.out.println(externalConfig.getSqlPassword());
//        System.out.println(externalConfig.getSqlUser());
//        System.out.println(externalConfig.getCompanyDb());
//        System.out.println(externalConfig.getSqlPort());
//        System.out.println(externalConfig.getSqlHostName());
//
//        String sqlQuery = "SELECT centralDataPath FROM projectMaster WHERE projectId = 10002;";
//        String columnName = "centralDataPath";
//        String databaseName = fetcher.getDatabaseName(sqlQuery, columnName);
//
//        if(databaseName == null || databaseName.isEmpty()){
//            throw new RuntimeException("Failed to fetch the dynamic database name");
//        }
//
//
//        return DataSourceBuilder.create()
//                .url("jdbc:sqlserver://" + externalConfig.getSqlHostName() + ":" + externalConfig.getSqlPort() +
//                        ";databaseName=" + databaseName + ";encrypt=true;trustServerCertificate=true")
//                .username(externalConfig.getSqlUser())
//                .password(externalConfig.getSqlPassword())
//                .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
//                .build();
//
//    }
//
//    @Bean(name = "exploretechEntityManagerFactory")
//    LocalContainerEntityManagerFactoryBean transactionExploretechEntityManagerFactory(
//            @Qualifier("exploretechTransactionDataSource") DataSource dataSource){
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setDataSource(dataSource);
//        factory.setPackagesToScan("in.exploretech.collection.entity");
//        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        factory.setJpaProperties(jpaProperties());
//        return factory;
//
//    }
//
//    @Primary
//    @Bean(name = "transactionExploretechTransactionManager")
//    PlatformTransactionManager transactionExploretechTransactionManager(
//            @Qualifier("transactionExploretechEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory){
//        return new JpaTransactionManager(entityManagerFactory.getObject());
//    }
//
//    //Method to define JPA properties
//    private Properties jpaProperties(){
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
//        properties.setProperty("hibernate.show_sql", "true");
//        properties.setProperty("hibernate.format_sql", "true");
//        return properties;
//    }
//
//    @Bean(name = "exploretechTransactionJdbcTemplate")
//    JdbcTemplate exploretechTransactionJdbcTemplate(@Qualifier("exploretechTransactionDataSource") DataSource exploretechTransactionDataSource){
//        return new JdbcTemplate(exploretechTransactionDataSource);
//    }
//
//
//}



package in.exploretech.config;

import org.mymath.dbs.FetchDatabaseName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = {"in.exploretech.collection.repository"},
        entityManagerFactoryRef = "transactionExploretechEntityManagerFactory",
        transactionManagerRef = "transactionExploretechTransactionManager"
)
public class ExploretechTransactionDatasourceConfig {

    @Autowired
    private ExternalConfig externalConfig;

    @Autowired
    @Qualifier("companyExploreTechDataSource")
    private DataSource companyExploretechSource;

    @Bean(name = "exploretechTransactionDataSource")
    DataSource exploretechTransactionDataSource() {
        FetchDatabaseName fetcher = new FetchDatabaseName(
                externalConfig.getSqlHostName(),
                externalConfig.getSqlPort(),
                externalConfig.getCompanyDb(),
                externalConfig.getSqlUser(),
                externalConfig.getSqlPassword()
        );

        String sqlQuery = "SELECT centralDataPath FROM projectMaster WHERE projectId = 10002;";
        String columnName = "centralDataPath";
        String databaseName = fetcher.getDatabaseName(sqlQuery, columnName);

        if (databaseName == null || databaseName.isEmpty()) {
            throw new RuntimeException("Failed to fetch the dynamic database name");
        }

        return DataSourceBuilder.create()
                .url("jdbc:sqlserver://" + externalConfig.getSqlHostName() + ":" + externalConfig.getSqlPort() +
                        ";databaseName=" + databaseName + ";encrypt=true;trustServerCertificate=true")
                .username(externalConfig.getSqlUser())
                .password(externalConfig.getSqlPassword())
                .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                .build();
    }

    @Bean(name = "transactionExploretechEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean transactionExploretechEntityManagerFactory(
            @Qualifier("exploretechTransactionDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan("in.exploretech.collection.entity");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setJpaProperties(jpaProperties());
        return factory;
    }

    @Primary
    @Bean(name = "transactionExploretechTransactionManager")
    PlatformTransactionManager transactionExploretechTransactionManager(
            @Qualifier("transactionExploretechEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update"); // Add this line
        return properties;
    }

    @Bean(name = "exploretechTransactionJdbcTemplate")
    JdbcTemplate exploretechTransactionJdbcTemplate(@Qualifier("exploretechTransactionDataSource") DataSource exploretechTransactionDataSource) {
        return new JdbcTemplate(exploretechTransactionDataSource);
    }
}

