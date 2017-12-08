package ontology;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.ConceptSchema;
import jade.content.schema.ObjectSchema;
import jade.content.schema.PrimitiveSchema;


public class ParametersOntology extends Ontology {

    public static final String VEHICLEPARAMETERS = "VehicleParameters";
    public static final String X = "x";
    public static final String Y = "y";
    public static final String SPEED = "speed";
    public static final String MAX_SPEED = "max_speed";
    public static final String ACCELERATION = "acceleration";

    public static final String NAME = "parameters-ontology";

    public static Ontology instance = new ParametersOntology();

    public ParametersOntology() {
        super(NAME, BasicOntology.getInstance());
        try {

            add(new ConceptSchema(VEHICLEPARAMETERS), VehicleParameters.class);

            ConceptSchema cs = (ConceptSchema)getSchema(VEHICLEPARAMETERS);
            cs.add(X, (PrimitiveSchema)getSchema(BasicOntology.INTEGER));
            cs.add(Y, (PrimitiveSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.OPTIONAL);
            cs.add(SPEED, (PrimitiveSchema)getSchema(BasicOntology.INTEGER));
            cs.add(MAX_SPEED, (PrimitiveSchema)getSchema(BasicOntology.INTEGER));
            cs.add(ACCELERATION, (PrimitiveSchema)getSchema(BasicOntology.INTEGER));

        }
        catch(OntologyException oe) {
            oe.printStackTrace();
        }

    }

    public static Ontology getInstance() {
        return instance;
    }


}
