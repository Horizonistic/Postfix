package conversion.utils;

/**
 * A simple class for outputting a string to both a file and the
 * terminal window
 *
 * @author Richard Stegman
 * @author Horizonistic
 * @version 1.1.3
 */
import java.io.*;
public class SuperOutput {
    PrintWriter pw;

    /**
     * Constructor for SuperOutput objects
     * @param fileName the name of the file to print to
     */
    public SuperOutput(String fileName) {
        try {
            pw = new PrintWriter(new FileWriter(fileName));
        }
        catch (IOException e) {
            System.out.printf("File %s could not be opened for output.\n", fileName);
            pw = null;
        }
    }

    /**
     * Forwards a string of format specifiers and varargs
     * to two printf statements
     * @param string  the string to print
     * @param args  arguments to pass to print
     */
    public void output(String string, Object... args) {
        if (pw == null)
            return;
        System.out.printf(string, args);
        pw.printf(string, args);
    }

    /**
     * Outputs solely a newline character to the console and the file
     */
    public void outputln()
    {
        if (pw == null)
            return;
        System.out.printf("\n");
        pw.printf("\n");
    }

    /**
     * Same as this.output(), only prints on its own line
     * @param string the string to print
     * @param args  arguments to pass to print
     */
    public void outputln(String string, Object... args) {
        if (pw == null)
            return;
        System.out.printf("\n" + string, args);
        pw.printf("\n" + string, args);
    }

    /**
     * Forwards a string of format specifiers and varargs
     * to just the file. This is used for echoing items
     * the user may have input.
     * @param string the string to print
     * @param args  arguments to pass to print
     */
    public void outputFile(String string, Object... args) {
        if (pw == null)
            return;
        pw.printf(string, args);
    }

    /**
     * The same as this.outputFile(), only prints on its own line
     *
     * @param string the string to print
     * @param args  arguments to pass to print
     */
    public void outputlnFile(String string, Object... args) {
        if (pw == null)
            return;
        pw.printf("\n" + string, args);
    }

    /**
     * Outputs solely a newline character to the file
     */
    public void outputlnFile() {
        if (pw == null)
            return;
        pw.printf("\n");
    }

    /**
     * Closes the SuperOutput object
     */
    public void close () {
        if (pw != null) {
            pw.close();
        }
    }
}