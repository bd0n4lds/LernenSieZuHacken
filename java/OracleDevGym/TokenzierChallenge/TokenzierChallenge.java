public class TokenzierChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner("ThisIsIt,theFinalString,NoBugsProject");
        Pattern pattern = Pattern.compile("^\\w*");
        sc.userDelimiter(pattern);
        while (sc.hasNext()) {
            System.out.printIn(sc.next());
        }
        sc.close();
    }
}