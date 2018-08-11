package com.haw_hamburg.de.objectMapping.hibernateNeo4j.app;

import java.io.File;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

//import static org.neo4j.driver.v1.Values.parameters;

import com.haw_hamburg.de.objectMapping.utils.Result;

public class FrameworkTest {

	// Result Object
	private Result resultWrite;
	private Result resultRead;

	// Testkonfig
	public Integer inserts = 100000;
	public Integer runs = 5;
	StoreActivity storeActivity;
	ReadActivity readActivity;

	// Entity Manager Factory
	private EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("userPostsHibernateNeo4j");

	public FrameworkTest() {

	}

	public FrameworkTest(Integer inserts, Integer runs) {
		this.inserts = inserts;
		this.runs = runs;
		storeActivity = new StoreActivity(inserts, entityManagerFactory);
		readActivity = new ReadActivity(entityManagerFactory);
	}

	public Result performWriteTest() throws Exception {

		{
			// Intialize Variables
			this.resultWrite = new Result();

			// Execute Runs
			for (Integer i = 0; i < this.runs; i++) {

				// Record Start Time
				long startTime = System.nanoTime();

				// Insert Documents
				storeActivity.persistEntitiesDataNucleus();

				// Record End Time and calculate Run Time
				long estimatedTime = System.nanoTime() - startTime;
				double seconds = (double) estimatedTime / 1000000000.0;

				resultWrite.addMeasureResult("Write Run" + (i), seconds, this.inserts, true);
				System.out.println("Write Run" + (i) + " finished");

			}

			this.storeActivity.closeConnection();

			// Print Result
			return this.resultWrite;
		}
	}

	public Result performReadTest() throws Exception {
		// Intialize Variables
		this.resultRead = new Result();

		// int runsRead = this.runs;
		// for (Integer j = 0; j < runsRead; j++) {

		// Record Start Time
		long startTime = System.nanoTime();

		// Read Documents
		// readActivity.readEntities();
		readActivity.readUsers();

		// Record End Time and calculate Run Time
		long estimatedTime = System.nanoTime() - startTime;
		double seconds = (double) estimatedTime / 1000000000.0;

		resultRead.addMeasureResult("Read All Entries", seconds, this.inserts * this.runs, false);
		//
		// }
		this.readActivity.closeConnection();
		entityManagerFactory.close();
		
	//	System.out.println("USERS: " + readActivity.getUsers().size());

		// Print Result
		return this.resultRead;
	}

	public void printDbContents() {
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
