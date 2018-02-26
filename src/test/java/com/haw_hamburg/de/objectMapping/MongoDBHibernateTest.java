//package com.haw_hamburg.de.objectMapping;
//
//import static org.junit.Assert.*;
//
//import java.util.Arrays;
//import java.util.Date;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import com.haw_hamburg.de.objectMapping.appHibernate.Comment;
//import com.haw_hamburg.de.objectMapping.appHibernate.Post;
//import com.haw_hamburg.de.objectMapping.appHibernate.User;
//
//public class MongoDBHibernateTest {
//
//	private static EntityManagerFactory entityManagerFactory;
//
//	@BeforeClass
//	public static void setUpEntityManagerFactory() {
//		entityManagerFactory = Persistence.createEntityManagerFactory("userPosts");
//	}
//
//	@AfterClass
//	public static void closeEntityManagerFactory() {
//		entityManagerFactory.close();
//	}
//
//	@Test
//	public void canPersistAndLoadEntities() {
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//		entityManager.getTransaction().begin();
//
//		// create a Person
//		User bob = new User("Bob", "McRobb");
//
//		Comment comment1 = new Comment(new Date());
//		Comment comment2 = new Comment(new Date());
//		Comment comment3 = new Comment(new Date());
//		Comment comment4 = new Comment(new Date());
//
//		// and two posts
//		Post post1 = new Post("Title1", new Date());
//		Post post2 = new Post("Title2", new Date());
//
//		// let Bob post two posts
//		post1.setAuthor(bob);
//		bob.getUserPosts().add(post1);
//
//		post1.setComments(Arrays.asList(comment1, comment2));
//
//		post2.setAuthor(bob);
//		bob.getUserPosts().add(post2);
//
//		post2.setComments(Arrays.asList(comment3, comment4));
//
//		bob.setUserComments(Arrays.asList(comment1, comment2, comment3, comment4));
//
//		// persist author (will be cascaded to posts)
//		entityManager.persist(bob);
//
//		entityManager.getTransaction().commit();
//
//		// get a new EM to make sure data is actually retrieved from the store and not
//		// Hibernate’s internal cache
//		entityManager.close();
//		entityManager = entityManagerFactory.createEntityManager();
//
//		// load it back
//		entityManager.getTransaction().begin();
//
//		User loadedUser = entityManager.find(User.class, bob.getId());
//		assertNotNull(loadedUser);
//		assertEquals(loadedUser.getFirstName(), "Bob");
//		assertEquals(loadedUser.getUserPosts().size(), 2);
//
//		entityManager.getTransaction().commit();
//
//		entityManager.close();
//	}
//
//}
