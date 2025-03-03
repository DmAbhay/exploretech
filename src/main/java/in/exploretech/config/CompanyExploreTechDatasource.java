package in.exploretech.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "in.exploretech.logasup.repository",
        entityManagerFactoryRef = "companyExploreTechEntityManagerFactory",
        transactionManagerRef = "companyExploreTechTransactionManager"
)

public class CompanyExploreTechDatasource {

    @Autowired
    private ExternalConfig externalConfig;

    @Bean(name = "companyExploreTechDataSource")
    DataSource companyExploreTechDataSource(){

        return DataSourceBuilder.create()
                .url("jdbc:sqlserver://" + externalConfig.getSqlHostName() + ":" + externalConfig.getSqlPort() +
                        ";databaseName=" + externalConfig.getCompanyDb() + ";encrypt=true;trustServerCertificate=true")
                .username(externalConfig.getSqlUser())
                .password(externalConfig.getSqlPassword())
                .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                .build();

    }

    @Bean(name = "companyExploreTechEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean companyExploreTechEntityManagerFactory(
            @Qualifier("companyExploreTechDataSource") DataSource dataSource){
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan("in.exploretech.logasup.entity");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setJpaProperties(jpaProperties());
        return factory;

    }

    @Bean(name = "companyExploreTechTransactionManager")
    JpaTransactionManager companyExploretechTransactionManager(
            @Qualifier("companyExploreTechEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory.getObject());
    }

    //Method to define JPA properties
    private Properties jpaProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update"); // Add this line
        return properties;
    }


    // Add this inside CompanyExploreTechDatasource class
    @Bean(name = "companyExploreTechJdbcTemplate")
    public JdbcTemplate companyExploreTechJdbcTemplate(
            @Qualifier("companyExploreTechDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}