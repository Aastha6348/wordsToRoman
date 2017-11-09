import java.util.*;
import java.io.*;
class WordsToRoman
{
    private static final Map<String, Integer> digits;
    private static final Map<String, Integer> teens;
    private static final Map<String, Integer> tens;
    private static final Map<String, Integer> big;
    private static final int decimalnumber[]={1000,900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String romansymbol[]={"M","CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
    static
    {
        digits = new HashMap<String, Integer>();
        digits.put("ZERO", 0);
        digits.put("ONE", 1);
        digits.put("TWO", 2);
        digits.put("THREE", 3);
        digits.put("FOUR", 4);
        digits.put("FIVE", 5);
        digits.put("SIX", 6);
        digits.put("SEVEN", 7);
        digits.put("EIGHT", 8);
        digits.put("NINE", 9);
        
        teens = new HashMap<String, Integer>();
        teens.put("TEN", 10);
        teens.put("ELEVEN", 11);
        teens.put("TWELVE", 12);
        teens.put("THIRTEEN", 13);
        teens.put("FOURTEEN", 14);
        teens.put("FIFTEEN", 15);
        teens.put("SIXTEEN", 16);
        teens.put("SEVENTEEN", 17);
        teens.put("EIGHTEEN", 18);
        teens.put("NINETEEN", 19);
        
        tens = new HashMap<String, Integer>();
        tens.put("TWENTY", 2);
        tens.put("THIRTY", 3);
        tens.put("FORTY", 4);
        tens.put("FIFTY", 5);
        tens.put("SIXTY", 6);
        tens.put("SEVENTY", 7);
        tens.put("EIGHTY", 8);
        tens.put("NINETY", 9);
        
        big = new HashMap<String, Integer>();
        big.put("HUNDRED", 100);
        big.put("THOUSAND", 1000);
        
        
    }
    public static void main(String args[])throws IOException
    {
        try
        {
            FileReader fr = new FileReader("Q3.in");
            BufferedReader br = new BufferedReader(fr);
            
            FileWriter fw = new FileWriter("Q3.out");
            BufferedWriter bw = new BufferedWriter(fw);
            
            int count = 1;
            int T = Integer.parseInt(br.readLine());
            String line = "";
            while(T!=0 && (line = br.readLine()) != null)
            {
                int len = line.length();
                String words[] = line.split(" ");
                int value = 0;
                boolean thousandflag = false;
                int tempvalue = 0;
                for(int i=0;i<words.length;i++)
                {
                    if(big.get(words[i]) != null)
                    {
                        
                        if(thousandflag)
                            tempvalue *= big.get(words[i]);
                        else
                            value *= big.get(words[i]);
                        
                        if(words[i].equals("THOUSAND"))
                            thousandflag = true;
                    }
                    
                    else if(tens.get(words[i]) != null)
                    {
                        if(thousandflag)
                            tempvalue += tens.get(words[i])*10;
                        else
                            value += tens.get(words[i])*10;
                    }
                    else if(teens.get(words[i]) != null)
                    {
                        if(thousandflag)
                            tempvalue += teens.get(words[i]);
                        else
                            value += teens.get(words[i]);
                    }
                    else if(digits.get(words[i]) != null)
                    {
                        if(thousandflag)
                            tempvalue += digits.get(words[i]);
                        else
                            value += digits.get(words[i]);
                    }
                    
                }
                value += tempvalue;
                
                bw.write("Case #"+count+":"+integerToRoman(value));
                bw.newLine();
                T--;
                count++;
            }
            bw.close();
            br.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Input file Q3.in not found.");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
            
    }
    public static String integerToRoman(int num){
        String result = "";
        int times = num;
        for(int x=0; num>0; x++){
            times = num/decimalnumber[x];
            for(int i=1; i<=times; i++){
                result += romansymbol[x];
            }
            num %= decimalnumber[x];
        }
        return result;
    }
}