public class LinkCup
{
    private int value;
    private LinkCup nextCup = null;
    
    public LinkCup(int value, LinkCup nextCup)
    {
        this.value = value;
        this.nextCup = nextCup;
    }
    
    public boolean contains(int value)
    {
        if (this.value == value) return true;
        if (nextCup == null) return false;
        return nextCup.contains(value);
    }
    
    public boolean contains(LinkCup otherCup)
    {
        if (this == otherCup) return true;
        if (nextCup == null) return false;
        return nextCup.contains(otherCup);
    }
    
    public int getNum()
    {
        return value;
    }
    
    public LinkCup getNextCup()
    {
        return nextCup;
    }
    
    public LinkCup getTailCup()
    {
        if (nextCup == null) return this;
        return nextCup.getTailCup();
    }
    
    public int chainSize()
    {
        int res = 0;
        
        LinkCup temp = this;
        while (temp != null)
        {
            res++;
            temp = temp.getNextCup();
        }
        
        return res;
    }
    
    public void setNextCup(LinkCup cup)
    {
        nextCup = cup;
    }
}
