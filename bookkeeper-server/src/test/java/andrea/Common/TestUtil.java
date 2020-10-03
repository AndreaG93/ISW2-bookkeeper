package andrea.Common;

public class TestUtil {

    public static void printExceptionMessage(String methodName, Exception exception) {

        String exceptionClassName = exception.getClass().getName();
        String exceptionMessage = exception.getMessage();

        System.err.println(":::" + methodName + ":::" + exceptionClassName + " -> " + exceptionMessage);
    }
}