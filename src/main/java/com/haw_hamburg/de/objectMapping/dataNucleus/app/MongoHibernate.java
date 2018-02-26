package com.haw_hamburg.de.objectMapping.dataNucleus.app;

import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import com.haw_hamburg.de.objectMapping.dataNucleus.entities.Comment;
import com.haw_hamburg.de.objectMapping.dataNucleus.entities.Post;
import com.haw_hamburg.de.objectMapping.dataNucleus.entities.User;

public class MongoHibernate {

	// Testkonfig
	public Integer inserts = 1000;

	private static int runCount = 0;

//	private static EntityManagerFactory entityManagerFactory;
//	EntityManager entityManager;

	PersistenceManagerFactory pmf;
	PersistenceManager pm;

	public MongoHibernate(Integer inserts) {
		this.inserts = inserts;
//		entityManagerFactory = Persistence.createEntityManagerFactory("userPosts");
//		entityManager = entityManagerFactory.createEntityManager();
//		pmf = JDOHelper.getPersistenceManagerFactory("userPosts");
		
		Properties properties = new Properties();
		properties.setProperty("javax.jdo.PersistenceManagerFactoryClass", "org.datanucleus.api.jdo.JDOPersistenceManagerFactory");
		properties.setProperty("javax.jdo.option.ConnectionURL","mongodb:/UserPosts");
		pmf = JDOHelper.getPersistenceManagerFactory(properties);
		
		
	}


	public void persistEntitiesDataNucleus() {
		pm = pmf.getPersistenceManager();
		runCount++;

		for (int j = 0; j < this.inserts; j++) {

			Transaction tx = pm.currentTransaction();

			try {
				tx.begin();

				// create a User
				User user = new User("user", "user" + runCount + j);

				// and two posts
				Post post1 = new Post("Title1 " + runCount + j, new Date());
				Post post2 = new Post("Title2 " + runCount + j, new Date());

				Comment comment1 = new Comment(new Date());
				Comment comment2 = new Comment(new Date());
				Comment comment3 = new Comment(new Date());
				Comment comment4 = new Comment(new Date());
				// let Bob post two posts
				post1.setAuthor(user);
				user.getUserPosts().add(post1);
				post1.setComments(Arrays.asList(comment1, comment2));
				post2.setAuthor(user);
				user.getUserPosts().add(post2);
				post2.setComments(Arrays.asList(comment3, comment4));
				user.setUserComments(Arrays.asList(comment1, comment2, comment3, comment4));

				pm.makePersistent(user);
//				pm.makeTransient(user);
				tx.commit();
			} finally {
				if (tx.isActive()) {
					tx.rollback();
				}
				
			}
		}
		pm.close();
	}

	public Integer getInserts() {
		return inserts;
	}

	public void setInserts(Integer inserts) {
		this.inserts = inserts;
	}

	public void closeConnection() {
//		entityManager.close();
//		entityManagerFactory.close();
		pm.close();
		pmf.close();
	}

}
