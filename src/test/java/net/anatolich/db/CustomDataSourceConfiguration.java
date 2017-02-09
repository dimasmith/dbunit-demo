package net.anatolich.db;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Configures custom property for datasource to be used in tests.
 */
@Configuration
public class CustomDataSourceConfiguration {

    @Bean
    public DatabaseConfigBean dbUnitDatabaseConfig() {
        final DatabaseConfigBean config = new DatabaseConfigBean();
        config.setAllowEmptyFields(true);
        return config;
    }

    /**
     * Create datasource factory. It is very important for this bean to have id
     * equal to <code>dbUnitDatabaseConnection</code>
     * @see com.github.springtestdbunit.DbUnitTestExecutionListener for details
      * @param dataSource
     * @return
     */
    @Bean
    public DatabaseDataSourceConnectionFactoryBean dbUnitDatabaseConnection(DataSource dataSource) {
        final DatabaseDataSourceConnectionFactoryBean dataSourceFactoryBean = new DatabaseDataSourceConnectionFactoryBean(dataSource);
        dataSourceFactoryBean.setDatabaseConfig(dbUnitDatabaseConfig());
        return dataSourceFactoryBean;
    }

}
