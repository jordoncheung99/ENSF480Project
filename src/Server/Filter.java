package Server;

import java.util.ArrayList;

public interface Filter {
    ArrayList<Property> Filter (PropertyListing properties,  ArrayList<Criteria> criteria);
}
