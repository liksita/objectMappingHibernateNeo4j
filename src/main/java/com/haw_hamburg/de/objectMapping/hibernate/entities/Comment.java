package com.haw_hamburg.de.objectMapping.hibernate.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Comment extends Activity{

	public Comment(Date date) {
		super(date);
	}
	
	

}
