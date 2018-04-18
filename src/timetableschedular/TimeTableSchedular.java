/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetableschedular;
import java.io.*;
import java.util.*;
/**
 *
 * @author Divyalaptus
 */
public class TimeTableSchedular {

    /**
     * @param args the command line arguments
     */
    Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);  
        TimeTableSchedular t = new TimeTableSchedular();
        System.out.println("***** Welcome to Time Schedular *****\n");
        
        System.out.print("Schedule Time - Table for which semister?: ");
        String semister = scan.next();  //... name of semister for which time - table is being scheduled
        
        System.out.println("Enter number of slots per day");
        int slots = scan.nextInt();
        
        System.out.print("Enter number of sections: ");
        int numberOfSection = scan.nextInt();   //... get number of sections for which time - table is being scheduled for a semister
        
        String[] nameOfSection = t.getSections(numberOfSection);    //... get name of each section
        
        System.out.println("Enter number of Subjects in the semister(inclusive of Labs):");
        int noOfSubjects = scan.nextInt();
         
        System.out.println("Enter the name of Subjects: ");
        String[] subjects = t.getSubjects(noOfSubjects);    //... get subject names
        
        System.out.println("Enter name of Teachers for each subject( delimited with , ):");
        String[] Teachers = t.getTeachers(subjects);    //... get teachers
        
        t.assignLoads(Teachers,slots);
        String path = "C:\\Users\\Divyalaptus\\Documents\\NetBeansProjects\\TimeTableSchedular";
        System.out.println("\nVerify loads for all the faculties at "+path);
        scan.nextLine();
        
        Boolean a = false;
        while(a==false){
            System.out.println("\n\nPerform changes if any, SAVE, return and type 'Done' when ready...");
            String proceed = scan.nextLine();
        if(proceed.equalsIgnoreCase("done")){
            t.getUpdatedLoads(Teachers,slots);
            a=true;
        }
        else
            a=false;
        }
    }
    
    void assignLoads(String[] faculty, int slots){
        try{
            for(int i = 0; i < faculty.length; i++){
                String[] l = faculty[i].split("\\:");
                String filename = l[0]+"("+l[1]+")"+".txt";
                System.out.println(filename);
                BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
                String s = "Slot";
                bw.write("\t");
                for(int j = 1; j <= slots; j++){
                bw.write(s+j+"\t");
                }
                bw.newLine();
                
                for(int a = 0; a < 5; a++){
                    switch(a){
                        case 0:bw.write("Mon\t");
                        break;
                        case 1:bw.write("Tue\t");
                        break;
                        case 2:bw.write("wed\t");
                        break;
                        case 3:bw.write("Thr\t");
                        break;
                        case 4:bw.write("Fri\t");
                    }
                    for(int b = 0; b < slots; b++){
                        bw.write("0\t");    //... the teachers are by-default free at this slot
                    }
                    bw.newLine();
                }
            bw.close();
                    }
        }catch(IOException e){
        
        }
    }
    
    
    String[] getSections(int sections){ //... function that receives name to each section of the semister
        
        String[] nameOfSection = new String[sections];
        System.out.println("Enter name of sections: ");
        for(int i = 0; i < nameOfSection.length; i++){
        nameOfSection[i] = scan.nextLine();
        }
        
        /*for(int i = 0; i<nameOfSection.length;i++){
        System.out.println(nameOfSection[i]);
        }*/
        
        return nameOfSection;
        
        
    }
    
    String[] getSubjects(int numberofsubjects){ //... get name of Subjects for the semister
        String[] sub = new String[numberofsubjects];
        for(int i = 0; i < numberofsubjects; i++){
        sub[i] = scan.nextLine();
        }
        
        /*for(int i = 0; i<sub.length;i++){
        System.out.println(sub[i]);
        }*/
        
        return sub;
    }
    
    String[] getTeachers(String[] sub){ //... Extracts teacher's name and returns into string array
        String[] setOfTeachers = new String[sub.length];
        for(int i = 0; i < sub.length; i++){
            System.out.println("Enter name of teachers for "+ sub[i]+": ");
            setOfTeachers[i] = scan.nextLine();
        
        }
        int length = 0;
        for(int i = 0; i < setOfTeachers.length; i++){  //... counts number of teachers
            String[] s = setOfTeachers[i].split("\\,+");
            length = length + s.length;
        
        }
        String[] subjectAllocation = new String[length];
        for(int j = 0; j < subjectAllocation.length; )  //... makes list of all the teachers and there respective subject
        for(int i = 0; i < setOfTeachers.length; i++){
            String[] s = setOfTeachers[i].split("\\,");
            for(int k = 0; k < s.length; k++){
            subjectAllocation[j] = s[k]+":"+sub[i];
            j++;}
        
        }
        
        /*for(int i = 0; i < subjectAllocation.length; i++)
        System.out.println(subjectAllocation[i]);*/
    
      return subjectAllocation;
    }
    
    void getUpdatedLoads(String[] Teacher, int slots){
        String[][] TeacherLoad = new String[Teacher.length*5][slots];
        try{
            int j=0;
        for(int i = 0; i < Teacher.length; i++){
                String[] l = Teacher[i].split("\\:");
                String filename = l[0]+"("+l[1]+")"+".txt";
                System.out.println(filename);
                BufferedReader br = new BufferedReader(new FileReader(filename));
                String read=null;
                br.readLine();  //... ignores the slot number from each file
                while((read=br.readLine())!=null){
                    String loads[] = read.split("\\s+");
                    for(int k=0;k<slots;k++){
                    TeacherLoad[j][k]=loads[k+1];
                    }
                    j++;
                }
        br.close();
        }
        
        }catch(IOException e){}
            for(int i=0;i<TeacherLoad.length;i++){
            for(int j=0;j<slots;j++){
            System.out.print(TeacherLoad[i][j]+" ");
            }
            System.out.println();
            }
    }
}
