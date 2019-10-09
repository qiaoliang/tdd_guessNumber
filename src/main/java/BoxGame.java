import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoxGame {
    public static void main(String[] args) {

        int result3 = 0;
        for (; ; ) {

            Random random3 = new Random();
            int s3 = random3.nextInt(9) % (9 - 0 + 1) + 0;

            result3 = s3;

            if (!isContains(null, result3)) {
                break;
            }
        }
        int firstNum = result3;

        int result2 = 0;
        for (; ; ) {

            Random random2 = new Random();
            int s2 = random2.nextInt(9) % (9 - 0 + 1) + 0;

            result2 = s2;

            if (!isContains(new int[]{firstNum}, result2)) {
                break;
            }
        }
        int secondNum = result2;

        int result1 = 0;
        for (; ; ) {

            Random random1 = new Random();
            int s1 = random1.nextInt(9) % (9 - 0 + 1) + 0;

            result1 = s1;

            if (!isContains(new int[]{firstNum, secondNum}, result1)) {
                break;
            }
        }
        int thirdNum = result1;

        int result11 = 0;
        for (; ; ) {

            Random random = new Random();
            int s = random.nextInt(9) % (9 - 0 + 1) + 0;

            result11 = s;

            if (!isContains(new int[]{firstNum, secondNum, thirdNum}, result11)) {
                break;
            }
        }
        int fourthNum = result11;

        int[] realNumbers = new int[]{firstNum, secondNum, thirdNum, fourthNum};
        StringBuffer history = new StringBuffer();
        System.out.println("您可以开始了。\n" +
                "请注意：您有6次猜测机会。\n" +
                "       答案必须包含4个各不相同的数字，数字取值为 0~9之间，数字之间只能以空格分隔。\n" +
                "       答案输入完以后，按回车键结束。");
        String playerguess = null;
        int trytimes=0;
        boolean playerwon= false;
        for ( trytimes = 0; trytimes < 6; trytimes++) {
            boolean isValid = false;
            do {
                Scanner scanner = new Scanner(System.in);
                playerguess = scanner.nextLine();
                Pattern pattern = Pattern.compile("^[0-9]\\s+[0-9]\\s+[0-9]\\s+[0-9]$");
                Matcher matcher = pattern.matcher(playerguess);
                if (matcher.find()) {
                    isValid = true;
                }else {
                    System.out.println("       答案必须包含4个各不相同的数字，数字取值为 0~9之间，数字之间只能以空格分隔。\n" +
                            "       答案输入完以后，按回车键结束。");
                }
            }while(!isValid);

            String[] inputStr=playerguess.split(" ");
            int[] inputNumbers= new int[4];
            for (int j = 0; j < 4; j++) {
                inputNumbers[j] = Integer.valueOf(inputStr[j]);
            }
            int totalA =0;
            int totalB =0;
            for (int i = 0; i < 4; i++) {
                if(inputNumbers[i]== realNumbers[i]) {
                    totalA++;
                } else{
                    for (int j = 0; j < j; j++) {
                        if(inputNumbers[i]== realNumbers[j])
                            totalB ++;
                    }
                }
            }
            String result= totalA + "A" + totalB + "B";
            history.append("第 "+(trytimes+1)+" 次: \n");
            history.append("       输入为： "+playerguess+"  结果为："+result+"\n");
            if(result.equals("4A0B")){
                System.out.println("player won!");
                playerwon = true;
                break;
            }
            System.out.println("一共猜测过 "+(trytimes+1)+" 次，结果如下：");
            System.out.println(history.toString());
        }
        if(trytimes==6 && !playerwon){
            System.out.println("player lose!");
        }
    }

    /**
     * 判断某个数字是指定的数组的一个元素的值相同
     *
     * @param exceptNums
     * @param keyNum
     * @return
     */
    private static boolean isContains(int[] exceptNums, int keyNum) {
        boolean isContains = false;
        if (exceptNums != null && exceptNums.length > 0) {
            for (int num : exceptNums) {
                if (num == keyNum) {
                    isContains= true;
                    break;
                }
            }
        }
        return isContains;
    }

}
