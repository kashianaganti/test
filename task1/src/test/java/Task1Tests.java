import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Kashi on 9/5/2017.
 */
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Task1Tests {

    public Task1 task1;

    @BeforeClass
    public void setUp(){
        task1 = new Task1();
    }

    @Test(dataProvider = "testData")
    public void InefficientCodeTests(String[] input1, String[] input2, String[] expectedList){
        ArrayList<String> list1 = new ArrayList<String>(Arrays.asList(input1));
        ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(input2));
        assertThat(task1.getCommonWords(list1,list2),containsInAnyOrder(expectedList));
    }

    @Test(dataProvider = "testData")
    public void efficientCodeTests(String[] input1, String[] input2, String[] expectedList){
        ArrayList<String> list1 = new ArrayList<String>(Arrays.asList(input1));
        ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(input2));
        assertThat(task1.getCommonWordsEfficient(list1,list2),containsInAnyOrder(expectedList));
    }


    @Test(dataProvider = "performanceTestData")
    public void executionTimeComparisionTests(int list1Size, int list2Size){
        ArrayList<String> list1 = generateRandomWords(list1Size);
        ArrayList<String> list2 = generateRandomWords(list2Size);

        /**
         * Record execution time for in efficient code
         */
        long startTime = System.currentTimeMillis();
        task1.getCommonWords(list1,list2);
        long endTime = System.currentTimeMillis();
        long executionTimeForInefficientCode = endTime - startTime;

        /**
         * Record execution time for efficient code
         */
         startTime = System.currentTimeMillis();
        task1.getCommonWordsEfficient(list1,list2);
         endTime = System.currentTimeMillis();
        long executionTimeForEfficientCode = endTime - startTime;
        System.out.print("list1 size "+list1.size()+" , list2 size "+list2.size()+" ");
        System.out.println("Differencce in execution times "+(executionTimeForInefficientCode-executionTimeForEfficientCode)+" milli seconds");
    }






    @DataProvider(name = "testData")
    public Object[][] getDataForInEfficientCodeUnitTest(){
        return new Object[][]{
                // one common word
                {
                    new String[]{"hello","how","are","you"},new String[]{"hello","Im","very","good"}, new String[]{"hello"}
                },
                // no common words
                {
                    new String[]{"hello","how","are","you"}, new String[]{"hi","Im","very","good"}, new String[]{}
                },
                // two common words
                {
                    new String[]{"hello","how","are","you"}, new String[]{"hello","how","good"}, new String[]{"hello","how"}
                },
                //case sensitive test
                {
                        new String[]{"hello","how","are","you"}, new String[]{"HellO","HOW","good"}, new String[]{}
                },
                // empty list one
                {
                        new String[]{}, new String[]{"HellO","HOW","good"}, new String[]{}
                },
                 // empty list two
                {
                        new String[]{"HellO","HOW","good"}, new String[]{}, new String[]{}
                },
        };

    }



    @DataProvider(name="performanceTestData")
    public Object[][] getPerformanceUnitTestData(){
        return new Object[][]{

                {
                    new Integer(100), new Integer(100)
                }
                ,{
                    new Integer(1000), new Integer(100)
                }
                ,{
                    new Integer(10000), new Integer(1000)
                }
                ,{
                    new Integer(10000), new Integer(10000)
                }
                ,{
                    new Integer(100000), new Integer(10000)
                }

        };
    }


    private ArrayList<String> generateRandomWords(int number){
        ArrayList<String> words = new ArrayList<String>(number);
        Random random = new Random();
        for(int i=1 ; i<= number ; i++){
            words.add(RandomStringUtils.randomAlphabetic(random.nextInt(8)+3));   // 3 to 10 characters word length
        }
        return words;
    }


}
