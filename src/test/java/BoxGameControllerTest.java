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
    should_exit_immediately_as_soon_as_the_answer_is_correct_and_total_times_is_no_more_than_6_times() throws Exception {
        String firsWrongtAnswer = "5 6 7 8";
        String[] nextFiveAnswersGuessedWithLastRight = new String[]{"5 7 8 9", "8 7 6 9", "4 3 2 1", "5 1 3 4", "1 2 3 4"};
        when(answerInput.input()).thenReturn(firsWrongtAnswer, nextFiveAnswersGuessedWithLastRight);

        String result = boxGameController.startGameWithinMaxRounds(6);

        assertThat(result, is("player won!"));
    }
}