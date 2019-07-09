package it.emarolab.memorySIT.reasonerCore;
import it.emarolab.amor.owlInterface.OWLReferences;
import it.emarolab.sit.SITBase;
import it.emarolab.memorySIT.owloopDescriptor.ObjectClassDescriptor;
import it.emarolab.memorySIT.owloopDescriptor.ObjectIndividualDescriptor;
import it.emarolab.sit.realObject.GeometricPrimitive;
import it.emarolab.sit.sceneRepresentation.FullSceneRepresentation;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


/**
 * Uses the store() method that increases the storing counter, if the given node is recog-
 *  nized by the corresponding interface or adds a new node (initialized by buildScene()) if
 *  it is an unknown scene.
 *
 */

public class Storing extends ReasonerBase implements AddNode, BuildScene, Recognition, CleanIndividuals {


    private float storingScore;


    public Storing(OWLReferences ontoRef) {
        super(ontoRef);
    }


    public OWLReferences Store(FullSceneRepresentation recognition,Set<GeometricPrimitive> objects ) {

        Timestamp stamp = new Timestamp(System.currentTimeMillis());
        Date date = new Date(stamp.getTime());
        System.out.println("%%%%%%%%%  Timestamp Start <" + this +"> : " + date + "  %%%%%%%%%%%%%%");

        PropertyManager.InitalizeProperty(recognition);

        System.out.println("Recognised with best confidence: " + recognition.getRecognitionConfidence() + " should learn? " + recognition.shouldLearn());
        System.out.println("Best recognised class: " + recognition.getBestRecognitionDescriptor());
        System.out.println("Other recognised classes: " + recognition.getSceneDescriptor().getIndividualTypes());

/*        SceneIndividualDescriptor sceneIndDescr = new SceneIndividualDescriptor(recognition.getSceneIndName(), ontoRef);
        sceneIndDescr.readSemantic();*/



        Recognition.Recognize(recognition,ontoRef);


        /////// learn the new scene if is the case

        if (recognition.shouldLearn()) {
            System.out.println("Adding a new node... ");
            AddNode.addNode(recognition);
        }

        ////Build Scene
        ObjectClassDescriptor ocd= new ObjectClassDescriptor(SITBase.CLASS.PRIMITIVE, ontoRef);
        ocd.readExpressionAxioms();
        Set<ObjectIndividualDescriptor> objIndv = ocd.buildIndividualInstances();
        Set<GeometricPrimitive> objectsRead = BuildScene.buildScene(objIndv,ontoRef);
        ///////// clean ontology

        
        CleanIndividuals.Clean (objects, recognition, ontoRef);

        ontoRef.synchronizeReasoner();
        ontoRef.saveOntology(ontoPath);

        stamp = new Timestamp(System.currentTimeMillis());
        date = new Date(stamp.getTime());
        System.out.println("%%%%%%%%%  Timestamp Finish <" + this +"> : " + date + "  %%%%%%%%%%%%%%");
        return ontoRef;

    }

    public float getStoringScore() {

        return storingScore;
    }

    public void setStoringScore(float storingScore)
    {
        this.storingScore = storingScore;
    }



}