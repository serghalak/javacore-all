package shild.ch04;

public class ByteUShift {

    public static void main(String[] args) {
        char hex[]={
                '0','1','2','3','4','5','6','7',
                '8','9','a','b','c','d','e','f'
        };
        byte b =(byte)0xf1;
        System.out.println("b="+b + " " + Integer.toBinaryString(b));
        byte c=(byte)(b>>4);
        System.out.println("c="+c);
        byte d =(byte)(b>>>4);
        System.out.println("d="+d);
        byte e=(byte)((b & 0xff));
        System.out.println("e="+e);

    }
}
