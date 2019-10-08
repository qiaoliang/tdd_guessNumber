public class BoxGame {

    private final String rightAnswer;
    private final String[] rightAnswerList;
    private final AnswerInput answerInputer;
    private String tipsBeforeGame = null;

    public BoxGame(String actualAnswer, int maxRound, AnswerInput answerInputer) {
        this.rightAnswer = actualAnswer;
        rightAnswerList = actualAnswer.split("\\s");
        this.answerInputer = answerInputer;
        tipsBeforeGame ="您可以开始了。\n" +
                "请注意：您有"+maxRound+"次猜测机会。\n" +
                "       答案必须包含4个各不相同的数字，数字取值为 0~9之间，数字之间只能以空格分隔。\n" +
                "       答案输入完以后，按回车键结束。";
    }

    public Answer guess(String answer) {
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
