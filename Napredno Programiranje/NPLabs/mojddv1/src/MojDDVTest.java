import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MojDDVTest {

    public static void main(String[] args) {

        MojDDV mojDDV = new MojDDV();

        System.out.println("===READING RECORDS FROM INPUT STREAM===");
        mojDDV.readRecords(System.in);

        System.out.println("===PRINTING TAX RETURNS RECORDS TO OUTPUT STREAM ===");
        mojDDV.printTaxReturns(System.out);

    }
}

enum TYPE{
    A,B,V;
}
class Product{
    int price;
    TYPE type;

    public Product( int price, TYPE type ) {
        this.price=price;
        this.type=type;
    }

    public int getPrice() {
        return price;
    }

    public double getTaxReturn() {
        switch (type) {
            case A:
                return price * 0.18 * 0.15;
            case B:
                return price * 0.05 * 0.15;
            default:
                return 0;
        }
    }
}
class Receipt{
    int id;
    ArrayList<Product> products;

    public Receipt( int id, ArrayList<Product> products ) {
        this.id=id;
        this.products=products;
    }

    public static Receipt createReceipt(String line) throws AmountNotAllowedException {
        String[] parts = line.split("\\s+");
        int id = Integer.parseInt(parts[0]);
        ArrayList<Product> products = new ArrayList<>();
        for(int i=1;i<parts.length-1;i+=2){
            products.add(new Product(Integer.parseInt(parts[i]),TYPE.valueOf(parts[i+1])));
        }

        int sum = products.stream().mapToInt(product -> product.price).sum();
        if(sum>30000)
            throw new AmountNotAllowedException(sum);

        return new Receipt(id,products);
    }

    @Override
    public String toString(){
        return String.format("%d %d %.2f\n",
                id,
                products.stream().mapToInt(Product::getPrice).sum(),
                products.stream().mapToDouble(Product::getTaxReturn).sum()
                );
    }
}

class MojDDV{
    List<Receipt> receipts;

    public MojDDV(  ) {
        this.receipts=new ArrayList<>();
    }

    public void readRecords( InputStream inputStream ){
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        receipts = reader.lines().map(line -> {
            try{
                return Receipt.createReceipt(line);
            } catch (AmountNotAllowedException e) {
                System.out.println(e.getMessage());
                return null;
            }
        })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    void printTaxReturns( OutputStream outputStream ){
        PrintWriter pw = new PrintWriter(outputStream);
        receipts.stream().forEach(receipt -> pw.write(receipt.toString()));



        pw.flush();
        pw.close();
    }
}

class AmountNotAllowedException extends Exception {

    public AmountNotAllowedException(int sum) {
        super(String.format("Receipt with amount %d is not allowed to be scanned", sum));
    }
}