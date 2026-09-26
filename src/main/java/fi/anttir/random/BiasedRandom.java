package fi.anttir.random;

/**
 * Created by anttir on 23.10.2015.
 */
public class BiasedRandom {

    public enum NameChange {
        SEPARATOR(": "),

        RESPONSE_OK("ECase name has been changed."),
        RESPONSE_NO_SUITABLE_ACCOUNT("ECase has not been created with an account that belongs to an organization or a person."),
        RESPONSE_ECASE_NAME_ALREADY_CONTAINED_STRING("ECase name has not been changed because ECase's name already contained the string to be added"),
        RESPONSE_FAIL("ECase name was not changed."),

        //Id of the current form, not the one the user must fill
        REQUEST_PARAM_DOCUMENT_ID("documentId"),
        //Web id of service provider
        REQUEST_PARAM_WEB_ID("webId"),
        REQUEST_PARAM_FORM_CONTROL_NAME("formControlName"),
        REQUEST_PARAM_APPEND_TO_FRONT("appendToFront");


        private final String text;

        NameChange(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public enum Bias{
        LINEAR,
        EXPONENTIAL,
        EXPONENTIAL_REVERSE,
        BELL_CURVE
    }

    public static int getSomething(){
        return 8667;
    }
}
