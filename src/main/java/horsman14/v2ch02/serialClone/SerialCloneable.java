package horsman14.v2ch02.serialClone;

import module java.base;

/**
 * A class whose clone method uses serialization.
 */
public class SerialCloneable implements Cloneable, Serializable {
    public Object clone() throws CloneNotSupportedException {
        return serialClone(this);
    }

    public static <T extends Serializable> T serialClone(T obj) throws CloneNotSupportedException {
        try {
            // save the object to a byte array
            var bout = new ByteArrayOutputStream();
            try (var oout = new ObjectOutputStream(bout)) {
                oout.writeObject(obj);
            }

            // read a clone of the object from the byte array
            var bin = new ByteArrayInputStream(bout.toByteArray());
            try (var oin = new ObjectInputStream(bin)) {
                @SuppressWarnings("unchecked") T cloned = (T) oin.readObject();
                return cloned;
            }
        }
        catch (IOException | ClassNotFoundException e) {
            var e2 = new CloneNotSupportedException();
            e2.initCause(e);
            throw e2;
        }
    }
}
