import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import java.io.Console;
public class FileCommander {
    private Path path;

    public FileCommander() {
        this.path = Path.of(System.getProperty("user.home"));
    }

    public String pwd()
    {
        return path.toString();
    }
    public void cd(String pathname) {
        this.path = path.resolve(pathname);
    }

    public List<String> ls(Function<String, String> function) throws IOException {
        Stream<Path> stream = Files.list(path);
        return stream.sorted((path1, path2) -> path1.compareTo(path2))
                .sorted((path1, path2) ->
                        Boolean.compare(Files.isDirectory(path2), Files.isDirectory(path1)))
                .map((p) -> {
                    if(Files.isDirectory(p))
                    {
                        return  function.apply(ConsoleColors.RED + p.getFileName().toString() + ConsoleColors.RESET);
                    }
                    return  "\n" + ConsoleColors.BLUE_BRIGHT + p.getFileName().toString() + ConsoleColors.RESET;
                }).toList();
    }

    public Function<String, String> addBrackets() {
        return name -> {
                return "[" + name + "]";
        };
    }
    public Function<String, String> addColor(String color) {
        return name -> {
            return ConsoleColors.RED + name + ConsoleColors.RESET;
        };
    }

    public List<String> find(String argument) throws IOException {
        Stream<Path> stream = Files.walk(path);
        return stream.filter(p -> !Files.isDirectory(p))
                .filter(path1 -> path1.getFileName().toString().contains(argument))
                .map((p) -> {
                        return  "found: " + ConsoleColors.PURPLE + p.getFileName().toString()  + ConsoleColors.RESET;
        }).toList();
    }

}
