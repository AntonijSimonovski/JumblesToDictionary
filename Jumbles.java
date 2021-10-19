import java.util.*;
import java.io.*;
public class Jumbles
{
  public static void main(String args[]) throws Exception
  {
    if( args.length < 2)
    {
      System.out.println("usage: Jumbles <dictionary.txt> <jumbles.txt>");
      System.exit(0);
    }
    BufferedReader infile = new BufferedReader( new FileReader( args[0] ) );
    ArrayList<String> dictionaryArray = new ArrayList<String>();
    while( infile.ready() ) dictionaryArray.add( infile.readLine() );
    Collections.sort(dictionaryArray);

    BufferedReader infile1 = new BufferedReader( new FileReader( args[1] ) );
    ArrayList<String> jumblesArray = new ArrayList<String>();
    while( infile1.ready() ) jumblesArray.add( infile1.readLine() );
    Collections.sort(jumblesArray);

    ArrayList<String> jumblesArray2 = new ArrayList<String>();
    for(String n : jumblesArray) jumblesArray2.add(n);

    ArrayList<String> canonJumblesArray = new ArrayList<String>();
    for(String n : jumblesArray) canonJumblesArray.add(canonical(n));
    Collections.sort(canonJumblesArray);

    for(String n : dictionaryArray) {
      String c = canonical(n);
      int x = Collections.binarySearch( canonJumblesArray, c );
      if( x >= 0) {
        int i;
        if(canonical(jumblesArray.get(x)).equals(c)){
          jumblesArray2.set(x, jumblesArray2.get(x) + " " + n);
          continue;
        }
        else if(!canonical(jumblesArray.get(x)).equals(c))
          for(i = x - 1; i >= 0; i--)
            if( canonical(jumblesArray.get(i)).equals(c) )
             jumblesArray2.set(i, jumblesArray2.get(i) + " " + n);
          for(i = x + 1; i < jumblesArray.size(); i++)
            if( canonical(jumblesArray.get(i)).equals(c) )
              jumblesArray2.set(i, jumblesArray2.get(i) + " " + n);  
        }
      }
      for(String n : jumblesArray2) System.out.println(n);
    }//end main
    static String canonical( String word){
      char[] letters = word.toCharArray();
      Arrays.sort ( letters );return new String ( letters );
    }
  }//end class
