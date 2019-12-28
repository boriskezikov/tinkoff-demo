package tinkoff.demo.exceptions;

public interface Messages {
    public static final String NOT_FOUND_MESSAGE = "No applications found for id = ";
    public static final String WRONG_HEADER_MESSAGE = "Incorrect header passed. Only application/json or application/xml are permitted.";
    public static final String H2_ERROR_MESSAGE = "Error connecting to H2:";
    String EMPTY_RESULT = "Dataset is empty";
}
