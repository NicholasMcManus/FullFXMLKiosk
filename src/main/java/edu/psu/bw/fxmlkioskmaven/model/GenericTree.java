/*
 * file name: GenericTree.java
 * programmer name: Nick McManus
 * date created: 10-02-2020
 * date of last revision: 
 * details of last revision:
 * short description: 
 */

package edu.psu.bw.fxmlkioskmaven.model;

import java.util.ArrayList;
import java.util.List;

//A Basic Implementation of Tree Data Structure
public class GenericTree <T>{
    private static int layer = 0;
    
    //Variables
    private T data;
    private List<GenericTree> children;
    
    /**
     * Default constructor, puts together list
     */
    public GenericTree()
    {
        //By Default: Construct the List
        this.children = new ArrayList();
    }

    /**
     * Initializes the node with specific data
     * @param data The data to assign to the node
     */
    public GenericTree(T data) {
        //Construct the list and assign a value to data
        this();
        this.data = data;
    }

    /**
     * Creates a single layer of leafs based on a passed list
     * @param data The list to convert into leafs
     */
    public GenericTree(List<T> data)
    {
        this();
        for(T entry : data)
        {
            this.addLeaf(entry);
        }
    }
    
    /**
     * An access method for the data contained
     * @return The data stored in the node
     */
    public T getData() {
        return data;
    }

    /**
     * Change the data stored in the node
     * @param data The new data to have stored in the node
     */
    public void setData(T data) {
        this.data = data;
    }
    
    /**
     * Whether to not the current node is a leaf
     * @return True if the node has no branches
     */
    public boolean isLeaf()
    {
        return (this.getNumBranch() == 0);
    }
    
    /**
     * Get the number of branches
     * @return The number of nodes directly under this one
     */
    public int getNumBranch()
    {
        return children.size();
    }
            
    /**
     * Get a leaf which could be a forest of its own
     * @param leaf The number of the leaf being retrieved
     * @return A leaf of the node, if the integer was valid
     */
    public GenericTree getLeaf(int leaf)
    {
        //Handle potential failure by returning null
        if(leaf < 0 || leaf >= getNumBranch())
            return null;
        
        return this.children.get(leaf);
    }
    
    /**
     * Add a leaf to the tree
     * @param data the data of the leaf to be added
     */
    public final void addLeaf(T data)
    {
        this.children.add(new GenericTree(data));
    }
    
    /**
     * Add a branch that is a sub-forest
     * @param branch The tree to append to the forest
     */
    public final void addLeaf(GenericTree branch)
    {
        this.children.add(branch);
    }
    
    /**
     * Remove a leaf from the tree
     * @param leaf The number of the leaf that is being removed
     */
    public void removeLeaf(int leaf)
    {
        if(leaf < 0 || leaf >= getNumBranch())
            return;
        
        this.children.remove(leaf);
    }

    //Create a string representation of the tree
    @Override
    public String toString() {
        String output = this.getNumBranch() + ":";
        
        output += data.toString() + "\n";
        layer++;
        for(int i = 0; i < this.getNumBranch(); i++)
        {
            for(int j = 0; j < layer; j++)
                output += "    ";
            
            output += this.getLeaf(i).toString();
            layer--;
        }
        return output;
    }
}
