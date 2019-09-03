import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.endpoint.StreamingEndpoint;
import org.apache.flink.streaming.connectors.twitter.TwitterSource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TwitterMessageFilter implements TwitterSource.EndpointInitializer, Serializable {

    public static final List<String> tagList = new ArrayList<String>(Arrays.asList("#Messi", "#GOAT"));

    public StreamingEndpoint createEndpoint() {
        StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
        endpoint.trackTerms(tagList);
        return endpoint;
    }
}
