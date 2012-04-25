package com.liorh.paging;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: liorharel
 * Date: 4/24/12
 * Time: 10:46 PM
 * Abstraction over paging services
 * */
public interface PagesProvider<T> {
    /**
     * should fetch next page
     * @param page - page ordinal, starts at 1
     * @return - iterator over the page elements
     */
    Iterator<T> fetchPage(int page);
}
