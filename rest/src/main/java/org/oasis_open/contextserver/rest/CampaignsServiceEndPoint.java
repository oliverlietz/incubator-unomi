package org.oasis_open.contextserver.rest;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.oasis_open.contextserver.api.Metadata;
import org.oasis_open.contextserver.api.PartialList;
import org.oasis_open.contextserver.api.Profile;
import org.oasis_open.contextserver.api.campaigns.Campaign;
import org.oasis_open.contextserver.api.segments.Segment;
import org.oasis_open.contextserver.api.services.GoalsService;
import org.oasis_open.contextserver.api.services.ProfileService;
import org.oasis_open.contextserver.api.services.SegmentService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

/**
 * Created by loom on 26.04.14.
 */
@WebService
@Produces(MediaType.APPLICATION_JSON)
@CrossOriginResourceSharing(
        allowAllOrigins = true,
        allowCredentials = true
)
public class CampaignsServiceEndPoint {

    private GoalsService goalsService;

    public CampaignsServiceEndPoint() {
        System.out.println("Initializing campaigns service endpoint...");
    }

    @WebMethod(exclude=true)
    public void setGoalsService(GoalsService goalsService) {
        this.goalsService = goalsService;
    }

    @GET
    @Path("/")
    public Set<Metadata> getCampaignMetadatas() {
        return goalsService.getCampaignMetadatas();
    }

    @POST
    @Path("/")
    public void setCampaignDefinition(Campaign segment) {
        goalsService.setCampaign(segment);
    }

    @GET
    @Path("/{scope}")
    public Set<Metadata> getCampaignMetadatas(@PathParam("scope") String scope) {
        return goalsService.getCampaignMetadatas(scope);
    }

    @GET
    @Path("/{scope}/{campaignID}")
    public Campaign getCampaignDefinition(@PathParam("scope") String scope, @PathParam("campaignID") String campaignID) {
        return goalsService.getCampaign(scope, campaignID);
    }

    @DELETE
    @Path("/{scope}/{campaignID}")
    public void removeCampaignDefinition(@PathParam("scope") String scope, @PathParam("campaignID") String campaignID) {
        goalsService.removeCampaign(scope, campaignID);
    }


}