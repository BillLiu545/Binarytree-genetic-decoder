import StackQueueTree.*;
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.*;
public class Gene extends BinarySearchTree<ChemicalNode>
{
    // Randomly generated node to represent a chemical in the gene
    private String genRandChem()
    {
        Random rand = new Random();
        String[] arr = {"A", "C", "G", "T"};
        int randInt = rand.nextInt(arr.length);
        String s = arr[randInt];
        return s;
    }
    
    // User inputs a given chemical type; we also check to see if it is valid
    private String inputChem()
    {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Chemical Type");
        dialog.setHeaderText("Enter A, T, C, or G: ");
        dialog.setContentText("Type: ");
        Optional<String> optional = dialog.showAndWait();
        if (optional.isPresent())
        {
            String s = optional.get();
            if (s.equals("A") || s.equals("C") || s.equals("T") || s.equals("G"))
            {
                return s;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }
    
    // New chemical added to gene
    private String addChem(String chem)
    {
        if (chem == null)
        {
            return "Invalid chemical input.";
        }
        ChemicalNode node = new ChemicalNode(chem);
        add(node);
        return "Added additional " + chem + " to gene.";
    }
    
    // Add chemical based on user input
    public String add_input()
    {
        String chem = inputChem();
        return addChem(chem);
    }
    
    // Remove a chemical from the gene
    public String removeChem()
    {
        if (isEmpty()) 
        {
            return "Genetic string is empty";
        }
        Iterator<ChemicalNode> iter = getPreorderIterator();
        ChemicalNode removed = null;
        while (iter.hasNext())
        {
            removed = iter.next();
        }
        if (removed != null)
        {
            remove(removed);
            return "Removed additional " + removed.getType() + " from gene.";
        }
        return "No node found to remove.";
    }
    
    // Gene is mutated at a specific node of the tree
    private String mutate(int whichNode, String chem)
    {
        Iterator<ChemicalNode> iter = getPreorderIterator();
        int counter = 0;
        while (iter.hasNext())
        {
            ChemicalNode node = iter.next();
            counter++;
            if (counter == whichNode)
            {
                node.setType(chem);
                return "Node " + counter + " mutated to: " + chem;
            }
        }
        return "Node " + whichNode + " not found";
    }
    
    // Mutate based on user input
    public String mutate_input(int whichNode)
    {
        String chem = inputChem();
        return mutate(whichNode, chem);
    }
    
    // Gene is mutated at a random node with a new node that is also randomly generated
    public String random_mutate()
    {
        int num_nodes = getNumberOfNodes();
        if (num_nodes == 0)
        {
            return "No nodes available for mutation.";
        }
        Random rand = new Random();
        int randInt = rand.nextInt(num_nodes) + 1;
        String rand_chem =  genRandChem();
        Iterator<ChemicalNode> iter = getPreorderIterator();
        int counter = 0;
        ChemicalNode old = null;
        while (iter.hasNext())
        {
            ChemicalNode node = iter.next();
            counter++;
            if (counter == randInt)
            {
                node.setType(rand_chem);
                return "Node " + counter + " mutated to: " + rand_chem;
            }
        }
        return "Mutation failed.";
    }
    
    // Full gene as a concatenated string
    public String entireGene()
    {
        if (isEmpty())
        {
            return "N/A";
        }
        Iterator<ChemicalNode> iter = getPreorderIterator();
        String fullGene = "";
        while (iter.hasNext())
        {
            fullGene += iter.next().getType();
        }
        return fullGene;
    }
    
    // Find chemical composition of the entire gene in A, T, C, G
    public String findChems()
    {
        int num_a = 0, num_c = 0, num_g = 0, num_t = 0;
        Iterator<ChemicalNode> iter = getPreorderIterator();
        while (iter.hasNext())
        {
            ChemicalNode node = iter.next();
            if (node.getType().equals("A"))
            {
                num_a++;
            }
            else if (node.getType().equals("T"))
            {
                num_t++;
            }
            else if (node.getType().equals("C"))
            {
                num_c++;
            }
            else if (node.getType().equals("G"))
            {
                num_g++;
            }
        }
        return "A: " + num_a +"/T: " + num_t + "/C: " + num_c + "/G: " + num_g;
    }
    
    // Main method to test data structure operations
    public static void main(String[] args)
    {
        Gene g = new Gene();
        System.out.println(g.addChem("A"));
        System.out.println(g.addChem("G"));
        System.out.println(g.addChem("T"));
        System.out.println("Full gene: " + g.entireGene());

        System.out.println(g.mutate(2, "C"));
        System.out.println("After mutation: " + g.entireGene());

        System.out.println(g.random_mutate());
        System.out.println("After random mutation: " + g.entireGene());

        System.out.println(g.removeChem());
        System.out.println("Final gene: " + g.entireGene());
        System.out.println(g.findChems());
    }
}
