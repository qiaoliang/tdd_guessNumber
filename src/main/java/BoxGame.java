import java.util.Random;

public class BoxGame {


    /**
     * 随机生成制定范围内的数字
     * @param min
     * @param max
     * @return
     */
    public static int getRandom(int min, int max)
    {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;

    }

    /**
     * 判断某个数字是指定的数组的一个元素的值相同
     * @param exceptNums
     * @param keyNum
     * @return
     */
    private static  boolean isContains(int[] exceptNums, int keyNum){
        if(exceptNums != null && exceptNums.length > 0){
            for(int num : exceptNums){
                if(num == keyNum){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取指定范围的一个随机数字，但是该随机数不能包含在exceptNums中
     * @param min
     * @param max
     * @param exceptNums
     * @return
     */
    public static int getRandomExceptNum(int min, int max, int[] exceptNums){

        int result = 0;
        for(;;){

            result = getRandom(min, max);

            if(!isContains(exceptNums, result)){
                return result;
            }
        }
    }

    /**
     * 随机获取四个不相同的数字，数字范围0～9
     * @return
     */
    public static int[] getFourNumber(){

        int firstNum = getRandomExceptNum(0, 9, null);
        int secondNum = getRandomExceptNum(0, 9, new int[]{firstNum} );
        int thirdNum = getRandomExceptNum(0, 9, new int[]{firstNum, secondNum} );
        int fourthNum = getRandomExceptNum(0, 9, new int[]{firstNum, secondNum, thirdNum} );

        return new int[]{firstNum, secondNum, thirdNum, fourthNum} ;
    }

}
