**Streams tweets containg specific terms to the kafka topic**
Tweets having hash tag #Messi is read from twitter api

Steps involved:

Generate Twitter API keys
Install kafka, zookeeper and create kafka topic
Run java program with the following appropriate command line arguments

Generate Twitter API keys:
1. Go to  https://apps.twitter.com/  and create a new test application
2. In order to access the Twitter, that is to get recent tweets and twitter followers count, you need the four keys such as Consumer Key, Consumer Secret, Acess token, Access Token Secret.
3. After creating your Twitter Application, you have to give the access to your Twitter Account to use this Application. To do this, click the Create my Access Token.
4. Note down CONSUMER_KEY, CONSUMER_SECRET, TOKEN and TOKEN_SECRET. 

Install Kafka, zookeeper and create kafka topic:
1. Download kafka from https://kafka.apache.org/downloads
2. Download zookeeper from https://zookeeper.apache.org/releases.html
3. Unzip kafka and zookeeper.
4. Edit server.properties of kafka for relevant log location and other settings 
5. Edit zookeeper.properties to set data dir location
6. Start zookeeper and kafka by running zookeeper-server-start and kafka-server-start scripts respectively.
7. Create kafka topic using the command 
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic <topic name>

Run Java program with following command line arguments:
--twitter-source.consumerKey <consumer key> --twitter-source.consumerSecret <consumer secret> --twitter-source.token <source token> --twitter-source.tokenSecret <source token secret>
