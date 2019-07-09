package it.emarolab.memorySIT.owloopDescriptor;

import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.owloop.descriptor.construction.descriptorEntitySet.DescriptorEntitySet;
import it.emarolab.owloop.descriptor.construction.descriptorExpression.ConceptExpression;
import it.emarolab.owloop.descriptor.construction.descriptorGround.ConceptGround;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;

/**
 * The  <a href="https://github.com/EmaroLab/owloop">OWLOOP</a> {@code Descriptor} for a class representing an abstract Scene.
 * <p>
 *     This is an OWLClass {@code Descriptor} based on the
 *     <a href="https://github.com/EmaroLab/owloop">OWLOOP</a> API.
 *     It is in charge to synchronise the individuals classified in {@code this}
 *     class, as well as sub and super classes. It also implements
 *     method for ontological class definition and based on this, it
 *     computes the scene class cardinality.
 *
 * <div style="text-align:center;"><small>
 * <b>File</b>:        it.emarolab.sit.owloopDescriptor.SceneClassDescriptor <br>
 * <b>Licence</b>:     GNU GENERAL PUBLIC LICENSE. Version 3, 29 June 2007 <br>
 * <b>Author</b>:      Buoncompagni Luca (luca.buoncompagni@edu.unige.it) <br>
 * <b>affiliation</b>: EMAROLab, DIBRIS, University of Genoa. <br>
 * <b>date</b>:        05/06/17 <br>
 * </small></div>
 */
public class ObjectClassDescriptor
        extends ConceptGround
        implements ConceptExpression.Sub<ObjectClassDescriptor>,
        ConceptExpression.Instance<ObjectIndividualDescriptor> {

    private DescriptorEntitySet.Concepts subConcept = new DescriptorEntitySet.Concepts();
    private DescriptorEntitySet.Individuals classifiedIndividual = new DescriptorEntitySet.Individuals();

    public ObjectClassDescriptor(OWLClass instance, String ontoName) {
        super(instance, ontoName);
    }
    public ObjectClassDescriptor(String instanceName, String ontoName) {
        super(instanceName, ontoName);
    }
     public ObjectClassDescriptor(OWLClass instance, OWLReferences ontoName) {
        super(instance, ontoName);
    }
    public ObjectClassDescriptor(String instanceName, OWLReferences ontoName) {
        super(instanceName, ontoName);
    }

    @Override
    public List<MappingIntent> readExpressionAxioms() {
        List<MappingIntent> r = Sub.super.readExpressionAxioms();
        r.addAll( Instance.super.readExpressionAxioms());
        return r;
    }

    @Override
    public List<MappingIntent> writeExpressionAxioms() {
        List<MappingIntent> r = Instance.super.writeExpressionAxioms();
        r.addAll( Sub.super.writeExpressionAxioms());
        return r;
    }


    @Override
    public ObjectIndividualDescriptor getIndividualDescriptor(OWLNamedIndividual instance, OWLReferences ontology) {
        return new ObjectIndividualDescriptor( instance, ontology);
    }

    @Override
    public DescriptorEntitySet.Individuals getIndividualInstances() {
        return classifiedIndividual;
    }

    @Override
    public ObjectClassDescriptor getSubConceptDescriptor(OWLClass instance, OWLReferences ontology) {
        return new ObjectClassDescriptor( instance, ontology);
    }

    @Override
    public DescriptorEntitySet.Concepts getSubConcepts() {
        return subConcept;
    }


    @Override
    public String toString() {
        return "SceneClassDescriptor{" +
                NL + "\t\t\t" +
                "," + NL + "\t⇐ " + classifiedIndividual +
                "," + NL + "\t⊃ " + subConcept +
                NL + "}";


    }

}
