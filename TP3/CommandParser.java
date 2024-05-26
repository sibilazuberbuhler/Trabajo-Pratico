package drone;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.Map;

public class CommandParser {
    static public List<Command> parse(String s) {
        Map<Character, Function<Void, Command>> commandMap = Map.of(
                'i', v -> new IncrementVelocity(),
                's', v -> new DecrementVelocity(),
                'l', v -> new Left(),
                'r', v -> new Right(),
                'd', v -> new DeploySonda(),
                'f', v -> new RecuperarSonda()
        );

        List<Command> commands = new ArrayList<>();
        s.chars().forEach(c -> commands.add(commandMap.get((char) c).apply(null)));

        return commands;
    }
}
