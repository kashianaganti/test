import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kashi on 9/5/2017.
 */
public class Task1 {
    /**
     * Retuns list of string common between two list of strings
     * @param list1 - array list of strings
     * @param list2 - array list of strings
     * @return
     */
    public ArrayList<String> getCommonWords(ArrayList<String> list1, ArrayList<String> list2){
        ArrayList<String> commonWords = new ArrayList<String>();
        for(String str1 : list1){
            //check list 2 contains string from list1
           for (String str2 : list2){
               if (str1.equals(str2)){
                   if (!commonWords.contains(str1)){
                       commonWords.add(str1);
                   }

               }
           }
        }
        return commonWords;
    }


    public ArrayList<String> getCommonWordsEfficient(ArrayList<String> list1, ArrayList<String> list2){


        Set<String> commonWords = new HashSet<String>(); //To Store common words
        Set<String> largeList;// Converting to HashSet for faster search
        ArrayList<String> smallList;

        /**
         * Assigning the larger arraylist to set
         *  
         */
        if (list1.size() >= list2.size()){
            largeList = new HashSet<String>(list1);
            smallList = list2;
        }else {
            largeList = new HashSet<String>(list2);
            smallList =list1;
        }

        /**
         * Iterate over small list reducing number of iterations
         * Search over large hash set
         */
        for(String item : smallList){
            if (largeList.contains(item)){
                if (!commonWords.contains(item)){
                    commonWords.add(item);
                }
            }
        }
        return new ArrayList<String>(commonWords);

    }
}
