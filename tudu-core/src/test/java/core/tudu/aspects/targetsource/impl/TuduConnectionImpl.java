/**
 * 
 */
package tudu.aspects.targetsource.impl;

import tudu.aspects.targetsource.ITuduConnection;

/**
 * @author Arnaud Cogoluegnes
 *
 */
public class TuduConnectionImpl implements ITuduConnection {

	
	/**
	 * name : String :<br/>
	 * .<br/>
	 */
	private String name;	

	
	
	
	 /**
	 * method CONSTRUCTEUR TuduConnectionImpl() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pName
	 */
	public TuduConnectionImpl(
			final String pName) {
		super();
		this.name = pName;
	}

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return this.name;
	}
	
	
	
}
