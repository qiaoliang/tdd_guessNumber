import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BoxGameControllerTest {

        @Test public void
        should_exit_after_guess_6_times_with_wrong_answers() throws Exception{
            RandomAnswerGenerator randomAnswerGenerator = mock(RandomAnswerGenerator.class);
            AnswerInput answerInput = mock(AnswerInput.class);
            when(randomAnswerGenerator.createAnAnswer()).thenReturn("1 2 3 4");
            String firsWrongtAnswer = "5 6 7 8";
            String[] nextFiveWrongAnswersGuessed= new String[]{"5 7 8 9","8 7 6 9","4 3 2 1","5 1 3 4","7 1 3 4"};
            when(answerInput.input()).thenReturn(firsWrongtAnswer,nextFiveWrongAnswersGuessed);

        BoxGameController boxGameController = new BoxGameController(randomAnswerGenerator,answerInput);
        boxGameController.startGameWithinMaxRounds(6);
        assertThat(boxGameController.getGameResult(),is("player lose!"));
    }

    @Test public void
    should_exit_immediately_as_soon_as_the_answer_is_correct_and_total_times_is_no_more_than_6_times() throws Exception{
        RandomAnswerGenerator randomAnswerGenerator = mock(RandomAnswerGenerator.class);
        AnswerInput answerInput = mock(AnswerInput.class);
        when(randomAnswerGenerator.createAnAnswer()).thenReturn("1 2 3 4");
        String firsWrongtAnswer = "5 6 7 8";
        String[] nextFiveAnswersGuessedWithLastRight= new String[]{"5 7 8 9","8 7 6 9","4 3 2 1","5 1 3 4","1 2 3 4"};
        when(answerInput.input()).thenReturn(firsWrongtAnswer,nextFiveAnswersGuessedWithLastRight);

        BoxGameController boxGameController = new BoxGameController(randomAnswerGenerator,answerInput);
        boxGameController.startGameWithinMaxRounds(6);
        assertThat(boxGameController.getGameResult(),is("player won!"));
    }

}