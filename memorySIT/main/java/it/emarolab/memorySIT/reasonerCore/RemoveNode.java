package it.emarolab.memorySIT.reasonerCore;

import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.sit.owloopDescriptor.SceneClassDescriptor;

/**
 *
 * Deletes a node from the graph and puts it into the trash bin.
 *
 */

public interface RemoveNode
{

    static boolean deletenode(String sceneName, OWLReferences ontoRef)
    {
        SceneClassDescriptor remover = new SceneClassDescriptor(sceneName, ontoRef);
        remover.readExpressionAxioms();
        remover.getDefinitionConcepts().clear();
        remover.writeExpressionAxioms();
        remover.addSuperConcept("Trash");
        remover.writeExpressionAxioms();
        System.out.println(" $$$$$ " + remover.getDefinitionConcepts());

        return true;
    }
}
