import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class du {

    @Option(name = "-h", required = false, usage = "Human readable format")
    private boolean humanSize;

    @Option(name = "-c", required = false, usage = "Total size")
    private boolean sumSize;

    @Option(name = "--si", required = false, usage = "Unit base")
    private boolean baseSize;

    @Argument
    private List<String> argument = new ArrayList<String>();

    public static void main(String[] args) {

        new du().launch(args);
    }

    private void launch(String[] args) {

        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);

            if (argument.isEmpty())
                throw new CmdLineException(parser, "No arguments is given");
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar du.jar [-h] [-c] [--si] file1 file2 file3...");
            parser.printUsage(System.err);
            System.exit(1);
            return;
        }

        try {

            double base = baseSize ? 1000 : 1024;

            for (String i : argument) {

                long bytes = new HumanReaderSize(i).size();

                if (humanSize) {

                    final double kilobytes = bytes / base;
                    final double megabytes = kilobytes / base;
                    final double gigabytes = megabytes / base;

                    System.out.println(bytes + " B");
                    System.out.println(kilobytes + " KB");
                    System.out.println(megabytes + " MB");
                    System.out.println(gigabytes + " GB");
                } else {

                    double kilobytes = bytes / base;
                    System.out.println(kilobytes);
                }
            }
        } catch (IllegalArgumentException e) {

            System.err.println(e.getMessage());
            System.exit(1);
        }

        if (sumSize) {
            double sum = 0;

            for (int i = 0; i <= argument.size() - 1; i++) {

                final File file = new File(argument.get(i));

                if (file.isFile()) {
                    sum += file.length();
                } else {
                    sum += HumanReaderSize.folderSize(file);
                }
            }

            System.out.println("Total size");
            System.out.println(sum + " B");
        }
    }
}
