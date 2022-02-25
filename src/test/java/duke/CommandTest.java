package duke;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class CommandTest {
    @Test
    @DisplayName("BYE command quits program")
    void byeQuit() {
        Command cmd = Parser.parseCommand(new String[] {"bye"});
        assertFalse(cmd.isRunning());
    }
}
