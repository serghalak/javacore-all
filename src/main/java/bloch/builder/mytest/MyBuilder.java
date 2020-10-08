package bloch.builder.mytest;

public class MyBuilder {
    private String firstName;
    private String lastName;
    private int age;

    public static class BuilderClass{
        private String firstName;
        private String lastName="NoName";
        private int age=0;

        public BuilderClass(String firstName) {
            this.firstName = firstName;
        }


        public BuilderClass setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public BuilderClass setAge(int age) {
            this.age = age;
            return this;
        }

        public MyBuilder build(){
            return new MyBuilder(this);
        }
    }

    private MyBuilder (BuilderClass builderClass ){
        firstName=builderClass.firstName;
        lastName=builderClass.lastName;
        age=builderClass.age;
    }

    @Override
    public String toString() {
        return "MyBuilder{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
