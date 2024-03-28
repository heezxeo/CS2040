import java.util.* ;

public class t9spelling {
    public static void main (String[] args) { 
        Scanner sc = new Scanner(System.in);

        HashMap<Character, String> t9 = new HashMap<>();
        t9.put('a', "2");
        t9.put('b', "22");
        t9.put('c', "222");
        t9.put('d', "3");
        t9.put('e', "33");
        t9.put('f', "333");
        t9.put('g', "4");
        t9.put('h', "44");
        t9.put('i', "444");
        t9.put('j', "5");
        t9.put('k', "55");
        t9.put('l', "555");
        t9.put('m', "6");
        t9.put('n', "66");
        t9.put('o', "666");
        t9.put('p', "7");
        t9.put('q', "77");
        t9.put('r', "777");
        t9.put('s', "7777");
        t9.put('t', "8");
        t9.put('u', "88");
        t9.put('v', "888");
        t9.put('w', "9");
        t9.put('x', "99");
        t9.put('y', "999");
        t9.put('z', "9999");
        t9.put(' ', "0");

        int caseNumber = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i < caseNumber + 1; i++) {
            String result = "";
            String character= sc.nextLine();

            if (character != "") {
                result += t9.get(character.charAt(0));
            }

            for (int j = 1; j < character.length(); j++) {
                if (result != "" && t9.get(character.charAt(j - 1)).charAt(0) == t9.get(character.charAt(j)).charAt(0)){
                    result += " ";
                }
                result += t9.get(character.charAt(j));
            }
            System.out.println("Case #" + i + ":" + ' ' + result);
        }
    }
}

// Scanner
// make dictionary {'character', 'key stroke'}

// string result = ""
// Scanner num of case (int)
// for (i to number of case) do
    // Scanner lines
    // for (j to lines.length) do
        // if append result += key string.index(0) 

        // if same number as index before, add " "
        // end if 
        // update res from keys
    // end for
// print Case # i: res
// end for