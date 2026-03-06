import java.util.ArrayList;

public class arrayListWork
{
    public static void main(String args[])
    {
        ArrayList<Integer> intArrList = new ArrayList<Integer>();
        intArrList.add(15);
        intArrList.add(3);
        for(int i=1;i<=20;i++)
        {
            if (i%2==0)
                intArrList.add(i);
        }
        for(Integer i : intArrList)
            System.out.println(i);
        ArrayList<Integer>list2=new ArrayList<Integer>();
        list2.add(21);
        list2.add((24));
        list2.add(27);
        list2.add(2,30);
        intArrList.addAll(list2);
        System.out.println();
        intArrList.forEach(i -> System.out.println(i) );
        System.out.println();
        intArrList.forEach(i-> { if (i%5==0) System.out.println(i); });
        System.out.println();
        intArrList.remove(4);
        intArrList.remove(Integer.valueOf(8));
        intArrList.forEach(i-> System.out.print(i+" "));
        System.out.println();

        ArrayList<String> words=new ArrayList<String>();
        words.add("One");
        words.add("fish");
        words.add("two");
        words.add("fish");
        words.add("red");
        words.add("fish");
        words.add("blue");
        boolean c=words.contains("fish");
        System.out.println("Words list contains fish: "+ c);

        words.forEach(w->{
            if(w.equalsIgnoreCase("fish"))
            {
                System.out.println(w);
            }
        });
        System.out.println(
        );
        ArrayList<String> fruits=new ArrayList<String>();
        fruits.add("apple");
        fruits.add("banana");
        fruits.add("grape");
        fruits.add("gauva");
        fruits.add("mango");
        fruits.add("pear");
        fruits.forEach(f->{
            if(f.charAt(0)=='g')
                System.out.println(f);
        });
    }
}
