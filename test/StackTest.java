import models.StackVector;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class StackTest {
    @Test
    public void size(){
        StackVector stack = new StackVector();
        int expectedResult = 0;
        int result = stack.size();
        assertEquals(expectedResult, result);

    }

    @Test
    public void empty(){
        StackVector stack = new StackVector();
        boolean expectedResult = true;
        boolean result = stack.empty();
        assertEquals(expectedResult, result);
    }

    



}
