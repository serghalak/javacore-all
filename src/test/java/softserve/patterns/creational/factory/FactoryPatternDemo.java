package softserve.patterns.creational.factory;

public class FactoryPatternDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShapeFactory.getShape(ShapeType.CIRCLE).draw();;
	}

}
