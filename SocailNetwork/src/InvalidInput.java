public class InvalidInput extends Exception{
    public InvalidInput(){
        super("Warning!\n" +
                "Your input value is invalid.");
    }
    public InvalidInput(String msg){
        super(msg);
    }
}