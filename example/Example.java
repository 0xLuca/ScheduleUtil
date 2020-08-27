import java.sql.ResultSet;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Example {

    //This method is called from the main program thread.
    public void doStuff() {
        // Running stuff async to not block the main thread
        Schedulers.runAsync(() -> MysqlUtil.executeQuery("UPDATE some_stuff SET some_column = some_value WHERE some_condition"));

        // Getting stuff async to not block the main thread
        Future<ResultSet> resultSetFuture = Schedulers.getAsync(() -> MysqlUtil.getQuery("SELECT some_stuff FROM some_table WHERE some_condition"));

        // Executing code later
        String someInformationToUseLater = "Information";
        Schedulers.consumeLaterAsync((message) -> Logger.getGlobal().log(Level.INFO, "[INFORMATION] " + message), someInformationToUseLater, 3000);
    }

}
