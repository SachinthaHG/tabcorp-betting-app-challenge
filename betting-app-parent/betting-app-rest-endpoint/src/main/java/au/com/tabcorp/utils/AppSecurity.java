package au.com.tabcorp.utils;

/**
 * This class is to handle the security of the application
 */
public class AppSecurity {
    /**
     * @param accessToken access token to be validated
     * @return boolean
     */
    public boolean validateAccessToken(String accessToken) {
        int sum = 0;
        for (int i = 0; i < accessToken.length(); i++) {
            sum = sum + accessToken.charAt(i);
        }

        /* return true if sum of the ascii values of the characters > 500 */
        if (sum > 500) {
            return true;
        } else {
            return false;
        }
    }
}
