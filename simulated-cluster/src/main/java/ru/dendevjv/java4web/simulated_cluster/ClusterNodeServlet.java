package ru.dendevjv.java4web.simulated_cluster;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.Session;

@ClientEndpoint
public class ClusterNodeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Session session;
    private String nodeId;
    
    @Override
    public void init() throws ServletException {
        nodeId = this.getInitParameter("nodeId");
        String path = this.getServletContext().getContextPath() + "/clusterNodeSocket/" + nodeId;
        
        try {
            URI uri = new URI("ws", "localhost:8080", path, null, null);
            session = ContainerProvider.getWebSocketContainer().connectToServer(this, uri);
        } catch (URISyntaxException | DeploymentException | IOException e) {
            throw new ServletException("Cannot connect to " + path + ".", e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ClusterMessage message = new ClusterMessage(nodeId, "request:{ip:\"" + req.getRemoteAddr() 
                + "\",queryString:\"" + req.getQueryString() + "\"}");
        try (OutputStream output = session.getBasicRemote().getSendStream();
                ObjectOutputStream stream = new ObjectOutputStream(output)) {
            stream.writeObject(message);
        }
        resp.getWriter().append("OK");
    }
    
    @OnMessage
    public void onMessage(InputStream input) {
        try (ObjectInputStream stream = new ObjectInputStream(input)) {
            ClusterMessage message = (ClusterMessage) stream.readObject();
            System.out.println("INFO (Node " + nodeId
                    + "): Message received from cluster; node = "
                    + message.getNodeId() + ", message = "
                    + message.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    @OnClose
    public void onClose(CloseReason reason) {
        CloseReason.CloseCode code = reason.getCloseCode();
        if (code != CloseReason.CloseCodes.NORMAL_CLOSURE) {
            System.err.println("ERROR: WebSocket connection closed unexpectedly;"
                    + " code = " + code + ", reason = " + reason.getReasonPhrase());
        }
    }
    
    @Override
    public void destroy() {
        try {
            this.session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
