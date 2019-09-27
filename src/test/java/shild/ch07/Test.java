package shild.ch07;

public class Test {
		int а; // доступ, определяемый по умолчанию
		public int Ь; // открытый доступ
		private int с; // закрытый доступ
		
		private String privateString="private String";
		String packageString="package string";
		protected String protectedString="protected string";
		public String publicString="public string";
		
		
		
		
		@Override
		protected Object clone() throws CloneNotSupportedException {
			// TODO Auto-generated method stub
			return super.clone();
		}
		
		private void textPrivate(){
			System.out.println("private method Test");
		}
		void textPackage(){
			System.out.println("package method Test");
		}
		protected void textProtected(){
			System.out.println("protected method Test");
		}
		
		public void textPublic(){
			System.out.println("public method Test");
		}
		
		
		public String getPrivateString() {
			return privateString;
		}

		public void setPrivateString(String privateString) {
			this.privateString = privateString;
		}

		public String getPackageString() {
			return packageString;
		}

		public void setPackageString(String packageString) {
			this.packageString = packageString;
		}

		public String getProtectedString() {
			return protectedString;
		}

		public void setProtectedString(String protectedString) {
			this.protectedString = protectedString;
		}

		public String getPublicString() {
			return publicString;
		}

		public void setPublicString(String publicString) {
			this.publicString = publicString;
		}

		// методы доступа к члену с данного класса
		void setc ( int i) {
			//установить значение члена с данного класса
			с =- i;
		}
		
		int getc(){
		//получить значение члена с данного класса
			return с;
		}
	
	
}
