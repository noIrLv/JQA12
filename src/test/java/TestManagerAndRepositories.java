import netology.ru.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestManagerAndRepositories {

    Product product1 = new Book(1, "Книга: 1984", 865, "Джордж Оруэл");
    Product product2 = new Book(2, "Книга: Бледный огонь", 1300, "Владимир набоков");
    Product product3 = new Smartphone(3, "3310", 99999, "Nokia");


    @Test
    public void shouldNotAddProduct() {
        ProductRepository repo = new ProductRepository();
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.save(product2);
        });
    }

    @Test
    public void shouldNotRemoveById() {
        ProductRepository repo = new ProductRepository();
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeId(4);
        });
    }


    @Test
    public void shouldAddProduct() {
        ProductRepository repo = new ProductRepository();
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);

        Product[] expected = {product1, product2, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldRemoveById() {
        ProductRepository repo = new ProductRepository();
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        repo.removeId(2);

        Product[] expected = {product1, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void shouldSearchProduct() {
        ProductRepository repo = new ProductRepository();
        ProductManager manager = new ProductManager(repo);
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        manager.searchBy("3310");

        Product[] expected = {product3};
        Product[] actual = manager.searchBy("3310");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchTwoProduct() {
        ProductRepository repo = new ProductRepository();
        ProductManager manager = new ProductManager(repo);
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        manager.searchBy("Книга");

        Product[] expected = {product1, product2};
        Product[] actual = manager.searchBy("Книга");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchProductWhichIsNot() {
        ProductRepository repo = new ProductRepository();
        ProductManager manager = new ProductManager(repo);
        repo.save(product1);
        repo.save(product2);
        repo.save(product3);
        manager.searchBy("Незнайка");

        Product[] expected = {};
        Product[] actual = manager.searchBy("Незнайка");

        Assertions.assertArrayEquals(expected, actual);
    }


}



