import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class BoxGameTest {

    private BoxGame boxGame = new BoxGame("1 7 8 9");

    @Test
    public void
    should_return_0A_when_no_number_is_fully_matched() throws Exception {
        assertThat(boxGame.guess("2 3 4 5").startsWith("0A"), is(true));
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
        assertThat(boxGame.guess(para), startsWith(expected));
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
        assertThat(boxGame.guess(para), startsWith(expected));
    }
    @ParameterizedTest
    @CsvSource({
            "1 7 3 9, 3A",
            "1 7 8 0, 3A",
    })
    public void
    should_return_3A_when_three_numbers_are_fully_matched_by_number_and_position(String para, String expected) throws Exception {
        assertThat(boxGame.guess(para), startsWith(expected));
    }

    @ParameterizedTest
    @CsvSource({
            "1 7 8 9, 4A",
    })
    public void
    should_return_4A_when_four_numbers_are_fully_matched_by_number_and_position(String para, String expected) throws Exception {
        assertThat(boxGame.guess(para), startsWith(expected));
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
        assertThat(boxGame.guess(para), endsWith(expected));
    }
    @ParameterizedTest
    @CsvSource({
            "1 3 7 5, 1A1B",
            "1 3 7 9, 2A1B",
    })
    public void
    should_return_1A1B_when_some_number_are_fully_matched_and_other_numbers_are_partialy_matched(String para, String expected) throws Exception {
        assertThat(boxGame.guess(para), is(expected));
    }
}