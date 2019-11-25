package Server;


import java.util.ArrayList;

public class BureFroceFilter implements Filter {


    @Override
    public ArrayList<Property> Filter(PropertyListing properties, Criteria criteria) {
        ArrayList<Property> send = new ArrayList<>();
        ArrayList<Property> master = properties.getProperties();
        //Initally populate the list
        for (int i  = 0; i < properties.getProperties().size(); i++){
            if (master.get(i).active && !master.get(i).rented && !master.get(i).suspended){
                send.add(master.get(i));
            }
        }
        //check against criteria
        for (int j = 0; j < send.size(); j++){
            if (!isWithinRange(send.get(j),criteria)){
                send.remove(j);
                j--;
            }
        }
        return  send;
    }

    private boolean isWithinRange(Property property, Criteria criteria){
        if (criteria.getFurnishedEnable()){
            if (property.getFurnished() != criteria.getFurnished()){
                return false;
            }
        }

        if(property.getNumOfBedRooms() < criteria.getNumBedRoom()){
            return false;
        }

        if(property.getNumOfBathRooms() < criteria.getNumBathRooms()){
            return false;
        }
        if (criteria.getTypeOfProprty() != null){
            String[] types = criteria.getTypeOfProprty();
            boolean inList = false;
            for (int i =0; i < types.length; i++){
                if (types[i].equalsIgnoreCase(property.getTypeOfProperty())){
                    inList = true;
                }
            }
            if(!inList){
                return false;
            }
        }

        if(property.getArea() < criteria.getArea()){
            return false;
        }

        if(property.getRentTerm() < criteria.getRentTerm()){
            return false;
        }

        if(criteria.getRentAmmount() != -1){
            if (property.getRentAmmount() > criteria.getRentAmmount()){
                return false;
            }
        }

//        if (criteria.getCityQuadrant() != null){
//            String[] quad = criteria.getCityQuadrant();
//            boolean inList = false;
//            for (int i = 0; i < quad.length; i++){
//                if (quad[i].equalsIgnoreCase(property.getAddress().getCityQuadrant())){
//                    inList = true;
//                    break;
//                }
//            }
//            if (!inList){
//                return false;
//            }
//        }

        if (criteria.getPropertyIDs() != null){
            int[] ids = criteria.getPropertyIDs();
            boolean inList = false;
            for (int i = 0; i < ids.length; i++){
                if (ids[i] == property.getListID()){
                    inList = true;
                    break;
                }
            }
            if (!inList){
                return false;
            }
        }


        return true;
    }
}
