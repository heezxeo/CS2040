import java.util.* ;

public class peasoup {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> restaurant = new ArrayList<String>();

        
        //number of restaurants
        int restNumber = sc.nextInt();

        for (int i = 0; i < restNumber; i++) {
            //number of menu
            int menuNumber = sc.nextInt();
            sc.nextLine();
            
            //restaurant name
            String restName = sc.nextLine();
            boolean peasoup = false;
            boolean pancake = false;

            for (int j = 0; j < menuNumber; j++) {
                String menuName = sc.nextLine();

                if (menuName.equals("pea soup")) {
                    peasoup = true;
                }
                else if (menuName.equals("pancakes")) {
                    pancake = true;
                }
                if (peasoup && pancake) {
                    restaurant.add(restName);
                    break;
                }

            }
        }
        
        if (restaurant.size() > 0) {
            System.out.println(restaurant.get(0));
        } else {
            System.out.println("Anywhere is fine I guess");
        }
    }
}

