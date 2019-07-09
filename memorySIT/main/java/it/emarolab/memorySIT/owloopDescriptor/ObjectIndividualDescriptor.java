package it.emarolab.memorySIT.owloopDescriptor;

import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.owloop.descriptor.construction.descriptorEntitySet.DescriptorEntitySet;
import it.emarolab.owloop.descriptor.construction.descriptorExpression.IndividualExpression;
import it.emarolab.owloop.descriptor.construction.descriptorGround.IndividualGround;
import it.emarolab.sit.SITBase;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;


public class ObjectIndividualDescriptor
        extends IndividualGround
        implements IndividualExpression.Type<ObjectClassDescriptor>,
        SITBase{


    private DescriptorEntitySet.Concepts individualTypes = new DescriptorEntitySet.Concepts();

    public ObjectIndividualDescriptor(OWLNamedIndividual instance, OWLReferences onto) {
        super(instance, onto);
    }
    public ObjectIndividualDescriptor(String instanceName, OWLReferences onto) {
        super(instanceName, onto);
    }
    public ObjectIndividualDescriptor(OWLNamedIndividual instance, String ontoName) {
        super(instance, ontoName);
    }
    public ObjectIndividualDescriptor(String instanceName, String ontoName) {
        super(instanceName, ontoName);
    }


    @Override
    public List<MappingIntent> readExpressionAxioms() {
        List<MappingIntent> r = Type.super.readExpressionAxioms();
        return r;
    }

    @Override
    public List<MappingIntent> writeExpressionAxioms() {
        List<MappingIntent> r = Type.super.writeExpressionAxioms();
        return r;
    }

    @Override
    public ObjectClassDescriptor getNewIndividualType(OWLClass instance, OWLReferences ontology) {
        return new ObjectClassDescriptor( instance, ontology);
    }

    @Override
    public DescriptorEntitySet.Concepts getIndividualTypes() {
        return individualTypes;
    }

    @Override
    public String toString() {
        return "SceneIndividualDescriptor{" +
                NL + "\t\t\t" +
                "," + NL + "\t∈ " + individualTypes +
                NL + "}";
    }
}
