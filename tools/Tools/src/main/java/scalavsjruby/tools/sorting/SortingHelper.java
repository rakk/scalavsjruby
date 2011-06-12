package scalavsjruby.tools.sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;

public class SortingHelper {
    public static void prepareSorting(StringTokenizer st, String procedure) {
        SortingTask sortingTask = new SortingTask(st);

        for (String generateMethod : SortingTask.GENERATE_METHODS) {
            if (sortingTask.getSortingMethod() == null || generateMethod.equals(sortingTask.getSortingMethod())) {
                getIntegersFromFileOrCreateNewOnes(sortingTask, generateMethod);
            }
        }
    }
    
    public static List<Integer> getIntegersFromFileOrCreateNewOnes(SortingTask sortingTask,String generateMethod) {
        if(sortingTask.getGenerateMethod() == null){
            sortingTask = new SortingTask(sortingTask);
            sortingTask.setGenerateMethod(generateMethod);
        }
        return getIntegersFromFileOrCreateNewOnes(sortingTask);
    }

    public static List<Integer> getIntegersFromFileOrCreateNewOnes(SortingTask sortingTask) {
        List<Integer> result = new ArrayList<Integer>();
        String fileName = createFileName(sortingTask);
        try {
            File file = new File(RESOURCES_PATH + fileName);
            if (file.exists()) {
                BufferedReader in = new BufferedReader(new FileReader(file));
                String line;
                while((line = in.readLine()) != null){
                    if(StringUtils.isNotBlank(line)){
                        result.add(Integer.parseInt(line));
                    }
                }
            } else {
                result = getListIntegers(sortingTask);
                file.createNewFile();
                BufferedWriter out = new BufferedWriter(new FileWriter(file));
                for (final Integer integer : result) {
                    out.write(integer + "\n");
                }
                out.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(SortingHelper.class.getName()).log(Level.SEVERE, null, ex);
        }finally{       
            checkResult(result, sortingTask);
        }
        return result;
    }

    private static void checkResult(List<Integer> result, SortingTask sortingTask){
        if(result == null){
            throw new RuntimeException("getListIntegers(" + sortingTask.toString() + ") returned null");
        } else if(result.size() != sortingTask.getSize()){
            throw new RuntimeException("getListIntegers(" + sortingTask.toString() + ") should return size [" + sortingTask.getSize() +"], but return size [" + result.size() +"]");
        }
    }

    private static String createFileName(SortingTask sortingTask){
        return sortingTask.getGenerateMethod() + "." + sortingTask.getSize() + ".txt";
    }

    private static List<Integer> getListIntegers(SortingTask sortingTask) {        
        if (SortingTask.DEFAULT_METHOD.equals(sortingTask.getGenerateMethod())) {
            return sortUtils.generateIntegers(sortingTask.getSize());
        } else if (SortingTask.SORTED_METHOD.equals(sortingTask.getGenerateMethod())) {
            return sortUtils.generateSortedIntegers(sortingTask.getSize());
        } else if (SortingTask.REVERSED_METHOD.equals(sortingTask.getGenerateMethod())) {
            return sortUtils.generateReversedIntegers(sortingTask.getSize());
        }
        throw new RuntimeException("Unsupported generate method [" + sortingTask.getGenerateMethod() + "]");
    }
    
    private static final String RESOURCES_PATH = "src/main/resources/";
    private static final SortUtils sortUtils = new SortUtils();
}
