import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleInstrumentedTest {


    Logger Log = Logger.getLogger(ExampleInstrumentedTest.class);

    @Test
    public void testgetRandomExceptNum(){

        int[] tmp = new int[]{1, 2, 3, 4};

        int result = BoxGame.getRandomExceptNum(0, 9, tmp);

        boolean isContains = false;
        for(int num : tmp){
            if(num == result){
                isContains = true;
                break;
            }
        }

        Log.info("ExampleInstrumentedTest: result=" + result);

        assertFalse(isContains);
    }

    @Test
    public void testgetFourNumber(){

        int[] tmp = BoxGame.getFourNumber();

        boolean mark = tmp[0] != tmp[1] && tmp[1] != tmp[2] && tmp[2] != tmp[3];

        for(int num : tmp){
            Log.info("ExampleInstrumentedTest: num=" + num);
        }

        assertTrue(mark);
    }






}
