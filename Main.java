public class Main {
    public static void main(String[] args) {
        Series<String> s;
        String[] rn = {"Jen", "Sam", "God"};
        String[] d = {"1", "2", "3"};

        s = new Series<String>(rn, d);
        // System.out.println(s);
        // s.append("Jim", "1");
        // System.out.println(s);
        // String[] st = s.getData();
        // String[] rt = s.getRowNames();
        // for (int i = 0; i < st.length; i++) {
        //     System.out.println(st[i]);
        // }
        // for (int i = 0; i < st.length; i++) {
        //     System.out.println(rt[i]);
        // }
        // s.append("Bob", null);
        // System.out.println(s);
        // s.fillNull("0");
        // System.out.println(s);

        String[] searches = {"Jen", "Sam", "God", "Jim", "Bob"};
        Object[] found = s.loc(searches);
        for (int i = 0; i < found.length; i++) {
            System.out.println(found[i]);
        }




    }
}
