package bloch.builder.singleton.field;

// Singleton with public final field  (Page 17)
public class Elvis implements Cloneable {
    public static final Elvis INSTANCE = new Elvis();
    public int intClone;
    public String stringClone;

    private Elvis() {
        System.out.println("private constructor Elvis");
    }

    public void leaveTheBuilding() {
        System.out.println("Whoa baby, I'm outta here!");
    }

    // This code would normally appear outside the class!
    public static void main(String[] args) {
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding();
        elvis.intClone = 1;
        elvis.stringClone = "origin Elvis";

        Elvis clone = null;
        try {
            clone = (Elvis) elvis.clone();
            clone.intClone = 2;
            clone.stringClone = "clone Elvis";
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(elvis == clone);
        System.out.println(elvis.equals(clone));
        System.out.println(elvis);
        System.out.println(elvis.intClone + " " + elvis.stringClone);
        System.out.println(clone.intClone + " " + clone.stringClone);
        System.out.println(clone);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("Cloning Elvis");
        return super.clone();
    }

//    @Override
//    protected Elvis clone() throws CloneNotSupportedException {
//        System.out.println("Cloning Elvis");
//        return (Elvis) super.clone();
//    }
}