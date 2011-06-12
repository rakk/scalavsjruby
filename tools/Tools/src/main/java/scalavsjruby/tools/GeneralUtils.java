package scalavsjruby.tools;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneralUtils {
    private static final Runtime runtime = Runtime.getRuntime ();
    
    public static void waitNseconds(int nSeconds){
        System.out.println("Waiting " + nSeconds +" seconds");
        while(nSeconds >= 0){
            if(nSeconds == 0){
                System.out.println("used memory:\t" + usedMemory());
            }else {
                runtime.gc();
            }
            System.out.print(".");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GeneralUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
            nSeconds--;
        }
    }
    
    public static void waitAwhile(){
        waitNseconds(6);
    }
    
    public static long usedMemory () {
        return runtime.totalMemory () - runtime.freeMemory ();
    }
}
