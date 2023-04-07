import java.io.*;
import java.nio.Buffer;

public class FileCommanderCLI {
    private FileCommander fileCommander;
    private BufferedReader reader;
    private BufferedWriter writer;

    public FileCommanderCLI(InputStream reader, OutputStream writer) {
        this.reader = new BufferedReader(new InputStreamReader(reader));
        this.writer = new BufferedWriter(new OutputStreamWriter(writer));
        this.fileCommander = new FileCommander();
    }
    public void eventLoop() throws IOException {
        while (true){
            // writer.write(this.reader.readLine());
            runCommand(this.reader.readLine());
            if (!writer.equals(""))
            writer.flush();
        }
    }


    public void runCommand(String command) throws IOException {
        String[] arguments = command.split(" ");

    switch (arguments[0]) {
        case "pwd" -> writer.write(fileCommander.pwd());
        case "cd" -> fileCommander.cd(arguments[1]);
        case "q" -> System.exit(0);
        case "ls" -> writer.write(fileCommander.ls(fileCommander.addBrackets()).toString());
        default -> writer.write("Unknown command");
    }
    }
}
