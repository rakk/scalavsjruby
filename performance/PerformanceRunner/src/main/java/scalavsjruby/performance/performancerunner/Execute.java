package scalavsjruby.performance.performancerunner;

import scalavsjruby.tools.sorting.SortingHelper;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import org.apache.commons.lang.StringUtils;

public class Execute {

    public static void main(String[] args) {
        if (args.length > 0) {
            String firstParamert = args[0];
            if (StringUtils.isNotBlank(firstParamert)) {
                runProcedure(firstParamert.toLowerCase());
            } else {
                unknownProcedure(firstParamert);
            }
        } else {
            unknownProcedure("");
        }
    }

    private static void runProcedure(String firstParameter) {
        try{
            StringTokenizer st = new StringTokenizer(firstParameter, ";");
            StringTokenizer procedureST = new StringTokenizer(st.nextToken(), "=");
            if("procedure".equals(procedureST.nextToken())){    
                chooseExecutor(procedureST.nextToken(), st);
            } else{
                throw new NoSuchElementException();
            }
        } catch(NoSuchElementException nsee){
            throw new RuntimeException("Procedure configuration missing! First parameter = [" + firstParameter + "]");
        }
    }

    private static void chooseExecutor(String procedureName, StringTokenizer st) {
        if ("sort".equals(procedureName)) {
            new ExecuteSort().execute(st, procedureName);
        } else if ("prepare_sorting".equals(procedureName)) {
            SortingHelper.prepareSorting(st, procedureName);
        } else if("memory_consumption".equals(procedureName)){
            new ExecuteMemoryConsumption().execute(st, procedureName);
        } else if("cpu".equals(procedureName)){
            new ExecuteCPU().execute(st,procedureName);
        } else {
            unknownProcedure(procedureName);
        }
    }

    private static void unknownProcedure(String procedure) {
        throw new RuntimeException("Procedure unsupported! [" + procedure + "]");
    }
}
