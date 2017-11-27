/**
 * 
 */
package tudu.aspects.targetsource;

/**
 * @author Arnaud Cogoluegnes
 *
 */
public class TuduConnectionUser {

	/**
	 * tuduConnection : ITuduConnection :<br/>
	 * .<br/>
	 */
	private ITuduConnection tuduConnection;

	
	
	/**
	 * method getTuduConnection() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @return :  :  .<br/>
	 */
	public ITuduConnection getTuduConnection() {
		return this.tuduConnection;
	}

	
	
	/**
	 * method setTuduConnection() :<br/>
	 * .<br/>
	 * <br/>
	 *
	 * @param pTuduConnection :  :  .<br/>
	 */
	public void setTuduConnection(
			final ITuduConnection pTuduConnection) {
		this.tuduConnection = pTuduConnection;
	}	

	
	
}
