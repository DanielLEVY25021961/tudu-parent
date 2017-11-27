package tudu.domain.configuration;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * class DataSourceFactory :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 18 nov. 2017
 *
 */
public class DataSourceFactory {

    /**
     * log : Log :<br/>
     * .<br/>
     */
    private final Log log = LogFactory.getLog(DataSourceFactory.class);

    
    
    /**
     * jndiDatasourceName : String :<br/>
     * .<br/>
     */
    private String jndiDatasourceName;

    
    
    /**
     * mysqlDataSource : DataSource :<br/>
     * .<br/>
     */
    private DataSource mysqlDataSource;

    
    
    /**
     * hsqldbDataSource : DataSource :<br/>
     * .<br/>
     */
    private DataSource hsqldbDataSource;

    
    
    /**
     * currentDataSource : DataSource :<br/>
     * .<br/>
     */
    private DataSource currentDataSource = null;

    
    
    /**
     * hibernateJpaVendorAdapter : HibernateJpaVendorAdapter :<br/>
     * .<br/>
     */
    private HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();

    
    
    /**
     * method setJndiDatasourceName() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pJndiDatasourceName :  :  .<br/>
     */
    public void setJndiDatasourceName(
    		final String pJndiDatasourceName) {
        this.jndiDatasourceName = pJndiDatasourceName;
    }

    
    
    /**
     * method setHsqldbDataSource() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pHsqldbDataSource :  :  .<br/>
     */
    public void setHsqldbDataSource(
    		final DataSource pHsqldbDataSource) {
        this.hsqldbDataSource = pHsqldbDataSource;
    }

    
    
    /**
     * method setMysqlDataSource() :<br/>
     * .<br/>
     * <br/>
     *
     * @param pMysqlDataSource :  :  .<br/>
     */
    public void setMysqlDataSource(
    		final DataSource pMysqlDataSource) {
        this.mysqlDataSource = pMysqlDataSource;
    }

    
    
    
    /**
     * method init() :<br/>
     * .<br/>
     * <br/>
     * :  :  .<br/>
     */
    public void init() {
    	
        this.log.warn("Initializing database connection pool.");
        
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource jndiDataSource = (DataSource) envContext
                    .lookup(this.jndiDatasourceName);

            Connection conn = jndiDataSource.getConnection();
            conn.close();
            this.currentDataSource = jndiDataSource;
            this.hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
            this.log.warn("MySQL database found in JNDI.");
        } catch (Exception e) {
            if (this.log.isDebugEnabled()) {
                e.printStackTrace();
            }
            findMysqlDataSource();
        }
    }

    
    
    /**
     * method findMysqlDataSource() :<br/>
     * Find a MySQL instance and create a datasource from it.<br/>
     * <br/>
     * :  :  .<br/>
     */
    private void findMysqlDataSource() {
    	
        this.log.warn("No database configured in JNDI at \"java:/comp/env"
                + this.jndiDatasourceName + "\"");

        try {
            Connection conn = this.mysqlDataSource.getConnection();
            conn.close();
            this.currentDataSource = this.mysqlDataSource;
            this.hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
            this.hibernateJpaVendorAdapter.setShowSql(true);
            this.log.warn("MySQL database found.");
        } catch (SQLException mysqlE) {
        	this.log.warn("MySQL database could not be found");
            if (this.log.isDebugEnabled()) {
                mysqlE.printStackTrace();
            }
            launchHsqldbDataSource();
        }
    }

    
    
 
    /**
     * method launchHsqldbDataSource() :<br/>
     * Launch an in-memory HSQLDB database, and create a datasource from it.<br/>
     * <br/>
     * :  :  .<br/>
     */
    private void launchHsqldbDataSource() {
    	
        this.hibernateJpaVendorAdapter.setDatabase(Database.HSQL);
        this.hibernateJpaVendorAdapter.setGenerateDdl(true);
        this.hibernateJpaVendorAdapter.setShowSql(false);
        
        try {
            Connection conn = this.hsqldbDataSource.getConnection();
            conn.close();
            this.currentDataSource = this.hsqldbDataSource;
            this.log.warn("HSQLDB database started.");
        } catch (SQLException sqle) {
        	this.log.warn("HSQLDB database could not be started : "
                    + sqle.getMessage());

            sqle.printStackTrace();
            this.log.fatal("No database could be used, the application cannot run!");
        }
    }

    
    
    /**
     * method getDataSource() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    public DataSource getDataSource() {
        return this.currentDataSource;
    }

    
    
    /**
     * method getHibernateJpaVendorAdapter() :<br/>
     * .<br/>
     * <br/>
     *
     * @return :  :  .<br/>
     */
    public HibernateJpaVendorAdapter getHibernateJpaVendorAdapter() {
        return this.hibernateJpaVendorAdapter;
    }
    
    
    
}
