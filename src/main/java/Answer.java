public class Answer {
    private String playerAnswer;
    private String result;

    public Answer(String answer, String result) {
        this.playerAnswer = answer;
        this.result = result;
    }
    public String answer(){
        return playerAnswer;
    }
    public String result(){
        return result;
    }
    public String toString(){
        return playerAnswer + " " + result;
    }
}
