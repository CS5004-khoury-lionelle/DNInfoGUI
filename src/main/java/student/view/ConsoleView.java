package student.view;


import java.util.Properties;
import java.util.Scanner;
import student.controller.IController;
import student.model.DomainNameModel.DNRecord;
import student.model.formatters.Formats;

/**
 * Basic Console/Terminal interactive interface to the program.
 * 
 * RECOMMENDATION: Do not modify this class!
 * 
 * We are assuming this ConsoleView in our grading, so while you are free to modify it, you should
 * not remove the functionality.
 * 
 * You also *MUST* make sure it follows the same 'flow', meaning it asks the user for an action, the
 * action names remain exactly the same, and the order of the actions remains the same.
 * 
 * For example, if someone types in export, it will then ask for the filename.
 * 
 * quit will still end the program, list still lists the records, etc.
 * 
 * The way we are grading this console view is we have a preset set of commands and will run them in
 * order. If the console view does not match the expected output, it will be difficult to pass the
 * tests.
 * 
 * It is suggested to leave this view AS IS, and make sure the controller you create works with it,
 * an uses IController interface.
 */
public class ConsoleView implements IView {
    /**
     * Scanner helps with processing system.in. We leave it stack to reduce collisions. In this
     * case, we use System.in instead of System.console() because System.console() doesn't allow us
     * to redirect the input. For testing, we redirect the System.in to read from a String and not
     * the console.
     */
    private static Scanner console = new Scanner(System.in);
    /** The controller. */
    private IController controller;


    /**
     * Constructor.
     * 
     * @param controller the controller
     */
    public ConsoleView(IController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }
        printf("%s%n", CText.WELCOME_MESSAGE);
        while (true) {
            String cmd = readLine(CText.PROMPT_MESSAGE).trim().toLowerCase();
            if (cmd.equalsIgnoreCase(CText.EXIT_CMD)) {
                break;
            }
            processCmd(cmd);

        }
        printf(CText.EXIT_MESSAGE);
    }

    /**
     * Read a line from the console.
     * 
     * Used so the rest of the view isn't tied to the scanner.
     * 
     * @param prompt the prompt
     * @return the line
     */
    private String readLine(String prompt) {
        System.out.print(prompt);
        return console.nextLine().trim();
    }

    /**
     * Print a formatted string. Note that Object... args is a varargs parameter. It means any
     * number of arguments can be passed to the method.
     * 
     * @param format the format
     * @param args the arguments
     */
    private void printf(String format, Object... args) {
        System.out.printf(format, args);
    }


    /**
     * Process the command.
     * 
     * @param cmd the command
     */
    private void processCmd(String cmd) {
        if (cmd.equalsIgnoreCase(CText.LIST_CMD)) {
            listRecords();
        } else if (cmd.equalsIgnoreCase(CText.EXPORT_CMD)) {
            exportRecords();
        } else {
            getSingleRecord(cmd);
        }
    }

    /**
     * Export records to a file.
     */
    private void exportRecords() {
        String filename = readLine(CText.EXPORT_PROMPT);
        try {
            controller.exportToFile(filename);
        } catch (Exception e) {
            printf("%s%s%n", CText.INVALID_MESSAGE, e.getMessage());
        }


        printf("%s%n", CText.EXPORT_MESSAGE_END);
    }

    /**
     * List all the records.
     */
    private void listRecords() {
        printf("%s%n", CText.LIST_MESSAGE);
        IController.writeRecords(controller.getRecords(), Formats.PRETTY, System.out);
    }

    /**
     * Get a single record.
     * 
     * @param cmd should be a host name. If the hostname is empty, or null, will print an error.
     */
    private void getSingleRecord(String cmd) {
        try {
            if (cmd == null || cmd.isEmpty()) {
                printf(CText.ERROR);
                return;
            }
            DNRecord record = controller.getRecord(cmd);
            if (record == null) {
                printf(CText.ERROR);
            } else {
                printf("%s%n", IController.recordToString(record, Formats.PRETTY));
            }
        } catch (Exception e) {
            printf(CText.ERROR);
        }
    }


    /**
     * Inner class to help with processing the properties file.
     */
    private static final class CText {
        /** Properties file. Relative to the resources folder. */
        private static final String PROPS_FILE = "/ConsoleProperties.xml";
        // these can all be private as it is an internal class, so still accessible to ConsoleView
        /** Welcome message. */
        private static final String WELCOME_MESSAGE;
        /** Initial prompt. */
        private static final String PROMPT_MESSAGE;
        /** Exit message. */
        private static final String EXIT_MESSAGE;
        /** Invalid message. */
        private static final String INVALID_MESSAGE;
        /** List message. */
        private static final String LIST_MESSAGE;
        /** Export message end. */
        private static final String EXPORT_MESSAGE_END;
        /** Error message. */
        private static final String ERROR;
        /** List command. */
        private static final String LIST_CMD;
        /** Export command. */
        private static final String EXPORT_CMD;
        /** Exit command. */
        private static final String EXIT_CMD;
        /** Export prompt. */
        private static final String EXPORT_PROMPT;

        /** Private constructor. */
        private CText() {
            // empty
        }

        /*
         * These only need to be loaded once, so static block is used to load them.
         */
        static {
            Properties properties = loadProperties();
            WELCOME_MESSAGE = properties.getProperty("welcome");
            PROMPT_MESSAGE = properties.getProperty("prompt");
            EXIT_MESSAGE = properties.getProperty("goodbye");
            INVALID_MESSAGE = properties.getProperty("invalid");
            LIST_MESSAGE = properties.getProperty("list");
            EXPORT_MESSAGE_END = properties.getProperty("exported");
            ERROR = properties.getProperty("error");
            LIST_CMD = properties.getProperty("list_cmd");
            EXPORT_CMD = properties.getProperty("export_cmd");
            EXIT_CMD = properties.getProperty("exit_cmd");
            EXPORT_PROMPT = properties.getProperty("export_prompt");
        }

        /**
         * Load the properties file.
         * 
         * @return the properties
         */
        private static Properties loadProperties() {
            Properties props = new Properties();
            try {
                props.loadFromXML(ConsoleView.class.getResourceAsStream(PROPS_FILE));
            } catch (Exception e) {

                System.err.println("Error loading properties file: " + e.getMessage());
                System.exit(1);
            }
            return props;
        }


    }

}
