package com.example.course.hystrix;

import java.util.concurrent.Callable;

import com.example.course.hystrix.model.UserContext;

public class DelegatingUserContextCallable <V> implements Callable<V> {
  private final Callable<V> delegate;
  private UserContext userContext;

  public DelegatingUserContextCallable(Callable<V> delegate,
                                       UserContext userContext) {
    this.delegate = delegate;
    this.userContext = userContext;
  }

  public V call() throws Exception {
    UserContextHolder.setContext( userContext );

    try {
      return delegate.call();
    }
    finally {
      this.userContext = null;
    }
  }

  public static <V> Callable<V> create(Callable<V> delegate,
                                       UserContext userContext) {
    return new DelegatingUserContextCallable<V>(delegate, userContext);
  }
}
