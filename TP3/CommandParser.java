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
//import java.util.ArrayList;
//import java.util.List;
//
//public class CommandParser {
//    static public List<Command> parse(String s) {
//        List<Command> commands = new ArrayList<>();
//        for (int i = 0; i < s.length(); i++) {
//            char letra = s.charAt(i);
//            if (letra == 'i') {
//                commands.add(new IncrementVelocity());
//            } else if (letra == 's') {
//                commands.add(new DecrementVelocity());
//            } else if (letra == 'l') {
//                commands.add(new Left());
//            } else if (letra == 'r') {
//                commands.add(new Right());
//            } else if (letra == 'd') {
//                commands.add(new DeploySonda());
//            } else if (letra == 'f') {
//                commands.add(new RecuperarSonda());
//            }
//        }
//        return commands;
//    }
//}