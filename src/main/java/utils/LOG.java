package utils;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class LOG {

   public static final Logger L = Logger.getLogger(LOG.class);

   public static LOG instance = null;

   {
      BasicConfigurator.configure();
   }

   private LOG() {}

   /**
    * @return instance
    */
   public static LOG getInstance() {
      if (instance == null)
         synchronized (LOG.class) {
            instance = new LOG();
         }
      return instance;
   }

   /**
    * @param parameter
    */
   public void info(String parameter) {
      L.info(parameter);
   }

   /**
    * @param parameter
    */
   public void debug(String parameter) {
      L.debug(parameter);
   }

   /**
    * @param parameter
    */
   public void warn(String parameter) {
      L.warn(parameter);
   }

   /**
    * @param parameter
    */
   public void err(String parameter) {
      L.error(parameter);
   }


}
