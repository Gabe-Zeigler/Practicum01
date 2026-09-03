//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class PersonGenerator
//{
//    public static void main(String[] args)
//    {
//        /* ID (a String)
//    FirstName
//    LastName
//    Title (a string like Mr., Mrs., Ms., Dr., etc.)
//    YearOfBirth (an int)
//    */
//        Person aPerson = new Person("", "", "", "", 0);
//        Scanner in = new Scanner(System.in);
//        ArrayList<Person> folks = new ArrayList<>(); //Store the person object as we create them
//        String ID = "";
//        String firstName = "";
//        String lastName = "";
//        String title = "";
//        int YOB = 0;
//
//        boolean done = false;
//
//        //input loop
//        do{
//            ID = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits]");
//            firstName = SafeInput.getNonZeroLenString(in, "Enter your first name");
//            lastName = SafeInput.getNonZeroLenString(in, "Enter your last name");
//            title = SafeInput.getNonZeroLenString(in, "Enter your title");
//            YOB = SafeInput.getInt(in, "Enter the YOGB");
//
//            //Create the Person object with the data
//            //And save it to the folks arraylist
//
//            aPerson = new Person(ID, firstName, lastName, title, YOB);
//            folks.add(aPerson);
//
//            done = SafeInput.getYNConfirm(in, "Are you done?");
//
//        }while(!done);
//
//        for(Person p : folks){
//            System.out.println(p.toCSV() + " Age "+ p.getAge());
//        }
//    }
//}
