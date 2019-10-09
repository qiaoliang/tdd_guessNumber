import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConsoleInputTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private AnswerInput inputs= new ConsoleInput();
    @BeforeEach
    public void
    setup(){
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

    }
    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test public void
    should_print_tips_when_input_format_is_wrong() throws Exception{
        inputs.validate("unvalid input");
        assertThat(outContent.toString(), containsString(ConsoleInput.FORMAT_TIPS));
    }
    @Test public void
    should_not_print_out_tips_when_input_format_is_correct() throws Exception{
        inputs.validate("1 3 3 4");
        assertThat(outContent.toString().contains(ConsoleInput.FORMAT_TIPS),is(false));
    }
}