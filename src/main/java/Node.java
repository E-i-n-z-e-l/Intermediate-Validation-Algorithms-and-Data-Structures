/**
Создаем класс Node (узел), который представляет собой элемент дерева и описываем его.
 */
public class Node {
    // Описываем поля
    private int value; // Значение;
    private Node left; // Левый потомок;
    private Node right; // Правый потомок
    private int height; // Высота узла (определяется как количество уровней в дереве от данного узла до самого дальнего листа);


    /**
    Этот код описывает конструктор класса Node, который создает новый узел дерева с заданным значением value.
    При создании узла, его левый и правый потомки устанавливаются в значение null, а высота узла устанавливается на 1
    (так как новый узел не имеет потомков и находится на самом нижнем уровне дерева).
     */
    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.height = 1;
    }

    /**
    Этот код описывает методы класса Node, которые позволяют установить и получить:
     */
    public void setValue(int value) { // значение узла (value)
        this.value = value;
    }
    public int getValue() { // получение узла
        return value;
    }

    public Node getLeft() { // получить левого потомка
        return left;
    }

    public void setLeft(Node left) { // установить левого потомка
        this.left = left;
    }

    public Node getRight() { // получение правого потомка
        return right;
    }

    public void setRight(Node right) { // установить правого потомка
        this.right = right;
    }


    /**
    Методы для получения и установки высоты узла (getHeight() и setHeight()).
     */
    public int getHeight() {  //
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
