package com.example.MyList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

public class MyStream {
    public static void main(String[] args) {
        Person person = new Person("风息", 18);
//        PredicateImp personPerdi = new PredicateImp();

        // test21
//        Predicate<Person> personPerdi = new Predicate<Person>() {
//
//            /**
//             * 定义一个test方法，传入任意对象，返回true or false，具体判断逻辑由子类实现
//             *
//             * @param person t
//             * @return boolean
//             */
//            @Override
//            public boolean test(Person person) {
//                return person.getAge() < 18;
//            }
//        };

        // test3
        Predicate<Person> personPerdi = testPer -> testPer.getAge() == 18;

//        java.util.function.Function<Person, Integer> function = testFunction -> testFunction.getAge();
//
//        Integer integer = function.apply(person);
//        System.out.println("我的年龄是：" + integer);

        //将数乘10
        java.util.function.Function<Integer, Integer> function1 = integer -> integer * 10;
        //将数减5
        java.util.function.Function<Integer, Integer> function2 = integer -> integer - 5;

        System.out.println(function1.compose(function2).apply(10));
        System.out.println(function1.andThen(function2).apply(10));

        System.out.println("测试结果" + personPerdi.test(person));

    }

}

/**
 * 新建perdicate接口
 */
@FunctionalInterface
interface Predicate<T> {

    /**
     * 定义一个test方法，传入任意对象，返回true or false，具体判断逻辑由子类实现
     *
     * @param t t
     * @return boolean
     */
    boolean test(T t);
}

class PredicateImp implements Predicate<Person> {

    /**
     * 定义一个test方法，传入任意对象，返回true or false，具体判断逻辑由子类实现
     *
     * @param t t
     * @return boolean
     */
    @Override
    public boolean test(Person t) {
        return t.getAge() > 18;
    }
}

@Data
@AllArgsConstructor
class Person {
    private String name;
    private Integer age;
}


/**
 * 定义一个Function接口
 * 从接口看Function<E, R>中，E(Enter)表示入参类型，R(Return)表示返回值类型
 *
 * @param <E> 入参类型
 * @param <R> 返回值类型
 */
@FunctionalInterface
interface Function<E, R> {
    /**
     * 定义一个apply()方法，接收一个E返回一个R。也就是把E映射成R
     *
     * @param e
     * @return
     */
    R apply(E e);

    /**
     * 类似于python
     * def apply_func(func, arg):
     *     return func(arg)
     */
}

/**
 * Function接口的实现类，规定传入Person类型返回Integer类型
 */
class FunctionImpl implements Function<Person, Integer> {

    /**
     * 传入person对象，返回age
     *
     * @param person
     * @return
     */
    @Override
    public Integer apply(Person person) {
        return person.getAge();
    }
}