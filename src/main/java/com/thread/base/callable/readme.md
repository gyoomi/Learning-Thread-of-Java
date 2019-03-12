## Callable、Future与FutureTask
1. Callable

   和Runnable差不多，两者都是为那些其实例可能被另一个线程执行的类而设计的，最主要的差别在于Runnable不会返回线程运算结果，
   Callable可以（假如线程需要返回运行结果）
2. Future

   Future是一个接口表示异步计算的结果，它提供了检查计算是否完成的方法，以等待计算的完成，并获取计算的结果。
   Future提供了get()、cancel()、isCancel()、isDone()四种方法，表示Future有三种功能

   1. 判断任务是否完成
   2. 中断任务
   3. 获取任务执行结果
3. FutureTask

   FutureTask是Future的实现类，它提供了对Future的基本实现。可使用FutureTask包装Callable或Runnable对象，
   因为FutureTask实现了Runnable，所以也可以将FutureTask提交给Executor。

4. 使用Callable、Future和FutureTask的好处

   上面演示了两个例子，其实反映的是现实中一种情况，把上面的例子稍微扩展一下就是：

   有一个method()方法，方法中执行方法A返回一个数据要10秒钟，A方法后面的代码一共要执行20秒钟，但是这20秒的代码中有10秒的方法并不依赖方法A的执行结果，有10秒钟的代码依赖方法A的执行结果。此时若采用同步的方式，那么势必要先等待10秒钟，等待方法A执行完毕，返回数据，再执行后面20秒的代码。

   不得不说这是一种低效率的做法。有了Callable、Future和FutureTask，那么：

   1. 先把A方法的内容放到Callable实现类的call()方法中
   2. method()方法中，Callable实现类传入Executor的submit方法中
   3. 执行后面方法中10秒不依赖方法A运行结果的代码
   4. 获取方法A的运行结果，执行后面方法中10秒依赖方法A运行结果的代码

   这样代码执行效率一下子就提高了，程序不必卡在A方法处。

   当然，也可以不用Callable，采用实现Runnable的方式，run()方法执行完了想个办法给method()方法中的某个变量V赋个值就好了。
   但是我上一篇文章开头就说了，之所以要用多线程组件，就是因为JDK帮我们很好地实现好了代码细节，让开发者更多可以关注业务层的逻辑。
   如果使用Runnable的方式，那么我们自己就要考虑很多细节，比如Runnable实现类的run()方法执行完毕给V赋值是否线程安全、
   10秒后如果A方法没有执行完导致V还没有值怎么办，何况JDK还给用户提供了取消任务、判断任务是否存在等方法。
   既然JDK已经帮我们考虑并实现这些细节了，在没有有说服力的理由的情况下，我们为什么还要自己写run()方法的实现呢

































