package utilities;



//import java.util.logging.LogManager;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;

public  class Log {
    public static String LOGS_PATH = "test-outputs/Logs";
    public void trace(String message){
       LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
               .trace(message);
       Allure .addAttachment("logs",message);
    }
    public static void info(String message){
       LogManager.getLogger(Thread.currentThread()
               .getStackTrace()[2].toString())
               .info(message);
       Allure.addAttachment("logs",message);
    }
    public static void debug(String message){
       LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
               .debug(message);
        Allure.addAttachment("logs",message);
    }
    public static void error(String message){
        LogManager.getLogger(Thread.currentThread()
                        .getStackTrace()[2].toString())
                .error(message);
        Allure.addAttachment("logs",message);
    }
    public static void warn(String message){
        LogManager.getLogger(Thread.currentThread()
                        .getStackTrace()[2].toString())
                .warn(message);
        Allure.addAttachment("logs",message);
    }
}
