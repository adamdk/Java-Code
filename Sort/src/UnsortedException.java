/**
 * Handles exceptions if a list was not sorted properly.
 * @author Adam
 *
 */
public class UnsortedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UnsortedException() 
	{
		super();
	}
	public UnsortedException(String message)
	{
		super(message);
	}
	public UnsortedException(String message, Throwable throwable) {
		super(message, throwable);
	 }
	public UnsortedException(Throwable throwable) {
		super(throwable);
	}
	
	

}
