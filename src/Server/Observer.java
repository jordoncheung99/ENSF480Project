package Server;

import Server.Criteria;

public interface Observer {
    public void update(int propertyID);
    public Criteria getCriteria();
}
