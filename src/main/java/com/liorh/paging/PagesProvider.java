package com.liorh.paging;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: liorharel
 * Date: 4/24/12
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */
public interface PagesProvider<T> {

    Iterator<T> fetchPage(int page);
}
