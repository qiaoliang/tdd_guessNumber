import java.util.ArrayList;
import java.util.List;

public class BoxGameController {

    private AnswerInput answerInput = null;
    private RandomAnswerGenerator randomAnswerGenerator =null;
    private String answer ="";


    public BoxGameController(RandomAnswerGenerator randomAnswerGenerator) {
        this(randomAnswerGenerator,null);
    }

    public BoxGameController(RandomAnswerGenerator randomAnswerGenerator, AnswerInput answerInput) {
        this.randomAnswerGenerator= randomAnswerGenerator;
        this.answerInput = answerInput;
    }
    public void startGame(){
        BoxGame game = new BoxGame(randomAnswerGenerator.createAnAnswer());
        answer = game.guess(answerInput.input());
    }
    public String getGameResult() {
        if(answer.equals("4A0B"))
            return "player won!";
        else
            return "player lose!";
    }
}
