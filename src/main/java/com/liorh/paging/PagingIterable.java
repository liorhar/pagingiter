package com.liorh.paging;

import com.google.common.collect.AbstractIterator;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: liorharel
 * Date: 4/24/12
 * Time: 10:43 PM
 * Iterator over paging provider, PagesProvider will be asked to fetch pages
 * until an iterator over empty collection is returned
 */
public class PagingIterable<T> implements Iterable<T> {
    private final PagesProvider<T> pageProvider;

    public PagingIterable(PagesProvider<T> pageProvider) {
        this.pageProvider = pageProvider;
    }

    @Override
    public Iterator<T> iterator() {
        return new AbstractIterator<T>() {
            int page = 0;

            Iterator<T> iter = null;

            @Override
            protected T computeNext() {
                if (iter == null || !iter.hasNext()) {
                    iter = pageProvider.fetchPage(++page);
                    if (!iter.hasNext())
                        return endOfData();
                 }
                return iter.next();
            }
        };
    }

}
