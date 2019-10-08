import java.util.ArrayList;

public class BoxGameController {

    private AnswerInput answerInput;
    private RandomAnswerGenerator randomAnswerGenerator;
    private ArrayList<Answer> answerHistory = new ArrayList<>();
    private int round = 0;
    private String tipsBeforeGame ="您可以开始了。\n" +
            "请注意：您有六次猜测机会。\n" +
            "       答案必须包含4个各不相同的数字，数字取值为 0~9之间，数字之间只能以空格分隔。\n" +
            "       答案输入完以后，按回车键结束。";

    public BoxGameController(RandomAnswerGenerator randomAnswerGenerator, AnswerInput answerInput) {
        this.randomAnswerGenerator= randomAnswerGenerator;
        this.answerInput = answerInput;
    }
    public String getGameResult() {
        Answer lastAnswer = (Answer) answerHistory.get(answerHistory.size()-1);
        if(lastAnswer.result().equals("4A0B"))
            return "player won!";
        else
            return "player lose!";
    }

    public void startGameWithinMaxRounds(int maxRounds) {
        String CorrectAnswer = randomAnswerGenerator.createAnAnswer();
        BoxGame game = new BoxGame(CorrectAnswer, maxRounds, answerInput);
        do {
            round++;
            if(round==1)
                System.out.println(tipsBeforeGame);
            else {
                printHistory();
            }
            String input = answerInput.input();
            Answer result = game.guess(input);
            answerHistory.add(result);
            if (result.result().equals("4A0B"))
                return;
        }while (round < maxRounds);
    }

    private void printHistory() {
        System.out.println("一共猜测过 "+answerHistory.size()+" 次，结果如下：");
        for (int i = 0; i < answerHistory.size(); i++) {
            System.out.println("第 "+(i+1)+" 次: ");
            Answer each = (Answer)answerHistory.get(i);
            System.out.println("       输入为： "+each.answer()+"  结果为："+each.result());
        }
    }

    public int getGameRound() {
        return round;
    }
}
