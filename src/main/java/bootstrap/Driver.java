package bootstrap;


import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Driver {


    static Logger logger = LoggerFactory.getLogger(Driver.class);

    public static void main(String[] args) throws Exception
    {
        configureLogging("var/log/gitIntro/", false,System.currentTimeMillis());
        logger.info("Hello world");
    }


    public static String configureLogging(String logDirectory, boolean debug , long startTimeMs) {
        DailyRollingFileAppender dailyRollingFileAppender = new DailyRollingFileAppender();

        String logFileName = "";
        if (!debug) {
            dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.INFO_INT));
            logFileName = logDirectory + "gitIntro.log";

        } else {
            dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.DEBUG_INT));
            logFileName = logDirectory + "gitIntro-debug.log";
        }

        System.out.println("Log files written out at " + logFileName);
        dailyRollingFileAppender.setFile(logFileName);
        dailyRollingFileAppender.setLayout(new EnhancedPatternLayout("%-6d [%t] %-5p %c - %m%n"));

        dailyRollingFileAppender.activateOptions();
        org.apache.log4j.Logger.getRootLogger().addAppender(dailyRollingFileAppender);
        return dailyRollingFileAppender.getFile();
    }


}