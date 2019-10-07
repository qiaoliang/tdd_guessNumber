import java.util.ArrayList;
import java.util.List;

public class BoxGameController {

    private AnswerInput answerInput = null;
    private RandomAnswerGenerator randomAnswerGenerator =null;
    private String answer ="";
    private int round = 0;

    public BoxGameController(RandomAnswerGenerator randomAnswerGenerator, AnswerInput answerInput) {
        this.randomAnswerGenerator= randomAnswerGenerator;
        this.answerInput = answerInput;
    }
    public String getGameResult() {
        if(answer.equals("4A0B"))
            return "player won!";
        else
            return "player lose!";
    }

    public void startGameWithinMaxRounds(int maxRounds) {
        BoxGame game = new BoxGame(randomAnswerGenerator.createAnAnswer());
        for (round = 0; round < maxRounds; round++) {
            answer = game.guess(answerInput.input());
            if (answer.equals("4A0B"))
                return;
        }
    }
}
