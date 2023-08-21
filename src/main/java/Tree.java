/**
Создаем интерфейс в котором определяем методы, но не реализовываем их.
 */
public interface Tree {
    void insert(int value); // Добавление в бинарное дерево;
    void delete(int value); // Удаление
    boolean contains(int value); // Проверка на наличие;
}
