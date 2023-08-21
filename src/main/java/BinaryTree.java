/**
Создаем класс BinaryTree и реализуем в нем методы из интерфейса Tree.
 */
public class BinaryTree implements Tree{
    private Node root; // Приватный класс Node, который представляет узел дерева

    /**
    Метод getHeight(Node node) возвращает высоту узла или 0, если узел равен null.
     */
    private int getHeight(Node node) {
        return node != null ? node.getHeight() : 0;
    }

    /**
    Метод getBalanceFactor(Node node) возвращает разницу между высотами левого и правого поддеревьев узла.

     Сначала проверяется, что переданный узел не является null.
     Если это так, то метод возвращает ноль, так как у пустого узла нет ни левого, ни правого поддерева.

     Если же узел не является пустым, то метод вызывает метод getHeight(Node node) для левого и правого потомков узла и
     вычисляет разницу их высот.

     Метод getHeight(Node node) возвращает высоту поддерева, начиная с переданного узла.
     */
    private int getBalanceFactor(Node node) {
        return node != null ? getHeight(node.getLeft()) - getHeight(node.getRight()) : 0;
    }

    /**
    Методы rotateRight(Node node) и rotateLeft(Node node) осуществляют правый и левый повороты соответственно.
     */

    /**
    При правом повороте узел node становится правым потомком своего левого потомка leftNode,
    а правый потомок leftNode становится левым потомком node.
     */
    private Node rotateRight(Node node) {
        Node leftNode = node.getLeft(); // Сохраняем  ссылки на левого и правого потомков узла leftNode
        Node rightNode = leftNode.getRight(); // в переменные leftNode и rightNode.

        leftNode.setRight(node); // leftNode стал правым потомком node.
        node.setLeft(rightNode); //  rightNode - левым потомком leftNode.

        /*
        Пересчитываем высоты узлов node и leftNode с помощью метода getHeight(Node node) и
        устанавливаем новые значения высот с помощью метода setHeight(int height).
         */
        node.setHeight(Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1);
        leftNode.setHeight(Math.max(getHeight(leftNode.getLeft()), getHeight(leftNode.getRight())) + 1);

        return leftNode; // Возвращаем ссылку на новый корень поддерева - узел leftNode.
    }
    /**
     При левом повороте узел node становится левым потомком своего правого потомка rightNode,
     а левый потомок rightNode становится правым потомком node. Высоты узлов пересчитываются после поворота.

     Входным параметром является узел node, который должен стать правым потомком своего левого потомка leftNode
     */
    private Node rotateLeft(Node node) {
        Node rightNode = node.getRight();    // Сохраняем ссылки на левого и правого потомков узла leftNode в переменные.
        Node leftNode = rightNode.getLeft(); // leftNode и rightNode соответственно.
                                             // Это необходимо для того, чтобы сохранить связи между узлами после поворота.

        rightNode.setLeft(node); // leftNode стал правым потомком node.
        node.setRight(leftNode); // rightNode - левым потомком leftNode.

        // Пересчитываем высоты узлов node и leftNode.
        node.setHeight(Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1);
        // Устанавливаем новые значения высот.
        rightNode.setHeight(Math.max(getHeight(rightNode.getLeft()), getHeight(rightNode.getRight())) + 1);
        // Высота узла определяется как максимальная высота его потомков плюс один.


        /*
        Возвращаем ссылку на новый корень поддерева - узел leftNode.
        Это необходимо для того, чтобы обновить ссылки на корень поддерева в родительском узле.
         */
        return rightNode;
    }

    /**
    Метод rebalance(Node node) проверяет балансировку узла и, если она нарушена, выполняет необходимые повороты.
    Если разница между высотами левого и правого поддеревьев больше 1, то выполняется правый поворот,
    если разница меньше -1, то выполняется левый поворот. Если узел сбалансирован, то он возвращается без изменений.
     */
    private Node rebalance(Node node) {
        // Проверяем его баланс-фактор с помощью метода getBalanceFactor(Node node).
        int balanceFactor = getBalanceFactor(node);


        if (balanceFactor > 1) {                          // Проверяем баланс-фактор левого потомка узла.
            if (getBalanceFactor(node.getLeft()) < 0) {   // Если он меньше нуля, то сначала выполняется левый поворот
                node.setLeft(rotateLeft(node.getLeft())); // у левого потомка с помощью метода rotateLeft(Node node)
            }
            // а затем правый поворот у текущего узла с помощью метода rotateRight(Node node).
            return rotateRight(node);
        }

        // Если баланс-фактор меньше минус единицы, то дерево несбалансировано вправо и необходимо выполнить левый поворот.
        if (balanceFactor < -1) {
            if (getBalanceFactor(node.getRight()) > 0) { // Проверяем баланс-фактор правого потомка узла.
                node.setRight(rotateRight(node.getRight()));
            }
            // Если он больше нуля, то сначала выполняется правый поворот у правого потомка
            // с помощью метода rotateRight(Node node), а затем левый поворот у текущего узла
            // с помощью метода rotateLeft(Node node).
            return rotateLeft(node);
        }

        // Если баланс-фактор находится в диапазоне от минус единицы до единицы,
        // то дерево сбалансировано и метод просто возвращает переданный узел node.
        return node;
    }

    /**
     Метод insert(Node node, int value) вставляет новый узел со значением value в дерево.
     Если дерево пустое, то создается корневой узел с заданным значением.
     Если значение меньше значения текущего узла, то оно помещается в левое поддерево, иначе - в правое.
     Высоты узлов пересчитываются после вставки, а затем происходит балансировка дерева с помощью метода
     rebalance(Node node).
     */
    private Node insert(Node node, int value) {
        if (node == null) {         // Проверяем, что переданный узел не является null
            return new Node(value); // Создаем новый узел с переданным значением и возвращаем его.
        }

        if (value < node.getValue()) {                   // Если же узел не является пустым,
            node.setLeft(insert(node.getLeft(), value)); // то сравниваем переданное значение с значением текущего узла
        }
        // Если значение меньше значения текущего узла, то вызываем метод рекурсивно для левого потомка узла
        // и устанавливаем его как новый левый потомок текущего узла.


        // Если значение больше или равно значению текущего узла, то вызываем метод рекурсивно
        // для правого потомка узла и устанавливаем его как новый правый потомок текущего узла.
        else {
            node.setRight(insert(node.getRight(), value));
        }

        // Вычисляем высоту текущего узла, как максимальную из высот его левого и правого поддеревьев, плюс один.
        node.setHeight(Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1);


        // Вызываем метод rebalance(Node node), который проверяет баланс-фактор текущего узла и
        // выполняет необходимые повороты, чтобы дерево оставалось сбалансированным.
        return rebalance(node);
    }

    /**
     Метод delete(Node node, int value) удаляет узел с заданным значением из дерева.
     Если узел не найден, то возвращается null. Если узел не имеет потомков, то он просто удаляется.
     Если узел имеет одного потомка, то он заменяется на своего потомка.
     Если узел имеет двух потомков, то его значение заменяется на значение минимального узла в правом поддереве,
     а затем этот узел удаляется. Высоты узлов пересчитываются после удаления,
     а затем происходит балансировка дерева с помощью метода rebalance(Node node).
     */
    private Node delete(Node node, int value) {
        // Если узел пустой, возвращаем null.
        if (node == null) {
            return null;
        }
        // Если значение меньше значения текущего узла, идем влево
        if (value < node.getValue()) {
            // Рекурсивный вызов для левого поддерева
            node.setLeft(delete(node.getLeft(), value));
        }
        // Если значение больше значения текущего узла, идем вправо
        else if (value > node.getValue()) {
            // Рекурсивный вызов для правого поддерева
            node.setRight(delete(node.getRight(), value));
        }
        // Если значение равно значению текущего узла
        else {
            // Если у узла нет дочерних узлов, просто удаляем его
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            // Если у узла есть только один дочерний узел, заменяем текущий узел на него
            if (node.getLeft() == null || node.getRight() == null) {

                return node.getLeft() != null ? node.getLeft() : node.getRight();
            }

            // // Если у узла есть два дочерних узла, находим минимальный элемент в правом поддереве
            // и заменяем текущий узел на него
            Node minRightNode = node.getRight();
            while (minRightNode.getLeft() != null) {
                minRightNode = minRightNode.getLeft();
            }
            node.setValue(minRightNode.getValue());
            node.setRight(delete(node.getRight(), minRightNode.getValue()));
        }
        // Обновляем высоту узла
        node.setHeight(Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1);
        return rebalance(node); // Выполняем балансировку дерева
    }

    /**
     Метод contains(Node node, int value) проверяет, содержится ли узел с заданным значением в дереве.
     Если узел равен null, то возвращается false. Если значение меньше значения текущего узла,
     то поиск продолжается в левом поддереве, иначе - в правом.
     */
    private boolean contains(Node node, int value) {
        // Если текущий узел пустой, метод возвращает false.
        if (node == null) {
            return false;
        }
        // Если искомое значение меньше значения текущего узла, поиск продолжается в левом поддереве,
        // вызывая метод contains рекурсивно для левого поддерева.
        if (value < node.getValue()) {
            return contains(node.getLeft(), value);
        }
        // Если искомое значение больше значения текущего узла, поиск продолжается в правом поддереве,
        // вызывая метод contains рекурсивно для правого поддерева.
        else if (value > node.getValue()) {
            return contains(node.getRight(), value);
        }
        // Если искомое значение равно значению текущего узла, значит, что узел найден, и метод возвращает true.
        else {
            return true;
        }
    }

    /**
    Методы insert(int value), delete(int value) и contains(int value) являются публичными методами интерфейса Tree
     и обеспечивают доступ к соответствующим приватным методам класса BinaryTree.
     */
    public void insert(int value) {
        root = insert(root, value);
    }
    public boolean contains(int value) {
        return contains(root, value);
    }

    public void delete(int value) {
        root = delete(root, value);
    }
}
