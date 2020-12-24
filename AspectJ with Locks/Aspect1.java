import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class Aspect1 {
    private ReadWriteLock lock = new ReadWriteLock();

    @Before("execution(void BinaryTree.insert(int))")
    public void BeForeInsertAdvice(JoinPoint joinPoint) throws InterruptedException {
        System.out.println("Entering Insert..");
        lock.enterWrite();
    }

    @After("execution (void BinaryTree.insert(int))")
    public void AfterInsertAdvice(JoinPoint joinPoint) {
        System.out.println("Exiting Insert..");
        lock.exitWrite();
    }

    @Before("execution (void BinaryTree.remove(int))")
    public void BeForeRemoveAdvice(JoinPoint joinPoint) throws InterruptedException {
        System.out.println("Entering Remove..");
        lock.enterWrite();
    }

    @After("execution (void BinaryTree.remove(int))")
    public void AfterRemoveAdvice(JoinPoint joinPoint) {
        System.out.println("Exiting Remove..");
        lock.exitWrite();
    }

    @Before("execution (* BinaryTree.search(..))")
    public void BeForeSearchAdvice(JoinPoint joinPoint) throws InterruptedException {
        System.out.println("Entering Search..");
        lock.enterRead();
    }

    @After("execution (* BinaryTree.search(..))")
    public void AfterSearchAdvice(JoinPoint joinPoint) {
        System.out.println("Exiting Search..");
        lock.exitRead();
    }
}
