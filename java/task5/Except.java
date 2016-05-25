package task5;

/**
 * Created by anykey on 23.05.16.
 */
public class Except implements ExceptionGenerator {

    @Override
    public void generateNullPointerException() {
        Integer a = null;
        String string = a.toString();
    }

    @Override
    public void generateClassCastException() {
        Object x = 1;
        String string = (String) x;

    }

    @Override
    public void generateNumberFormatException() {
        String string = "2O0"; //  в Числе 2O0, один знако-это буква O.
        Integer integer = Integer.parseInt(string);

    }


    @Override
    public void generateStackOverflowError() {
        n++;
        System.out.println(n);
        generateStackOverflowError();
    }

    @Override
    public void generateOutOfMemoryError() {
        Long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println(maxMemory);

        int[] matrix = new int[(int) (maxMemory + 1)];
        for(int i = 0; i < matrix.length; ++i)
            matrix[i] = i+1;
    }

    @Override
    public void generateMyException(String message) throws MyException {
        System.out.println("Это MyException ");
        throw new MyException(message);

    }

   /* public static void printStackTrace(Throwable throwable) {
        System.out.println(throwable);
        for (StackTraceElement element : throwable.getStackTrace()) {
            System.out.println(element);
        }
        System.out.println();
    }*/

    static int n=0;
    public static void main(String[] args) {

        Except except = new Except();
        try {
            except.generateNullPointerException();
        } catch (Exception e) {

            e.printStackTrace();
        }
        try {
            except.generateClassCastException();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            except.generateNumberFormatException();

        } catch (Exception e) {
            e.printStackTrace();
        }
        /*try {
            except.generateStackOverflowError();

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try {
            except.generateOutOfMemoryError();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            except.generateMyException("Всё поломалось");
        } catch (MyException e)
        {
        e.printStackTrace();

        }
    }
}
