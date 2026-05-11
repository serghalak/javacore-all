package horsman14.v2ch09.auth;

import module java.base;

/**
 * This program obtains information about a user's Unix login.
 */
class AuthDemo {
    void main() {
        try {
            var context = new LoginContext("Login1");
            context.login();
            IO.println("Authentication successful.");
            Subject subject = context.getSubject();
            for (Principal p : subject.getPrincipals()) {
                IO.println(p.getClass().getName() + ": " + p.getName());
            }
            context.logout();
        }
        catch (LoginException e) {
            IO.println("Authentication failed.");
            e.printStackTrace();
        }
    }
}
