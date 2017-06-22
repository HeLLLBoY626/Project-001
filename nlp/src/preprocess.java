
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.StringTokenizer;

/**
 * Created by suman on 16/06/17.
 */
class pp {

    public void preprocess(String input_file_hi,String output_file_hi, String input_file_en,String output_file_en)
    {
        int skip=0;
        boolean noEng=false;
        HashMap<Character,Character> map=new HashMap<>();
        map.put('\u0966','\u0030');
        map.put('\u0967','\u0031');
        map.put('\u0968','\u0032');
        map.put('\u0969','\u0033');
        map.put('\u096A','\u0034');
        map.put('\u096B','\u0035');
        map.put('\u096C','\u0036');
        map.put('\u096D','\u0037');
        map.put('\u096E','\u0038');
        map.put('\u096F','\u0039');
        map.put('\u0964','\u002E');
        map.put('\u0965','\u002E');
        map.put('\u0970','\u002E');
        map.put('\u0901','\u0902');
        map.put('\u0952','\u003A');
        map.put('\u0986','\u005F');
        map.put('\u007C','\u002E');
        map.put('\u093C','\u0000');
        HashSet<Character> valid=new HashSet<>();
        for (char i = '\u0900';i<'\u097F';i++) {
            valid.add(i);
        }
        for (char i = '\u0020'; i <'\u0040' ; i++) {
            valid.add(i);
        }
        //System.out.println(valid);
        try (BufferedReader in=new BufferedReader(new FileReader(input_file_hi));
             BufferedWriter out=new BufferedWriter(new FileWriter(output_file_hi));
             BufferedReader in2=new BufferedReader(new FileReader(input_file_en));
             BufferedWriter out2=new BufferedWriter(new FileWriter(output_file_en))){
            next:while (in.ready() && in2.ready())
            {
                String line=in.readLine(),line2=in2.readLine();
                StringBuilder line3=new StringBuilder();
                StringTokenizer tk=new StringTokenizer(line);
                if( line.length()>80 || line2.length()>80 || line.length()<1 || line2.length()<1 || (line.length()/line2.length()>4)  )
                {
                    skip++;
                    continue next;
                }
                boolean skp=false;
                while (tk.hasMoreTokens())
                {
                    String token=tk.nextToken();
                    StringTokenizer tk2=new StringTokenizer(token,".()!~#$%^&*_-+/*-:;\"'[]{}|\\/?`,",true);
                    while (tk2.hasMoreTokens() && !skp) {
                        String token2=tk2.nextToken();
                        char[] buf=new char[token2.length()];
                        for (int i = 0; i < token2.length(); i++) {
                            if(!valid.contains(token2.charAt(i)))
                                skp=true;
                            if(map.containsKey(token2.charAt(i)))
                                buf[i]=map.get(token2.charAt(i));
                            else
                                buf[i]=token2.charAt(i);
                        }
                        line3.append(buf);
                        line3.append(" ");
                    }
                }
                if(skp)
                {
                    skip++;
                    continue next;
                }
                skp=true;
                for (int i = 0; i <line3.length(); i++) {
                    if (line3.charAt(i)<='\u096F' && line3.charAt(i)>='\u0900' )
                        skp=false;
                }
                if(skp)
                {
                    skip++;
                    continue next;
                }
                out.write(line3.toString());
                out2.write(line2);
                out.newLine();
                out2.newLine();
            }
            System.out.println("Lines skipped : "+skip);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

