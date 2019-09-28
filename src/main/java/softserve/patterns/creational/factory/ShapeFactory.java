package softserve.patterns.creational.factory;

public class ShapeFactory {
	
	
	static Shape getShape(ShapeType shapeName){
		Shape shape=null;
		switch(shapeName){
			case CIRCLE:
				shape= new Circle();
				break;
			case RECTANGLE:
				shape=new Rectangle();
				break;
			case SQUARE:
				shape=new Square();
				break;
			default :
				shape=null;
		}
		
		return shape;
	}
}
