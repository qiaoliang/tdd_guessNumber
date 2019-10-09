import java.util.*;

public class RandomAnswerGenerator {

    public static final String SPLITTER = " ";

    public String createAnAnswerString() {
        ArrayList <String> t = new ArrayList <>() ;
        t.addAll(Arrays.asList(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}));
        String answer = "";
        while(!isEnoughNumbers(t)) {
            answer += drawNumberFromListRondomly(t);
        }
        return answer.trim();
    }
    public Answer CreateFinalAnswer(){
        return new Answer(createAnAnswerString(),"4A0B");
    }


    private boolean isEnoughNumbers(List<String> t) {
        return t.size()<7;
    }
    private String drawNumberFromListRondomly(List<String> t){
        int position = new Random().nextInt(t.size());
        String result=t.get(position);
        t.remove(position);
        return SPLITTER +result;
    }
}
