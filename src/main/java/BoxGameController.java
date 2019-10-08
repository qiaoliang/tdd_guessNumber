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

    public String startGameWithinMaxRounds(int maxRounds) {
        String CorrectAnswer = randomAnswerGenerator.createAnAnswer();
        BoxGame game = new BoxGame(CorrectAnswer, maxRounds, answerInput);
        game.start();
        return game.getGameResult();
    }
}
