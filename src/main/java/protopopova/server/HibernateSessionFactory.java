package protopopova.server;

import com.mysql.jdbc.Driver;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.sql.DriverManager;

public class HibernateSessionFactory {

    private static SessionFactory sessionFactory = buildSessionFactory();

//    protected static SessionFactory buildSessionFactory() {
//        // A SessionFactory is set up once for an application!
//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure("hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
//                .build();
//        try {
//            MetadataSources metadataSources = new MetadataSources( registry );
//            Metadata metadata = metadataSources.buildMetadata();
//            sessionFactory = metadata.buildSessionFactory();
//        }
//        catch (Exception e) {
//            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
//            // so destroy it manually.
//
//            StandardServiceRegistryBuilder.destroy( registry );
//
//            throw new ExceptionInInitializerError("Initial SessionFactory failed" + e);
//        }
//        return sessionFactory;
//    }
        protected static SessionFactory buildSessionFactory() {
            //DriverManager.registerDriver(new Driver());
            Configuration configuration = new Configuration();
            configuration = configuration.configure();
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            return sessionFactory;
        }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}
