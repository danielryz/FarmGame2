import com.farmgame.game.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class FarmPlacementTest {
    @Test
    public void testAddPlotFailsWhenOccupied() {
        Farm farm = new Farm(2, 2, 2, 2);
        Plot newPlot = new Plot();
        assertFalse("Should not add plot on occupied cell", farm.addPlot(0, 0, newPlot));
    }

    @Test
    public void testAddPlotSucceedsWhenCellFree() {
        Farm farm = new Farm(2, 2, 2, 2);
        farm.removePlot(1, 1);
        farm.removeAnimalPen(1, 1);
        Plot newPlot = new Plot();
        assertTrue("Should add plot on empty cell", farm.addPlot(1, 1, newPlot));
    }

    @Test
    public void testAddAnimalPenFailsWhenOccupied() {
        Farm farm = new Farm(2, 2, 2, 2);
        AnimalPen pen = new AnimalPen(0, 0);
        assertFalse("Should not add pen on occupied cell", farm.addAnimalPen(0, 0, pen));
    }

    @Test
    public void testAddAnimalPenSucceedsWhenCellFree() {
        Farm farm = new Farm(2, 2, 2, 2);
        farm.removePlot(1, 1);
        farm.removeAnimalPen(1, 1);
        AnimalPen pen = new AnimalPen(1, 1);
        assertTrue("Should add pen on empty cell", farm.addAnimalPen(1, 1, pen));
    }
}
