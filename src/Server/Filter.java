package Server;

import java.util.ArrayList;

public interface Filter {
    ArrayList<Property> Filter (PropertyListing properties,  Criteria criteria);
}
