import java.util.Scanner;

public class Solution {
    public static void test1(){
        String s="asdfafd";
        String s1="fdsadf";
        System.out.println(s.charAt(0));
        System.out.println(s.charAt(0)==s.charAt(4));
        System.out.println(s.charAt(0)==s1.charAt(3));
    }
    public static void testCompare(){
        String s1="12366666";
        String s2="1236555555";
        System.out.println(compare(s1,s2));
    }
    public static int compare(String s1, String s2){
        int min=0;
        if(s1.length()<s2.length())
            min=s1.length();
        else
            min=s2.length();
        int index=0;
        for(int i=0;i<min;i++){
            if(s1.charAt(i)==s2.charAt(i))
                index=i;
            else
                break;
        }
        return index+1;
    }
    public static void main(String[] argc){
        //test1();
        //testCompare();
        String phrase=null;
        Scanner scanner=new Scanner(System.in);
        SearchTree searchTree=new SearchTree();
        /*
        while(scanner.hasNext()){
            searchTree.insert(scanner.next());
        }
        searchTree.display();*/

        if(scanner.hasNext())
            phrase=scanner.nextLine();
        System.out.println(phrase);
        String word="";
        for(int i=0;i<phrase.length();i++){
            if(phrase.charAt(i)==' '){
                if(!word.equals("")){
                    searchTree.insert(word);
                }
                word="";
            }else{
                word+=phrase.charAt(i);
            }
        }
        if(!word.equals("")){
            searchTree.insert(word);
        }
        searchTree.display();
    }
}

class SearchTree{
    private Node root=new Node();
    public void insert(String s){
        insert(s, root);
    }
    private void insert(String s, Node root){
        Node child=root.children[s.charAt(root.index)-'a'];
        if(child==null) {//complete insert
            root.children[s.charAt(root.index) - 'a'] = new Node(s);
            root.children[s.charAt(root.index) - 'a'].count++;
        }
        else{//child!=null
            int diffIndex = Solution.compare(s,child.value);
            if(diffIndex<child.index){
                if(diffIndex==s.length()){//complete insert
                    Node node=new Node(s);
                    node.count++;
                    node.children[child.value.charAt(node.index)-'a']=child;
                    root.children[s.charAt(root.index)-'a']=node;
                }else{//diffIndex<s.length()
                    if(diffIndex==root.index){//complete insert
                        root.children[s.charAt(root.index)-'a']=new Node(s);
                        root.children[s.charAt(root.index)-'a'].count++;
                    }else{//diffIndex>root.index
                        String s1="";
                        for(int i=0;i<diffIndex;i++){
                            s1+=s.charAt(i);
                        }
                        Node node=new Node(s1);
                        node.children[child.value.charAt(node.index)-'a']=child;
                        root.children[s.charAt(root.index)-'a']=node;
                        insert(s, node);
                    }
                }
            }else{//diffIndex==child.index
                if(s.length()==child.value.length()){//complete insert
                    child.count++;
                }
                else{//s.length()>child.value.length() goto next level
                    insert(s, child);
                }
            }
        }
    }
    public void display(){
        display(root);
    }
    private void display(Node root){
        for(int i=0;i<26;i++){
            if(root.children[i]!=null){
                display(root.children[i]);
            }
        }
        if(root.value!=null&&root.count!=0){
            System.out.println(root.value+" "+root.count);
        }
    }
}

class Node{
    protected String value=null;
    protected int index=0;//the children nodes' value is different from this node at its position index
    protected int count=0;//count the times value appears
    protected Node[] children=new Node[26];//store nodes whose position index is from 'a' to 'z'
    {
        for(int i=0;i<26;i++)
            children[i]=null;
    }
    public Node(){
        index=0;
        value=null;
    }
    public Node(String s){
        index=s.length();
        value=s;
    }
}