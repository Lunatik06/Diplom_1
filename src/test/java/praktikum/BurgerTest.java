package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    Burger burger = new Burger();

    //Добавил использование моков
    @Mock
    Bun bunMock;

    @Mock
    Ingredient fillingSausageMock;

    @Mock
    Ingredient sauceSourCreamMock;

    @Test
    public void setBunsTest() {
        burger.setBuns(bunMock);
        Assert.assertEquals("Ошибка при выборе булки", bunMock, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(fillingSausageMock);
        Assert.assertTrue("Ошибка при добавлении ингредиентов", burger.ingredients.contains(fillingSausageMock));
    }

    @Test
    public void removeIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(fillingSausageMock);
        burger.removeIngredient(0);
        Assert.assertFalse("Ошибка при удалении ингредиентов", burger.ingredients.contains(fillingSausageMock));
    }

    @Test
    public void moveIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(fillingSausageMock);
        burger.addIngredient(sauceSourCreamMock);
        int oldIndexIngredient = 0;
        int newIndexIngredient = 1;
        burger.moveIngredient(oldIndexIngredient, newIndexIngredient);
        Assert.assertEquals("Ошибка при перемещение ингредиентов", newIndexIngredient, burger.ingredients.indexOf(fillingSausageMock));
    }

    @Test
    public void getPriceTest() {
        Burger burger = new Burger();
        float expected = 700;

        //добавил использование моков, в качестве стабов
        Mockito.when(bunMock.getPrice()).thenReturn(100F);
        Mockito.when(fillingSausageMock.getPrice()).thenReturn(300f);
        Mockito.when(sauceSourCreamMock.getPrice()).thenReturn(200f);

        burger.setBuns(bunMock);
        burger.addIngredient(fillingSausageMock);
        burger.addIngredient(sauceSourCreamMock);

        Assert.assertEquals(expected, burger.getPrice(), 0);
    }

    @Test
    public void getReceiptTest() {
        Burger burger = new Burger();
        String expected = "(==== black bun ====)\n" +
                "= filling sausage =\n" +
                "= sauce sour cream =\n" +
                "(==== black bun ====)\n" +
                "\n" +
                "Price: 700,000000\n";

        //добавил использование моков, в качестве стабов
        Mockito.when(bunMock.getName()).thenReturn("black bun");
        Mockito.when(fillingSausageMock.getName()).thenReturn("sausage");
        Mockito.when(sauceSourCreamMock.getName()).thenReturn("sour cream");
        Mockito.when(fillingSausageMock.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(sauceSourCreamMock.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(bunMock.getPrice()).thenReturn(100F);
        Mockito.when(fillingSausageMock.getPrice()).thenReturn(300f);
        Mockito.when(sauceSourCreamMock.getPrice()).thenReturn(200f);

        burger.setBuns(bunMock);
        burger.addIngredient(fillingSausageMock);
        burger.addIngredient(sauceSourCreamMock);

        Assert.assertEquals(expected, burger.getReceipt());
    }
}
