public class BoxGameController {

    private AnswerInput answerInput;
    private RandomAnswerGenerator randomAnswerGenerator;

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

    public static void main(String[] args) {
        BoxGameController controller = new BoxGameController(new RandomAnswerGenerator(),new ConsoleInput());
        controller.startGameWithinMaxRounds(2);
    }
}
