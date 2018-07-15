package com.haw_hamburg.de.objectMapping.hibernateNeo4j.app;

import java.io.File;
import java.net.UnknownHostException;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
//import org.neo4j.tooling.GlobalGraphOperations;

//import static org.neo4j.driver.v1.Values.parameters;

import com.haw_hamburg.de.objectMapping.utils.Result;

public class FrameworkTest {

	// Result Object
	private Result result = null;

	// private Driver driver = null;
	//
	// // Node and Port Config
	// private String node = "localhost";
	// private Integer port = 27017;
	//
	// // Host
	// private MongoClient mongoClient;
	//
	// // Collection
	// private String collection_user_name = "User";
	// private DBCollection collection_user = null;
	//
	// // Collection
	// private String collection_post_name = "Post";
	// private DBCollection collection_post = null;
	//
	// // DB
	// private String db_name = "UserPosts";
	// private DB db = null;
	//
	// Testkonfig
	public Integer inserts = 100000;
	public Integer runs = 5;
//	GraphDatabaseService graphDb;
	MongoHibernate mh;

	public FrameworkTest() {

	}

	public FrameworkTest(Integer inserts, Integer runs) {
		this.inserts = inserts;
		this.runs = runs;
		mh = new MongoHibernate(inserts);
		// driver = GraphDatabase.driver("bolt://localhost:7687");
		// graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(new
		// File("/home/diana/Dokumente/userPostsHibernateNeo4j"));
	}

	//
	// public Integer getInserts() {
	// return inserts;
	// }
	//
	// public void setInserts(Integer inserts) {
	// this.inserts = inserts;
	// }
	//
	// public Integer getRuns() {
	// return runs;
	// }
	//
	// public void setRuns(Integer runs) {
	// this.runs = runs;
	// }
	//
	// private ServerAddress initialise() throws UnknownHostException {
	// ServerAddress addrs = new ServerAddress(this.node, this.port);
	// return addrs;
	// }
	//
	public Result performWriteTest() throws Exception {

		{
			// Intialize Variables
			this.result = new Result();

			// Create Test Environment
			createTestEnvironment();
			// Execute Runs
			for (Integer i = 0; i < this.runs; i++) {

				// Record Start Time
				long startTime = System.nanoTime();

				// Insert Documents
				// mh.persistEntities();
				mh.persistEntitiesDataNucleus();

				// Print Count
				// printCount();

				// Record End Time and calculate Run Time
				long estimatedTime = System.nanoTime() - startTime;
				double seconds = (double) estimatedTime / 1000000000.0;

				result.addMeasureResult("Run" + (i), seconds, this.inserts);
				System.out.println("Run" + (i) + " finished");
				mh.closeConnection();
				printDbContents();

			}

			 // Delete Test Environment
//			 deleteTestEnvironment();
			

			// Print Result
			return this.result;
		}
	}

	private void createTestEnvironment() throws Exception {

		// try ( Session session = driver.session() )
		// {
		// String greeting = session.writeTransaction( new TransactionWork<String>()
		// {
		// @Override
		// public String execute( Transaction tx )
		// {
		// StatementResult result = tx.run( "CREATE (a:Greeting) " +
		// "SET a.message = $message " +
		// "RETURN a.message + ', from node ' + id(a)",
		// parameters( "message", "..." ) );
		// return result.single().get( 0 ).asString();
		// }
		// } );
		// System.out.println( greeting );
		// }
		//
		// // Connect to Database (Creates the DB if it does not exist)
		// this.db = this.mongoClient.getDB(this.db_name);
		//
		// // Create and Connect to Collection
		// this.db.createCollection(this.collection_user_name, null);
		// this.db.createCollection(this.collection_post_name, null);
		// this.collection_user = this.db.getCollection(this.collection_user_name);
		// this.collection_post = this.db.getCollection(this.collection_post_name);

	}

	private void deleteTestEnvironment() {

		// // Delete Connection
		// this.db.getCollection(this.collection_user_name).drop();
		// this.db.getCollection(this.collection_post_name).drop();
		// driver.close();

	}
	//
	// // private void insertDocuments() {
	// // for (int i = 0; i < this.inserts; i++) {
	// // try {
	// // this.collection.insert(new BasicDBObject(String.valueOf(i), "test"));
	// // } catch (Exception e) {
	// // System.out.println("Error on inserting element: " + i);
	// // e.printStackTrace();
	// // }
	// // }
	// // }

	// private void printCount() {
	// System.out.println("Count users " + this.collection_user.find().count());
	// System.out.println("Count posts " + this.collection_post.find().count());
	// }

	private static void printDbContents() {
		final GraphDatabaseService graphDb = new GraphDatabaseFactory()
				.newEmbeddedDatabase(new File("/home/diana/Dokumente/userPostsHibernateNeo4j"));
		final Transaction tx = graphDb.beginTx();
		try {
			for (final Node node : graphDb.getAllNodes()) {
				System.out.print("Node: " + node.getId() + " ");
				for (final String key : node.getPropertyKeys()) {
					System.out.print("Property: " + key + " - " + node.getProperty(key) + ", ");
				}
				System.out.print("\n");
			}
			tx.success();
		} finally {
			tx.terminate();
			graphDb.shutdown();
		}
	}

}
