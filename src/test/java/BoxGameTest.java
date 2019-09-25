import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class BoxGameTest {
    @Test public void
    should_return_0A_when_no_number_is_fully_matched() throws Exception{
        assertThat(new BoxGame("1 1 1 1").guess("2 3 4 5").startsWith("0A"),is(true));
    }
    @Test public void
    should_return_1A_when_only_one_number_is_fully_matched() throws Exception{
        assertThat(new BoxGame("1 1 1 1").guess("1 2 3 4"),startsWith("1A"));
    }
    @ParameterizedTest
    @CsvSource({
            "1 2 3 4, 1A",
            "0 1 3 4, 1A",
            "0 3 1 4, 1A",
            "0 3 4 1, 1A",
    })
    public void
    should_return_1A_when_only_one_number_is_fully_matched(String para, String expected ) throws Exception {
        assertThat(new BoxGame("1 1 1 1").guess(para),startsWith(expected));
    }

}