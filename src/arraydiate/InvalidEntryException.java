package arraydiate;

public class InvalidEntryException extends Exception{
    /**
	 * 
	 */
	
	private int invalidNum;
    public InvalidEntryException( int num ){
        invalidNum = num;
    }
    public int getInvalidQuantity(){
        return invalidNum;
    }
    
    private static final long serialVersionUID = 616428939967420978L;
}
