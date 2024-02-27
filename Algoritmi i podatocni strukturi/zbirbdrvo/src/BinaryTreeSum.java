import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class BNode<E> {

    public E info;
    public BNode<E> left;
    public BNode<E> right;

    static int LEFT = 1;
    static int RIGHT = 2;

    @SuppressWarnings("unchecked")
    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }
    @SuppressWarnings("unchecked")
    public BNode() {
        this.info = null;
        left = null;
        right = null;
    }
    @SuppressWarnings("unchecked")
    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

}
@SuppressWarnings("unchecked")
class BTree<E extends Comparable<E>> {


    @SuppressWarnings("unchecked")
    public BNode<E> root;
    @SuppressWarnings("unchecked")
    public BTree() {
        root = null;
    }
    @SuppressWarnings("unchecked")
    public BTree(E info) {

        root = new BNode<E>(info);
    }
    @SuppressWarnings("unchecked")
    public void makeRoot(E elem) {

        root = new BNode(elem);
    }
    @SuppressWarnings("unchecked")
    public void makeRootNode(BNode<E> node) {

        root = node;
    }
    @SuppressWarnings("unchecked")
    public BNode<E> addChild(BNode<E> node, int where, E elem) {
        @SuppressWarnings("unchecked")
        BNode<E> tmp = new BNode<E>(elem);

        if (where == BNode.LEFT) {

            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }

        return tmp;
    }
    @SuppressWarnings("unchecked")
    public BNode<E> addChildNode(BNode<E> node, int where, BNode<E> tmp) {

        if (where == BNode.LEFT) {

            if (node.left != null)  // veke postoi element

                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }

        return tmp;
    }

}
@SuppressWarnings("unchecked")
public class BinaryTreeSum {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        @SuppressWarnings("unchecked")
        int i, j, k;
        @SuppressWarnings("unchecked")
        int index;
        @SuppressWarnings("unchecked")
        String action;
        @SuppressWarnings("unchecked")
        String line;
        @SuppressWarnings("unchecked")
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        @SuppressWarnings("unchecked")
        StringTokenizer st;
        @SuppressWarnings("unchecked")
        int N = Integer.parseInt(br.readLine());
        @SuppressWarnings("unchecked") BNode<Integer>[] nodes= new BNode[N];
        @SuppressWarnings("unchecked")
        BTree<Integer> tree = new BTree<Integer>();

        for (i=0;i<N;i++)
            nodes[i] = new BNode<Integer>();

        for (i = 0; i < N; i++) {

            line = br.readLine();

            st = new StringTokenizer(line);

            index = Integer.parseInt(st.nextToken());

            nodes[index].info = Integer.parseInt(st.nextToken());

            action = st.nextToken();

            if (action.equals("LEFT")) {

                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);

            } else if (action.equals("RIGHT")) {

                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
            } else {
                // this node is the root

                tree.makeRootNode(nodes[index]);
            }
        }

        @SuppressWarnings("unchecked")
        int baranaVrednost=Integer.parseInt(br.readLine());

        br.close();
        Stack<BNode<Integer>> stack = new Stack<>();
        BNode<Integer> node = tree.root;
        stack.push(node);
        while(!stack.isEmpty()){
            node=stack.pop();
            if(node.info == baranaVrednost) break;
            if(node.left != null) stack.push(node.left);
            if(node.right != null) stack.push(node.right);
        }
        stack.clear();
        BNode<Integer> leftSubtree = node.left;
        BNode<Integer> rightSubtree = node.right;

        int leftSum=0,rightSum=0;

        if(leftSubtree!=null){
            stack.push(leftSubtree);
            while(!stack.isEmpty()){
                node = stack.pop();
                if(node != null) {
                    if ( node.info < baranaVrednost )
                        leftSum+=node.info;
                    if ( node.left != null )
                        stack.push(node.left);
                    if ( node.right != null )
                        stack.push(node.right);
                }
            }
        }
        if(rightSubtree!=null){
            stack.push(rightSubtree);
            while(!stack.isEmpty()){
                node = stack.pop();
                if(node != null) {
                    if ( node.info > baranaVrednost )
                        rightSum+=node.info;
                    if ( node.left != null )
                        stack.push(node.left);
                    if ( node.right != null )
                        stack.push(node.right);
                }
            }
        }

        /*@SuppressWarnings("unchecked")
        Stack<BNode<Integer>> stack = new Stack<>();
        BNode<Integer> root = tree.root;
        BNode<Integer> node=null;
        stack.push(root);
        while(!stack.isEmpty()){
            node = stack.pop();
            if(node.info==baranaVrednost)
                break;
            if(node.left!=null)
                stack.push(node.left);
            if(node.right!=null)
                stack.push(node.right);
        }

        @SuppressWarnings("unchecked")
        BNode<Integer> leftSubtree = node.left;
        @SuppressWarnings("unchecked")
        BNode<Integer> rightSubtree = node.right;


        stack.push(leftSubtree);
        @SuppressWarnings("unchecked")
        int leftSum=0,rightSum=0;
        while(!stack.isEmpty()){
            node = stack.pop();
            if(node != null) {
                if ( node.info < baranaVrednost )
                    leftSum+=node.info;
                if ( node.left != null )
                    stack.push(node.left);
                if ( node.right != null )
                    stack.push(node.right);
            }
        }

        stack.push(rightSubtree);
        while(!stack.isEmpty()){
            node = stack.pop();
            if(node != null) {
                if ( node.info > baranaVrednost )
                    rightSum+=node.info;
                if ( node.left != null )
                    stack.push(node.left);
                if ( node.right != null )
                    stack.push(node.right);
            }
        }

*/

        System.out.println(leftSum + " " + rightSum);
    }
}
