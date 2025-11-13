# Binarytree-genetic-decoder
In this repository is a JavaFX project that decodes a genetic sequence in the form of a binary search tree. Functionality includes:  adding/removing certain chemicals, mutation through human input and random choice, and checking the chemical composition

# How does it work?
Upon booting, the gene is initialized as empty. There are buttons to add new chemicals (as tree nodes, through human input as A, T, C, or G), and removing the last chemical. On the top menu are functions to mutate specific tree node upon human input (specify which node number and what chemical), and mutating a randomly selected tree node with random chemical. If we were to add more of the same chemical as the next one in the gene, we have to mutate the previous one before adding. We also have an option to iterate through the tree to calculate each chemical's composition.
