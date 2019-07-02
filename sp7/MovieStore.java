import java.util.*;

public class MovieStore {
    private List<Tape> tapeList=null;
    public MovieStore(){
        tapeList=new ArrayList<>();
    }
    public boolean addTape(Tape tape){
        for(Iterator<Tape> itr=tapeList.iterator();itr.hasNext();){
            Tape temp=itr.next();
            if(temp.getId()==tape.getId())
                return false;
        }
        return tapeList.add(tape);
    }
    public void rmvTape(int id){
        for(Iterator<Tape>itr=tapeList.iterator();itr.hasNext();){
            Tape temp=itr.next();
            if(temp.getId()==id)
                itr.remove();
        }

    }
    public void rmvTapes(String name){
        for(Iterator<Tape> itr=tapeList.iterator();itr.hasNext();){
            Tape temp=itr.next();
            if(temp.getName().equals(name)){
                itr.remove();
            }
        }
    }
    public Tape searchTape(int id){
        for(Iterator<Tape> itr=tapeList.iterator();itr.hasNext();){
            Tape temp=itr.next();
            if(temp.getId()==id)
                return temp;
        }
        return null;
    }
    public List<Tape> searchTape(String name){
        List<Tape> list=new ArrayList<>();
        for(Iterator<Tape> itr=tapeList.iterator();itr.hasNext();){
            Tape temp=itr.next();
            if(temp.getName().equals(name))
                list.add(temp);
        }
        return list;
    }

    public boolean rent(User user, String name){
        List<Tape> tapes=searchTape(name);
        boolean flag=true;
        for(Iterator<Tape> itr=tapes.iterator();itr.hasNext()&&!flag;){
            Tape tape=itr.next();
            flag=user.rent(tape);
        }
        return flag;
    }
    public boolean rtn(int id){
        boolean flag=false;
        Tape tape=searchTape(id);
        flag=tape.rtn();
        return flag;
    }

    private static void testDate(){
        Date date=new Date();
        System.out.println(date);
        System.out.println(date.getTime());
        date.setTime(date.getTime()+1000*60*60*24*24);
        System.out.println(date);
        System.out.println(date.getTime());
        date.setTime(date.getTime()+1000*60*60*24*1);
        System.out.println(date);
        System.out.println(date.getTime());
    }
    public static void main(String[] args){
        testDate();
        //
    }
}

class Tape{
    private Date rentDate, deadline;
    private double pay;
    private double overpay;
    private boolean isRent;
    private int id;
    private String name;
    private User user;
    public Tape(String name, int id, double pay, double overpay){
        this.name=name;
        this.id=id;
        isRent=false;
        this.pay=pay;
        this.overpay=overpay;
        user=null;
    }
    public Tape(String name, int id){
        this(name, id, 0, 0);
    }
    public boolean rent(User user){
        if(isRent){
            return false;
        }else{
            isRent=true;
            rentDate=new Date();
            deadline=new Date(rentDate.getTime()+1000*60*60*24*7);//a week later
            this.user=user;
            return true;
        }
    }
    public boolean rtn(){
        Date date=new Date();
        if(date.after(deadline)){
            //overpay
        }else{
            //pay
        }
        boolean flag=user.getBorrowList().remove(this);
        isRent=false;
        if(flag)
            user=null;
        return flag;
    }

    public Date getRentDate(){return (Date)rentDate.clone();}
    public Date getDeadline(){return (Date) deadline.clone();}
    public double getPay(){return pay;}
    public void setPay(double pay){this.pay=pay;}
    public double getOverpay(){return overpay;}
    public void setOverpay(double overpay){this.overpay=overpay;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public User getUser(){return user.clone();}
}

class People{
    protected int id;
    protected String name;
    protected String pw;
    public People(int id, String name, String pw){
        this.id=id;
        this.name=name;
        this.pw=pw;
    }
    public int getId(){return id;}
    public String getName(){return name;}
    public void setPw(String pw){this.pw=pw;}
    public People clone(){return new People(id, name, pw);}
}

class User extends People{
    private List<Tape> borrowList=new ArrayList<>();//store the tapes he/she borrows
    public User(int id, String name, String pw){
        super(id, name, pw);
    }
    public boolean rent(Tape tape){
        boolean flag=false;
        flag=tape.rent(this);
        if(flag)
            while(!borrowList.add(tape));
        return flag;
    }

    public List<Tape> getBorrowList(){return borrowList;}
    public User clone(){
        User temp=new User(id, name, pw);
        temp.borrowList=borrowList;
        return temp;
    }
}