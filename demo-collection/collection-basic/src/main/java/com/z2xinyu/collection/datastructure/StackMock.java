package com.z2xinyu.collection.datastructure;

import java.util.LinkedList;

/**
 * LIFO是"Last-In, First-Out"的缩写，意味着后进先出。在计算机科学中，LIFO通常用于描述栈（Stack
 * ）这种数据结构的特点。栈是一种线性数据结构，只能在一端进行插入和删除操作，这一端被称为栈顶。当元素被插入到栈中时，它成为新的栈顶，
 * 而最后插入的元素总是第一个被删除。换句话说，最后进入栈的元素最先出栈。
 * <p>
 * 这种LIFO特性在许多情况下都很有用。例如，在函数调用过程中，每次调用函数时，当前的函数状态被压入栈中，当函数返回时，栈顶的函数状态被弹出，
 * 从而恢复到上一个函数的状态。此外，栈还可以用于表达式求值、内存分配等方面。
 * <p>
 * 在Java中，可以使用Stack类或者更常见的LinkedList类来实现栈的功能。LinkedList类实现了Deque接口，Deque接口定义了栈和队列的功能。
 * 通过使用LinkedList类的push()方法来压入元素，使用pop()方法来弹出栈顶元素，就可以实现LIFO的特性。
 *
 * @author zhangxinyu
 * @date 2023/7/9
 **/
public class StackMock {

    private LinkedList linkedList;

    public StackMock() {
        linkedList = new LinkedList();
    }

    public void add(Object obj) {
        linkedList.addFirst(obj);
    }

    public Object get() {
        return linkedList.removeFirst();
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }
}

/*
在Java中，Vector类可以用作栈的实现，但它并不是专门用于栈的类。Vector是一个动态数组，它可以根据需要调整大小并支持随机访问元素。它实现了List接口，因此可以用作普通的列表。

然而，Vector类也可以用作栈的实现，因为它提供了一些方法来模拟栈的行为，比如push()、pop()和peek()。通过使用这些方法，可以实现后进先出（LIFO）的栈行为。

虽然Vector可以用作栈的实现，但在Java中更常用的是使用Stack类或者更推荐使用Deque接口的LinkedList类来实现栈。Stack类继承自Vector类，并添加了一些额外的方法来模拟栈的行为。而LinkedList类实现了Deque接口，提供了更灵活的栈和队列操作的功能。

总结：Vector类可以用作栈的实现，但不是专门为栈设计的类。在Java中，更常用的是使用Stack类或者LinkedList类来实现栈的功能。


Vector是Java中的一个动态数组类，它实现了List接口，并且支持自动调整大小。与ArrayList类似，Vector也可以存储多个元素，并且可以通过索引访问和修改元素。但是，与ArrayList不同的是，Vector是线程安全的，即多个线程可以同时对Vector进行操作而不会导致数据不一致的问题。

由于Vector的线程安全特性，它常被用于多线程环境下的数据共享。例如，在一个多线程的应用程序中，多个线程需要访问和修改同一个列表时，可以使用Vector来保证线程安全。此外，Vector还提供了一些同步方法，如addElement()、removeElement()等，可以在多线程环境下进行安全的操作。

然而，需要注意的是，由于Vector的线程安全特性，它的性能相对较低。在单线程环境下，如果不需要考虑线程安全问题，通常建议使用ArrayList来代替Vector。
 */
