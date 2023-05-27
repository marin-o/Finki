import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class TermFrequencyTest {
    public static void main(String[] args) throws FileNotFoundException {
        String[] stop = new String[] { "во", "и", "се", "за", "ќе", "да", "од",
                "ги", "е", "со", "не", "тоа", "кои", "до", "го", "или", "дека",
                "што", "на", "а", "но", "кој", "ја" };
        TermFrequency tf = new TermFrequency(System.in,stop);
        System.out.println(tf.countTotal());
        System.out.println(tf.countDistinct());
        System.out.println(tf.mostOften(10));
    }
}


class TermFrequency{
    Map<String,Integer> frequency;
    Set<String> stopWords;
    public TermFrequency(InputStream inputStream,String[] stopWords){
        this.frequency=new TreeMap<String,Integer>();
        this.stopWords=new HashSet<String>();
        this.stopWords.addAll(Arrays.asList(stopWords));
        Scanner scanner = new Scanner(inputStream);
        while(scanner.hasNext()){
            String line = scanner.nextLine();
            line=line.trim();

            if(line.length()>0){
                String[] words = line.split(" ");
                for ( String word : words ){
                    String key = word.toLowerCase().replace(",","").replace(".","").trim();
                    if(key.isEmpty() || this.stopWords.contains(key)){
                        continue;
                    }
                    if(frequency.containsKey(key)){
                        frequency.put(key,frequency.get(key)+1);
                    } else{
                        frequency.put(key,1);
                    }
                }
            }
        }
        scanner.close();
    }
    public int countTotal(){
        return frequency.values().stream().mapToInt(integer -> integer).sum();
    }
    public int countDistinct(){
        return frequency.keySet().size();
    }
    public List<String> mostOften(int k){
        List<Entry<String,Integer>> sorted = frequency.entrySet().stream().sorted(new Comparator<Entry<String,Integer>>(){
            @Override
            public int compare(Entry<String,Integer> o1, Entry<String,Integer> o2){
                if(o1.getValue()==o2.getValue()) return 0;
                return o1.getValue()>o2.getValue()?1:-1;
            }
        }.reversed()).collect(Collectors.toList());
        List<String> strings = new ArrayList<>();
        for ( int i=0; i < k; i++ ) {
            strings.add(sorted.get(i).getKey());
        }
        return strings;
    }
}
