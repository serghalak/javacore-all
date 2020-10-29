package sertification.igor;

enum Season{
    WINTER(true){},
    SPRING(false),
    SUMMER(false){
        @Override
        void tell(){
            System.out.println(this.name().toLowerCase()+"? that depends ...");
        }
    },
    FALL(true);

    boolean favSeason;

    Season(boolean favSeason){
        this.favSeason=favSeason;
    }

    void tell(){
        String flag=this.favSeason ? "" : "not ";
        System.out.println("I do " + flag + "like " + this.name().toLowerCase());
    }

    void setFavSeason(boolean flag){
        this.favSeason=flag;
    }
}

public class Enum314 {

    public static void main(String[] args) {
        Season.WINTER.tell();
        Season.SPRING.tell();
        Season.SUMMER.tell();
        Season.FALL.tell();
        System.out.println("------------------------------------");
        Season.WINTER.setFavSeason(false);
        Season.WINTER.tell();
    }
}
