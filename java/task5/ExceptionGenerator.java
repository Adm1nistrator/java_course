package task5;

/**
 * Created by anykey on 23.05.16.
 */
public interface ExceptionGenerator {
    void generateNullPointerException();

    void generateClassCastException();

    void generateNumberFormatException();

    void generateStackOverflowError();

    void generateOutOfMemoryError();

    void generateMyException(String message) throws MyException;
}
