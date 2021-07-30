package com.ridge.digitalreceiptreader.service.sql;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Builds out sql strings to be executed.
 *
 * @author Sam Butler
 * @since July 29, 2021
 */
public class SqlBuilder {

    /**
     * Method called to get a sql block and returns a SqlClient object so that it
     * can be executed and get the expected result from the query.
     *
     * @param queryName - Name of the query that we want to execute
     * @return a String
     */
    public String getSql(String queryName) {
        String query = "";

        try {
            query = getQueryString(getScriptFile(new Throwable().getStackTrace()), queryName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return query;
    }

    /**
     * Get the file path of the source
     *
     * @param trace - stack trace of the called method
     * @return String of the expected query path
     */
    private String getScriptFile(StackTraceElement[] trace) {
        String traceClassName = trace[1].getClassName().replace('.', '/') + ".class";
        String classPath = getClass().getClassLoader().getResource(traceClassName).toString();
        String className = classPath.substring(classPath.lastIndexOf("/") + 1, classPath.indexOf(".class"));

        return String.format("../DigitalReceiptReader/app/src/main/java/com/ridge/digitalreceiptreader/db/%s.elsql",
                className);
    }

    /**
     * Method to get the query string from a the set file based on the given name
     * that we are looking for.
     *
     * @param queryFile    - File path to where the query is located
     * @param fragmentName - Name of the query we are trying to receive
     * @return A String of the query with all the filled in values
     * @throws IOException
     */
    public String getQueryString(String queryFile, String fragmentName) throws IOException, Exception {
        String returnQuery = "";
        BufferedReader br = new BufferedReader(new FileReader(queryFile));

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            if (line.contains(String.format("@NAME(%s)", fragmentName)))
                break;
        }

        // while ((line = br.readLine()) != null && !line.contains(NAME.text())) {
        // returnQuery += parseQueryLine(line);
        // }

        br.close();
        return returnQuery.replaceAll("\\s{2,}", " ").trim();
    }

    public <T> SqlBuilder withParam(String field, T value) {
        return null;
    }
}
