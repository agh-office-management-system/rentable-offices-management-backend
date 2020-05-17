package pl.edu.agh.rentableoffices.security;

public class SecurityContextHelper {

    public static String getCurrentUsername() {
        return null;
        /*
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }*/
    }
}
