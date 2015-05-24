//*** Implementation of Trie using Linked List***//
/* Every TrieNode has a letter(char), a boolean to mark the if this char is an end of the word, a count of no. of word this letter appears in and a linked list of its children */
/* Every Trie begins with a root node */


import java.util.*;
 
class TrieNode 
{
    char letter; 
    boolean isLastLetter; 
    int count;  
    LinkedList<TrieNode> children; 
 
    
    public TrieNode(char c)
    {
        children = new LinkedList<TrieNode>();
        isLastLetter = false;
        letter = c;
        count = 0;
    }  
    public TrieNode subNode(char c)
    {
        if (children != null)
            for (TrieNode eachChild : children)
                if (eachChild.letter == c)
                    return eachChild;
        return null;
    }
}
/* Insert , Search and Remove operations on trie */
class Trie
{
    private TrieNode root;
 
    
    public Trie()
    {
        root = new TrieNode(' '); 
    }
     
    public void insert(String word)
    {
        if (search(word) == true) 
            return;        
        TrieNode current = root; 
        for (char ch : word.toCharArray() )
        {
            TrieNode child = current.subNode(ch);
            if (child != null)
                current = child;
            else 
            {
                 current.children.add(new TrieNode(ch));
                 current = current.subNode(ch);
            }
            current.count++;
        }
        current.isLastLetter = true;
    }
    
    public boolean search(String word)
    {
        TrieNode current = root;  
        for (char ch : word.toCharArray() )
        {
            if (current.subNode(ch) == null)
                return false;
            else
                current = current.subNode(ch);
        }      
        if (current.isLastLetter == true) 
            return true;
        return false;
    }
    
    public void remove(String word)
    {
        if (search(word) == false)
        {
            System.out.println(word +" does not exist in trie\n");
            return;
        }             
        TrieNode current = root;
        for (char ch : word.toCharArray()) 
        { 
            TrieNode child = current.subNode(ch);
            if (child.count == 1) 
            {
                current.children.remove(child);
                return;
            } 
            else 
            {
                child.count--;
                current = child;
            }
        }
        current.isLastLetter = false;
    }
}    
 
/* Class Trie Test */
public class TrieTest
{
    public static void main(String[] args)
    {            
        Scanner s = new Scanner(System.in);
        
        Trie t = new Trie(); 
        System.out.println("Trie Test\n");          
        char ch;
        
        while (true)    
        {
            System.out.println("\nChoose Trie Operation\n");
            System.out.println("1. Insert ");
            System.out.println("2. Delete");
            System.out.println("3. Search");
			System.out.println("4. Exit");
			
			int choice = s.nextInt(); 
			
            switch (choice)
            {
            case 1 : 
                System.out.println("Enter string element to insert");
                t.insert( s.next() );                     
                break;                          
            case 2 : 
                System.out.println("Enter string element to delete");
                try
                {
                    t.remove( s.next() ); 
                }                    
                catch (Exception e)
                {
                    System.out.println(e.getMessage()+" not found ");
                }
                break;                         
            case 3 : 
                System.out.println("Enter string element to search");
                System.out.println("Search result : "+ t.search( s.next() ));
                break;                                          
            case 4 : 
                System.exit(0);   
			default : 
				System.out.println("Invalid Operation. Enter Again.");
			}                       
        }               
    }
}