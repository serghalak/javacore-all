package horsman.v11.t1.ch06;

public interface DefaultMethod {

	default String getText(){
		return "Hello from default method of interface";
	}
}
