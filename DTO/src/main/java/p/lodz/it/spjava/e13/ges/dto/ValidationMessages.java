package p.lodz.it.spjava.e13.ges.dto;

public class ValidationMessages {
    public static class Auth {
        public static final String LOGIN_BLANK = "{ACCOUNT.LOGIN_BLANK}";
        public static final String PASSWORD_BLANK = "{ACCOUNT.PASSWORD_BLANK}";
    }
    public static class Account {
        public static final String LOGIN_FORMAT = "{ACCOUNT.LOGIN_FORMAT}";
        public static final String LOGIN_LENGTH = "{ACCOUNT.LOGIN_LENGTH}";
        public static final String EMAIL_FORMAT = "{ACCOUNT.EMAIL_FORMAT}";
        public static final String PASSWORD_LENGTH = "{ACCOUNT.PASSWORD_LENGTH}";
        public static final String FIRSTNAME_LENGTH = "{ACCOUNT.FIRSTNAME_LENGTH}";
        public static final String LASTNAME_LENGTH = "{ACCOUNT.LASTNAME_LENGTH}";

        public static final String PHONENUMBER_LENGTH = "{ACCOUNT.PHONENUMBER_LENGHT";
    }

    public static class FarmLocation {
        public static final String CITY_NAME_LENGTH = "{FARMLOCATION.CITY_NAME_LENGTH}";

        public static final String CITY_NAME_FORMAT = "{FARMLOCATION.CITY_NAME_FORMAT}";

        public static final String POSTAL_CODE_LENGTH = "{FARMLOCATION.POSTAL_CODE_LENGTH}";
    }


}
