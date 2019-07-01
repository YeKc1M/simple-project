import java.util.*;

public class Inventory {
    private List<Product> productList=null;
    public Inventory(){
        productList=new ArrayList<Product>();
    }
    public void addItem(Product product){productList.add(product);}
    public void rmvItem(int id){
        for(Iterator<Product> itr=productList.iterator();itr.hasNext();){
            if(itr.next().id==id){
                itr.remove();
                break;
            }
        }
    }
    public double value(){
        double v=0;
        for(Iterator<Product> itr=productList.iterator();itr.hasNext();){
            v+=itr.next().value();
        }
        return v;
    }
    public static void main(String[] args){
        //
    }
}

class Product{
    protected int id;
    protected int num;
    protected double price;
    public Product(int id, int price, int num){
        this.id=id;
        this.price=price;
        this.num=num;
    }
    public Product(int id, int price){
        this.id=id;
        this.price=price;
        this.num=0;
    }
    public boolean equals(Product product){
        return this.id==product.id&&this.price==product.price;
    }
    public int ID(){return id;}
    public int Num(){return num;}
    public double Price(){return price;}
    public void setId(int i){id=i;}
    public void setNum(int n){num=n;}
    public void setPrice(double p){price=p; }
    public void addNum(int add){num+=add;}
    public void decNum(int dec){num-=dec;}
    public double value(){
        return num*price;
    }
}