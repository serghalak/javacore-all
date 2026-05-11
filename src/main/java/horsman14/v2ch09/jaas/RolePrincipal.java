package horsman14.v2ch09.jaas;

import module java.base;

/**
 * A principal with a named role.
 */
public record RolePrincipal(String name) implements Principal {
    /**
     * @return the role name.
     */
    public String getName() {
        return name;
    }
}
