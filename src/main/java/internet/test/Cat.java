package internet.test;

public class Cat extends Animal{
	 String tail = "Изначальное значение tail в классе Cat";

	   static int catsCount = 37;
	   static{
		   System.out.println("catsCount="+catsCount);
	   }
	   {
		   System.out.println("tail="+tail);
	   }

	   public Cat(String brain, String heart, String tail) {
	       super(brain, heart);
	       System.out.println("Конструктор класса Cat начал работу (конструктор Animal уже был выполнен)");
	       System.out.println("Текущее значение статической переменной catsCount = " + catsCount);
	       System.out.println("Текущее значение tail = " + this.tail);
	       this.tail = tail;
	       System.out.println("Текущее значение tail = " + this.tail);
	       Object obj;
	   }

	   public static void main(String[] args) {
	       Cat cat = new Cat("Мозг", "Сердце", "Хвост");
	   }
}
