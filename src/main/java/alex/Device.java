package alex;

import javax.persistence.Column;
import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Device extends PanacheEntity {
    
    @Column (unique=true)
    public String deviceID; 

    // Connection 
    // public boolean connected;
    
    // Common Fields 
    // public String deviceType;
    // public String buildingRoom;
    // public String desk;
    // public String mac;
    
    // Device Specific Fields
    //public boolean occupied;     
    // public double energy;    
    // public double voltage;
    // public double current;
    //public double secOccupied;
    public int batteryPer;
}
