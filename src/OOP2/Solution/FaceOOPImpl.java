package OOP2.Solution;

import OOP2.Provided.*;

import java.util.*;

public class FaceOOPImpl implements FaceOOP {
	TreeMap<Integer,Person> personMap;

	/**
	 * Constructor - receives no parameters and initializes the system.
	 */
	public FaceOOPImpl()
	{
		personMap = new TreeMap<Integer, Person>();

	}

	@Override
	public Person joinFaceOOP(Integer id, String name) throws PersonAlreadyInSystemException {
		Person p = new PersonImpl(id,name);
		if(personMap.containsValue(p))
		{
			throw new PersonAlreadyInSystemException();
		}
		personMap.put(id,p);

		return p;
	}

	@Override
	public int size() {
		return personMap.size();
	}

	@Override
	public Person getUser(Integer id) throws PersonNotInSystemException {
	if(!personMap.containsKey(id))
	{
		throw new PersonNotInSystemException();
	}
		Person res = personMap.get(id);

		return res;
	}

    @Override
    public void addFriendship(Person p1, Person p2) throws PersonNotInSystemException, SamePersonException, ConnectionAlreadyExistException {

        if(!personMap.containsValue(p1) ||  !personMap.containsValue(p2))
        {
            throw new PersonNotInSystemException();
        }
        if(p1.equals(p2))
        {
            throw new SamePersonException();
        }

        p1.addFriend(p2);
        p2.addFriend(p1);

    }

    @Override
    public StatusIterator getFeedByRecent(Person p) throws PersonNotInSystemException {
       if(!personMap.containsValue(p))
       {
           throw new PersonNotInSystemException();
       }
        Collection<Person> friends = p.getFriends();

        List<Person> friendsList = new ArrayList<Person>(friends);

        Collections.sort(friendsList);

        LinkedList<Status> feed = new LinkedList<Status>();

        for (Person curr : friendsList)
        {
            Iterable<Status> temp = curr.getStatusesRecent();
            for (Status s : temp)
            {
                feed.addLast(s);
            }

        }

        StatusIterator res;
        res = new StatusItaratorImpl(feed.iterator());
        return res;
    }

    @Override
    public StatusIterator getFeedByPopular(Person p) throws PersonNotInSystemException {
        if(!personMap.containsValue(p))
        {
            throw new PersonNotInSystemException();
        }
        Collection<Person> friends = p.getFriends();

        List<Person> friendsList = new ArrayList<Person>(friends);

        Collections.sort(friendsList);

        LinkedList<Status> feed = new LinkedList<Status>();

        for (Person curr : friendsList)
        {
            Iterable<Status> temp = curr.getStatusesPopular();
            for (Status s : temp)
            {
                feed.addLast(s);
            }

        }

        StatusIterator res = new StatusItaratorImpl(feed.iterator());

        return res;
    }

    @Override
    public Integer rank(Person source, Person target) throws PersonNotInSystemException, ConnectionDoesNotExistException {
        if(!personMap.containsValue(source)||!personMap.containsValue(target))
        {
            throw new PersonNotInSystemException();
        }
        if(source.equals(target))
        {
            return 0;
        }

        LinkedList<Person> q = new LinkedList<Person>();
        TreeMap<Person,Integer> graph = new TreeMap<Person, Integer>();
        for(Person p : personMap.values())
        {
            graph.put(p,Integer.MAX_VALUE);
        }
        graph.put(source,0);
        q.addLast(source);
        while(!q.isEmpty())
        {
            Person curr = q.removeFirst();
            for(Person p: curr.getFriends())
            {
                if(graph.get(p).equals(Integer.MAX_VALUE))
                {
                    graph.put(p,graph.get(curr)+1);
                    q.addLast(p);
                }
            }
        }
        if(graph.get(target).equals(Integer.MAX_VALUE))
        {
            throw new ConnectionDoesNotExistException();
        }

        return graph.get(target);
    }

    @Override
    public Iterator<Person> iterator() {
        return personMap.values().iterator();
    }
}
