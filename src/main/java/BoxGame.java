import java.util.ArrayList;

public class BoxGame {

    private final Answer rightAnswer;
    private final String rightAnswerString;
    private final String[] rightAnswerList;
    private final AnswerInput answerInputer;
    private final int maxRound;
    private ArrayList <Answer> answerHistory = new ArrayList<>();
    private final String tipsBeforeGame;

    public BoxGame(Answer actualAnswer, int maxRound, AnswerInput answerInputer) {
        this.rightAnswer = actualAnswer;
        this.rightAnswerString = rightAnswer.answer();
        rightAnswerList = rightAnswerString.split("\\s");
        this.answerInputer = answerInputer;
        this.maxRound = maxRound;
        tipsBeforeGame ="您可以开始了。\n" +
                "请注意：您有"+ maxRound +"次猜测机会。\n" +
                "       答案必须包含4个各不相同的数字，数字取值为 0~9之间，数字之间只能以空格分隔。\n" +
                "       答案输入完以后，按回车键结束。";

    }

    public BoxGame(String rightAnswerString, int maxRound, AnswerInput answerInputer) {
        this(new Answer(rightAnswerString,"4A0B"),maxRound,answerInputer);
    }

    public Answer guess(String answer) {
        int fullyMatches = 0;
        int partialMatches= 0;
        String[] answerByPlayers = answer.split("\\s");
        for (int i = 0; i < rightAnswerList.length; i++) {
            if(rightAnswerList[i].equals(answerByPlayers[i])) {
                fullyMatches++;
            }else if(rightAnswerString.contains(answerByPlayers[i])){
                partialMatches++;
            }
        }
        return new Answer(answer,fullyMatches+"A"+partialMatches+"B");
    }
    public void start(){

        for (int round = 0; round < maxRound; round++) {
            if(answerHistory.size()==0)
                System.out.println(tipsBeforeGame);
            else {
                printHistory();
            }
            String input = answerInputer.input();
            Answer result = guess(input);
            answerHistory.add(result);
            if (isCorrectAnswer(result))
                return;
        }
    }

    private boolean isCorrectAnswer(Answer result) {
        return result.toString().equals(rightAnswer.toString());
    }

    private void printHistory() {
        System.out.println("一共猜测过 "+answerHistory.size()+" 次，结果如下：");
        for (int i = 0; i < answerHistory.size(); i++) {
            System.out.println("第 "+(i+1)+" 次: ");
            Answer each = (Answer)answerHistory.get(i);
            System.out.println("       输入为： "+each.answer()+"  结果为："+each.result());
        }
    }

    public int getGameRoundAfterGameOver() {
        return answerHistory.size();
    }

    public String getGameResult() {
        Answer lastAnswer = answerHistory.get(answerHistory.size()-1);
        if(isCorrectAnswer(lastAnswer))
            return "player won!";
        else
            return "player lose!";
    }
}
