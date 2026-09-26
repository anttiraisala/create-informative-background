package fi.anttir.helpers;

import java.util.HashMap;

/**
 * Created by anttir on 18.11.2015.
 */
public class CommandLineParameters {

    /**
     * All possible parameters
     */
    private HashMap<String, Parameter> shortFormParameters = new HashMap<>();
    private HashMap<String, Parameter> longFormParameters = new HashMap<>();
    private HashMap<String, Parameter> keyFormParameters = new HashMap<>();

    /**
     * Parameters actually entered by user
     */
    private HashMap<String, Parameter> userGivenParameters = new HashMap<>();



    public CommandLineParameters() {
    }

    public void addParameter(Parameter parameter) {
        shortFormParameters.put(parameter.getShortForm(), parameter);
        longFormParameters.put(parameter.getLongForm(), parameter);
        keyFormParameters.put(parameter.getKey(), parameter);
    }

    public void parseCommandLineParameters(String[] args) {
        if (args != null)
            for (int i = 0; i < args.length; i++) {
                String s = args[i];

                Parameter p = null;

                if(s.startsWith("--")){
                    String parameterSwitch = s.substring(2, s.length());

                    if(longFormParameters.containsKey(parameterSwitch))
                        p=longFormParameters.get(parameterSwitch);
                }

                if(s.startsWith("-")){
                    String parameterSwitch = s.substring(1, s.length());

                    if(shortFormParameters.containsKey(parameterSwitch))
                        p=shortFormParameters.get(parameterSwitch);
                }

                if(p!=null){
                    userGivenParameters.put(p.getKey(), p);
                    if(i < args.length){
                        p.setUserEnteredValue(args[i+1]);
                    }
                }
            }
    }

    public boolean hasUserEnteredParameter(String key){
        return userGivenParameters.containsKey(key);
    }

    public String getParameterValueForKey(String key){

        String value = null;

        if(hasUserEnteredParameter(key))
            value = userGivenParameters.get(key).getUserEnteredValue();

        if(value==null && keyFormParameters.containsKey(key))
            value = keyFormParameters.get(key).getDefaultValue();

        return value;
    }




    public class Parameter {
        private String key;
        private String shortForm;
        private String longForm;
        private String defaultValue;

        private String userEnteredValue;

        public Parameter(String key, String shortForm, String longForm, String defaultValue) {
            this.key = key;
            this.shortForm = shortForm;
            this.longForm = longForm;
            this.defaultValue = defaultValue;
        }


        public String getUserEnteredValue() {
            return userEnteredValue;
        }

        public void setUserEnteredValue(String userEnteredValue) {
            this.userEnteredValue = userEnteredValue;
        }

        /********************/

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getShortForm() {
            return shortForm;
        }

        public void setShortForm(String shortForm) {
            this.shortForm = shortForm;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        public String getLongForm() {
            return longForm;
        }

        public void setLongForm(String longForm) {
            this.longForm = longForm;
        }
        /********************/

    }


}
