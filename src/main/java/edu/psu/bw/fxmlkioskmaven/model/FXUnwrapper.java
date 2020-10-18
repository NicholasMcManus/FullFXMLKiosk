/*
 * file name: FXUnwrapper.java
 * programmer name: Nick McManus
 * date created: 10-02-2020
 * date of last revision: 
 * details of last revision:
 * short description: 
 */

package edu.psu.bw.fxmlkioskmaven.model;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javafx.scene.Node;
import javafx.scene.Parent;

public class FXUnwrapper {
    private final static Logger LOG = LogManager.getLogger(FXUnwrapper.class);
    
    public static GenericTree<Node> getStructure(Node parentNode)
    {
        GenericTree<Node> root = new GenericTree(parentNode);
        
        //If the node is not a parent, it can be assumed to be a leaf
        if(parentNode instanceof Parent)
        {
            LOG.error(parentNode.toString() + " | " + ((Parent)parentNode).getChildrenUnmodifiable().size());
            for(Node node : ((Parent)parentNode).getChildrenUnmodifiable())
            {
                LOG.info("\t"+node.toString());
                root.addLeaf(getStructure(node));
            }
        }
        else
        {
            LOG.info("Leaf: " + parentNode.toString());
        }
        
        return root;
    }
}
