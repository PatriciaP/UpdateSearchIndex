import structures.hash.HashMapTable;
import structures.hash.LinkedHashMap;
import structures.list.SearchList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static final int hashSize = 10;

    public static void main(String[] args) {
//        String path = args[0];
//        String keywordPath = args[1];
//        SearchList keywords = null;
//        try {
//            keywords = keywordReader(keywordPath);
//        }catch (IOException e){
//            System.err.printf("ERROR while opening the file : %s.\n",e.getMessage());
//        }

//        SearchList searchList = new SearchList(path,keywords);
//        searchList.run();
//        Tree tree = new Tree(path,keywords);
//        tree.run();
//        Tree tree = new Tree();
//        tree.add("a",1);
//        tree.add("b",2);
//        tree.add("c",3);
//        tree.add("d",3);
//        tree.print();
//        tree.printInorder();
//        Node result = tree.searchAux(tree.root,"c");
//        System.out.println(result.word);
//        result = tree.search("b");
//        System.out.println(result.word);
//        result = tree.search("c");
//        System.out.println(result.word);
//        result = tree.search("d");
//        System.out.println(result.word);
        HashMapTable hashMap = new HashMapTable(hashSize);
        hashMap.add("ab",1,0);
        hashMap.add("Ab",2,0);
        hashMap.add("Ab",3,0);
        hashMap.add("a",5,2);
        hashMap.add("d",8,0);
        System.out.println();
//        hashMap.search("ab");

        System.out.println();
        LinkedHashMap linkedHashMap = new LinkedHashMap(hashSize);
        linkedHashMap.add("a",3);
        linkedHashMap.add("a",5);
        linkedHashMap.add("a",7);
        linkedHashMap.add("b",5);
        linkedHashMap.add("cc",6);
        linkedHashMap.add("Cc",7);

        System.out.println(hashMap.toString());
        System.out.println();

        System.out.println(linkedHashMap.toString());
    }

    public static SearchList keywordReader(String path) throws IOException {

        BufferedReader buffRead = new BufferedReader(new FileReader(path));

        String string = "";
        int index = 1;

        SearchList list = new SearchList(null, null);

        while (buffRead.ready()) {
            string = buffRead.readLine();
            string = (String) string.replace(".", "");
            string = (String) string.replace(",", "");
            String[] words = string.split("\\s");
            words = string.trim().split("\\s+");
            for (String word : words) {
                list.add(word, index);
            }
            index++;
        }

        return  list;

    }
}
