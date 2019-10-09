import java.util.Scanner;
import java.util.regex.Matcher;

public class ConsoleInput extends AnswerInput {
    public static String FORMAT_TIPS =
            "       答案必须包含4个各不相同的数字，数字取值为 0~9之间，数字之间只能以空格分隔。\n" +
            "       答案输入完以后，按回车键结束。";

    @Override
    protected String getStringInputs() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    protected boolean validate(String inputs) {
        Matcher matcher = pattern.matcher(inputs);
        if (matcher.find())
            return true;
        System.out.println(FORMAT_TIPS);
        return false;
    }
}
