public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        String[] strs = StdIn.readAllStrings();
        StdRandom.shuffle(strs);
        for (int i = 0; i < k; i++) {
            StdOut.println(strs[i]);
        }
    }
}