package com.liorh.paging;

import com.google.common.collect.Lists;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: liorharel
 * Date: 4/24/12
 * Time: 10:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class PagingIterableTest {

    private Mockery context;
    private PagesProvider pagesProvider;

    @Before
    public void setUp(){
       context = new Mockery();
       pagesProvider = context.mock(PagesProvider.class);
    }

    @After
    public void tearDown() {
       context.assertIsSatisfied();
    }
    @Test
    public void testNoPagesIterator(){
        context.checking(new Expectations(){{
            oneOf(pagesProvider).fetchPage(1); will(returnIterator());
        }});
        Iterable<String> iterable = new PagingIterable<String>(pagesProvider);
        assertEquals(new ArrayList<String>(), Lists.newArrayList(iterable));
    }
    @Test
    public void testOnePageIterator(){

        context.checking(new Expectations(){{
            oneOf(pagesProvider).fetchPage(1); will(returnIterator("one", "two"));
            oneOf(pagesProvider).fetchPage(2); will(returnIterator());

        }});

        Iterable<String> iterable = new PagingIterable<String>(pagesProvider);
        List<String> results = Lists.newArrayList(iterable);
        assertEquals(Arrays.asList(new String[]{"one", "two"}), results);
    }
    @Test
        public void testTwoPagesIterator(){

            context.checking(new Expectations(){{
                oneOf(pagesProvider).fetchPage(1); will(returnIterator("one", "two"));
                oneOf(pagesProvider).fetchPage(2); will(returnIterator("three"));
                oneOf(pagesProvider).fetchPage(3); will(returnIterator());

            }});

            Iterable<String> iterable = new PagingIterable<String>(pagesProvider);
            List<String> results = Lists.newArrayList(iterable);
            assertEquals(Arrays.asList(new String[]{"one", "two", "three"}), results);
        }


}
