import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AnswerInput {
    Pattern pattern = Pattern.compile("^[0-9]\\s+[0-9]\\s+[0-9]\\s+[0-9]$");
    public String input() {
        boolean isValid = false;
        String stringInputs= null;
        while(!isValid){
            stringInputs = getStringInputs();
            isValid = validate(stringInputs);
        }
        return stringInputs;
    }
    abstract protected String getStringInputs();
    protected boolean validate(String inputs){
        Matcher matcher = pattern.matcher(inputs);
        return matcher.find();
    }
}
