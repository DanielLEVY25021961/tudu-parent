package tudu.test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Helper for DbUnit operations.
 * @author Arnaud Cogoluegnes
 *
 */
public class DbUnitHelper {
	
	/**
	 * VERIFICATION_EXCEPTION : String :<br/>
	 * .<br/>
	 */
	private static final String VERIFICATION_EXCEPTION = "Error during verification";

	
	/**
	 * dataSource : DataSource :<br/>
	 * .<br/>
	 */
	private DataSource dataSource;

	
	
	 /**
	 * method CONSTRUCTEUR DbUnitHelper() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pDataSources
	 */
	@Autowired
	public DbUnitHelper(
			final DataSource pDataSources) {
		this.dataSource = pDataSources;
	}

	
	
	
	 /**
	 * method CONSTRUCTEUR DbUnitHelper() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 */
	public DbUnitHelper() { }

	
	
	/**
	 * method getConnection() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return
	 * @throws Exception :  :  .<br/>
	 */
	private IDatabaseConnection getConnection() throws Exception {
		
		final DatabaseDataSourceConnection datasourceConnection = new DatabaseDataSourceConnection(
				this.dataSource
		);
		// avoid the type not recognized problem
		// http://dbunit.sourceforge.net/faq.html#typenotrecognized
		DatabaseConfig config = datasourceConnection.getConfig();
	    config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
	            new HsqldbDataTypeFactory()
	    );
		return datasourceConnection;
	}

	
	
	
	/**
	 * method getDataSet() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pDataSetResourcePath
	 * @return IDataSet
	 * @throws Exception :  :  .<br/>
	 */
	public IDataSet getDataSet(String pDataSetResourcePath) throws Exception {
		
		final FlatXmlDataSetBuilder xmlBuilder = new FlatXmlDataSetBuilder();
		
		final IDataSet dataset = xmlBuilder.build(((getClass().getResourceAsStream(
				pDataSetResourcePath))));
		
		return dataset;
	}	

	
	
	/**
	 * method doCleanInsert() :<br/>
	 * Do a clean insert.<br/>
	 * <br/>
	 *
	 * @param pDataSet :  :  .<br/>
	 */
	public void doCleanInsert(IDataSet pDataSet) {
		try {
			DatabaseOperation.CLEAN_INSERT.execute(
					getConnection(),
					pDataSet
			);
		} catch (Exception e) {
			throw new RuntimeException("Error while doing a clean insert : "+e.getMessage(),e);
		}
	}

	
	
	/**
	 * Table content and dataset comparison.
	 * Comparison is based on the dataset columns.
	 * @param pTableName
	 * @param pDataSet
	 */
	public void compareTables(String pTableName, IDataSet pDataSet) {
		
		try {
			// retrieve table content
			IDataSet databaseDataSet = getConnection().createDataSet();
			ITable currentTable = databaseDataSet.getTable(pTableName);

			// what we are expecting (from the dataset)
			ITable expectedTable = pDataSet.getTable(pTableName);
			
			// filter from the dataset content
			currentTable = DefaultColumnFilter.includedColumnsTable(currentTable,expectedTable.getTableMetaData().getColumns());

			// assertion
			Assertion.assertEquals(expectedTable, currentTable);
		} catch (DataSetException e) {
			throw new RuntimeException(VERIFICATION_EXCEPTION, e);
		} catch (SQLException e) {
			throw new RuntimeException(VERIFICATION_EXCEPTION, e);
		} catch (DatabaseUnitException e) {
			throw new RuntimeException(VERIFICATION_EXCEPTION, e);
		} catch (Exception e) {
			throw new RuntimeException(VERIFICATION_EXCEPTION, e);
		}
	}

	
	
	/**
	 * Check row count in a table.
	 * @param tableName
	 * @param expectedCount
	 */
	public void compareTableCount(String tableName,int expectedCount) {
		try {
			IDataSet databaseDataSet = getConnection().createDataSet();
			ITable currentTable = databaseDataSet.getTable(tableName);
			assertEquals(expectedCount,currentTable.getRowCount());
		} catch (DataSetException e) {
			throw new RuntimeException(VERIFICATION_EXCEPTION, e);
		} catch (SQLException e) {
			throw new RuntimeException(VERIFICATION_EXCEPTION, e);
		} catch (Exception e) {
			throw new RuntimeException(VERIFICATION_EXCEPTION, e);
		}		
	}

	
	
	/**
	 * Table content and dataset comparison.
	 * Comparison is based on the dataset columns.
	 * @param tableName
	 * @param ressourceDataSet
	 */
	public void compareTables(String tableName, String ressourceDataSet) {
		IDataSet dataSet = null;
		final FlatXmlDataSetBuilder xmlBuilder = new FlatXmlDataSetBuilder();
		
		try {
			
			dataSet = xmlBuilder.build(getClass().getResourceAsStream(
					ressourceDataSet));
			
		} catch (DataSetException e) {
			throw new RuntimeException(VERIFICATION_EXCEPTION, e);
		}
		compareTables(tableName, dataSet);
	}

	
	
	/**
	 * method getTable() :<br/>
	 * Retrieve a table from the database.<br/>
	 * <br/>
	 *
	 * @param pTableName
	 * @return : ITable :  .<br/>
	 */
	public ITable getTable(
			final String pTableName) {
		try {
			
			IDataSet databaseDataSet = getConnection().createDataSet();
			
			return databaseDataSet.getTable(pTableName);
			
		} catch (DataSetException e) {
			throw new RuntimeException(VERIFICATION_EXCEPTION, e);
		} catch (SQLException e) {
			throw new RuntimeException(VERIFICATION_EXCEPTION, e);
		} catch (Exception e) {
			throw new RuntimeException(VERIFICATION_EXCEPTION, e);
		}		
	}

	
	
}
