package tudu.test;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

/**
 * Listener for the DbUnit injection pre-test database injection.
 * @author acogoluegnes
 */
public class CleanInsertTestExecutionListener implements TestExecutionListener {
	
	/**
	 * LOG : Log :<br/>
	 * .<br/>
	 */
	private static final Log LOG = LogFactory.getLog(CleanInsertTestExecutionListener.class);

	/**
	 * NO-OP
	 */
	@Override
	public void afterTestMethod(TestContext ctx) throws Exception { }

	
	
	/**
	 * Does data injection.
	 */
	@Override
	public void beforeTestMethod(TestContext ctx) throws Exception {
		if(ctx.getTestInstance() instanceof IDataSetLocator) {
			IDataSetLocator test = (IDataSetLocator) ctx.getTestInstance();	
			DbUnitHelper helper = null;
			// a DbUnit helper in the context?			
			if(ctx.getApplicationContext().containsBean("dbUnitHelper")) {
				// yes, use it
				helper = (DbUnitHelper) ctx.getApplicationContext().getBean("dbUnitHelper");
			} else {
				// no, create one
				DataSource ds = (DataSource) ctx.getApplicationContext().getBean("dataSource");
				helper = new DbUnitHelper(ds);
			}			
			// do the clean insert
			LOG.info("doing clean insert from "+test.getDataSet()+" for method "+ctx.getTestMethod().getName());
			helper.doCleanInsert(helper.getDataSet(test.getDataSet()));
		} else {
			LOG.info(ctx.getClass().getName()+" n'est pas une classe contenant un dataset, pas d'injection de donn�es");
		}		
	}

	
	/**
	 * NO-OP
	 */
	@Override
	public void prepareTestInstance(TestContext ctx) throws Exception { }

	
	
	
}
