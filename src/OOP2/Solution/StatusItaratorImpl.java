package OOP2.Solution;

import OOP2.Provided.Status;
import OOP2.Provided.StatusIterator;

import java.util.Iterator;

public class StatusItaratorImpl implements StatusIterator
{
    private Iterator<Status> original;

    public StatusItaratorImpl(Iterator<Status> original) {
        this.original = original;
    }

    @Override
    public boolean hasNext() {
        return original.hasNext();
    }

    @Override
    public Status next() {
        return original.next();
    }

    @Override
    public void remove() {

    }
}
