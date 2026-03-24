import java.sql.SQLOutput;

public class VectorMain
{
    public static void main(String args[])
    {
        try{
            VectorClass v1= new VectorClass (new double[]{1.0,2.0});
            VectorClass v2= new VectorClass (new double[]{3.0,4.0});
            VectorClass v3= new VectorClass (new double[]{5.0,6.0});
            System.out.print("Vector 1--");
            v1.print();
            System.out.print("Vector 2--");
            v2.print();
            System.out.print("Vector 3--");
            v3.print();
            VectorClass sum= v1.add(v2);
            System.out.print("THE sum of v1 and v2 is--");
            sum.print();
            VectorClass sum2= v1.add(v3);
            System.out.print("THE sum of v1 and v3 is--");
            sum2.print();
            VectorClass diff1= v1.substract(v2);
            System.out.print("THE difference of v1 and v2--");
            diff1.print();
            VectorClass diff2= v3.substract(v1);
            System.out.print("THE difference of v3 and v2--");
            diff2.print();
            double pro1=v1.dotProduct(v2);
            System.out.println("THE dot product of v1 and v2 is: "+pro1);

            VectorClass v4= new VectorClass (new double[]{1.0,2.0,3.0});
            VectorClass v5= new VectorClass (new double[]{5.0,6.0,7.0});
            VectorClass v6= new VectorClass (new double[]{8.0,9.0,10.0});
            System.out.println();
            System.out.print("Vector 4--");
            v4.print();
            System.out.print("Vector 5--");
            v5.print();
            System.out.print("Vector 6--");
            v6.print();
            VectorClass sum3= v4.add(v5);
            System.out.print("THE sum of v4 and v5 is--");
            sum3.print();
            VectorClass sum4= v4.add(v6);
            System.out.print("THE sum of v4 and v6--");
            sum4.print();
            VectorClass diff3= v5.substract(v4);
            System.out.print("THE difference of v5 and v4--");
            diff3.print();
            VectorClass diff4= v5.substract(v6);
            System.out.print("THE difference of v5 and v6--");
            diff4.print();
            double pro2=v4.dotProduct(v6);
            System.out.println("THE dot product of v4 and v6 is: "+pro1);
            System.out.println();

        }
        catch(InvalidVectorException  e)
        {
            System.out.println(e.getMessage());
        }
        try {
            VectorClass v7= new VectorClass (new double[]{1.0,2.0,3.0,4.0});
        } catch (InvalidVectorException e) {
            System.out.println(e.getMessage());
        }
        try {
            VectorClass v7= new VectorClass (new double[]{1.0,2.0,3.0});
            VectorClass v8= new VectorClass (new double[]{8.0,9.0});
            System.out.println();
            System.out.print("Vector 7--");
            v7.print();
            System.out.print("Vector 8--");
            v8.print();
            double pro3=v7.dotProduct(v8);
        } catch (InvalidVectorException e) {
            System.out.println(e.getMessage());
        }
        }
    }

