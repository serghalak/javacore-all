package shild.ch28.prodconsum;

public class ConProdTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Q q= new Q();
			new Consumer(q);
			new Producer(q);
			
	}

}
