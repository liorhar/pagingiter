package com.liorh.paging;

import com.google.common.collect.AbstractIterator;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: liorharel
 * Date: 4/25/12
 * Time: 3:12 PM
 * Iterator over pages of fixed size. PagesProvider will be asked to
 * fetch pages until a page with less elements than page size is returned
 */
public class FixedPagingIterable<T> implements Iterable<T> {
    private final PagesProvider pageProvider;
    private final int pageSize;

    public FixedPagingIterable(PagesProvider<T> pagesProvider, int pageSize) {
        this.pageProvider = pagesProvider;
        this.pageSize = pageSize;
    }

    @Override
    public Iterator<T> iterator() {
        return new AbstractIterator<T>() {
            int page = 1;
            int currentPageCounter=0;
            Iterator<T> iter = pageProvider.fetchPage(page);

            @Override
            protected T computeNext() {
                if (!iter.hasNext())  {
                    if (currentPageCounter < pageSize) {
                        return endOfData();
                    }
                    currentPageCounter = 0;
                    iter = pageProvider.fetchPage(++page);
                    if (!iter.hasNext())
                        return endOfData();
                }
                currentPageCounter++;
                return iter.next();
            }
        };
    }
}
