import java.util.Stack;

public class DataStructuresAndAlgorithm {

    int maxSubsequenceSum1(int[] src){
        int max=0;
        for(int i=0;i<src.length;i++){
            int tempMax=0;
            for(int j=i;j<src.length;j++){
                tempMax+=src[j];
                if(tempMax>max)
                    max=tempMax;
            }

        }
        return max;
    }

    int maxSubsequenceSum2(int[] src,int left,int right){
        if(left >= right)
            return src[right];
        int mid=(left+right)/2;
        int onlyLeft=maxSubsequenceSum2(src,left,mid);
        int onlyRight=maxSubsequenceSum2(src,mid+1,right);
        int partOfLeft=0,tempPartOfLeft=0,partOfRight=0,tempPartOfRigt=0;
        for(int i=mid;i>=left;i--){
            tempPartOfLeft+=src[i];
            if(tempPartOfLeft>partOfLeft)
                partOfLeft=tempPartOfLeft;
        }
        for(int j=mid+1;j<=right;j++){
            tempPartOfRigt+=src[j];
            if(tempPartOfRigt>partOfRight)
                partOfRight=tempPartOfRigt;
        }
        return maxInThree(onlyLeft,onlyRight,partOfLeft+partOfRight);
    }

    int maxSubsequenceSum3(int[] src){
        int max=0,tempMax=0;
        for(int i=0;i<src.length;i++){
            tempMax+=src[i];
            if(tempMax>max)
                max=tempMax;
            else if(tempMax<0)
                tempMax=0;
        }
        return max;
    }

    int maxInThree(int a, int b, int c){
        int max=0;
        if(a>b)
            max=a;
        else
            max=b;
        if(max<c)
            max=c;
        return max;
    }

    public static int pow(int x,int n){
        if(n==0)
            return 1;
        if(n==1)
            return x;
        if(n%2==0)
            return pow(x*x,n/2);
        else
            return pow(x*x,n/2)*x;
    }

    int reversePolish(String expression){
        ArrayStack arrayStack=new ArrayStack();
        int result=0;
        for(int i=0;i<expression.length();i++){
            String c=expression.substring(i,i+1);
            try{
                Integer integer=Integer.parseInt(c);
                arrayStack.push(new Node<>(integer));
            }
            catch (Exception e) {
                try {
                    int temp=0;
                    if (c.equals("+")) {
                        Node one = arrayStack.pop();
                        Node two = arrayStack.pop();
                        temp= (Integer)one.val + (Integer)two.val;
                    } else if (c.equals("-")) {
                        Node one = arrayStack.pop();
                        Node two = arrayStack.pop();
                        temp= (Integer)one.val - (Integer)two.val;
                    } else if (c.equals("*")) {
                        Node one = arrayStack.pop();
                        Node two = arrayStack.pop();
                        temp= (Integer)one.val * (Integer)two.val;
                    } else if (c.equals("/")) {
                        Node one = arrayStack.pop();
                        Node two = arrayStack.pop();
                        temp= (Integer)one.val / (Integer)two.val;
                    }
                    arrayStack.push(new Node<>(temp));
                    result=temp;
                }
                catch (Exception ea){
                    ea.printStackTrace();
                }
            }
        }
        return result;
    }

    String infixToPostfix(String src){
        LinkedStack linkedStack=new LinkedStack();
        StringBuilder stringBuilder=new StringBuilder();
        Node tempNode=null;
        for(int i=0;i<src.length();i++){
            String c=src.substring(i,i+1);
            try{
            Integer integer=Integer.parseInt(c);
            stringBuilder.append(integer);
            }catch (Exception e){
                if(c.equals("+") || c.equals("-")){
                    while((tempNode=linkedStack.top())!=null){
                        String tempString=(String)tempNode.val;
                        if(tempString.equals("*") || tempString.equals("/") || tempString.equals("+") || tempString.equals("-")){
                            stringBuilder.append(tempString);
                            linkedStack.pop();
                        }else
                            break;
                    }
                    if(c.equals("+"))
                        linkedStack.push(new Node<>("+"));
                    else
                        linkedStack.push(new Node<>("-"));
                }
                else if(c.equals("*") || c.equals("/")){
                    while((tempNode=linkedStack.top())!=null){
                        String tempString=(String)tempNode.val;
                        if(tempString.equals("*") || tempString.equals("/")){
                            stringBuilder.append(tempString);
                            linkedStack.pop();
                        }else
                            break;
                    }
                    if(c.equals("*"))
                        linkedStack.push(new Node<>("*"));
                    else
                        linkedStack.push(new Node<>("/"));
                }
                else if(c.equals("(")){
                    linkedStack.push(new Node<>("("));
                }
                else if(c.equals(")")){
                    while((tempNode=linkedStack.pop())!=null){
                        String tempString=(String)tempNode.val;
                        if(tempString.equals("(")){
                            break;
                        }
                        stringBuilder.append(tempString);
                    }
                }
            }
        }
        while((tempNode=linkedStack.pop())!=null){
            stringBuilder.append(tempNode.val);
        }
        return stringBuilder.toString();
    }


    //类似于逆波兰
    TreeNode postfixToInfixTree(String src){
        ArrayStack arrayStack=new ArrayStack();
        for(int i=0;i<src.length();i++){
            String c=src.substring(i,i+1);
            try {
                Integer integer=Integer.parseInt(c);
                arrayStack.push(new TreeNode<>(c));
            } catch (Exception e) {
                TreeNode root=new TreeNode<>(c);
                root.right=(TreeNode) arrayStack.pop();
                root.left=(TreeNode) arrayStack.pop();
                try {
                    arrayStack.push(root);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
        return (TreeNode)arrayStack.pop();
    }

    public static void stackPre(TreeNode root){
        if(root==null)
            return;
        Stack<TreeNode> stack=new Stack<>();
        TreeNode currentNode=root;
        while(!stack.empty() || currentNode!=null){
            while(currentNode!=null){
                System.out.print(currentNode.val+" ");
                stack.push(currentNode);
                currentNode=currentNode.left;
            }
            currentNode=stack.pop();
            currentNode=currentNode.right;
        }
    }

    public static void stackIn(TreeNode root){
        if(root==null)
            return;
        Stack<TreeNode> stack=new Stack<>();
        TreeNode currentNode=root;
        while(!stack.empty() || currentNode!=null){
            while(currentNode!=null){
                stack.push(currentNode);
                currentNode=currentNode.left;
            }
            currentNode=stack.pop();
            System.out.print(currentNode.val+" ");
            currentNode=currentNode.right;
        }
    }

    public static void stackPost(TreeNode root){
        if(root==null)
            return;
        Stack<TreeNode> stack=new Stack<>();
        TreeNode currentNode=root;
        TreeNode lastPopNode=new TreeNode(-1);
        while(!stack.empty() || currentNode!=null) {
            while(currentNode!=null){
                stack.push(currentNode);
                currentNode=currentNode.left;
            }
            currentNode=stack.pop();
            if(currentNode.right==null || currentNode.right==lastPopNode) {
                System.out.print(currentNode.val + " ");
                lastPopNode = currentNode;
                currentNode=null;
            } else{
                stack.push(currentNode);
                currentNode=currentNode.right;
            }
        }
    }


    public static void main(String[] args){
        HashTable hashTable=new HashTable();
        hashTable.insertOnHashTable("hello","nico");
        System.out.println(hashTable.findOnHashTable("hello"));
    }

}


//应该将next设置为private，然后将访问器方法public，要不然就将返回给用户的部分信息封装在另一类型对象里
//TreeNode也是同样的道理，笔者就不做修改了
//更常见的用法应该是对find返回的Node进一步计算或处理再返给用户
class Node<T>{
    Node next;
    T val;
    Node(T val){
        this.val=val;
    }

}




class TreeNode<T> extends Node<T>{
    //子类域声明与父相同会有覆盖行为
    TreeNode<T> left;
    TreeNode<T> right;
    TreeNode(T value){
        super(value);
    }
}