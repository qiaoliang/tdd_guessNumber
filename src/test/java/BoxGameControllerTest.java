import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BoxGameControllerTest {

    private RandomAnswerGenerator randomAnswerGenerator;
    private AnswerInput answerInput;
    private BoxGameController boxGameController;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final String PrintTextBeforeStartGame = "您可以开始了。\n" +
            "请注意：您有六次猜测机会。\n" +
            "       答案必须包含4个各不相同的数字，数字取值为 0~9之间，数字之间只能以空格分隔。\n" +
            "       答案输入完以后，按回车键结束。\n";


    @AfterEach
    public void afterTestClass() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @BeforeEach
    public void
    setup() {
        randomAnswerGenerator = mock(RandomAnswerGenerator.class);
        answerInput = mock(AnswerInput.class);

        boxGameController = new BoxGameController(randomAnswerGenerator, answerInput);
        when(randomAnswerGenerator.createAnAnswer()).thenReturn("1 2 3 4");
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

    }

    @Test
    public void
    should_print_tips_before_guess() throws Exception {
        String firsRightAnswer = "1 2 3 4";
        when(answerInput.input()).thenReturn(firsRightAnswer);
        boxGameController.startGameWithinMaxRounds(1);
        assertThat(outContent.toString().startsWith(PrintTextBeforeStartGame),is(true));
    }
    @Test
    public void
    should_print_answer_history_before_guess() throws Exception {
        String firsRightAnswer = "1 5 3 4";
        String secondAnswer = "1 4 2 3";
        String expectedHistory = "一共猜测过 1 次，结果如下：\n"
                +"第 1 次: \n" +
                "       输入为： 1 5 3 4  结果为：3A0B\n";
        when(answerInput.input()).thenReturn(firsRightAnswer).thenReturn(secondAnswer);
        boxGameController.startGameWithinMaxRounds(2);
        assertThat(outContent.toString().startsWith(PrintTextBeforeStartGame),is(true));
        assertThat(outContent.toString().endsWith(expectedHistory),is(true));

    }
    @Test
    public void
    should_exit_as_soon_as_the_answer_is_right() throws Exception {
        String firsRightAnswer = "1 2 3 4";
        String[] nextFiveWrongAnswersGuessed = new String[]{"5 7 8 9", "8 7 6 9", "4 3 2 1", "5 1 3 4", "7 1 3 4"};
        when(answerInput.input()).thenReturn(firsRightAnswer, nextFiveWrongAnswersGuessed);

        boxGameController.startGameWithinMaxRounds(6);

        assertThat(boxGameController.getGameRound(), is(1));
        assertThat(boxGameController.getGameResult(), is("player won!"));
    }


    @Test
    public void
    should_exit_after_guess_6_times_with_wrong_answers() throws Exception {

        String firsWrongtAnswer = "5 6 7 8";
        String[] nextAnswersGuessed = new String[]{"5 7 8 9", "8 7 6 9", "4 3 2 1", "5 1 3 4", "7 1 3 4", "1 3 8 4"};

        when(answerInput.input()).thenReturn(firsWrongtAnswer, nextAnswersGuessed);

        boxGameController.startGameWithinMaxRounds(6);

        assertThat(boxGameController.getGameRound(), is(6));
        assertThat(boxGameController.getGameResult(), is("player lose!"));
    }

    @Test
    public void
    should_exit_immediately_as_soon_as_the_answer_is_correct_and_total_times_is_no_more_than_6_times() throws Exception {
        String firsWrongtAnswer = "5 6 7 8";
        String[] nextFiveAnswersGuessedWithLastRight = new String[]{"5 7 8 9", "8 7 6 9", "4 3 2 1", "5 1 3 4", "1 2 3 4"};
        when(answerInput.input()).thenReturn(firsWrongtAnswer, nextFiveAnswersGuessedWithLastRight);

        boxGameController.startGameWithinMaxRounds(6);

        assertThat(boxGameController.getGameRound(), is(6));
        assertThat(boxGameController.getGameResult(), is("player won!"));
    }

}