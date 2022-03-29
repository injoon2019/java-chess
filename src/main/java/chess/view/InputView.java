package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final String COMMAND_DELIMITER = " ";

    public static List<String> inputCommand() {
        return Arrays.asList(SCANNER.nextLine().split(COMMAND_DELIMITER));
    }
}
