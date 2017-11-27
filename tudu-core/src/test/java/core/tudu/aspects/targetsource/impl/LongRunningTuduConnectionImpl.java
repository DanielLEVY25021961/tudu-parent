/**
 * 
 */
package tudu.aspects.targetsource.impl;

import tudu.aspects.targetsource.ITuduConnection;

/**
 * @author Arnaud Cogoluegnes
 *
 */
public class LongRunningTuduConnectionImpl implements ITuduConnection {

	/**
	 * name : String :<br/>
	 * .<br/>
	 */
	private String name;	

	
	
	 /**
	 * method CONSTRUCTEUR LongRunningTuduConnectionImpl() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pName
	 */
	public LongRunningTuduConnectionImpl(
			final String pName) {
		super();
		this.name = pName;
		System.out.println("passe");
	}


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		try {
			Thread.sleep(500);
			return this.name;
		} catch (InterruptedException e) {
			return null;
		}		
	}
	
}
