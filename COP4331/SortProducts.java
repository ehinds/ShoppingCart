package COP4331;

import java.util.Comparator;

/**
 *
 * @author Brownie
 */
public class SortProducts
{
    public static class SortName implements Comparator<Product>
    {

        @Override
        public int compare(Product p1, Product p2) 
        { 
                int NameCompare = p1.title.compareTo(p2.title); 

                return NameCompare;
        } 
        
    }
    
    public static class SortNameReverse implements Comparator<Product>
    {

        @Override
        public int compare(Product p1, Product p2) 
        { 
                int NameCompare = p2.title.compareTo(p1.title); 

                return NameCompare;
        } 
        
    }
    
    
    public static class SortPrice  implements Comparator<Product>
    {

        @Override
        public int compare(Product p1, Product p2) 
        { 
                int NameCompare = p1.getPrice().compareTo(p2.getPrice()); 

                return NameCompare;
        } 
        
    }
    
    public static class SortPriceReverse  implements Comparator<Product>
    {

        @Override
        public int compare(Product p1, Product p2) 
        { 
                int NameCompare = p2.getPrice().compareTo(p1.getPrice()); 

                return NameCompare;
        } 
        
    }
}
