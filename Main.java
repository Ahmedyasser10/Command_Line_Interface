import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static String currentDirectory = System.getProperty("user.dir");
    public static final String homeDirectory = System.getProperty("user.home");

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        while (true) {

            Terminal terminal = new Terminal();
            terminal.pwd();
            String command = in.nextLine();
            String[] splited = command.split("\\s+");
            Parser parse = new Parser(splited);
            String cmd = parse.getCmd();
            if (cmd.equalsIgnoreCase("pwd")) {
                if (parse.getFirstArg().equals("")) {
                    System.out.print("Your current directory is ");
                    terminal.pwd();
                    System.out.println();

                } else {
                    System.out.println("pwd does not take any arguments");
                }
            } else if (cmd.equalsIgnoreCase("touch")) {
                if (parse.getFirstArg().equals("")) {
                    System.out.println("touch takes an argument");
                } else {
                    terminal.touch(parse.getFirstArg());
                }
            } else if (cmd.equalsIgnoreCase("echo")) {
                if (parse.getFirstArg().equals("")) {
                    System.out.println("echo command takes arguments");
                } else {
                    terminal.echo(splited);
                }
            } else if (cmd.equalsIgnoreCase("mkdir")) {
                if (parse.getSecondArg().equals("")) {
                    terminal.mkdir(parse.getFirstArg());
                } else {
                    System.out.println("mkdir takes one argument");
                }
            } else if (cmd.equalsIgnoreCase("rmdir")) {
                if (parse.getSecondArg().equals("")) {
                    terminal.rmdir(parse.getFirstArg());
                } else {
                    System.out.println("rmdir takes one argument");
                }
            } else if (cmd.equalsIgnoreCase("ls")) {
                if (parse.getFirstArg().equals("")) {
                    terminal.ls();
                } else {
                    System.out.println("ls does not take any arguments");
                }
            } else if (cmd.equalsIgnoreCase("ls-r")) {
                if (parse.getFirstArg().equals("")) {
                    terminal.ls_r();
                } else {
                    System.out.println("ls_r does not take any arguments");
                }
            } else if (cmd.equalsIgnoreCase("cp")) {
                if (parse.getSecondArg().equals("")) {
                    System.out.println("cp takes two arguments");
                } else {
                    terminal.cp(splited);
                }
            } else if (cmd.equalsIgnoreCase("cp-r")) {
                if (parse.getSecondArg().equals("")) {
                    System.out.println("cp takes two arguments");
                } else {
                    terminal.cp_r(splited);
                }
            } else if (cmd.equalsIgnoreCase("cat")) {
                terminal.cat(parse.getFirstArg(), parse.getSecondArg());
            } else if (cmd.equalsIgnoreCase("cd")) {
                terminal.cd(parse.getFirstArg(), Main.currentDirectory);

            } else if (cmd.equalsIgnoreCase("rm")) {
                if (parse.getSecondArg().equals("")) {
                    terminal.rm(parse.getFirstArg());
                } else {
                    System.out.println("rm takes one argument");
                }
            } else if (cmd.equalsIgnoreCase("wc")) {
                if (parse.getFirstArg().equals("")) {
                    System.out.println("wc takes one argument");
                } else {
                    terminal.wc(parse.getFirstArg());
                }
            } else if (cmd.equalsIgnoreCase("exit")) {
                if (parse.getFirstArg().equals("")) {
                    System.out.println("exit !");
                    System.exit(0);
                } else {
                    System.out.println("exit does not take any arguments");
                }
            }

        }
    }
}