package OOD3;

public class OOD3 {


    @Override
    public String toString() {
        return "2";
    }

    public static class container {

        @Override
        public String toString() {
            return "1";
        }

        public void display() {

            System.out.println(this);
        }

    }

    public class container2 {
        public void good() {
            System.out.println(OOD3.this);
        }

    }

    public static void main(String[] args) {
        OOD3 ood3 = new OOD3();
        OOD3.container2 container2 = ood3.new container2();
        container2.good();
    }
}
