package IXLOA;

public class Triangle {
    public boolean isTriangle(int a, int b, int c) {
        if (a <=0 || b <= 0 || c <= 0) {
            return false;
        }
        return (a + b) > c && (Math.abs(a - b) < c);
    }


    public static void main(String[] args) {
        Triangle instance = new Triangle();
        System.out.println(instance.isTriangle(1, 5, 3));
    }
}
