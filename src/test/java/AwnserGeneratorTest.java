import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.not;
public class AwnserGeneratorTest {
    @Test public void
    should_should_generate_an_awnser_with_four_numbers() throws Exception{
        String result = new AnswerGenerator().createAnAnswer();
        assertThat(result,is("1 1 1 1"));
    }

}