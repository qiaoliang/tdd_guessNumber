import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class AnswerInputTest {

    private AnswerInput inputs;

    @BeforeEach
    public void
    setup(){
        inputs = new AnswerInput() {
            @Override
            protected String getStringInputs() {
                return "1 2 3 4";
            }
        };
    }

    @Test public void
    should_return_true_when_there_are_4_numbers_seperated_with_spaces() throws Exception{
        assertThat(inputs.validate("1 2 3 4"),is(true));
        assertThat(inputs.validate("2 2 2 2"),is(true));
        assertThat(inputs.validate("   2 2 2 2   "),is(false));
    }
    @ParameterizedTest
    @CsvSource({
            "12 3 4 5",
            "12 s 4 5",
            "1 3 4",
            "1 13 4 5",
            "1 3 44 5",
            "1 3 4 15",
            "1 3 4 15 6",
    })
    public void
    should_return_false_when_input_is_not_valid( String para) throws Exception {
        assertThat(inputs.validate(para),is(false));
    }
}