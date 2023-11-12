package christmas.utils;

public interface WeekPredicate<T> {

    boolean match(T t);

    String getDescription();
}
