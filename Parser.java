public class Parser {
    private String cmd;
    private String [] args;
    public Parser(String [] commands){
        cmd = commands[0];
        args = commands;
    }
    public String getCmd(){
        return cmd;
    }
    public String getFirstArg(){
        if(args.length < 2)
            return "";
        else {
            return args[1];
        }
    }
    public String getSecondArg(){
        if(args.length < 3)
            return "";
        else {
            return args[2];
        }
    }
}
