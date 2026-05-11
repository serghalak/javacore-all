package horsman14.v2ch02.serializationTweaks;

import module java.base;

class SerializationTweaks {
    void main() throws Exception {
        // Demonstrates writeObject/readObject
        var lp = new LabeledPoint("Rome", 41.902782, 12.496366);
        // Demonstrates writeExternal/readExternal
        var lp2 = new LabeledPixel("bottom right", 1919, 1079);
        // Demonstrates writeReplace/readResolve
        Person p = PersonDatabase.INSTANCE.findById(2);

        try (var out = new ObjectOutputStream(new FileOutputStream("test.ser"))) {
            out.writeObject(lp);
            out.writeObject(lp2);
            out.writeObject(p);
        }

        try (var in = new ObjectInputStream(new FileInputStream("test.ser"))) {
            lp = (LabeledPoint) in.readObject();
            IO.println(lp);
            lp2 = (LabeledPixel) in.readObject();
            IO.println(lp2);
            Person q = (Person) in.readObject();
            IO.println(q);
            IO.println(p == q);
        }
    }
}
