public class Main {
    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree(); // Создаем экземпляр класса BinaryTree

        // Добавляем в него узлы со значениями 5, 3 и 7
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);

        tree.delete(3); // Удаляем узел со значением 3 из дерева

        boolean contains1 = tree.contains(5); // Проверяем, содержит ли дерево узел со значением 5
        boolean contains2 = tree.contains(3); // Проверяем, содержит ли дерево узел со значением 3


        // Выводим в терминал
        System.out.println(contains1); // Переменная contains1 будет равна true
        System.out.println(contains2); // Переменная contains2 будет равна false, так как узел со значением 3 был удален из дерева.
    }
}
