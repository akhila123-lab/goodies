package goddies.abc;

import java.io.*;
import java.util.*;
class Item {
  String goodie_name;
  int goodie_price;

  public Item(String goodie_name, int goodie_price) {
    this.goodie_name = goodie_name;
    this.goodie_price = goodie_price;
  }

  public String toString() { 
      return this.goodie_name + ": " + this.goodie_price;
  }
}	

public class Main {
  public static void main(String[] args) throws Exception {
	  String fileName = "C:\\Users\\Ashwini\\javaEE\\goddies\\src\\goddies\\abc\\sample_input.txt";
    FileInputStream fis=new FileInputStream(fileName);       
    Scanner sc=new Scanner(fis);
    int no_of_employees = Integer.parseInt(sc.nextLine().split(": ")[1]);
    sc.nextLine(); sc.nextLine(); sc.nextLine();

    ArrayList<Item> goodies_items = new ArrayList<Item>();

    while(sc.hasNextLine())  
    {
      String current[] = sc.nextLine().split(": ");
      goodies_items.add(new Item(current[0], Integer.parseInt(current[1])));
    }
    sc.close();

    // sort the goodies by price
    
    Collections.sort(goodies_items, new Comparator<Item>(){
      public int compare(Item a, Item b) { 
        return a.goodie_price - b.goodie_price; 
      }  
    });
    
    int min_diff = goodies_items.get(goodies_items.size()-1).goodie_price;
    int min_index = 0;
    for(int i=0;i<goodies_items.size()-no_of_employees+1;i++) { //7
      int diff = goodies_items.get(no_of_employees+i-1).goodie_price-goodies_items.get(i).goodie_price;

      System.out.println("difference "+diff);
      if(diff<=min_diff) {
        min_diff = diff;
        min_index = i;
      }
    }
    String fileName1 = "C:\\Users\\Ashwini\\javaEE\\goddies\\src\\goddies\\abc\\sample_output.txt";
    FileWriter fw = new FileWriter(fileName1);
    fw.write("The goodies selected for distribution are:\n\n");
    for(int i=min_index;i<min_index + no_of_employees; i++) {
      fw.write(goodies_items.get(i).toString() + "\n");
    }

    fw.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + min_diff);
	  fw.close();
    
  }
}