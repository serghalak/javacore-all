package shild.ch12;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno{
	String str();
	int val();
}


public class Meta {
	
	@MyAnno(str="Sample of annotation",val=100)
	public static void myMeth(){
		Meta obMeta=new Meta();
		Class cl=obMeta.getClass();
		try {
			Method method=cl.getMethod("myMeth");
			MyAnno ann=method.getAnnotation(MyAnno.class);
			System.out.println(ann.str()+" "+ann.val());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		myMeth();
	}

}
