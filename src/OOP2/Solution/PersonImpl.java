package OOP2.Solution;

import OOP2.Provided.ConnectionAlreadyExistException;
import OOP2.Provided.Person;
import OOP2.Provided.SamePersonException;
import OOP2.Provided.Status;

import java.util.*;

public class PersonImpl implements Person {

	Integer id;
	String name;
	LinkedList<Status> statuses;
	Set<Person> friends;
	/**
	 * Constructor receiving person's id and name.
	 */
	public PersonImpl(Integer id, String name)
	{
		this.id=id;
		this.name=name;
		statuses = new LinkedList<Status>();
		friends = new HashSet<Person>();

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
		statuses.addFirst(st);
		return st;
	}

	@Override
	public void addFriend(Person p) throws SamePersonException, ConnectionAlreadyExistException
    {
	    if (this.equals(p))
	    {
		    throw new SamePersonException();
	    }
        if(friends.contains(p))
	    {
		    	throw new ConnectionAlreadyExistException();
        }
        friends.add(p);
	}

    @Override
    public Collection<Person> getFriends() {
        return friends;
    }

	@Override
	public Iterable<Status> getStatusesRecent() {
		Collections.sort(statuses, new Comparator<Status>() {
			@Override
			public int compare(Status s1, Status s2) {
					return (s2.getId()- s1.getId());
			}
		});
		return statuses;
	}

	@Override
	public Iterable<Status> getStatusesPopular() {
		Collections.sort(statuses, new Comparator<Status>() {
			@Override
			public int compare(Status s1, Status s2) {
				if(s1.getLikesCount() != s2.getLikesCount()) {
					return (s1.getLikesCount() - s2.getLikesCount());
				}
				else
				{
					return (s2.getId() - s1.getId());
				}
			}
		});
		return statuses;
	}

    @Override
    public int compareTo(Person o) {
        return (id - o.getId());
    }
    @Override
    public boolean equals(Object o) {
        if(o== null)
            return false;
        if (o.getClass() != this.getClass())
            return false;
        return (id == ((Person)o).getId());
    }

}
