import java.util.ArrayList;

public class PropertyListing {
    private ArrayList<Property> properties;

    PropertyListing(){
        properties = new ArrayList<>();
    }

    void addProperty(Property property){
        properties.add(property);
    }

    ArrayList<Property> getProperties(){
        return properties;
    }
}
