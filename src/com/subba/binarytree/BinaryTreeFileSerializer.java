package com.subba.binarytree;

import java.io.*;

/**
 * Convenience class to serialize and deserialize BinaryTree to File.
 * See BinaryTree.java for core serialization and deserialization.
 *
 * --> This class provides helper methods to write BinaryTree to File.
 * --> Reads file content and rebuild file stream to Binary Tree instance.
 */
public class BinaryTreeFileSerializer implements TreeSerializer{

    //TODO -- have FileStore quotas. throw Quota Exceptions

    //disk file location.  Take from property file.
    public static final String FILE_STORE = getTempFolderLocation() + File.separator + "test.txt";

    //default empty constructor
    BinaryTreeFileSerializer() {};

    /**
     * convenience method to write BinaryTree to file.
     *
     * @param tree -- root of binary tree
     * @throws IOException
     */
    public void serialize(BinaryTreeNode tree) throws IOException {
        BinaryTree bt = new BinaryTree();

        //serialize and write to file
        OutputStream outStream = new FileOutputStream(new File(FILE_STORE));
        ObjectOutputStream stream = new ObjectOutputStream(outStream);
        bt.serialize(tree, stream);

        //close output stream
        stream.close();
        outStream.close();
    }

    public BinaryTreeNode deSerialize() throws IOException {
        BinaryTree bt = new BinaryTree();

        //read content from file
        InputStream filestream = new FileInputStream(new File(FILE_STORE));
        ObjectInputStream istream = new ObjectInputStream(filestream);

        //deserialize file content to Binary Tree
        BinaryTreeNode tree = bt.deSerialize(istream);

        istream.close();
        filestream.close();

        return tree;
    }

    private static String getTempFolderLocation(){
        return System.getProperty("java.io.tmpdir");
    }
}
