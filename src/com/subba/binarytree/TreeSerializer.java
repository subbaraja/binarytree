package com.subba.binarytree;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface TreeSerializer {

    /**
     * Serialize binary tree
     **/
    void serialize(BinaryTreeNode bt) throws Exception;

    /**
     * deserialize stream to Binary Tree.
     **/
    BinaryTreeNode deSerialize() throws Exception;
}
