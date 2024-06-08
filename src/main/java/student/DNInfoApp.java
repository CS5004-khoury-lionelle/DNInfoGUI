package student;


/** Main driver for the program. */
public final class DNInfoApp {


    /**
     * Help message.
     * 
     * This is just copied here for convenience / so you can easily see the changes to the
     * help/command line options. You may have this in another file by default.
     * 
     * @return the help message
     */
    public static String getHelp() {
        return """
                DNInfoApp [hostname|all] [-f json|xml|csv|pretty] [-o file path] [-h | --help] [--data filepath] \
                [-g | --gui] [-c | --console]

                Looks up the information for a given hostname (url) or displays information for
                all domains in the database. Can be output in json, xml, csv, or pretty format.
                If -o file is provided, the output will be written to the file instead of stdout.
                if [-g | --gui] is provided, the GUI will be displayed.
                if [-c | --console] is provided, the console will be displayed.
                For both GUI and console, other args except for --data will be ignored.

                --data is mainly used in testing to provide a different data file, defaults to the hostrecords.xml file.
                """;
    }

    /** Private constructor to prevent instantiation. */
    private DNInfoApp() {
        // empty
    }

    /**
     * Main entry point for the program.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }
}
