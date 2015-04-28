package io.inkstand.example.jcr.inmemory;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import io.inkstand.InkstandRuntimeException;

/**
 * The service is session scoped and uses an in-memory repository. A JCR session is created upon initialization
 * of the service.
 * Created by <a href="mailto:gerald.muecke@gmail.com">Gerald M&uuml;cke</a> on 4/28/2015
 *
 * @author <a href="mailto:gerald.muecke@gmail.com">Gerald M&uuml;cke</a>
 */
@Path("/")
public class JcrService implements Serializable{

    /*
     * inject the repository provided by the inkstand JCR providers
     */
    @Inject
    private Repository repository;
    private Session adminSession;

    @PostConstruct
    protected void initRepository() {

        try {
            /*
             * The session is created on post-construct and not in the request as the repository will be
             * shutdown if the last user logs out.
             * The user is admin/admin to obtain a session with admin-privileges so we can modify the
             * repository content. In real-life scenario you'd use a login/logout per request and have
             * proper security in place.
             */
            this.adminSession = repository.login(new SimpleCredentials("admin","admin".toCharArray()));
        } catch (RepositoryException e) {
            throw new InkstandRuntimeException(e);
        }
    }

    @PreDestroy
    protected void shutdownRepository(){
        this.adminSession.logout();
    }

    /**
     * The method prints out the nodes underneath the root node as a list.
     * @return
     * @throws RepositoryException
     */
    @GET
    @Path("/nodes")
    public Response getNodes() throws RepositoryException {

        final StringBuilder buf = new StringBuilder(64);
        Session session = null;
        try {
            //log in to the repository as anonymous
            session = repository.login();
            //get the root node of the workspace
            final Node root = session.getRootNode();
            if(root.hasNodes()) {
                buf.append("<ul>");
                //iterate over all the childnodes and print their names and identifiers
                final NodeIterator nit = root.getNodes();
                for(;nit.hasNext();){
                    final Node node = nit.nextNode();
                    buf.append("<li>")
                       .append(node.getName())
                       .append('(')
                       .append(node.getIdentifier())
                        .append(") ").append(node.getPath())
                       .append("</li>");
                }
                buf.append("</ul>");
            } else {
                buf.append("no nodes");
            }
        } finally {
            if(session != null) {
                session.logout();
            }
        }

        return Response.ok(buf.toString()).build();
    }

    /**
     * Creates a new node with a name specified as 'name' parameter
     * @return
     *
     * @throws RepositoryException
     */
    @POST
    @Path("/nodes")
    public Response createNode(String name) throws RepositoryException {

        adminSession.refresh(false);
        final Node node = adminSession.getRootNode().addNode(name);
        adminSession.save();

        return Response.ok(node.getPath()).build();
    }
}
