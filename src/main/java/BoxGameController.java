public class BoxGameController {

    private AnswerInput answerInput;
    private RandomAnswerGenerator randomAnswerGenerator;

    public BoxGameController(RandomAnswerGenerator randomAnswerGenerator, AnswerInput answerInput) {
        this.randomAnswerGenerator= randomAnswerGenerator;
        this.answerInput = answerInput;
    }

    public void startGameWithinMaxRounds(int maxRounds) {
        Answer CorrectAnswer = randomAnswerGenerator.CreateFinalAnswer();
        BoxGame game = new BoxGame(CorrectAnswer, maxRounds, answerInput);
        game.start();
        System.out.println(game.getGameResult());
    }

    public static void main(String[] args) {
        BoxGameController controller = new BoxGameController(new RandomAnswerGenerator(),new ConsoleInput());
        controller.startGameWithinMaxRounds(6);
    }

}
