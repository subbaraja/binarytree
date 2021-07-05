package com.subba.binarytree;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

/**
 * Binary Tree (non ordered + unbalanced)
 * Since the tree is not ordered and non-balanced, data search operations
 * will not be optimal.
 *
 * @author Subbaraja
 */
public class BinaryTree implements Serializable {

    //marker node to fill for empty node cases
    private final Message MARKER_NODE = new Message(-1, "MARKER");
    private static final Logger logger = Logger.getLogger(BinaryTree.class.getName());

    /**
     * serialize <TreeNode> BinaryTree instance using pre-order traversal recursion approach.
     * The empty nodes will be filled with MARKER_NODE.
     *
     * @param node   -- The root of the tree.
     * @param stream -- outputstream object
     * @throws IOException
     */
    public void serialize(BinaryTreeNode node, ObjectOutputStream stream) throws IOException {

        //check if node is empty and add a marker empty node.
        if (node == null) {
            stream.writeObject(MARKER_NODE);
            return;
        }

        //write to stream
        stream.writeObject(node.data);

        //serialize left and right node
        serialize(node.left, stream);
        serialize(node.right, stream);
    }

    /**
     * deserialize given inputstream and covert to BinaryTree instance.
     *
     * @param stream -- input stream.
     * @return BinaryTreeNode instance.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public BinaryTreeNode deSerialize(ObjectInputStream stream) throws IOException {
        BinaryTreeNode node = null;

        try{
            Object message = stream.readObject();
            if(message == null || !(message instanceof Message)){
                return null;
            }
            Message m = (Message) message;
            if (m.equals(MARKER_NODE)) {
                return null;
            }

            node = new BinaryTreeNode(m);
            node.left = deSerialize(stream);
            node.right = deSerialize(stream);
        } catch (ClassNotFoundException e){
            logger.info("Not able to deserialize -- "+ e.toString());
        }
        return node;
    }


    /**
     * serialize <TreeNode> BinaryTree without recursion.  This method is not used, its only for
     * educational purpose.  Just FYI.
     *
     * @param root
     * @return
     * @throws IOException
     */
    public ObjectOutputStream serialize(BinaryTreeNode root) throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(new ByteArrayOutputStream());

        //check if node is empty and add a marker empty node.
        if (root == null) {
            stream.writeObject(MARKER_NODE);
            return stream;
        }

        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.poll();
            if (node == null) {
                stream.writeObject(MARKER_NODE);
                continue;
            }
            stream.writeObject(node.data);
            queue.add(node.left);
            queue.add(node.right);
        }

        return stream;
    }

    //build moke tree for testing purpose
    private static BinaryTreeNode buildMokeTree() {

        //Build root
        Message root = new Message(1, "20");
        BinaryTreeNode tree = new BinaryTreeNode(root);

        //build rest of the tree for testing purpose.
        tree.left = new BinaryTreeNode(new Message(8, "8"));
        tree.right = new BinaryTreeNode(new Message(22, "22"));
        tree.left.left = new BinaryTreeNode(new Message(4, "4"));
        tree.left.right = new BinaryTreeNode(new Message(12, "12"));
        tree.left.right.left = new BinaryTreeNode(new Message(10, "10"));
        tree.left.right.right = new BinaryTreeNode(new Message(14, "14"));

        return tree;
    }

    public static void main(String[] arg) throws Exception, ClassNotFoundException {

        TreeSerializer fileSerializer = new BinaryTreeFileSerializer();

        //case#1: serialize binarytree to file
        logger.info("Serializing binary tree to file");
        fileSerializer.serialize(BinaryTree.buildMokeTree());
        logger.info("Binary tree successfully written to disk! -- File location:" + BinaryTreeFileSerializer.FILE_STORE);

        //case #2: read bytes from file and deserialize to BinaryTree instance.
        logger.info("Deserializing..");
        BinaryTreeNode t = fileSerializer.deSerialize();
        logger.info("Successfully deserialized tree:" +t);

    }

}

/**
 * TreeNode -- represents a node of a binary tree.
 */
class BinaryTreeNode implements Serializable {

    private static final long serialVersionUID = -4123474751201349234L;

    //data variable
    protected Message data;

    public BinaryTreeNode(Message data) {
        this.data = data;
    }

    //initialize left and right nodes with empty node.
    BinaryTreeNode left = null;
    BinaryTreeNode right = null;

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + this.data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}



