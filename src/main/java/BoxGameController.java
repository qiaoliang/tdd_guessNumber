public class BoxGameController {

    private AnswerInput answerInput;
    private RandomAnswerGenerator randomAnswerGenerator;
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
        do {
            round++;
            answer = game.guess(answerInput.input());
            if (answer.equals("4A0B"))
                return;
        }while (round < maxRounds);
    }

    public int getGameRound() {
        return round;
    }
}
