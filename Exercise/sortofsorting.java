//Ha HeeJu A2066301N

import java.util.*;
import java.io.*;
import java.util.Comparator;

class Name {
	public String firstname;
	public Name (String firstname) {
		this.firstname = firstname;
	}

	public String gettwoLetter() {
		return firstname.substring(0,2);
	}
}

class nameComparator implements Comparator<Name> {
	public int compare(Name n1, Name n2) {
		return n1.gettwoLetter().compareTo(n2.gettwoLetter());
	}
}

public class sortofsorting {
    public static void main(String[] args) {

    	Scanner sc = new Scanner(System.in);

    	nameComparator nameSort = new nameComparator();

    	while (true) {
    		int n = sc.nextInt();
    		
    		if (n == 0) break;
    		sc.nextLine();

    		ArrayList<Name> lastNames = new ArrayList<Name>();
    		for (int i = 0; i < n; i++) {
    			String fname = sc.nextLine(); 
    			lastNames.add(new Name(fname));
    		}

    			Collections.sort(lastNames, nameSort);

    		for (Name name : lastNames){
    			System.out.println(name.firstname);
    		}
    	}

    }
}
