package com.company;
import java.util.*;
import java.util.HashMap;
import java.util.Map.Entry;

class Add_slot{
    int Number;
    int Day;
    int Qun;
    String Vaccine_name;


    public int getDay() {
        return Day;
    }

    public void setDay(int day) {
        Day = day;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public Add_slot(int number,int day,int qun,String name){
        Number = number;
        Day = day;
        Qun = qun;
        Vaccine_name = name;
    }



    public String getVaccine_name() {
        return Vaccine_name;
    }

    public void setVaccine_name(String vaccine_name) {
        Vaccine_name = vaccine_name;
    }

    @Override
    public String toString() {
        return  Number+"-> Day: "+Day+" Available Qty:"+Qun+" Vaccine: "+Vaccine_name;
    }

    public int getQun() {
        return Qun;
    }
    public void setQun(int qun) {
        this.Qun = qun;
    }
}


public class project_1 {
    static Scanner sc = new Scanner(System.in);
    static Map<Integer,String> location = new HashMap<>();
    static Map<Integer,String> id_hospname = new HashMap<>();
    static Map<String,ArrayList<String>> vaccine = new HashMap<>();
    static Map<Long,String> citiname = new HashMap<>();

    static HashMap<Integer,List<Add_slot>> addslot = new HashMap<>();
    static List<Add_slot> temp = new ArrayList<>();

    static ArrayList<Long> arr = new ArrayList<>();
    static int count = 0;


    static void Add_vaccine(){
        System.out.print("Vaccine Name: ");
        String Name = sc.next();
        System.out.print("Number of Doses: ");
        int Doses = sc.nextInt();
        if(Doses==1){
            System.out.println("Vaccine Name: "+Name+", "+"Number of Doses: "+Doses+", "+"Gap between Doses: 0");
        }
        else if((Doses>2) | (Doses<0)){
            System.out.print("Vaccine Name: "+Name+", "+"Number of Doses: Please Enter Valid Number of Doses");
        }
        else {
            System.out.print("Gap between Doses: ");
            int Gap = sc.nextInt();
            if((Gap>2) | (Gap<0)){
                System.out.println("Vaccine Name: " + Name + ", " + "Number of Doses: " + Doses + ", " + "Gap between Doses: Please Enter Valid Number Gap between Doses");
            }
            else {
                System.out.println("Vaccine Name: " + Name + ", " + "Number of Doses: " + Doses + ", " + "Gap between Doses: " + Gap);
            }
        }
        Main aa=new Main();
    }

    static void Register_Hospital(){

        System.out.print("Hospital Name: ");
        String Hospital_name = sc.next();
        System.out.print("PinCode: ");
        int pincode = sc.nextInt();
        Random R = new Random();
        int Unique_Id = R.nextInt(1000000);
        System.out.println("Hospital Name: "+Hospital_name+", "+"PinCode: "+pincode+", "+"Unique ID: "+Unique_Id);
        id_hospname.put(Unique_Id, Hospital_name);
        location.put(pincode,Hospital_name);
        Main aa=new Main();

    }

    static void Register_Citizen(){
        System.out.print("Citizen Name: ");
        String  citizen_name = sc.next();
        System.out.print("Age: ");
        int age = sc.nextInt();
        System.out.print("Unique ID: ");
        long ID = sc.nextLong();
        citiname.put(ID,citizen_name);
        if(age<18){
            System.out.println("Citizen Name: "+citizen_name+", "+"Age: "+age+", "+"Unique ID: "+ID);
            System.out.println("Only above 18 are allowed");
        }
        else{
            System.out.println("Citizen Name: "+citizen_name+", "+"Age: "+age+", "+"Unique ID: "+ID);
        }
        citiname.put(ID,citizen_name);
        Main aa=new Main();
    }
    static void Create_Slots() {
        System.out.print("Enter Hospital ID: ");
        int hospital_id = sc.nextInt();
        System.out.print("Enter number of slots to be added: ");
        int number_slots = sc.nextInt();
        Add_slot k;
        for (int i = 0; i < number_slots; i++) {
            System.out.print("Enter Day Number: ");
            int day = sc.nextInt();
            System.out.print("Enter Quantity: ");
            int quantity = sc.nextInt();
            System.out.print("Select Vaccine\n0.Covax\n1.Covi\n");
            int option = sc.nextInt();
            String name;
            if (option == 0) {
                name = "covax";
            } else {
                name = "covi";
            }
            System.out.println("Slot added by Hospital " + hospital_id + " for Day: " + day + ", Available Quantity: " + quantity + " of Vaccine " + name);
            k = new Add_slot(i, day, quantity, name);

            temp.add(k);
            addslot.put(hospital_id, temp);


        }

        Main aa = new Main();
    }
    static void Book_slot(){
        System.out.print("Enter patient Unique ID: ");
        long unique_id = sc.nextLong();
        arr.add(unique_id);
        for(int i=0;i< arr.size();i++){
            if(arr.get(i)==unique_id)
                count+=1;
        }

        System.out.print("1.Search by area\n2.Search by Vaccine\n3.Exit\nEnter option: ");
        int option = sc.nextInt();
        if(option==1){
            System.out.print("Enter Pincode: ");
            int pin = sc.nextInt();
            String val=location.get(pin);
            for(Entry<Integer, String> entry: id_hospname.entrySet()) {
                if(entry.getValue().equals(val)) {
                    System.out.print(entry.getKey()+" ");
                    break;
                }
            }
            System.out.println(val);
            System.out.print("Enter Hospital id: ");
            int id = sc.nextInt();
            addslot.get(id);
            temp.forEach(t -> System.out.println(t));
            System.out.println("Choose slot: ");
            int choose = sc.nextInt();
            System.out.println(citiname.get(unique_id)+" vaccinated with covax");

        }
        else if(option==2){
            System.out.print("Enter Vaccine name: ");
            String vaccine_name = sc.next();


        }
        else {
            Main aa=new Main();
        }
        Main aa=new Main();
    }
    static void Check_slot(){
        System.out.print("Enter Hospital Id: ");
        int hospital_id = sc.nextInt();
        addslot.get(hospital_id);
        temp.forEach(t -> System.out.println(t));
    }
    public static void status() {

        System.out.print("Enter Patient ID:");
        long id=sc.nextLong();
        if(count==1){
            System.out.println("PARTIALLY VACCINATED");
            System.out.println("Next Dose due date: 3");
        }
        else if(count==2){
            System.out.println("FULLY VACCINATED");
            System.out.println("Number of Doses given: 2");
        }
        else{
            System.out.println("citizen REGISTERED");
        }

    }

    public static void main(String[] args) {
//
        Scanner sc = new Scanner(System.in);
//        Register_Hospital();
//        Book_slot();
        System.out.println("coWin Portal initialized.....\n-----------------------------------");
        System.out.println("1.Add Vaccine\n2.Register Hospital\n3.Register Citizen\n4.Add Slot for Vaccination\n5.Book Slot for Vaccination\n6.List all Slots for a hospital\n7.Check Vaccination Status\n8.Exit\n------------------------------");

        int option;
        option = sc.nextInt();
        while(option!=8){
            switch (option) {
                case 1:
                    Add_vaccine();
                    System.out.println("{Menu Options}");
                    break;
                case 2:
                    Register_Hospital();
                    System.out.println("{Menu Options}");
                    break;
                case 3:
                    Register_Citizen();
                    System.out.println("{Menu Options}");
                    break;
                case 4:
                    Create_Slots();
                    System.out.println("{Menu Options}");
                    break;
                case 5:
                    Book_slot();
                    System.out.println("{Menu Options}");
                    break;
                case 6:
                    Check_slot();
                    System.out.println("{Menu Options}");
                    break;
                case 7:
                    status();
                    System.out.println("{Menu Options}");
                    break;
                default:
                    System.out.print("Please Enter correct option");
                    System.out.println("{Menu Options}");

            }
            option = sc.nextInt();
        }


    }

}





