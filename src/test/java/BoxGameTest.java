import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BoxGameTest {

    private AnswerInput answerInput;
    private BoxGame boxGame;

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
        answerInput = mock(AnswerInput.class);
        boxGame= new BoxGame("1 7 8 9", 6, answerInput);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

    }

    @Test
    public void
    should_return_0A_when_no_number_is_fully_matched() throws Exception {
        assertThat(boxGame.guess("2 3 4 5").result().startsWith("0A"), is(true));
    }

    @ParameterizedTest
    @CsvSource({
            "1 2 3 4, 1A",
            "0 7 3 4, 1A",
            "0 3 8 4, 1A",
            "0 2 4 9, 1A",
    })
    public void
    should_return_1A_when_only_one_number_is_fully_matched(String para, String expected) throws Exception {
        assertThat(boxGame.guess(para).result(), startsWith(expected));
    }

    @ParameterizedTest
    @CsvSource({
            "1 2 3 9, 2A",
            "1 7 3 4, 2A",
            "0 3 8 9, 2A",
            "1 3 8 0, 2A",
    })
    public void
    should_return_2A_when_two_numbers_are_fully_matched_by_number_and_position(String para, String expected) throws Exception {
        assertThat(boxGame.guess(para).result(), startsWith(expected));
    }
    @ParameterizedTest
    @CsvSource({
            "1 7 3 9, 3A",
            "1 7 8 0, 3A",
    })
    public void
    should_return_3A_when_three_numbers_are_fully_matched_by_number_and_position(String para, String expected) throws Exception {
        assertThat(boxGame.guess(para).result(), startsWith(expected));
    }

    @ParameterizedTest
    @CsvSource({
            "1 7 8 9, 4A",
    })
    public void
    should_return_4A_when_four_numbers_are_fully_matched_by_number_and_position(String para, String expected) throws Exception {
        assertThat(boxGame.guess(para).result(), startsWith(expected));
    }
    @ParameterizedTest
    @CsvSource({
            "2 3 4 5, 0B",
            "7 3 4 5, 1B",
            "2 3 7 5, 1B",
            "9 3 7 5, 2B",
            "9 3 5 7, 2B",
            "9 8 5 7, 3B",
            "9 8 1 7, 4B",
    })
    public void
    should_endwith_XB_when_number_are_matched_partialy_by_only_number(String para, String expected) throws Exception {
        assertThat(boxGame.guess(para).result(), endsWith(expected));
    }
    @ParameterizedTest
    @CsvSource({
            "1 3 7 5, 1A1B",
            "1 3 7 9, 2A1B",
    })
    public void
    should_return_xAyB_format_when_some_number_are_fully_matched_and_other_numbers_are_partialy_matched(String para, String expected) throws Exception {
        assertThat(boxGame.guess(para).result(), is(expected));
    }
    @Test
    public void
    should_print_tips_before_guess() throws Exception {
        String PrintTextBeforeStartGame = "您可以开始了。\n" +
                "请注意：您有6次猜测机会。\n" +
                "       答案必须包含4个各不相同的数字，数字取值为 0~9之间，数字之间只能以空格分隔。\n" +
                "       答案输入完以后，按回车键结束。\n";

        String anyAnswer = "1 2 3 4";
        when(answerInput.input()).thenReturn(anyAnswer);
        
        boxGame.start();

        assertThat(outContent.toString(),startsWith(PrintTextBeforeStartGame));
    }
    @Test
    public void
    should_print_answer_history_before_guess() throws Exception {
        boxGame= new BoxGame("1 7 8 9", 2, answerInput);
        String firsRightAnswer = "1 5 3 4";
        String secondAnswer = "1 4 2 3";
        String expectedHistory = "一共猜测过 2 次，结果如下：\n"
                +"第 1 次: \n" +
                "       输入为： 1 5 3 4  结果为：3A0B\n";
        when(answerInput.input()).thenReturn(firsRightAnswer).thenReturn(secondAnswer);

        boxGame.start();
        
        assertThat(outContent.toString(), containsString("第 1 次: "));
    }
    @Test
    public void
    should_exit_as_soon_as_the_answer_is_right() throws Exception {
        String firsRightAnswer = "1 7 8 9";
        String[] nextFiveWrongAnswersGuessed = new String[]{"5 7 8 9", "8 7 6 9", "4 3 2 1", "5 1 3 4", "7 1 3 4"};
        when(answerInput.input()).thenReturn(firsRightAnswer, nextFiveWrongAnswersGuessed);

        boxGame.start();

        assertThat(boxGame.getGameRound(), is(1));
        assertThat(boxGame.getGameResult(), is("player won!"));
    }
    @Test
    public void
    should_exit_after_guess_6_times_with_wrong_answers() throws Exception {

        String firsWrongtAnswer = "5 6 7 8";
        String[] nextAnswersGuessed = new String[]{"5 7 8 9", "8 7 6 9", "4 3 2 1", "5 1 3 4", "7 1 3 4", "1 3 8 4"};

        when(answerInput.input()).thenReturn(firsWrongtAnswer, nextAnswersGuessed);

        boxGame.start();

        assertThat(boxGame.getGameRound(), is(6));
        assertThat(boxGame.getGameResult(), is("player lose!"));
    }

    @Test
    public void
    should_exit_immediately_as_soon_as_the_answer_is_correct_and_total_times_is_no_more_than_6_times() throws Exception {
        String firsWrongtAnswer = "5 6 7 8";
        String[] nextFiveAnswersGuessedWithLastRight = new String[]{"5 7 8 9", "8 7 6 9", "4 3 2 1", "5 1 3 4", "1 7 8 9"};
        when(answerInput.input()).thenReturn(firsWrongtAnswer, nextFiveAnswersGuessedWithLastRight);

        boxGame.start();

        assertThat(boxGame.getGameRound(), is(6));
        assertThat(boxGame.getGameResult(), is("player won!"));
    }
}