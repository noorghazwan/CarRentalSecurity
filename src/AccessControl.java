/**
 * This class manages user sessions and enforces role-based access control.
 * Controls what actions each user is permitted to perform
 * based on their role and session validity.
 *
 * Security Design Principles Applied:
 * 1. Complete Mediation:
 *    Every action is verified at the time it is requested.
 *    Session validity is checked on every call to isAuthorized(),
 *    ensuring no stale or expired session can be used to gain access.
 *    Sessions automatically expire after 15 minutes of activity.
 *
 * 2. Least Privilege:
 *    Users are granted only the minimum permissions required by
 *    their role. Regular users can only perform USER_TASK actions,
 *    while ADMIN_TASK actions are exclusively reserved for Admin users.
 *
 * 3. Fail-Safe Defaults:
 *    If the session is expired or the user is not recognized,
 *    access is denied by default. No action is permitted unless
 *    authorization is explicitly confirmed.
 */

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