import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class AnwserGeneratorTest {
    @Test
    public void
    should_generate_an_answer_of_four_numbers_and_those_numbers_is_different_with_each_other()  {
        String result = new RandomAnswerGenerator().createAnAnswerString();
        String[] answers = result.split("\\s");
        assertThat(answers.length,is(4));
        assertEachNumberIsdefferent(answers);
    }

    private void assertEachNumberIsdefferent(String[] answers) {
        Set box = new HashSet <Integer>();
        for (String each :
                answers) {
            Integer actual = Integer.valueOf(each);
            assertThat(actual, isA(Integer.TYPE));
            box.add(actual.intValue());
        }
        assertThat(box.size(),is(4));
    }
}