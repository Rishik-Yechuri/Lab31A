import javax.swing.text.AbstractDocument;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Lab31A {
    public static void main(String args[]) {
        System.out.println("\nLab31 80 Point Version\n");
        MyList list1 = new MyStack(100);
        MyList list2 = new MyQueue(100);
        MyList list3 = new MySet(100);
        MyList list4 = new MyOrdList(100);
        Random rnd = new Random(12345);
        for (int k = 1; k <= 20; k++) {
            int rndInt = rnd.nextInt(20) + 10;
            addData(list1, rndInt);
            addData(list2, rndInt);
            addData(list3, rndInt);
            addData(list4, rndInt);
        }
        showData(list1, list2,list3,list4);
        System.out.println();
        for (int k = 1; k <= 5; k++) {
            removeData(list1);
            removeData(list2);
            removeData(list3);
            removeData(list4);
        }
        showData(list1, list2,list3,list4);
        System.out.println();
    }
    public static void addData(MyList list,int numToAdd){
        list.add(numToAdd);
    }
    public static void removeData(MyList list){
        list.remove();
    }
    public static void showData(MyList list1,MyList list2,MyList list3,MyList list4){
        System.out.println(list1.toString());
        System.out.println(list2.toString());
        System.out.println(list3.toString());
        System.out.println(list4.toString());
    }
}

abstract interface MyList {
    public abstract void add(int x);

    public abstract int remove();

    public abstract boolean isEmpty();

    public abstract int getSize();

    public String toString();
}
class MyStack implements  MyList{
    int sizeOfList = 0;
    int locationOfLastElement = 100;
    int[] elements = new int[100];
    public MyStack(int size){

    }

    @Override
    public void add(int x) {
        locationOfLastElement--;
        elements[locationOfLastElement] = x;
        sizeOfList++;
    }

    @Override
    public int remove() {
        int itemRemoved = elements[locationOfLastElement] = 0;
        locationOfLastElement++;
        sizeOfList--;
        return itemRemoved;
    }

    @Override
    public boolean isEmpty() {
        return locationOfLastElement>99;
    }

    @Override
    public int getSize() {
        return 100-locationOfLastElement;
    }
    @Override
    public String toString(){
        int posInArray = 0;
        int[] arrayToReturn = new int[sizeOfList];
        for(int x =0;x<100;x++){
            if(elements[x] != 0){
                arrayToReturn[posInArray] = elements[x];
                posInArray++;
            }
        }
        return Arrays.toString(arrayToReturn);
    }
}
class MyQueue implements MyList{
    int sizeOfList = 0;
    int locationOfLastElement = -1;
    int[] elements = new int[100];
    public MyQueue(int size){
    }

    @Override
    public void add(int x) {
        locationOfLastElement++;
        elements[locationOfLastElement] = x;
        sizeOfList++;
    }

    @Override
    public int remove() {
        int itemRemoved = elements[0];
        for(int x=0;x<100;x++){
            if(x<sizeOfList-1) {
                elements[x] = elements[x + 1];
                elements[x + 1] = 0;
            }
        }
        sizeOfList--;
        return itemRemoved;
    }

    @Override
    public boolean isEmpty() {
        return locationOfLastElement<=0;
    }

    @Override
    public int getSize() {
        if(locationOfLastElement==-1){return 0;}
        return locationOfLastElement;
    }
    @Override
    public String toString(){
        int posInArray = 0;
        int[] arrayToReturn = new int[sizeOfList];
        for(int x =0;x<100;x++){
            if(elements[x] != 0){
                arrayToReturn[posInArray] = elements[x];
                posInArray++;
            }
        }
        return Arrays.toString(arrayToReturn);
    }
}

class MySet extends MyQueue{
    public MySet(int size) {
        super(size);
    }
    @Override
    public void add(int x) {
        boolean duplicated = false;
        for(int y=0;y<locationOfLastElement;y++){
            if(elements[y] == x){
                duplicated = true;
            }
        }
        if(!duplicated) {
            locationOfLastElement++;
            elements[locationOfLastElement] = x;
            sizeOfList++;
        }
    }
}
class MyOrdList extends MyQueue{

    public MyOrdList(int size) {
        super(size);
    }
    @Override
    public void add(int x) {
            locationOfLastElement++;
            elements[locationOfLastElement] = x;
            sizeOfList++;
    }
    @Override
    public String toString(){
        int posInArray = 0;
        int[] arrayToReturn = new int[sizeOfList];
        for(int x =0;x<sizeOfList;x++){
            if(elements[x] != 0){
                arrayToReturn[posInArray] = elements[x];
                posInArray++;
            }
        }
        elements = arrayToReturn.clone();
        Arrays.sort(elements);
        return Arrays.toString(elements);
    }
}