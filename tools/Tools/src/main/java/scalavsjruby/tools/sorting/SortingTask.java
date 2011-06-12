package scalavsjruby.tools.sorting;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class SortingTask {

    public static final String DEFAULT_METHOD = "default";
    public static final String SORTED_METHOD = "sorted";
    public static final String REVERSED_METHOD = "reversed";
    public static final List<String> GENERATE_METHODS = Arrays.asList(new String[]{DEFAULT_METHOD, SORTED_METHOD, REVERSED_METHOD});
    private String language = null;
    private String sortingMethod = null;
    private String generateMethod = null;
    private int size = 0;

    public SortingTask(SortingTask sortingTask) {
        setLanguage(sortingTask.getLanguage());
        setSortingMethod(sortingTask.getSortingMethod());
        setGenerateMethod(sortingTask.getGenerateMethod());
        setSize(sortingTask.getSize());
    }

    public SortingTask(StringTokenizer st) {
        while (st.hasMoreTokens()) {
            handleParameter(st.nextToken());
        }
    }
    
    public SortingTask(String language, String generateMethod, int size) {
        this.language = language;
        this.generateMethod = generateMethod;
        this.size = size;
    }

    public String getDescription(String sortingClassName) {
        return "sorting;\t" + this.getLanguage() + ";\t" + this.getSortingMethod() + " (" + sortingClassName + ");\t"
                + getGenerateMethod() + ";\t" + getSize() + ";\t";
    }

    private void handleParameter(String parameter) {
        StringTokenizer parameterST = new StringTokenizer(parameter, "=");
        String parameterValue;
        String parameterName;
        if (parameterST.hasMoreTokens()) {
            parameterName = parameterST.nextToken().toLowerCase();
            if (parameterST.hasMoreTokens()) {
                parameterValue = parameterST.nextToken().toLowerCase();
                setParameter(parameterName, parameterValue);
            }
        }
    }

    public String getLanguage() {
        return this.language;
    }

    public String getSortingMethod() {
        return this.sortingMethod;
    }

    public String getGenerateMethod() {
        return this.generateMethod;
    }

    public int getSize() {
        return this.size;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setSortingMethod(String sortingMethod) {
        this.sortingMethod = sortingMethod;
    }

    public void setGenerateMethod(String generateMethod) {
        this.generateMethod = generateMethod;
        if (this.generateMethod != null && !GENERATE_METHODS.contains(this.generateMethod)) {
            throw new RuntimeException("unknown generate method: [" + this.generateMethod + "], known generate methods ["
                    + GENERATE_METHODS.toString() + "]");
        }
    }

    public void setSize(int size) {
        if (size < 0) {
            throw new RuntimeException("Size cannot be lower that 0! [" + size + "]");
        }
        this.size = size;
    }

    private void setParameter(String parameterName, String parameterValue) {
        if ("language".equals(parameterName)) {
            setLanguage(parameterValue);
        } else if ("sorting_method".equals(parameterName)) {
            setSortingMethod(parameterValue);
        } else if ("generate_method".equals(parameterName)) {
            setGenerateMethod(parameterValue);
        } else if ("size".equals(parameterName)) {
            try {
                setSize(Integer.parseInt(parameterValue));
            } catch (NumberFormatException nfe) {
                throw new RuntimeException("Size is unproper number [" + parameterName + "]");
            }
        }
    }
}
