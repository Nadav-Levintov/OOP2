package OOP2.Solution;

import OOP2.Provided.Person;
import OOP2.Provided.Status;

import java.util.*;

public class PersonImpl implements Person {

	Integer id;
	String name;
	Set<Status> statuses;
	/**
	 * Constructor receiving person's id and name.
	 */
	public PersonImpl(Integer id, String name)
	{
		this.id=id;
		this.name=name;
		statuses = new HashSet<Status>();

	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Status postStatus(String content) {
		Status st = new StatusImpl(this,content,statuses.size());
		statuses.add(st);
		return st;
	}
}
