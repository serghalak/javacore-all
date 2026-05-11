package horsman14.v2ch09.jaas;

import module java.base;
import module jdk.security.auth;

/**
 * This program shows how to authenticate a user via a custom login
 */
class JAASDemo {
    void main(String[] args) {
        CallbackHandler handler;
        if (args.length == 0)
            handler = new TextCallbackHandler();
        else {
            // In most apps, you have your own way of reading the username and password.
            // Here, the user provides the user name as a command line argument and the
            // password on the console.
            String username = args[0];
            Console console = System.console();
            char[] password = console.readPassword("Password: ");
            handler = new SimpleCallbackHandler(username, password);
        }

        try {
            var context = new LoginContext("Login1", handler);
            context.login();
            Subject subject = context.getSubject();
            for (Principal p : subject.getPrincipals()) {
                IO.println(p.getClass().getName() + " " + p.getName());
            }
            context.logout();
        }
        catch (LoginException e) {
            e.printStackTrace();
            Throwable cause = e.getCause();
            if (cause != null) cause.printStackTrace();
        }
    }
}
