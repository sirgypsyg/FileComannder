import java.io.Console;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) throws IOException {

        FileCommander fileCommander = new FileCommander();
        //System.out.println(fileCommander.pwd());

        //fileCommander.cd("Documents");
        //System.out.println(fileCommander.pwd());


        try {
            System.out.println(fileCommander.ls(p -> fileCommander.addBrackets().toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileCommanderCLI fileCommanderCLI = new FileCommanderCLI(System.in, System.out);
        fileCommanderCLI.eventLoop();

        System.out.println(fileCommander.find("autoportret.jpeg"));

    }
}