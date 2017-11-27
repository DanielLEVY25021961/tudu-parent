/**
 * 
 */
package tudu.service.notify;

/**
 * @author Arnaud Cogoluegnes
 *
 */
public interface INotifier {


	/**
	 * method notify() :<br/>
	 * Notify interested listeners.<br/>
	 * <br/>
	 *
	 * @param pMessage : IMessage :  .<br/>
	 */
	public void notify(IMessage pMessage);
	
}
