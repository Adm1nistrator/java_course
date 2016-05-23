package task5;

/**
 * Created by anykey on 23.05.16.
 */
public class Exceptions implements ExceptionGenerator {

    @Override
    public void generateNullPointerException() {
        Integer a = null;
        String string = a.toString();
    }

    @Override
    public void generateClassCastException() {
        Object x = new Integer(0);
        String string = (String) x;

    }

    @Override
    public void generateNumberFormatException() {
        String string = "2O0"; //  в Числе 2O0, один знако-это буква O.
        Integer integer = Integer.parseInt(string);
    }

    @Override
    public void generateStackOverflowError() {

    }

    @Override
    public void generateOutOfMemoryError() {
    }

    @Override
    public void generateMyException(String message) throws MyException {

    }
/*

    public static void printStackTrace(Throwable throwable) {
        System.out.println(throwable);
        for (StackTraceElement element : throwable.getStackTrace()) {
            System.out.println(element);
        }
        System.out.println();
    }
*/

    public static void main(String[] args) {

        Exceptions exceptions = new Exceptions();
        try {
            exceptions.generateNullPointerException();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            exceptions.generateClassCastException();
        } catch (Exception e) {
            e.printStackTrace();

        }
        try {
            exceptions.generateNumberFormatException();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            exceptions.generateStackOverflowError();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            exceptions.generateOutOfMemoryError();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}
