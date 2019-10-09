import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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
        when(randomAnswerGenerator.CreateFinalAnswer()).thenReturn(new Answer("1 2 3 4","4A0B"));
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

    }
    @Test
    public void
    should_print_player_result() throws Exception {
        String correctAnswer = "1 2 3 4";
        when(answerInput.input()).thenReturn(correctAnswer);
        boxGameController.startGameWithinMaxRounds(1);
        assertThat(outContent.toString(), containsString("player won!"));
        String wrongAnswer = "2 5 3 4";
        when(answerInput.input()).thenReturn(wrongAnswer);
        boxGameController.startGameWithinMaxRounds(1);
        assertThat(outContent.toString(), containsString("player lose!"));
    }
}