import org.junit.Test;
import static org.junit.Assert.*;

public class TestCard {

    @Test
    public void getValue_returnsCorrectValue() {
        Card card = new Card(5);
        assertEquals(5, card.getValue());
    }

    @Test
    public void toString_returnsCorrectString() {
        Card card = new Card(7);
        assertEquals("7", card.toString());
    }
}