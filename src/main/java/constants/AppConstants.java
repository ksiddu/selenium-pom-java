package constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

    private AppConstants() {}

    public static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";
    public static final String CONFIG_DEV_FILE_PATH = "src/test/resources/dev.properties";
    public static final String CONFIG_QA_FILE_PATH = "src/test/resources/qa.properties";
    public static final String CONFIG_STAGE_FILE_PATH = "src/test/resources/stage.properties";
    public static final String CONFIG_UAT_FILE_PATH = "src/test/resources/uat.properties";
    public static final String CONFIG_PROD_FILE_PATH = "src/test/resources/prod.properties";

    public static final String LOGIN_PAGE_TITLE = "The Internet";
    public static final String DASHBOARD_PAGE_TITLE = "Welcome to the Secure Area. When you are done click logout below.";

    public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
    public static final String ACC_PAGE_FRACTION_URL = "route=account/account";



    public static final List<String> ACC_PAGE_HEADERS_LIST =
            Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");

    public static final String USER_REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";


    //*****************SHEET CONSTANTS************//
    public static final String REGISTER_SHEET_NAME = "register";
    public static final String PRODUCT_IMAGES_SHEET_NAME = "productimages";

    // App 2
    public static final String PROFILE_PAGE_TEXT = "Logged in as";
    public static final String INVALID_LOGIN_TEXT = "Your email or password is incorrect!";




}
