import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class AwnserGeneratorTest {
    @Test
    public void
    should_generate_an_answer_of_four_numbers_and_those_numbers_is_different_with_each_other()  {
        String result = new AnswerGenerator().createAnAnswer();
        String[] answers = result.split(" ");
        Set box = new HashSet<Integer>();
        for (String each :
                answers) {
            Integer actual = Integer.valueOf(each).intValue();
            assertThat(actual, isA(Integer.TYPE));
            box.add(actual);
        }
        assertThat(box.size(),is(4));
    }
}