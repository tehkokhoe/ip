package duke;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {
    @Test
    @DisplayName("Invalid Command")
    void executeInvalidCommand() {
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.parseCommand(new String[] {"chicken", "little"});
        });
    }
}
