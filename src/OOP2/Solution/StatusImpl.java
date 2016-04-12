package OOP2.Solution;

import OOP2.Provided.Person;
import OOP2.Provided.Status;

import java.util.HashSet;
import java.util.Set;

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
		likes = new HashSet<Person>();

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
}
