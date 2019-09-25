import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
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
        assertThat(new BoxGame("1 7 8 9").guess(para), startsWith(expected));
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
        assertThat(new BoxGame("1 7 8 9").guess(para), startsWith(expected));
    }
    @ParameterizedTest
    @CsvSource({
            "1 7 3 9, 3A",
            "1 7 8 0, 3A",
    })
    public void
    should_return_3A_when_three_numbers_are_fully_matched_by_number_and_position(String para, String expected) throws Exception {
        assertThat(new BoxGame("1 7 8 9").guess(para), startsWith(expected));
    }

    @ParameterizedTest
    @CsvSource({
            "1 7 8 9, 4A",
    })
    public void
    should_return_4A_when_four_numbers_are_fully_matched_by_number_and_position(String para, String expected) throws Exception {
        assertThat(new BoxGame("1 7 8 9").guess(para), startsWith(expected));
    }

}