import java.time.LocalTime;

public class AccessControl {

    private static User currentUser;
    private static LocalTime sessionExpiryTime;

    // Start a new session for the logged-in user
    public static void startSession(User user) {
        currentUser = user;
        // Session Expiry: 15 minutes
        sessionExpiryTime = LocalTime.now().plusMinutes(15) ;
    }

    // Authorization check for specific actions based on user role and session validity
    public static boolean isAuthorized(String action) {

        if (currentUser == null || LocalTime.now().isAfter(sessionExpiryTime)) {
            System.out.println("Session expired. Please log in again.");
            currentUser = null;
            sessionExpiryTime = null; 
            return false;
        }

        String role = currentUser.getRole();
        if (action.equals("USER_TASK") && (role.equals("User") || role.equals("Admin"))) {
            return true;
        } else if (action.equals("ADMIN_TASK") && role.equals("Admin")) {
            return true;
        }else{
            System.out.println("Access Denied. You do not have permission to perform this action.");
            return false;
        }
        
    }

    public static void logout() {
        currentUser = null;
        sessionExpiryTime = null;
        System.out.println("Logged out successfully.");
    }

    // Check if session is still active
    public static boolean isSessionActive() {
        return currentUser != null && LocalTime.now().isBefore(sessionExpiryTime);
    }
}