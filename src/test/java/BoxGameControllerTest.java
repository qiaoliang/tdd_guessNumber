import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BoxGameControllerTest {
    @Test public void
    should_exit_with_success_when_guess_correctly() throws Exception{
        RandomAnswerGenerator randomAnswerGenerator = mock(RandomAnswerGenerator.class);
        AnswerInput answerInput = mock(AnswerInput.class);
        when(randomAnswerGenerator.createAnAnswer()).thenReturn("1 2 3 4");
        when(answerInput.input()).thenReturn("1 2 3 4");

        BoxGameController boxGameController = new BoxGameController(randomAnswerGenerator,answerInput);
        boxGameController.startGame();

        assertThat(boxGameController.getGameResult(),is("player won!"));
    }

    @Test public void
    should_exit_with_failed_when_guess_answer_is_wrong() throws Exception{
        RandomAnswerGenerator randomAnswerGenerator = mock(RandomAnswerGenerator.class);
        AnswerInput answerInput = mock(AnswerInput.class);
        when(randomAnswerGenerator.createAnAnswer()).thenReturn("1 2 3 4");
        when(answerInput.input()).thenReturn("5 6 7 8");

        BoxGameController boxGameController = new BoxGameController(randomAnswerGenerator,answerInput);
        boxGameController.startGame();

        assertThat(boxGameController.getGameResult(),is("player lose!"));
    }
}