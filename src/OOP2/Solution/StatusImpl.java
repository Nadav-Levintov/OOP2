package OOP2.Solution;

import OOP2.Provided.Person;
import OOP2.Provided.Status;
import OOP2.Provided.StatusIterator;

import java.util.*;

public class StatusImpl implements Status {

	Person publisher;
	String content;
	Integer id;
	Set<Person> likes;


	/*
	 * A constructor that receives the status publisher, the text of the status
	 *  and the id of the status.
	 */
	public StatusImpl(Person publisher, String content, Integer id)
	{
		this.id = id;
		this.content = content;
		this.publisher = publisher;
		likes = new TreeSet<Person>();

	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public Person getPublisher() {
		return publisher;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public void like(Person p) {
		if(!likes.contains(p))
		{
			likes.add(p);
		}
	}

	@Override
	public void unlike(Person p) {
		if(likes.contains(p))
		{
			likes.remove(p);
		}
	}

	@Override
	public Integer getLikesCount() {
		return likes.size();
	}

	public boolean equals(Object o) {
		if(o== null)
			return false;
		if (o.getClass() != this.getClass())
			return false;
		return ((id.equals(((Status)o).getId()))&& (publisher.equals(((Status)o).getPublisher())));
	}
}
