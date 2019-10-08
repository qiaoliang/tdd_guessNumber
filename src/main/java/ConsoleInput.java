import java.util.Scanner;

public class ConsoleInput extends AnswerInput {

    @Override
    protected String getStringInputs() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
