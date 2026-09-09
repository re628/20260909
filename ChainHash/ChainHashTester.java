package ChainHash;



import java.util.Scanner;

public class ChainHashTester {
    static Scanner stdIn = new Scanner(System.in);

    static class Data{

        static final int NO = 1;
        static final int NAME = 2;

        private Integer no;
        private String name;

        Integer keyCode(){
            return no;
        }

        public String toString(){
            return name;
        }

        void scanData(String guide,int sw){
            System.out.println(guide + "Enter");

            if((sw & NO) == NO){
                System.out.print(" number ");
                no = stdIn.nextInt();
            }
            if((sw & NAME) == NAME){
                System.out.print(" name ");
                name = stdIn.next();
            }
        }
    }

    enum Menu{
        ADD      ("add"),
        REMOVE("delite"),
        SEARCH("search"),
        DUMP ("display"),
        TERMINATE("end");

        private final String message;

        static Menu MenuAt(int idx){
            for (Menu m : Menu.values()){
                if(m.ordinal() == idx){
                    return m;
                }
            }
            return null;
        }

        Menu(String string){
            message = string;
        }

        String getMessage(){
            return message;
        }
    }

    static Menu selectMenu(){
        int key;
        do{
            for(Menu m : Menu.values()){
                System.out.printf("(%d) %s ", m.ordinal(), m.getMessage());
            }
            System.out.print(" : ");
            key = stdIn.nextInt();
        }while (key < Menu.ADD.ordinal() || key > Menu.TERMINATE.ordinal() );

        return Menu.MenuAt(key);
    }

    public static void main(String[] args){
        Menu menu;
        Data data;
        Data temp = new Data();

        ChainHash<Integer,Data> hash = new ChainHash<Integer,Data>(13);

        System.out.println("hash");

        do{
            switch(menu = selectMenu()){
                case ADD:
                    data = new Data();
                    data.scanData("add", Data.NO| Data.NAME);
                    hash.add(data.keyCode(),data);
                    break;

                case REMOVE:
                    temp.scanData("delite",Data.NO);
                    hash.remove(temp.keyCode());
                    break;

                case SEARCH:
                    temp.scanData("search",Data.NO);
                    Data t = hash.search(temp.keyCode());
                    if(t != null){
                        System.out.println("key" + t);
                    }else{
                        System.out.println(" not found ");
                    }

                    break;

                case DUMP:
                    hash.dump();
                    break;

            }
        }while (menu != Menu.TERMINATE);
    }


}

