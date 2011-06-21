package fr.generali.gfb.ws.sinistre.tools;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

/**
 * @author e004035
 */
public class SchemaExport {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ApplicationContext context =
                        new ClassPathXmlApplicationContext(
                                        new String[] {"fr/generali/gfb/sinistre/tools/applicationContext-schema-export.xml" });
        AnnotationSessionFactoryBean factory = new AnnotationSessionFactoryBean();
        factory.setDataSource((DataSource ) context.getBean("dataSource"));
        Properties prop = new Properties();
        prop.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle9iDialect");
        prop.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.HashtableCacheProvider");
        prop.setProperty("hibernate.hbm2ddl", "validate");
        prop.setProperty("hibernate.show_sql", "true");
        factory.setHibernateProperties(prop);
        factory.setConfigLocation(new ClassPathResource("fr/generali/gfb/ws/sinistre/persistence/hibernate.cfg.xml"));
        factory.afterPropertiesSet();

        org.hibernate.tool.hbm2ddl.SchemaExport export =
                        new org.hibernate.tool.hbm2ddl.SchemaExport(factory.getConfiguration());
        export.setOutputFile("target/schemaExport.sql");
        export.create(true, false);

    }
}
