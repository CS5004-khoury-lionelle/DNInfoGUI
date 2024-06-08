package student.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import student.model.DomainNameModel;
import student.model.DomainNameModel.DNRecord;
import student.model.formatters.Formats;


/**
 * Simple Controller interface.
 * 
 * You do not have to use this interface, but we have provided it. You can use it as is, modify it
 * as you need, or ignore it completely and create your own.
 * 
 * HOWEVER, the provided ConsoleView.java assumes THIS interface. If you change it, you will need to
 * update ConsoleView.java.
 */
public interface IController {

    /**
     * Get the records as a list.
     * 
     * @return the list of records
     */
    List<DNRecord> getRecords();

    /**
     * Gets a single record by hostname.
     * 
     * If the record does not exist, gets the information based off the IP address, builds the
     * record, adds (and saves) it to hostrecords.xml (or whatever file is specified in the model),
     * then returns the new record.
     * 
     * @param hostname the hostname to look up
     * @return the record
     * @see NetUtils#lookUpIp(String)
     * @see NetUtils#getIpDetails(String, Formats)
     */
    DNRecord getRecord(String hostname);


    /**
     * Exports out toa file.
     * 
     * Uses the file extension to determine the format. .xml .json .csv .txt
     * 
     * .txt uses the PRETTY_PRINT format, the others match the specified format.
     * 
     * If it is not one of the supported formats, it will raise an exception.
     * 
     * @param filename the filename to write to
     */
    void exportToFile(String filename) throws Exception;


    /**
     * Formats a single record based on the provided format.
     * 
     * Uses the pretty format by default calling the more specific version.
     * 
     * @param record the record to format
     * @return the formatted record as a string
     */
    static String recordToString(DNRecord record) {
        return recordToString(record, Formats.PRETTY);
    }

    /**
     * Formats a single record based on the provided format.
     * 
     * 
     * @param record the record to format
     * @param format the format to use
     * @return the formatted record as a string
     */
    static String recordToString(DNRecord record, Formats format) {
        OutputStream out = new ByteArrayOutputStream();
        DomainNameModel.writeRecords(List.of(record), format, out);
        return out.toString();
    }


    /**
     * Writes out the records to the outputstream.
     * 
     * OutputStream could be System.out or a FileOutputStream.
     * 
     * @param records the records to write, could be a single entry.
     * @param format the format to write the records in
     * @param out the output stream to write to
     */
    static void writeRecords(List<DNRecord> records, Formats format, OutputStream out) {
        DomainNameModel.writeRecords(records, format, out);
    }

}
