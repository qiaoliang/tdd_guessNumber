import java.util.*;

public class AnswerGenerator {

    public String createAnAnswer() {
        ArrayList <String> t = new ArrayList <>() ;
        t.addAll(Arrays.asList(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}));
        StringBuffer result = new StringBuffer();
        while(!isEnoughNumbers(t)) {
            int position = new Random().nextInt(t.size());
            result.append(" ").append(t.get(position));
            t.remove(position);
        }
        return result.toString().trim();
    }

    private boolean isEnoughNumbers(List<String> t) {
        return t.size()<7;
    }
}
