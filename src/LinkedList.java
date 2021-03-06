class LinkedList<T>{

    private Node head;

    public Node find(T value){
        Node tempNode=head;
        while(tempNode!=null){
            if((Integer)tempNode.val==value)
                return tempNode;
            tempNode=tempNode.next;
        }
        return null;
    }

    public void delete(T value){
        Node tempNode=head,previousNode=head;
        while(tempNode!=null){
            if((Integer)tempNode.val==value){
                if(previousNode.next==null)
                    head=null;
                previousNode.next=tempNode.next;
                break;
            }
            previousNode=tempNode;
            tempNode=tempNode.next;
        }
    }

    public void insert(Node previous, T value){
        Node newNode=new Node<>(value);
        if(previous!=null){
            newNode.next=previous.next;
            previous.next=newNode;
        }
        else{
            head=newNode;
        }
    }

    public void insertToHead(T value){
        if(head==null)
            head=new Node<>(value);
        else{
            Node newHead=new Node<>(value);
            newHead.next=head;
            head=newHead;
        }
    }

    public void insertToHead(Node node){
        if(head==null)
            head=node;
        else{
            node.next=head;
            head=node;
        }
    }

}

