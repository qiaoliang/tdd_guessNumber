import java.util.Random;

public class BoxGame {

    private final String rightAnswer;
    private final String[] rightAnswerList;

    public BoxGame(String actualAnswer) {
        this.rightAnswer = actualAnswer;
        rightAnswerList = actualAnswer.split("\\s");
    }

    public String guess(String answer) {
        int fullyMatches = 0;
        int partialMatches= 0;
        String[] answerByPlayers = answer.split("\\s");
        for (int i = 0; i < rightAnswerList.length; i++) {
            if(rightAnswerList[i].equals(answerByPlayers[i])) {
                fullyMatches++;
            }else if(rightAnswer.contains(answerByPlayers[i])){
                partialMatches++;
            }
        }
        Answer answerResult = new Answer(answer,fullyMatches+"A"+partialMatches+"B");
        return answerResult.result();
    }
    public Answer guessIt(String answer) {
        int fullyMatches = 0;
        int partialMatches= 0;
        String[] answerByPlayers = answer.split("\\s");
        for (int i = 0; i < rightAnswerList.length; i++) {
            if(rightAnswerList[i].equals(answerByPlayers[i])) {
                fullyMatches++;
            }else if(rightAnswer.contains(answerByPlayers[i])){
                partialMatches++;
            }
        }
        return new Answer(answer,fullyMatches+"A"+partialMatches+"B");
    }
}
