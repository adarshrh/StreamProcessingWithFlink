import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.twitter.TwitterSource;

public class TwitterMessageReader {


    public static void main(String[] args) throws Exception {

        final ParameterTool params = ParameterTool.fromArgs(args);
        System.out.println("Usage: TwitterExample [--output <path>] " +
                "[--twitter-source.consumerKey <key> --twitter-source.consumerSecret <secret> --twitter-source.token <token> --twitter-source.tokenSecret <tokenSecret>]");

        // set up the execution environment
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.getConfig().setGlobalJobParameters(params);
        env.setParallelism(1);

        DataStream<String> streamSource;
        if (params.has(TwitterSource.CONSUMER_KEY) &&
                params.has(TwitterSource.CONSUMER_SECRET) &&
                params.has(TwitterSource.TOKEN) &&
                params.has(TwitterSource.TOKEN_SECRET)
        ) {
            TwitterSource twitterSource = new TwitterSource(params.getProperties());
            TwitterMessageFilter twitterMessageFilter = new TwitterMessageFilter();
            twitterSource.setCustomEndpointInitializer(twitterMessageFilter);
            streamSource = env.addSource(twitterSource);
            KafkaSink kafkaSink = new KafkaSink();
            kafkaSink.addSinkTopics("messi_tweets",streamSource);
         //   streamSource.print();
            //streamSource.writeAsText(params.get("output"));
        } else {
            System.out.println("Invalid twitter access props.");
        }
        env.execute("Reading Tweets");

    }
}
