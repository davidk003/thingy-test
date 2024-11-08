public class LL<T> {
    class LLNode{
        private String index;
        private T data;
        private LLNode next;

        // default constructor. Sets all instance variables to be null
        public LLNode()
        {
            this.index = null;
            this.data = null;
            this.next = null;
        }

        // another constructor. Set data and index to be _data and _index each
        public LLNode(String _index, T _data)
        {
            this.index = _index;
            this.data = _data;
        }

        // return the index that’s stored in this node
        public String getIndex()
        {
            return index;
        }

        // return the data that’s stored in this node
        public T getData()
        {
            return data;
        }

        // update the data in this node to d
        public void setData(T d)
        {
            //Probably add null protection
            this.data = d;
        }
    }
    private LLNode head;
    private LLNode tail;
    private int length;

    public LL()
    {
        this.head = new LLNode();
        this.tail = new LLNode();
        head.next = tail;
        this.length = 0;
    }

    public String toString()
    {
        LLNode current = head.next;
        String result = "null\t: null\n";
        while(current != tail)
        {
            result += current.getIndex() + "\t: " + current.getData() + "\n";
            current = current.next;
        }
        result += "null\t: null\n";
        return result;
    }

    public int getLength()
    {
        return this.length;
    }

    public String[] getDataArray()
    {
        String[] result = new String[this.length];
        LLNode current = head.next;
        int i = 0;
        while(current != tail)
        {
            result[i] = current.getData().toString();
            System.out.println(result[i]);
            current = current.next;
            i++;
        }
        
        return result;
    }

    public String[] getIndexArray()
    {
        String[] result = new String[this.length];
        LLNode current = head.next;
        int i = 0;
        while(current != tail)
        {
            result[i] = current.getIndex().toString();
            System.out.println(result[i]);
            current = current.next;
            i++;
        }
        
        return result;
    }
    
    public void appendNode(String _index, T _data)
    {
        //Probably check for null index here
        if (length == 0)
        {
            LLNode newNode = new LLNode(_index, _data);
            head.next = newNode;
            newNode.next = tail;
            length++;
            return;
        }
        LLNode current = head.next;
        LLNode previous = head;
        while(current != tail)
        {
            previous = current;
            current = current.next;
        }
        LLNode tempNode = new LLNode(_index, _data);
        previous.next = tempNode;
        tempNode.next = tail;
        length++;
    }

    public LLNode searchNode(String _index)
    {
        //Probably check for null index here
        LLNode current = head.next;
        while(current != tail)
        {
            if(current.getIndex().equals(_index))
            {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void removeNode(String _index) throws IllegalArgumentException
    {
        //Probably also check null here but idk
        LLNode current = head;
        while(current.next != tail)
        {
            if(current.next.getIndex().equals(_index)) //Similar to find keep going thru LL until match
            {
                current.next = current.next.next;
                this.length--;
                return;
            }
            current = current.next;
        }
        //If we get here then no node was found so throw exception
        throw new IllegalArgumentException(String.format("removeNode(String _index): No node with an index <%s> in the list", _index));
    }

    public void updateNode(String _index, T value) throws IllegalArgumentException
    {
        //Pretty much same thing as remove but instead of updating next pointer update value
        //If not there throw exception yeh yeh probabl;y check nul blah blah
        LLNode current = head;
        while(current.next != tail)
        {
            if(current.next.getIndex().equals(_index)) //Similar to find keep going thru LL until match
            {
                current.next.setData(value);
                return;
            }
            current = current.next;
        }
        //If we get here then no node was found so throw exception
        throw new IllegalArgumentException(String.format("updateNode(String _index, T value): No node with an index <%s> in the list", _index));
    }


}
