import structures.Block;

public class Application {

    public static void main(String[] args) {
        Block b = new Block("Meu nome é Bruno", "dfa8yr23hfsd");
        System.out.println(b.calculateSHA256Hash());
    }

}
