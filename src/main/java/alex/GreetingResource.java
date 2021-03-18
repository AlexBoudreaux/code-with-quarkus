package alex;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

// import javax.inject.Inject;
// import org.eclipse.microprofile.jwt.JsonWebToken;
// import io.quarkus.oidc.IdToken;
// import io.quarkus.security.Authenticated;


@Path("/devices")
public class GreetingResource {

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDevice(Device dIn) { 

        dIn.persist();
        return Response.accepted().entity(dIn.id).build();

        // if (Optional.ofNullable(Device.<Device>find("deviceName", dIn.deviceName).firstResult()).isPresent()) {
        //     return Response.status(Status.CONFLICT).build();
        // } else {
        //     dIn.persist();
        //     return Response.accepted().entity(dIn.id).build();            
        // }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Device> getDevices() {
        return Device.listAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Device getDevice(@PathParam("id") Long id){
        return Device.findById(id);
    }

    @PUT
    @Transactional
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editDevice(@PathParam("id") Long id, Device d){
        Device oldDevice = Device.findById(id);
        if (oldDevice != null) {
            //oldDevice.deviceType = d.deviceType;
            //oldDevice.buildingRoom = d.buildingRoom;
            //oldDevice.desk = d.desk;
            return Response.accepted().entity(d.id).build();
        }
        else {
            throw new NotFoundException();
        }
    }

    @PATCH
    @Transactional
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDevice(@PathParam("id") Long id, Device d){
        Device currentDevice = Device.findById(id);
        if (currentDevice != null) {
            //currentDevice.occupied = d.occupied;
            //currentDevice.energy = d.energy;
            //currentDevice.voltage = d.voltage;
            //currentDevice.current = d.current;
            currentDevice.batteryPer = d.batteryPer;
            return Response.accepted().entity(d.id).build();
        }
        else {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Long deleteDevice(@PathParam("id") Long id){
        Device p = Device.findById(id);
        if (p != null) {
            p.delete();
            return 1L;
        } else {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Long deleteDevice(){
        Long numDel = Device.deleteAll();
        return numDel;
    }

}